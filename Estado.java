class Estado {
    static int generalId = 1;
    int id;
    bool aceptacion;
    ArrayList<Transicion> transiciones;
    
    //=====
    
    int getId(void){return this.id;}
    
    Estado(void){
        this.id = generalId;
        this.aceptacion = false;
        generalId++;
    }
}
