
import java.util.HashSet;

class Lexica {
    String lexema;
    String total;
    String prevLexema;
    Estado currentEstado;
    Estado prevEstado;
    Estado prevAceptacion;
    int currentIndex;
    int prevIndexAceptacion;
    int token;
    int prevToken;
    AFD automata;
    
    //=========================
    
    Lexica(){
        this.total = "";
        this.currentIndex = 0;
        this.token = -1;
        this.currentEstado = null;
        this.prevEstado = null;
        this.prevAceptacion = null;
        this.prevIndexAceptacion = -1;
        this.prevLexema = "";
        this.prevToken = -1;
    }
    
    Lexica(String toTotal, AFD toAutomata){
        this.total = toTotal;
        this.currentIndex = 0;
        this.automata = toAutomata;
        this.token = -1;
        this.currentEstado = null;
        this.prevEstado = toAutomata.getEdoIni();
        this.prevAceptacion = null;
        this.prevIndexAceptacion = -1;
        this.prevLexema = "";
        this.prevToken = -1;
    }
    
    public void compute(){
        System.out.print("============ INICIA ANÁLISIS: ============");
        if(currentIndex == total.length()){
            token = 0;
            lexema = "FIN DEL ANÁLISIS";
            return;
        }
        
        lexema = "";
        
        prevEstado = automata.getEdoIni();
        prevAceptacion = null;

        while(currentIndex < total.length()){
            System.out.print("-------\nCurrent index: "+currentIndex+"\nCurrent char: "+total.charAt(currentIndex)+"\n");
            currentEstado = validateTransition(total.charAt(currentIndex), prevEstado);
            
            if(currentEstado != null){
                
                this.token = currentEstado.getToken();
                lexema += total.charAt(currentIndex);
                System.out.print("\nCurrent id: "+currentEstado.getId()+"\nCurrent lexema: "+lexema+"\nCurrent token: "+token+"\n");
                
                if(currentEstado.getToken() > 0){
                    prevAceptacion = currentEstado;
                    prevIndexAceptacion = currentIndex;
                    prevLexema = lexema;
                    prevToken = token;
                }
                
                prevEstado = currentEstado;
                
                currentIndex++;
            }
            else{
                if(prevAceptacion == null){
                    token = 0;
                    lexema = "Error.";
                    return;
                }
                else{
                    token = prevToken;
                    lexema = prevLexema;
                    currentIndex = prevIndexAceptacion;
                    currentIndex++;
                    System.out.print("\n->->->-> LEXEMA A RETORNAR: "+lexema+" TOKEN: "+token+" <-<-<-<-<-\n");
                    return;
                }
            }
            
        }
        
    }
    
    public Estado validateTransition(char toVerify, Estado e){
        System.out.print("Estoy");
        if(e == null) return null;
        System.out.print("sigo");
        HashSet<Transicion> trans = new HashSet<>();
        trans = e.getTransiciones();
        System.out.print(trans.size());
        for(Transicion t : trans){
            if(t.getSimbolo() == toVerify) return t.getEstado();
        }
        System.out.print(" noencontre");
        return null;
    }
    
    int getToken(){ return this.token; }
    String getLexema(){ return this.lexema; }
}
