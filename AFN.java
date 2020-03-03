import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Stack;
import java.util.TreeMap;

class AFN {
    Estado edoIni;
    HashSet<Estado> edosAceptacion;
    HashSet<Character> alfabeto;
    HashSet<Estado> edosAFN;

    //=========================

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

    AFN(char s, int toToken){
        Estado ef;
        edoIni = new Estado();
        ef = new Estado();
        ef.setAceptacion(true);
        ef.setToken(toToken);
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

    AFN AFNBasico(char s, int toToken){
        Estado ef;
        edoIni = new Estado();
        ef = new Estado();
        ef.setAceptacion(true);
        ef.setToken(toToken);
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

    AFN unirAFN(AFN f, int toToken){
        Estado nuevoInicio = new Estado();
        Estado nuevoFinal = new Estado();

        nuevoInicio.addTransicion('&', this.edoIni);
        nuevoInicio.addTransicion('&', f.edoIni);

        for(Estado e : this.edosAceptacion){
            e.addTransicion('&', nuevoFinal);
            e.setAceptacion(false);
        }

        for(Estado e : f.edosAceptacion){
            e.addTransicion('&', nuevoFinal);
            e.setAceptacion(false);
        }

        nuevoFinal.setAceptacion(true);
        nuevoFinal.setToken(toToken);

        this.alfabeto.addAll(f.getAlfabeto());
        this.edosAFN.addAll(f.getEdosAFN());
        this.edosAFN.add(nuevoInicio);
        this.edosAFN.add(nuevoFinal);
        this.edosAceptacion.clear();
        this.edosAceptacion.add(nuevoFinal);
        this.edoIni = nuevoInicio;
        f = null;

        return this;
    }

    AFN concatenar(AFN f2, int toToken){ //Thompson
        for (Estado e : this.edosAceptacion) {
            for(Transicion entry: f2.edoIni.getTransiciones()) {
                e.addTransicion(entry.getSimbolo(), entry.getEstado());
            }
        }
        f2.edosAFN.remove(f2.edoIni);
        for (Estado e : this.edosAceptacion) {
            e.setAceptacion(false);
            //e.setToken(-1);
        }
        this.edosAceptacion.clear();
        for (Estado e : f2.edosAceptacion) {
            e.setToken(toToken);
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
    AFN CerrMas(int toToken){
        Estado NuevoIni = new Estado();
        Estado NuevoFin = new Estado();
        NuevoIni.addTransicion('&', this.edoIni);
        for(Estado e : this.edosAceptacion){
            e.addTransicion('&', NuevoFin);
            e.addTransicion('&', this.edoIni);
            e.setAceptacion(false);
        }
        NuevoFin.setAceptacion(true);
        NuevoFin.setToken(toToken);
        this.edosAceptacion.clear();
        this.edosAceptacion.add(NuevoFin);
        this.edosAFN.add(NuevoIni);
        this.edosAFN.add(NuevoFin);
        this.edoIni = NuevoIni;
        return this;
    }
     
    AFN CerrEstrella(int toToken){
        Estado NuevoIni = new Estado();
        Estado NuevoFin = new Estado();
        NuevoIni.addTransicion('&', this.edoIni);
        
        for(Estado e : this.edosAceptacion){
            e.addTransicion('&', NuevoFin);
            e.addTransicion('&', this.edoIni);
            e.setAceptacion(false);
        }
        
        NuevoFin.setAceptacion(true);
        NuevoFin.setToken(toToken);
        this.edosAceptacion.clear();
        this.edosAceptacion.add(NuevoFin);
        this.edosAFN.add(NuevoIni);
        this.edosAFN.add(NuevoFin);
        this.edoIni = NuevoIni;
        this.edoIni.addTransicion('&', NuevoFin);
        return this;
    }
    
    AFN CerrOpcional(int toToken){
        Estado NuevoIni = new Estado();
        Estado NuevoFin = new Estado();
        NuevoIni.addTransicion('&', this.edoIni);
        
        for(Estado e : this.edosAceptacion){
            e.addTransicion('&', NuevoFin);
            e.setAceptacion(false);
        }
        
        NuevoFin.setAceptacion(true);
        NuevoFin.setToken(toToken);
        this.edosAceptacion.clear();
        this.edosAceptacion.add(NuevoFin);
        this.edosAFN.add(NuevoIni);
        this.edosAFN.add(NuevoFin);
        this.edoIni = NuevoIni;
        this.edoIni.addTransicion('&', NuevoFin);
        return this;
    }
     
    HashSet<Estado> Cerradura_e(Estado e){
        Estado e2;
        HashSet<Estado> R = new HashSet<>();
        R.clear();
        Stack<Estado> P = new Stack<>();
        P.clear();
        P.push(e);
        while(!P.empty()){
            e2 = P.pop();
            if(R.contains(e2)){
                continue;
            }
            R.add(e2);
            for(Transicion entry : e2.getTransiciones()){
                if(entry.getSimbolo() == '&'){
                    if(!R.contains(entry.getEstado())){
                        P.push(entry.getEstado());
                    }
                }
            }
        }
        return R;
    }

    HashSet<Estado> Cerradura_e(HashSet<Estado> c){
        HashSet<Estado> R = new HashSet<>();
        R.clear();
        for(Estado e : c)
            R.addAll(Cerradura_e(e));
        return R;
    }
    
    HashSet<Estado> mover(HashSet<Estado> toHash, char toChar){
        HashSet<Estado> response = new HashSet<>();
        HashSet<Transicion> currTran = new HashSet<>();
        for(Estado e : toHash){
            currTran = e.getTransiciones();
            for(Transicion t : currTran){
                if(t.getSimbolo() == toChar){
                    response.add(t.getEstado());
                }
            }
        }
        return response;
    }
    
    HashSet<Estado> irA(HashSet<Estado> toHash, char toChar){
        return Cerradura_e(mover(toHash, toChar));
    }
    
    int yaContiene(NavigableMap<Integer, HashSet> totales, HashSet<Estado> toAnalyze){
        for(Entry<Integer, HashSet> entry : totales.entrySet()){
            if(toAnalyze.equals(entry.getValue())){
                return entry.getKey();
            }
        }
        return -1;
    }
    
    int esAceptacion(HashSet<Estado> toVerify){
        for(Estado e : toVerify){
            for(Estado eA : this.edosAceptacion){
                if(e.getId() == eA.getId()) return eA.getToken();
            }
        }
        return -1;
    }
    
    int contieneTokenValido(HashSet<Estado> toVerify){
        for(Estado e : toVerify){
            for(Estado eI : this.edosAFN){
                if(e.getId() == eI.getId() && eI.getToken() != -1) return eI.getToken();
            }
            
        }
        return -1;
    }
    
    AFD parseAFD(){
        AFD response = new AFD();
        int currentId;
        int contadorId = 100, newId;
        ArrayList<Estado> estadosAFD = new ArrayList<>();
        Stack<HashSet<Estado>> A = new Stack<>();
        NavigableMap<Integer, HashSet> totales = new TreeMap<>();
        HashSet<Estado> current = Cerradura_e(edoIni);
        HashSet<Estado> analyzing = new HashSet<>();
        
        A.push(current);
        totales.put(contadorId, current);
        estadosAFD.add(new Estado());
        estadosAFD.get(0).setId(contadorId);
        contadorId++;
        
        while(!A.empty()){
            current = A.pop();
            for(char a : alfabeto){
                analyzing = irA(current, a);
                currentId = yaContiene(totales, current);
                if(analyzing.size() > 0){
                    newId = yaContiene(totales, analyzing);
                    if(newId == -1){
                        estadosAFD.add(new Estado());
                        
                        for(Estado currE : estadosAFD){
                            if(currE.getId() == currentId){
                                currE.addTransicion(a, estadosAFD.get(estadosAFD.size()-1));
                                break;
                            }
                        }
                        
                        estadosAFD.get(estadosAFD.size()-1).setId(contadorId);
                        int toAceptacion = esAceptacion(analyzing);
                        if(toAceptacion != -1){
                            estadosAFD.get(estadosAFD.size()-1).setAceptacion(true);
                            estadosAFD.get(estadosAFD.size()-1).setToken(toAceptacion);
                        }
                        else{
                            estadosAFD.get(estadosAFD.size()-1).setAceptacion(false);
                            estadosAFD.get(estadosAFD.size()-1).setToken(-1);
                        }
                        int tokenValido = contieneTokenValido(analyzing);
                        if(tokenValido != -1) estadosAFD.get(estadosAFD.size()-1).setToken(tokenValido);
                            
                        A.push(analyzing);
                        totales.put(contadorId, analyzing);
                        contadorId++;
                    }
                    else{
                        for(Estado currE : estadosAFD){
                            if(currE.getId() == currentId){
                                for(Estado currrr : estadosAFD){
                                    if(currrr.getId() == newId){
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

        for(Estado e : estadosAFD){
            if(e != response.getEdoIni()){
                response.addEstado(e);
                if(e.aceptacion){
                    response.addEdoAceptacion(e);
                }
            }
                
        }
        System.out.print("AFD:\n");
        HashSet<Transicion> currTran;
        System.out.print("# Edo   Simbolo   Edo final   Token    Estados de AFN\n");
        for(Estado e : response.getAllEstados()){
            currTran = e.getTransiciones();
            if(currTran.size() > 0){
                for(Transicion entry : currTran){
                    System.out.print(""+e.getId());
                    System.out.print("       "+entry.getSimbolo()+"          "+entry.getEstado().getId()+"       "+e.getToken()+"         "+totales.get(e.getId())+"\n");
                }
            }
            else{
                System.out.print(e.getId()+" ===================  "+e.getToken()+"\n");
            }
        }
        
        response.setAlfabeto(this.alfabeto);
        
        return response;
    }
    
}
