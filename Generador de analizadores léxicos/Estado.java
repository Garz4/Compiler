import java.util.HashSet;

class Estado {
    static int generalId = 1;
    HashSet<Transicion> transiciones;
    int id;
    int token;
    boolean aceptacion;

    //=========================

    int getId(){return this.id;}
    int getToken(){return this.token;}
    boolean getAceptacion(){return this.aceptacion;}
    HashSet<Transicion> getTransiciones(){return this.transiciones;}

    void setAceptacion(boolean toAceptacion){this.aceptacion = toAceptacion;}
    void setToken(int toToken){this.token = toToken;}
    void setId(int toId){ this.id = toId; }
    
    void addTransicion(char toChar, Estado toEstado){this.transiciones.add(new Transicion(toChar, toEstado));}

    Estado(){
        this.token = -1;
        this.id = generalId;
        this.aceptacion = false;
        this.transiciones = new HashSet<>();
        this.transiciones.clear();
        generalId++;
    }
}
