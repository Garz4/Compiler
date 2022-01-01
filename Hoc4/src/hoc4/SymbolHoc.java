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

public class SymbolHoc {
    public SymbolHoc() {
        _name = "";
        _value = 0;
    }

    public SymbolHoc(String $name, EnumTipoSymbol $tipoSymbol, float $value) {
        _name = $name;
        _tipoSymbol = $tipoSymbol;
        _value = $value;
    }

    public SymbolHoc(
            String $name, EnumTipoSymbol $tipoSymbol, EnumBLTIN $funcPredef) {
        _name = $name;
        _tipoSymbol = $tipoSymbol;
        _funcPredef = $funcPredef;
    }

    public float getValue() { return _value; }
    public String getName() { return _name; }
    public EnumBLTIN getFuncPredef() { return _funcPredef; }
    public EnumTipoSymbol getTipoSymbol() { return _tipoSymbol; }

    public void setValue(float $value) { _value = $value; }
    public void setName(String $name) { _name = $name; }
    public void setFuncPredef(EnumBLTIN $funcPredef) {
        _funcPredef = $funcPredef;
    }
    public void setTipoSymbol(EnumTipoSymbol $tipoSymbol) {
        _tipoSymbol = $tipoSymbol;
    }

    private float _value;
    private String _name;

    // "double (*ptr)()"
    private EnumBLTIN _funcPredef;

    // VAR, UNDEF, BLTIN, CONST_NUM, CONST_PREDEF
    private EnumTipoSymbol _tipoSymbol;
}
