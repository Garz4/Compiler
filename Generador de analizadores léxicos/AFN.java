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
        // TODO(Garz4): Avoid magic values.
        _estadoInicial = null;
        _alfabeto = new HashSet<>();
        _alfabeto.clear();
        _estadosAceptacion = new HashSet<>();
        _estadosAceptacion.clear();
        _estadosAFN = new HashSet<>();
        _estadosAFN.clear();
    }

    public AFN(char $simbolo, int $token) {
        Estado estado;

        _estadoInicial = new Estado();
        estado = new Estado();
        estado.setAceptacion(true);
        estado.setToken($token);
        _estadosAceptacion = new HashSet<>();
        _estadosAceptacion.clear();
        _estadosAceptacion.add(estado);
        _estadosAFN = new HashSet<>();
        _estadosAFN.clear();
        _estadosAFN.add(_estadoInicial);
        _estadosAFN.add(estado);
        _alfabeto = new HashSet<>();
        _alfabeto.clear();
        _alfabeto.add($simbolo);
        _estadoInicial.addTransicion($simbolo, estado);
    }

    public AFN unirAFN(AFN $automata, int $token) {
        Estado nuevoInicio = new Estado();
        Estado nuevoFinal = new Estado();

        // TODO: Stop hardcoding.
        nuevoInicio.addTransicion('&', _estadoInicial);
        nuevoInicio.addTransicion('&', $automata.getEstadoInicial());

        for (Estado estado : _estadosAceptacion) {
            estado.addTransicion('&', nuevoFinal);
            estado.setAceptacion(false);
        }

        for (Estado estado : $automata.getEstadosAceptacion()) {
            estado.addTransicion('&', nuevoFinal);
            estado.setAceptacion(false);
        }

        nuevoFinal.setAceptacion(true);
        nuevoFinal.setToken($token);

        _alfabeto.addAll($automata.getAlfabeto());
        _estadosAFN.addAll($automata.getEstadosAFN());
        _estadosAFN.add(nuevoInicio);
        _estadosAFN.add(nuevoFinal);
        _estadosAceptacion.clear();
        _estadosAceptacion.add(nuevoFinal);
        _estadoInicial = nuevoInicio;
        $automata = null;

        return this;
    }

    // Thompson.
    public AFN concatenar(AFN $automata, int $token) {
        for (Estado estado : _estadosAceptacion) {
            for (Transicion transicion
                    : $automata.getEstadoInicial().getTransiciones()) {
                estado.addTransicion(
                        transicion.getSimbolo(), transicion.getEstado());
            }
        }

        // TODO: Does this work?
        $automata.getEstadosAFN().remove($automata.getEstadoInicial());

        for (Estado estado : _estadosAceptacion) {
            estado.setAceptacion(false);
            //e.setToken(-1); <- TODO?
        }

        _estadosAceptacion.clear();

        for (Estado estado : $automata.getEstadosAceptacion()) {
            estado.setToken($token);
            _estadosAceptacion.add(estado);
        }

        for (Character letra : $automata.getAlfabeto()) {
            _alfabeto.add(letra);
        }

        for (Estado estado : $automata.getEstadosAFN()) {
            _estadosAFN.add(estado);
        }

        $automata = null;

        return this;
    }

    // Cerradura positiva.
    public AFN CerrMas(int $token) {
        Estado nuevoInicio = new Estado();
        Estado nuevoFinal = new Estado();

        // TODO: Stop hardcoding.
        nuevoInicio.addTransicion('&', _estadoInicial);

        for (Estado estado : _estadosAceptacion) {
            estado.addTransicion('&', nuevoInicio);
            estado.addTransicion('&', _estadoInicial);
            estado.setAceptacion(false);
        }

        nuevoFinal.setAceptacion(true);
        nuevoFinal.setToken($token);
        _estadosAceptacion.clear();
        _estadosAceptacion.add(nuevoFinal);
        _estadosAFN.add(nuevoInicio);
        _estadosAFN.add(nuevoFinal);
        _estadoInicial = nuevoInicio;

        return this;
    }

    public AFN CerrEstrella(int $token) {
        Estado nuevoInicio = new Estado();
        Estado nuevoFinal = new Estado();

        // TODO: Stop hardcoding.
        nuevoInicio.addTransicion('&', _estadoInicial);

        for (Estado estado : _estadosAceptacion) {
            estado.addTransicion('&', nuevoFinal);
            estado.addTransicion('&', _estadoInicial);
            estado.setAceptacion(false);
        }

        nuevoFinal.setAceptacion(true);
        nuevoFinal.setToken($token);
        _estadosAceptacion.clear();
        _estadosAceptacion.add(nuevoFinal);
        _estadosAFN.add(nuevoInicio);
        _estadosAFN.add(nuevoFinal);
        _estadoInicial = nuevoInicio;
        _estadoInicial.addTransicion('&', nuevoFinal);

        return this;
    }

    public AFN CerrOpcional(int $token) {
        Estado nuevoInicio = new Estado();
        Estado nuevoFinal = new Estado();

        // TODO: Stop hardcoding.
        nuevoInicio.addTransicion('&', _estadoInicial);

        for (Estado e : _estadosAceptacion) {
            e.addTransicion('&', nuevoFinal);
            e.setAceptacion(false);
        }

        nuevoFinal.setAceptacion(true);
        nuevoFinal.setToken($token);
        _estadosAceptacion.clear();
        _estadosAceptacion.add(nuevoFinal);
        _estadosAFN.add(nuevoInicio);
        _estadosAFN.add(nuevoFinal);
        _estadoInicial = nuevoInicio;
        _estadoInicial.addTransicion('&', nuevoFinal);

        return this;
    }

    public HashSet<Estado> Cerradura_e(Estado $estado) {
        HashSet<Estado> hashSetEstados = new HashSet<>();
        Stack<Estado> pilaEstados = new Stack<>();
        Estado estado;

        hashSetEstados.clear();
        pilaEstados.clear();
        pilaEstados.push($estado);

        while (!pilaEstados.empty()) {
            estado = pilaEstados.pop();

            if (hashSetEstados.contains(estado)) {
                continue;
            }

            hashSetEstados.add(estado);

            for (Transicion transicion : estado.getTransiciones()) {
                // TODO: Stop hardcoding.
                if (transicion.getSimbolo() == '&') {
                    if (!hashSetEstados.contains(transicion.getEstado())) {
                        pilaEstados.push(transicion.getEstado());
                    }
                }
            }
        }

        return hashSetEstados;
    }

    public HashSet<Estado> Cerradura_e(HashSet<Estado> $hashSetEstados) {
        HashSet<Estado> hashSetEstados = new HashSet<>();
        hashSetEstados.clear();

        for (Estado estado : $hashSetEstados) {
            hashSetEstados.addAll(Cerradura_e(estado));
        }

        return hashSetEstados;
    }

    public HashSet<Estado> mover(HashSet<Estado> $estados, char $simbolo) {
        HashSet<Estado> response = new HashSet<>();
        HashSet<Transicion> transiciones = new HashSet<>();

        for (Estado estado : $estados) {
            transiciones = estado.getTransiciones();

            for (Transicion transicion : transiciones) {
                if (transicion.getSimbolo() == $simbolo) {
                    response.add(transicion.getEstado());
                }
            }
        }

        return response;
    }

    public HashSet<Estado> irA(HashSet<Estado> $estados, char $simbolo) {
        return Cerradura_e(mover($estados, $simbolo));
    }

    public int yaContiene(
            NavigableMap<Integer, HashSet> $totales, HashSet<Estado> $compara) {
        for (Entry<Integer, HashSet> total : $totales.entrySet()) {
            if ($compara.equals(total.getValue())) {
                return total.getKey();
            }
        }

        return -1;
    }

    public int esAceptacion(HashSet<Estado> $estados) {
        for (Estado estado : $estados) {
            for (Estado estadoAceptacion : _estadosAceptacion) {
                if (estado.getId() == estadoAceptacion.getId()) {
                    return estadoAceptacion.getToken();
                }
            }
        }

        return -1;
    }

    public int contieneTokenValido(HashSet<Estado> $estados) {
        for (Estado estado : $estados) {
            for (Estado estadoAFN : _estadosAFN) {
                // TODO: Stop hardcoding.
                if (estado.getId() == estadoAFN.getId()
                        && estadoAFN.getToken() != -1) {
                    return estadoAFN.getToken();
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

                        estadosAFD.get(estadosAFD.size() - 1).setId(contadorId);
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
                        for (Estado estado : estadosAFD) {
                            if (estado.getId() == currentId) {
                                for (Estado currrr : estadosAFD) {
                                    if (currrr.getId() == newId) {
                                        estado.addTransicion(a, currrr);
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

        for (Estado estado : estadosAFD) {
            if (estado != response.getEdoIni()) {
                response.addEstado(estado);

                if (estado.getAceptacion()) {
                    response.addEdoAceptacion(estado);
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

    private Estado getEstadoInicial() { return _estadoInicial; }
    private HashSet<Estado> getEstadosAFN() { return _estadosAFN; }
    private HashSet<Estado> getEstadosAceptacion() {
        return _estadosAceptacion;
    }
    private HashSet<Character> getAlfabeto() { return _alfabeto; }

    private Estado _estadoInicial;
    private HashSet<Estado> _estadosAceptacion;
    private HashSet<Estado> _estadosAFN;
    private HashSet<Character> _alfabeto;
}
