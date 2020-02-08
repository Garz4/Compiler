class Estado {
    static int generalId = 1;
    int id;
    bool aceptacion;
    ArrayList<Transicion> transiciones;
    
    //=====
    
    int getId(void){return this.id;}
    bool getAceptacion(void){return this.aceptacion;}
    ArrayList<Transicion> getTransiciones(void){return this.transiciones;}
    
    void setAceptacion(bool toSet){this.aceptacion = toSet;}
    void addTransicion(Transicion toAdd){this.transiciones.add(toAdd);}
    
    Estado(void){
        this.id = generalId;
        this.aceptacion = false;
        this.transiciones = new ArrayList<Transicion>();
        generalId++;
    }
}
