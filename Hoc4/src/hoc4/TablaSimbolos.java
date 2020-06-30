package hoc4;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TablaSimbolos {
    List<SymbolHoc> ListaSimbolos;
    
    public TablaSimbolos() {
        this.ListaSimbolos = new LinkedList<>();
        this.ListaSimbolos.clear();
    }
    
    public SymbolHoc lookup(String name) {
        SymbolHoc s;
        Iterator it = ListaSimbolos.iterator();
        
        while(it.hasNext()) {
            s = (SymbolHoc)it.next();
            
            if(s.name.equals(name)) {
                return s;
            }
        }
        
        return null;
    }
    
    public SymbolHoc install(String name, EnumTipoSymbol type, float val) {
        SymbolHoc s;
        
        s = new SymbolHoc();
        s.SetSymbol(name, type, val); 
        /* verificar si no es que ya existe en la lista */
        ListaSimbolos.add(s);
        
        return s;
    }
    
    public SymbolHoc install(String name, EnumTipoSymbol type, EnumBLTIN funcPredef) {
        SymbolHoc s;
        
        s = new SymbolHoc();
        s.SetSymbol(name, type, funcPredef);
        ListaSimbolos.add(s);
        
        return s;
    }
    
    public void init() {
        ListaSimbolos.clear();
        InitConstPredef();
        InitFuncPredef();
    }
    
    private void InitConstPredef() {
        /*SymbolHoc s;
        float val;
        
        s = new SymbolHoc();
        val = (float)3.141592653589;
        s.SetSymbol("PI", EnumTipoSymbol.CONST_PREDEF, val);
        ListaSimbolos.add(s);*/
        ListaSimbolos.add(new SymbolHoc("PI", EnumTipoSymbol.CONST_PREDEF, (float)3.141592653589));
        ListaSimbolos.add(new SymbolHoc("E", EnumTipoSymbol.CONST_PREDEF, (float)2.718281828459));
        ListaSimbolos.add(new SymbolHoc("GAMMA", EnumTipoSymbol.CONST_PREDEF, (float)0.577215664901));
        ListaSimbolos.add(new SymbolHoc("DEG", EnumTipoSymbol.CONST_PREDEF, (float)57.2957795130));
        ListaSimbolos.add(new SymbolHoc("PHI", EnumTipoSymbol.CONST_PREDEF, (float)1.6180334989));
    }
    
    private void InitFuncPredef() {
        /*SymbolHoc s;
        
        s = new SymbolHoc();
        
        s.SetSymbol("sin", EnumTipoSymbol.BLTIN, EnumBLTIN.SIN);
        ListaSimbolos.add(s);*/
        ListaSimbolos.add(new SymbolHoc("sin", EnumTipoSymbol.BLTIN, EnumBLTIN.SIN));
        ListaSimbolos.add(new SymbolHoc("cos", EnumTipoSymbol.BLTIN, EnumBLTIN.COS));
        ListaSimbolos.add(new SymbolHoc("atan", EnumTipoSymbol.BLTIN, EnumBLTIN.ATAN));
        ListaSimbolos.add(new SymbolHoc("log", EnumTipoSymbol.BLTIN, EnumBLTIN.LOG));
        ListaSimbolos.add(new SymbolHoc("lo10", EnumTipoSymbol.BLTIN, EnumBLTIN.LO10));
        ListaSimbolos.add(new SymbolHoc("exp", EnumTipoSymbol.BLTIN, EnumBLTIN.EXP));
        ListaSimbolos.add(new SymbolHoc("sqrt", EnumTipoSymbol.BLTIN, EnumBLTIN.SQRT));
        ListaSimbolos.add(new SymbolHoc("int", EnumTipoSymbol.BLTIN, EnumBLTIN.INTEGER));
        ListaSimbolos.add(new SymbolHoc("abs", EnumTipoSymbol.BLTIN, EnumBLTIN.ABS));
    }
}
