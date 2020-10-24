/**
 * The Player class defines the attributes of a player in the game.
 *
 * Bilal Kaaouachi
 * Version 1: 20.02.2020
 */

import java.util.*;

public class Player extends Character
{
    private boolean isCaught;

    private Moves [] playerMoves = new Moves[4];
    
    private int isSick;

    public Player(String Pname, int Pcpoint, int Pstamina, int sickStatus){
        super(Pname,Pcpoint, Pstamina);
        
        isCaught=false;
                
        playerMoves[0] = new Moves("Sore Throat",-20);
        playerMoves[1] = new Moves("Cough",-30);
        playerMoves[2] = new Moves("Dizzy Walk",-40);
        playerMoves[3] = new Moves("Rest",100);
        
        
        isSick =  sickStatus;
        
    }
    
    
    
    public void caughtSetter(boolean status){
        isCaught=status;
        return;
    }
    
    public boolean caughtGetter(){
        return isCaught;
    }
    
    

      
    
    public int attackPowerGetter(String attackName){     
        int power=0;
        for (int i=0; i<playerMoves.length; i++){
            if ((playerMoves[i].moveNameGetter()).equals(attackName)){
                power = (playerMoves[i].movePowerGetter());

            }

        }
        
        return power;
    }
    
    public void performAttack(String attackName, Character enemy){}
    
    
    public boolean attackExist(String attackName){
        int attackPower = attackPowerGetter(attackName);
        boolean attackStatus = (attackPower!=0)?true:false;
        return attackStatus;
    }

    
    public int isSickGetter(){
        return isSick;
    }
    


    /****
    public void staminaSetter(){
        super.staminaSetter(0);
        
        Random rand = new Random();
        double rndmAdvantage = (rand.nextInt(10) +10)/10;
        
        maxstamina = cpoints*(rndmavantage);
        
    }
    ***/
    
    
}
