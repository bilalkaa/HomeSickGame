public class Moves
{
    private String moveName;
    private int movePower;
    
    public Moves(String name, int power){
        moveName = name;
        movePower = power;
    }
    
    public String moveNameGetter(){
        return moveName;
    }
    
    public int movePowerGetter(){
        return movePower;
    }

    
}
