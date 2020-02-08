class Estado {
    static int generalId = 1;
    int id;
    bool aceptacion;
    HashSet<Transicion> transiciones;
    
    //=====
    
    int getId(void){return this.id;}
    bool getAceptacion(void){return this.aceptacion;}
    
    void setAceptacion(bool toSet){this.aceptacion = toSet;}
    void addTransicion(Transicion toAdd){this.transiciones.add(toAdd);}
    
    Estado(void){
        this.id = generalId;
        this.aceptacion = false;
        this.transiciones = new HashSet<Transicion>();
        this.transiciones.clear();
        generalId++;
    }
}
