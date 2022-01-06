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

import java.util.HashSet;

public class Lexica {
    // TODO(Garz4): Avoid magic numbers like the following.
    public Lexica() {
        _total = "";
        _currentIndex = 0;
        _token = -1;
        _currentEstado = null;
        _prevEstado = null;
        _prevAceptacion = null;
        _prevIndexAceptacion = -1;
        _prevLexema = "";
        _prevToken = -1;
    }

    // TODO(Garz4): Avoid magic numbers like the following.
    public Lexica(String $total, AFD $automata) {
        _total = $total;
        _currentIndex = 0;
        _automata = $automata;
        _token = -1;
        _currentEstado = null;
        _prevEstado = $automata.getEdoIni();
        _prevAceptacion = null;
        _prevIndexAceptacion = -1;
        _prevLexema = "";
        _prevToken = -1;
    }

    public void compute() {
        System.out.print("============ INICIA ANÁLISIS: ============");
        if (_currentIndex == _total.length()) {
            _token = 0;
            _lexema = "FIN DEL ANÁLISIS";
            return;
        }

        _lexema = "";
        _prevEstado = _automata.getEdoIni();
        _prevAceptacion = null;

        while (_currentIndex < _total.length()) {
            System.out.print("-------\nCurrent index: "
                    + _currentIndex + "\nCurrent char: "
                    + _total.charAt(_currentIndex) + "\n");

            _currentEstado = validateTransition(
                    _total.charAt(_currentIndex), _prevEstado);

            if (_currentEstado != null) {
                _token = _currentEstado.getToken();
                _lexema += _total.charAt(_currentIndex);

                System.out.print("\nCurrent id: "
                        + currentEstado.getId() + "\nCurrent lexema: " + lexema
                        + "\nCurrent token: " + token + "\n");

                if (_currentEstado.getToken() > 0) {
                    _prevAceptacion = _currentEstado;
                    _prevIndexAceptacion = _currentIndex;
                    _prevLexema = _lexema;
                    _prevToken = _token;
                }

                _prevEstado = _currentEstado;
                _currentIndex++;
            } else {
                if (_prevAceptacion == null) {
                    _token = 0;
                    _lexema = "Error.";
                    return;
                } else {
                    _token = _prevToken;
                    _lexema = _prevLexema;
                    _currentIndex = _prevIndexAceptacion;
                    _currentIndex++;
                    System.out.print("\n->->->-> LEXEMA A RETORNAR: "
                            + _lexema + " TOKEN: " + _token + " <-<-<-<-<-\n");
                    return;
                }
            }
        }
    }

    public Estado validateTransition(char $simboloValidar, Estado $estado) {
        System.out.print("Estoy");

        if ($estado == null) {
            return null;
        }

        System.out.print("sigo");
        HashSet<Transicion> transiciones = $estado.getTransiciones();
        System.out.print(transiciones.size());

        for (Transicion transicion : transiciones) {
            if (transicion.getSimbolo() == $simboloValidar) {
                return transicion.getEstado();
            }
        }

        System.out.print(" noencontre");

        return null;
    }

    public int getToken() { return _token; }
    public String getLexema() { return _lexema; }

    private String _lexema;
    private String _total;
    private String _prevLexema;
    private Estado _currentEstado;
    private Estado _prevEstado;
    private Estado _prevAceptacion;
    private int _currentIndex;
    private int _prevIndexAceptacion;
    private int _token;
    private int _prevToken;
    private AFD _automata;
}
