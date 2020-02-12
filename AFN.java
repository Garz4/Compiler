class AFN {
    Estado edoIni;
    HashSet<Estado> edosAceptacion;
    HashSet<char> alfabeto;
    HashSet<Estado> edosAFN;
    
    //=====
    
    HashSet<char> getAlfabeto(){return this.alfabeto;}
    HashSet<Estado> getEdosAFN(){return this.edosAFN;}
    
    AFN(){
        edoIni = NULL;
        alfabeto = new HashSet<char>();
        alfabeto.clear();
        edosAceptacion = new HashSet<Estado>();
        edosAceptacion.clear();
        edosAFN = new HashSet<Estado>();
        edosAFN.clear();
    }
    
    AFN(char s){
        Estado ef;
        edoIni = new Estado();
        ef = new Estado();
        ef.setAceptacion(true);
        edosAceptacion = new HashSet<Estado>();
        edosAceptacion.clear();
        edosAceptacion.add(ef);
        edosAFN = new HashSet<Estado>();
        edosAFN.clear();
        edosAFN.add(edoIni);
        edosAFN.add(ef);
        alfabeto = new HashSet<char>();
        alfabeto.clear();
        alfabeto.add(s);
        edoIni.addTransicion(new Transicion(s, ef));
    }
    
    AFN AFNBasico(char s){
        Estado ef;
        edoIni = new Estado();
        ef = new Estado();
        ef.setAceptacion(true);
        edosAceptacion = new HashSet<Estado>();
        edosAceptacion.clear();
        edosAceptacion.add(ef);
        edosAFN = new HashSet<Estado>();
        edosAFN.clear();
        edosAFN.add(edoIni);
        edosAFN.add(ef);
        alfabeto = new HashSet<char>();
        alfabeto.clear();
        alfabeto.add(s);
        edoIni.addTransicion(new Transicion(s, ef));
        return this;
    }
    
    AFN unirAFN(AFN f){
        Estado e1 = new Estado();
        Estado e2 = new Estado();
        
        e1.addTransicion(new Transicion(Epsilon, this.edoIni));
        e1.addTransicion(new Transicion(Epsilon, f.edoIni));
        
        for(Estado e : this.edosAceptacion){
            e.addTransicion(new Transicion(Epsilon, e2));
            e.setAceptacion(false);
        }
        
        for(Estado e : f.edosAceptacion){
            e.addTransicion(new Transicion(Epsilon, e2));
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
    
    AFN concatenar(AFN f){ //Thompson

        for (Estado e : this.EdosAcept) {
            for (Transicion t : f2.EdoIni.getTrans()) {
                e.AgregarTrans(t.simboloMin, t.simboloMax, t.Edo);
            }
        }
        f2.Estados.remove(f2.EdoIni);
        for (Estado e : this.EdosAcept) {
            e.setAceptacion(false);
        }
        this.EdosAcept.clear();
        for (Estado e : f2.EdosAcept) {
            this.EdosAcept.add(e);
        }
        for (char a : f2.getAlfabeto()) {
            this.Alfabeto.add(a);
        }
        for (Estado e : f2.Estados) {
            this.Estados.add(e);
        }
        f2 = null;
        return this;
    }
     //Cerradura positiva. 
     AFN CerrMas(AFN f)() {
        Estado NuevoIni = new Estado();
        Estado NuevoFin = new Estado();
        NuevoIni.AgregarTrans('&', this.EdoIni);
        for (Estado e : this.EdosAcept) {
            e.AgregarTrans('&', NuevoFin);
            e.AgregarTrans('&', this.EdoIni);
            e.setAceptacion(false);
        }
        NuevoFin.setAceptacion(true);
        NuevoFin.setToken(AToken);
        AToken++;
        this.EdosAcept.clear();
        this.EdosAcept.add(NuevoFin);
        this.Estados.add(NuevoIni);
        this.Estados.add(NuevoFin);
        this.EdoIni = NuevoIni;
        return this;
    }
}

/*
Conjunto<Estado> Cerradura_e(Estado e){
    Conjunto<Estado> R = new Conjunto<Estado>();
    Pila<Estado> P = new Pila<Estado>();

    P.push(e);

    while(!P.vacia()){
        e2 = P.pop();
        if(R.contiene())
            continue;
        R.agregar(e2);
        for each(Transicion t in e2.transiciones)
            if(t.Simbolo == epsilon)
                if(t.Estado no está en R)
                    P.push(t.Estado);
    }
    return R;
}

Conjunto<Estado> Cerradura_e(Conjunto<Estado> c){
    Conjunto<Estado> R = new Conjunto<Estado>();
    R = {};
    for each(Estado in c)
        R.union(Cerradura_e(e));
    return R;
}
*/
