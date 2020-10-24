/**
 * class Character is the superclass for all characters in this game. This includes students, teachers and the player.
 *
 * Bilal Kaaouachi
 * Version 1: 20.02.2020
 */

public abstract class Character
{
    private String name;
    
    private int cpoint;
    private int maxcpoint;
    
    private int stamina;
    private int maxstamina;

    
    public Character(String nname, int ccpoint, int sstamina){
        name = nname;
        
        cpoint = ccpoint;
        
        stamina = sstamina;
        
        maxcpoint=ccpoint;
        maxstamina=sstamina;
    }
    //Initializing the private variables of this object.
    
    
    public String nameGetter(){
        return name;
    }
    //Returns the name.


    public void cpointSetter(int cpointsAdded){
        cpoint = cpoint + cpointsAdded;
        if (cpoint>maxcpoint){
            cpoint=maxcpoint;
        }
        else if (cpoint<0){
            cpoint=0;
        }
        
        return;
    }
    
    public int cpointGetter(){
        return cpoint;
    }
    //Setter and getter methods for CP.
    
    
    public void staminaSetter(int staminaAdded){
        stamina = stamina + staminaAdded;
        if (stamina>maxstamina){
            stamina=maxstamina;
        }
        else if (stamina<0){
        stamina=0;
        }
        
        return;
    }
    
    public int staminaGetter(){
        return stamina;
    }
    //Setters and Getters for Stamina.
    
    
    
    public int attackPowerGetter(String attackName){return 0;}
    
    public void performAttack(String attackName, Character enemy){}
    
        
}

