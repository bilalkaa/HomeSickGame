/**
 * The NonPlayer class defines the attributes of a NonPlayer in the game.
 *
 * Bilal Kaaouachi
 * Version 1: 20.02.2020
 */

public abstract class NonPlayer extends Character
{
    private boolean isConvinced;
    


    public NonPlayer(String NPname, int NPcpoint, int NPstamina){
        super(NPname, NPcpoint, NPstamina);
        
        isConvinced = false;

    }


    public void convincedSetter(boolean status){
        isConvinced = status;
        return;
    }
    
    public boolean convincedGetter(){
        return isConvinced;
    }
    
    public int attackPowerGetter(String attackName){
       return 0;
    }
    public void performAttack(String attackName, Character enemy){
    }
}
