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

package hoc4;

import java.util.Hashtable;

public class TablaSimbolos {
    public TablaSimbolos() {
        _hashtableSimbolos = new Hashtable<>();
    }

    // Constant time.
    public SymbolHoc lookup(String $name) {
        if (!_hashtableSimbolos.contains($name)) {
            return null;
        }

        return _hashtableSimbolos.get($name);
    }

    public SymbolHoc install(String $name, EnumTipoSymbol $type, float $value) {
        SymbolHoc symbol;

        symbol = new SymbolHoc($name, $type, $value);
        /* verificar si no es que ya existe en la lista <- Is this a TODO? */
        _hashtableSimbolos.put($name, symbol);

        return symbol;
    }

    public SymbolHoc install(
            String $name, EnumTipoSymbol $type, EnumBLTIN $funcPredef) {
        SymbolHoc symbol;

        symbol = new SymbolHoc($name, $type, $funcPredef);
        _hashtableSimbolos.put($name, symbol);

        return symbol;
    }

    public void init() {
        _hashtableSimbolos.clear();
        _initConstPredef();
        _initFuncPredef();
    }

    public Hashtable<String, SymbolHoc> getHashtableSimbolos() {
        return _hashtableSimbolos;
    }

    private void _initConstPredef() {
        _putIntoHashtableSimbolos("PI", (float) 3.141592653589);
        _putIntoHashtableSimbolos("E", (float) 2.718281828459);
        _putIntoHashtableSimbolos("GAMMA", (float) 0.577215664901);
        _putIntoHashtableSimbolos("DEG", (float) 57.2957795130);
        _putIntoHashtableSimbolos("PHI", (float) 1.6180334989);
    }

    private void _initFuncPredef() {
        _putIntoHashtableSimbolos("sin", EnumBLTIN.SIN);
        _putIntoHashtableSimbolos("cos", EnumBLTIN.COS);
        _putIntoHashtableSimbolos("atan", EnumBLTIN.ATAN);
        _putIntoHashtableSimbolos("log", EnumBLTIN.LOG);
        _putIntoHashtableSimbolos("lo10", EnumBLTIN.LO10);
        _putIntoHashtableSimbolos("exp", EnumBLTIN.EXP);
        _putIntoHashtableSimbolos("sqrt", EnumBLTIN.SQRT);
        _putIntoHashtableSimbolos("int", EnumBLTIN.INTEGER);
        _putIntoHashtableSimbolos("abs", EnumBLTIN.ABS);
    }

    private void _putIntoHashtableSimbolos(
            String $name,  EnumBLTIN $funcPredef) {
        _hashtableSimbolos.put(
                $name,
                new SymbolHoc($name, EnumTipoSymbol.BLTIN, $funcPredef));
    }

    private void _putIntoHashtableSimbolos(String $name,  float $value) {
        _hashtableSimbolos.put(
                $name,
                new SymbolHoc($name, EnumTipoSymbol.CONST_PREDEF, $value));
    }

    private Hashtable<String, SymbolHoc> _hashtableSimbolos;
}
