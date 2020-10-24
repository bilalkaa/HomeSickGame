/**
 * The teacher class defines the attributes of a teacher in the game.
 *
 * Bilal Kaaouachi
 * Version 1: 20.02.2020
 */

    
public final class Teacher extends NonPlayer
{
    
    private Moves [] teacherMoves = new Moves[4];


    public Teacher(String Tname, int Tcpoint, int Tstamina)
    {
        super(Tname, Tcpoint, Tstamina);
        
        teacherMoves[0] = new Moves("Questioning",-10);
        teacherMoves[1] = new Moves("Make Joke",-20);
        teacherMoves[2] = new Moves("Intense Glare",-25);
        teacherMoves[3] = new Moves("Rest",100);        
    }



    
    public int attackPowerGetter(String attackName){     
        int power=0;
        for (int i=0; i<teacherMoves.length; i++){
            if ((teacherMoves[i].moveNameGetter()).equals(attackName)){
                power = (teacherMoves[i].movePowerGetter());

            }

        }
        
        return power;
    }
    
    public void performAttack(String attackName, Character enemy){
        int attackPower= attackPowerGetter(attackName);
        super.staminaSetter(attackPower);
        if (attackPower<0){
            enemy.cpointSetter(attackPower);
        }
    }
    
    
}
