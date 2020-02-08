class AFN {
    Estado edoIni;
    HashSet<Estado> edosAceptacion;
    HashSet<char> alfabeto;
    HashSet<Estado> edosAFN;
    
    AFN(void){
        this.edoIni = NULL;
        this.alfabeto = new HashSet<char>();
        this.alfabeto.clear();
        this.edosAceptacion = new HashSet<Estado>();
        this.edosAceptacion.clear();
        this.edosAFN = new HashSet<Estado>();
        this.edosAFN.clear();
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
