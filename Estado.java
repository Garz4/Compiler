public class Estado {
    private static int generalId;
    private int id;
    
    //=====
    
    public int getId(void){return this.id;}
    
    public Estado(void){
        this.id = generalId;
        generalId++;
    }
}
