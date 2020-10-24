import java.util.*;
public class NormalPlayer extends Player
{
    
    public NormalPlayer(String Pname, int Pcpoint, int Pstamina, int isSick){
        super(Pname,Pcpoint, Pstamina, isSick);
    }
    
    public void performAttack(String attackName, Character enemy){
        int attackPower = attackPowerGetter(attackName);
        super.staminaSetter(attackPower);
        if(attackPower<0){
            enemy.cpointSetter(attackPower);
        }
    }
}
