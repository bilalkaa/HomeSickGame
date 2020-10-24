import java.util.*;
public class SickPlayer extends Player
{
    
    public SickPlayer(String Pname, int Pcpoint, int Pstamina, int isSick){
        super(Pname,Pcpoint, Pstamina, isSick);
    }
    
    public void performAttack(String attackName, Character enemy){
        int attackPower = super.attackPowerGetter(attackName);
        
        int staminaDecreaserDebuff = ((new Random().nextDouble())<=0.60&&attackName!="Rest")?10:((new Random().nextDouble())<=0.60&&attackName!="Rest")?5:0;
        
        super.staminaSetter(attackPower+staminaDecreaserDebuff);
        if(attackPower<0){
            enemy.cpointSetter(attackPower);
        }
    }
}
