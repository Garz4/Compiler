import java.util.HashSet;

public class AFD {
    Estado edoIni;
    HashSet<Estado> edosAceptacion;
    HashSet<Character> alfabeto;
    HashSet<Estado> edosAFN;
    
    //=========================
    
    AFD(){
        edosAceptacion = new HashSet<>();
        alfabeto = new HashSet<>();
        edosAFN = new HashSet<>();
    }
    
    void setEdoIni(Estado toSet){ this.edoIni = toSet; edosAFN.add(toSet); }
    void addEstado(Estado toAdd){ edosAFN.add(toAdd); }
    void addEdoAceptacion(Estado toSet){ edosAceptacion.add(toSet); }
    void setAlfabeto(HashSet<Character> toSet){ this.alfabeto = toSet; }
    
    Estado getEdoIni(){ return this.edoIni; }
    HashSet<Estado> getAllEstados(){ return this.edosAFN; }
    
}
