/**
 * The Classroom class defines the attributes of a Classroom in the game.
 *
 * Bilal Kaaouachi
 * Version 1: 20.02.2020
 */


public class Classroom
{

    
    private int numberOfStudents;
    private int numberOfTeachers;
    private int numberOfItems;
    
    private Student [] studentEnemy;
    private Teacher [] teacherEnemy;
    private Item [] roomItem;
    
    private int totalNumberOfEnemies;
    


    public Classroom(int noStudents, int noTeachers, int noItems, String [] studentNames, String [] teacherNames){
        
        studentEnemy = new Student[noStudents];
        
        for (int i=0; i<noStudents; i++){
            studentEnemy[i] = new Student(studentNames[i],50,20);
        }
        
        numberOfStudents=noStudents;
        
        
        teacherEnemy = new Teacher[noTeachers];
        
        for (int j=0; j<noTeachers; j++){
            teacherEnemy[j] = new Teacher(teacherNames[j],60,30);
        }
        
        numberOfTeachers=noTeachers;
        
        
        roomItem = new Item[noItems];
        
        for (int k=0; k<noItems; k++){
            roomItem[k] = new Item();
        }
        
        numberOfItems=noItems;

        
        totalNumberOfEnemies = noStudents + noTeachers;
        
    }


    
    public Student attackStudent(String name){
        return studentEnemy[searchForEnemy(name)];
    }
    
    public void defeatStudent(String name){
        studentEnemy[searchForEnemy(name)].convincedSetter(true);
        
        totalNumberOfEnemies--;
        
        return;
    }
    
    
    public Teacher attackTeacher(String name){
        return teacherEnemy[searchForEnemy(name)];
    }
    
    public void defeatTeacher(String name){
        teacherEnemy[searchForEnemy(name)].convincedSetter(true);
        
        totalNumberOfEnemies--;
        
        return;
    }
    
    
    public Item retrieveItem(){
        boolean itemRetrievable = false;
        
        while((itemRetrievable==false)&&(numberOfItems>0)){
            itemRetrievable = ((roomItem[numberOfItems-1].itemTakenStatusGetter())==false)?true:false;
            
            if(itemRetrievable==false){
                numberOfItems--;
            }
            
        }
        
        Item itemTaken = roomItem[numberOfItems-1];
        
        itemTaken.itemTakenStatusSetter(true);
        
        numberOfItems--;
        
        return itemTaken;
    }
    
    
    public int numberOfEnemies(){
        return totalNumberOfEnemies;
    }
    
    public int roomStudentCount(){
        return numberOfStudents;
    }
    
    public int roomTeacherCount(){
        return numberOfTeachers;
    }    
    
    public int roomItemCount(){
        return numberOfItems;
    }

    
    public int searchForEnemy(String name){
        int index=-1;
        for (int i=0; i<studentEnemy.length; i++){
            if (name.equals(studentEnemy[i].nameGetter())){
                index=i;
            }
        }
        for (int j=0; j<teacherEnemy.length; j++){
            if (name.equals(teacherEnemy[j].nameGetter())){
                index=j;
            }
        }
        return index;
    }
    
    
}
