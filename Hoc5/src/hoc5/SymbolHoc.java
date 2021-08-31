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

package hoc5;

public class SymbolHoc {
    public String name;
    public EnumTipoSymbol TipoSymbol; /* VAR, UNDEF, BLTIN, CONST_NUM, CONST_PREDEF */
    public float val;
    public EnumBLTIN FuncPredef; /* "double (*ptr)()" */
    
    public SymbolHoc() {
        this.name = "";
        this.val = 0;
    }
    
    public SymbolHoc(String name, EnumTipoSymbol TipoSymbol, float val) {
        this.name = name;
        this.TipoSymbol = TipoSymbol;
        this.val = val;
    }
    
    public SymbolHoc(String name, EnumTipoSymbol TipoSymbol, EnumBLTIN FuncPredef) {
        this.name = name;
        this.TipoSymbol = TipoSymbol;
        this.FuncPredef = FuncPredef;
    }
    
    public void SetSymbol(String name, EnumTipoSymbol TipoSymbol, float val) {
        this.name = name;
        this.TipoSymbol = TipoSymbol;
        this.val = val;
    }
    
    public void SetSymbol(String name, EnumTipoSymbol TipoSymbol, EnumBLTIN FuncPredef) {
        this.name = name;
        this.TipoSymbol = TipoSymbol;
        this.FuncPredef = FuncPredef;
    }
}
