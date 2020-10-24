public class FriendlyPlayer extends Character
{
    private int friendHelp;
    
    public FriendlyPlayer(String FPname, int FPcpoint, int FPstamina){
        super(FPname, FPcpoint, FPstamina);
        
        friendHelp=20;
    }

    public void friendHelpSetter(int amount){
        friendHelp=amount;
    }
    
    public int friendHelpGetter(){
        return friendHelp;
    }

    public int attackPowerGetter(String attackName){
        return 5;
    }
}
