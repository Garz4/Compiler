package hoc4;

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
