package automaton;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Stack;
import java.util.TreeMap;

class Estado {
    static int generalId = 1;
    NavigableMap<Character, Estado> transiciones;
    int id;
    int token;
    boolean aceptacion;

    //=====

    int getId(){return this.id;}
    boolean getAceptacion(){return this.aceptacion;}
    NavigableMap<Character, Estado> getTransiciones(){return this.transiciones;}

    void setAceptacion(boolean toAceptacion){this.aceptacion = toAceptacion;}
    void setToken(int toToken){this.token = toToken;}
    
    void addTransicion(char toChar, Estado toEstado){this.transiciones.put(toChar, toEstado);}

    Estado(){
        this.id = generalId;
        this.aceptacion = false;
        this.transiciones = new TreeMap<>();
        this.transiciones.clear();
        generalId++;
    }
}

class AFN {
    Estado edoIni;
    HashSet<Estado> edosAceptacion;
    HashSet<Character> alfabeto;
    HashSet<Estado> edosAFN;

    //=====

    HashSet<Character> getAlfabeto(){return this.alfabeto;}
    HashSet<Estado> getEdosAFN(){return this.edosAFN;}

    AFN(){
        edoIni = null;
        alfabeto = new HashSet<>();
        alfabeto.clear();
        edosAceptacion = new HashSet<>();
        edosAceptacion.clear();
        edosAFN = new HashSet<>();
        edosAFN.clear();
    }

    AFN(char s){
        Estado ef;
        edoIni = new Estado();
        ef = new Estado();
        ef.setAceptacion(true);
        edosAceptacion = new HashSet<>();
        edosAceptacion.clear();
        edosAceptacion.add(ef);
        edosAFN = new HashSet<>();
        edosAFN.clear();
        edosAFN.add(edoIni);
        edosAFN.add(ef);
        alfabeto = new HashSet<>();
        alfabeto.clear();
        alfabeto.add(s);
        edoIni.addTransicion(s, ef);
    }

    AFN AFNBasico(char s){
        Estado ef;
        edoIni = new Estado();
        ef = new Estado();
        ef.setAceptacion(true);
        edosAceptacion = new HashSet<>();
        edosAceptacion.clear();
        edosAceptacion.add(ef);
        edosAFN = new HashSet<>();
        edosAFN.clear();
        edosAFN.add(edoIni);
        edosAFN.add(ef);
        alfabeto = new HashSet<>();
        alfabeto.clear();
        alfabeto.add(s);
        edoIni.addTransicion(s, ef);
        return this;
    }

    AFN unirAFN(AFN f){
        Estado e1 = new Estado();
        Estado e2 = new Estado();

        e1.addTransicion('&', this.edoIni);
        e1.addTransicion('&', f.edoIni);

        for(Estado e : this.edosAceptacion){
            e.addTransicion('&', e2);
            e.setAceptacion(false);
        }

        for(Estado e : f.edosAceptacion){
            e.addTransicion('&', e2);
            e.setAceptacion(false);
        }

        e2.setAceptacion(true);

        this.alfabeto.addAll(f.getAlfabeto());
        this.edosAFN.addAll(f.getEdosAFN());
        this.edosAFN.add(e1);
        this.edosAFN.add(e2);
        this.edosAceptacion.clear();
        this.edosAceptacion.add(e2);
        this.edoIni = e1;
        f = null;

        return this;
    }

    AFN concatenar(AFN f2){ //Thompson
        for (Estado e : this.edosAceptacion) {
            for(Entry<Character, Estado> entry: f2.edoIni.getTransiciones().entrySet()) {
                e.addTransicion(entry.getKey(), entry.getValue());
            }
        }
        f2.edosAFN.remove(f2.edoIni);
        for (Estado e : this.edosAceptacion) {
            e.setAceptacion(false);
        }
        this.edosAceptacion.clear();
        for (Estado e : f2.edosAceptacion) {
            this.edosAceptacion.add(e);
        }
        for (char a : f2.getAlfabeto()) {
            this.alfabeto.add(a);
        }
        for (Estado e : f2.edosAFN) {
            this.edosAFN.add(e);
        }
        f2 = null;
        return this;
    }
    
     //Cerradura positiva. 
     AFN CerrMas(AFN f){
        Estado NuevoIni = new Estado();
        Estado NuevoFin = new Estado();
        NuevoIni.addTransicion('&', this.edoIni);
        for(Estado e : this.edosAceptacion){
            e.addTransicion('&', NuevoFin);
            e.addTransicion('&', this.edoIni);
            e.setAceptacion(false);
        }
        NuevoFin.setAceptacion(true);
        NuevoFin.setToken(10);
        this.edosAceptacion.clear();
        this.edosAceptacion.add(NuevoFin);
        this.edosAFN.add(NuevoIni);
        this.edosAFN.add(NuevoFin);
        this.edoIni = NuevoIni;
        return this;
    }
     
    HashSet<Estado> Cerradura_e(Estado e){
        Estado e2 = new Estado();
        HashSet<Estado> R = new HashSet<>();
        Stack<Estado> P = new Stack<>();

        P.push(e);

        while(!P.empty()){
            e2 = P.pop();
            if(R.contains(e2))
                continue;
            R.add(e2);
            for(Entry<Character, Estado> entry: e2.getTransiciones().entrySet())
                if(entry.getKey() == '&')
                    if(!R.contains(entry.getValue()))
                        P.push(entry.getValue());
        }
        return R;
    }

    HashSet<Estado> Cerradura_e(HashSet<Estado> c){
        HashSet<Estado> R = new HashSet<Estado>();
        R.clear();
        for(Estado e : c)
            R.addAll(Cerradura_e(e));
        return R;
    }
}

public class Automaton {
    public static void main(String[] args) {
        
    }
}
