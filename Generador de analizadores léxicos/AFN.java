/*
 * MIT License
 * 
 * Copyright (c) 2021 Uriel Rivas
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * https://github.com/Garz4/compilers/blob/master/LICENSE
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Stack;
import java.util.TreeMap;

public class AFN {
    public AFN() {
        _estadoInicial = null;
        _alfabeto = new HashSet<>();
        _alfabeto.clear();
        _estadosAceptacion = new HashSet<>();
        _estadosAceptacion.clear();
        _estadosAFN = new HashSet<>();
        _estadosAFN.clear();
    }

    public AFN(char s, int toToken) {
        Estado ef;

        _estadoInicial = new Estado();
        ef = new Estado();
        ef.setAceptacion(true);
        ef.setToken(toToken);
        _estadosAceptacion = new HashSet<>();
        _estadosAceptacion.clear();
        _estadosAceptacion.add(ef);
        _estadosAFN = new HashSet<>();
        _estadosAFN.clear();
        _estadosAFN.add(_estadoInicial);
        _estadosAFN.add(ef);
        _alfabeto = new HashSet<>();
        _alfabeto.clear();
        _alfabeto.add(s);
        _estadoInicial.addTransicion(s, ef);
    }

    public AFN AFNBasico(char s, int toToken) {
        Estado ef;

        _estadoInicial = new Estado();
        ef = new Estado();
        ef.setAceptacion(true);
        ef.setToken(toToken);
        _estadosAceptacion = new HashSet<>();
        _estadosAceptacion.clear();
        _estadosAceptacion.add(ef);
        _estadosAFN = new HashSet<>();
        _estadosAFN.clear();
        _estadosAFN.add(_estadoInicial);
        _estadosAFN.add(ef);
        _alfabeto = new HashSet<>();
        _alfabeto.clear();
        _alfabeto.add(s);
        _estadoInicial.addTransicion(s, ef);

        return this;
    }

    public AFN unirAFN(AFN f, int toToken) {
        Estado nuevoInicio = new Estado();
        Estado nuevoFinal = new Estado();

        nuevoInicio.addTransicion('&', _estadoInicial);
        nuevoInicio.addTransicion('&', f._estadoInicial);

        for (Estado e : _estadosAceptacion) {
            e.addTransicion('&', nuevoFinal);
            e.setAceptacion(false);
        }

        for (Estado e : f._estadosAceptacion) {
            e.addTransicion('&', nuevoFinal);
            e.setAceptacion(false);
        }

        nuevoFinal.setAceptacion(true);
        nuevoFinal.setToken(toToken);

        _alfabeto.addAll(f.getAlfabeto());
        _estadosAFN.addAll(f.getEdosAFN());
        _estadosAFN.add(nuevoInicio);
        _estadosAFN.add(nuevoFinal);
        _estadosAceptacion.clear();
        _estadosAceptacion.add(nuevoFinal);
        _estadoInicial = nuevoInicio;
        f = null;

        return this;
    }

    // Thompson.
    public AFN concatenar(AFN f2, int toToken) {
        for (Estado e : _estadosAceptacion) {
            for (Transicion entry: f2._estadoInicial.getTransiciones()) {
                e.addTransicion(entry.getSimbolo(), entry.getEstado());
            }
        }

        f2._estadosAFN.remove(f2._estadoInicial);

        for (Estado e : _estadosAceptacion) {
            e.setAceptacion(false);
            //e.setToken(-1); <- TODO?
        }

        _estadosAceptacion.clear();

        for (Estado e : f2._estadosAceptacion) {
            e.setToken(toToken);
            _estadosAceptacion.add(e);
        }

        for (char a : f2.getAlfabeto()) {
            _alfabeto.add(a);
        }

        for (Estado e : f2._estadosAFN) {
            _estadosAFN.add(e);
        }

        f2 = null;

        return this;
    }

    // Cerradura positiva. 
    public AFN CerrMas(int toToken) {
        Estado NuevoIni = new Estado();
        Estado NuevoFin = new Estado();
        NuevoIni.addTransicion('&', _estadoInicial);

        for (Estado e : _estadosAceptacion) {
            e.addTransicion('&', NuevoFin);
            e.addTransicion('&', _estadoInicial);
            e.setAceptacion(false);
        }

        NuevoFin.setAceptacion(true);
        NuevoFin.setToken(toToken);
        _estadosAceptacion.clear();
        _estadosAceptacion.add(NuevoFin);
        _estadosAFN.add(NuevoIni);
        _estadosAFN.add(NuevoFin);
        _estadoInicial = NuevoIni;

        return this;
    }

    public AFN CerrEstrella(int toToken) {
        Estado NuevoIni = new Estado();
        Estado NuevoFin = new Estado();
        NuevoIni.addTransicion('&', _estadoInicial);

        for (Estado e : _estadosAceptacion) {
            e.addTransicion('&', NuevoFin);
            e.addTransicion('&', _estadoInicial);
            e.setAceptacion(false);
        }

        NuevoFin.setAceptacion(true);
        NuevoFin.setToken(toToken);
        _estadosAceptacion.clear();
        _estadosAceptacion.add(NuevoFin);
        _estadosAFN.add(NuevoIni);
        _estadosAFN.add(NuevoFin);
        _estadoInicial = NuevoIni;
        _estadoInicial.addTransicion('&', NuevoFin);

        return this;
    }

    public AFN CerrOpcional(int toToken) {
        Estado NuevoIni = new Estado();
        Estado NuevoFin = new Estado();
        NuevoIni.addTransicion('&', _estadoInicial);

        for (Estado e : _estadosAceptacion) {
            e.addTransicion('&', NuevoFin);
            e.setAceptacion(false);
        }
        
        NuevoFin.setAceptacion(true);
        NuevoFin.setToken(toToken);
        _estadosAceptacion.clear();
        _estadosAceptacion.add(NuevoFin);
        _estadosAFN.add(NuevoIni);
        _estadosAFN.add(NuevoFin);
        _estadoInicial = NuevoIni;
        _estadoInicial.addTransicion('&', NuevoFin);

        return this;
    }

    public HashSet<Estado> Cerradura_e(Estado e) {
        HashSet<Estado> R = new HashSet<>();
        Stack<Estado> P = new Stack<>();
        Estado e2;
        R.clear();
        P.clear();
        P.push(e);

        while (!P.empty()) {
            e2 = P.pop();

            if (R.contains(e2)) {
                continue;
            }

            R.add(e2);

            for (Transicion entry : e2.getTransiciones()) {
                if (entry.getSimbolo() == '&') {
                    if (!R.contains(entry.getEstado())) {
                        P.push(entry.getEstado());
                    }
                }
            }
        }

        return R;
    }

    public HashSet<Estado> Cerradura_e(HashSet<Estado> c) {
        HashSet<Estado> R = new HashSet<>();
        R.clear();

        for (Estado e : c) {
            R.addAll(Cerradura_e(e));
        }

        return R;
    }

    public HashSet<Estado> mover(HashSet<Estado> toHash, char toChar) {
        HashSet<Estado> response = new HashSet<>();
        HashSet<Transicion> currTran = new HashSet<>();

        for (Estado e : toHash) {
            currTran = e.getTransiciones();

            for (Transicion t : currTran) {
                if (t.getSimbolo() == toChar) {
                    response.add(t.getEstado());
                }
            }
        }

        return response;
    }

    public HashSet<Estado> irA(HashSet<Estado> toHash, char toChar) {
        return Cerradura_e(mover(toHash, toChar));
    }

    public int yaContiene(
            NavigableMap<Integer, HashSet> totales, HashSet<Estado> toAnalyze) {
        for (Entry<Integer, HashSet> entry : totales.entrySet()) {
            if (toAnalyze.equals(entry.getValue())) {
                return entry.getKey();
            }
        }

        return -1;
    }

    public int esAceptacion(HashSet<Estado> toVerify){
        for (Estado e : toVerify) {
            for (Estado eA : _estadosAceptacion) {
                if (e.getId() == eA.getId()) {
                    return eA.getToken();
                }
            }
        }

        return -1;
    }

    public int contieneTokenValido(HashSet<Estado> toVerify) {
        for (Estado e : toVerify) {
            for (Estado eI : _estadosAFN) {
                if (e.getId() == eI.getId() && eI.getToken() != -1) {
                    return eI.getToken();
                }
            }
        }

        return -1;
    }

    public AFD parseAFD() {
        AFD response = new AFD();
        int currentId;
        // TODO(Garz4): Avoid magic numbers.
        int contadorId = 100;
        int newId;
        ArrayList<Estado> estadosAFD = new ArrayList<>();
        Stack<HashSet<Estado>> A = new Stack<>();
        NavigableMap<Integer, HashSet> totales = new TreeMap<>();
        HashSet<Estado> current = Cerradura_e(_estadoInicial);
        HashSet<Estado> analyzing = new HashSet<>();
        
        A.push(current);
        totales.put(contadorId, current);
        estadosAFD.add(new Estado());
        estadosAFD.get(0).setId(contadorId);
        response.setEdoIni(estadosAFD.get(0));
        contadorId++;
        
        while (!A.empty()) {
            current = A.pop();

            for (char a : _alfabeto) {
                analyzing = irA(current, a);
                currentId = yaContiene(totales, current);

                if (analyzing.size() > 0) {
                    newId = yaContiene(totales, analyzing);

                    if (newId == -1) {
                        estadosAFD.add(new Estado());
                        
                        for (Estado currE : estadosAFD) {
                            if (currE.getId() == currentId) {
                                currE.addTransicion(
                                        a,
                                        estadosAFD.get(estadosAFD.size() - 1));
                                break;
                            }
                        }
                        
                        estadosAFD.get(estadosAFD.size()-1).setId(contadorId);
                        int toAceptacion = esAceptacion(analyzing);

                        if (toAceptacion != -1) {
                            estadosAFD.get(estadosAFD.size() - 1)
                                    .setAceptacion(true);
                            estadosAFD.get(estadosAFD.size() - 1)
                                    .setToken(toAceptacion);
                        } else {
                            estadosAFD.get(estadosAFD.size() - 1)
                                    .setAceptacion(false);
                            estadosAFD.get(estadosAFD.size() - 1).setToken(-1);
                        }

                        int tokenValido = contieneTokenValido(analyzing);

                        if (tokenValido != -1) {
                            estadosAFD.get(estadosAFD.size() - 1)
                                    .setToken(tokenValido);
                        }

                        A.push(analyzing);
                        totales.put(contadorId, analyzing);
                        contadorId++;
                    } else {
                        for (Estado currE : estadosAFD) {
                            if (currE.getId() == currentId) {
                                for (Estado currrr : estadosAFD) {
                                    if (currrr.getId() == newId) {
                                        currE.addTransicion(a, currrr);
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }

        for (Estado e : estadosAFD) {
            if (e != response.getEdoIni()) {
                response.addEstado(e);

                if (e.aceptacion) {
                    response.addEdoAceptacion(e);
                }
            }
        }

        System.out.print("AFD:\n");
        HashSet<Transicion> currTran;
        System.out.print("# Edo   Simbolo   Edo final   Token    "
                + "Estados de AFN\n");

        for (Estado e : response.getAllEstados()) {
            currTran = e.getTransiciones();

            if (currTran.size() > 0) {
                for (Transicion entry : currTran) {
                    System.out.print("" + e.getId());
                    System.out.print("       " + entry.getSimbolo()
                            + "          " + entry.getEstado().getId()
                            + "       " + e.getToken() + "         "
                            + totales.get(e.getId()) + "\n");
                }
            } else {
                System.out.print(e.getId() + " ===================  "
                        + e.getToken() + "\n");
            }
        }

        response.setAlfabeto(_alfabeto);

        return response;
    }

    private HashSet<Character> getAlfabeto() { return _alfabeto; }
    private HashSet<Estado> getEdosAFN() { return _estadosAFN; }

    private Estado _estadoInicial;
    private HashSet<Estado> _estadosAceptacion;
    private HashSet<Estado> _estadosAFN;
    private HashSet<Character> _alfabeto;
}
