public class Transicion {
    char simbolo;
    Estado estado;
    
    public Transicion(){
        this.simbolo = 0;
        this.estado = new Estado();
    }
    
    public Transicion(char sim, Estado E){
        this.simbolo = sim;
        this.estado = E;
    }
    
    public void setEstado(Estado toSet){ this.estado = toSet; }
    public void setSimbolo(char toSet){ this.simbolo = toSet; }
    public char getSimbolo(){ return this.simbolo; }
    public Estado getEstado(){ return this.estado; }
}
