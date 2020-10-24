/**
 * The student class defines the attributes of a student in the game.
 *
 * Bilal Kaaouachi
 * Version 1: 20.02.2020
 */

public final class Student extends NonPlayer
{
    
    private Moves [] studentMoves = new Moves[3];

    
    public Student(String Sname, int Scpoints, int Sstamina){
        super(Sname, Scpoints, Sstamina);

        studentMoves[0] = new Moves("Questioning",-10);
        studentMoves[1] = new Moves("Make Joke",-15);
        studentMoves[2] = new Moves("Rest",50);
    }
    
    
    
    
    public int attackPowerGetter(String attackName){     
        for (int i=0; i<studentMoves.length; i++){
            if ((studentMoves[i].moveNameGetter()).equals(attackName)){
                return (studentMoves[i].movePowerGetter());
            }
        }
        
        return 0;
    }
    
    public void performAttack(String attackName, Character enemy){
        int attackPower= attackPowerGetter(attackName);
        super.staminaSetter(attackPower);
        if (attackPower<0){
            enemy.cpointSetter(attackPower);
        }
    }    
    
    
}
