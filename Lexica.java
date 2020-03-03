class Lexica {
    String lexema;
    String total;
    int currentIndex;
    int token;
    AFD automata;
    
    //=========================
    
    Lexica(){
        this.total = "";
        this.currentIndex = 0;
    }
    
    Lexica(String toTotal, AFD toAutomata){
        this.total = toTotal;
        this.currentIndex = 0;
        this.automata = toAutomata;
    }
    
    public void compute(){
        
    }
    
    int getToken(){ return this.token; }
    String getLexema(){ return this.lexema; }
}
