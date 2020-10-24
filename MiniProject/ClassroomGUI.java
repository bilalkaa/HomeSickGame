import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.io.*;

public class ClassroomGUI
{
    private Player player;
    private Classroom classroom;
    
    private int no_students, no_teachers, no_items, room_number;
    private String current_enemy_move;
    private String [] student_names;
    private String [] teacher_names;
    
    private JFrame main_frame;
    
    private JLabel room_title;
    
    private JButton button,item,save,exit;
    
    private JButton [] student_buttons;
    private JButton [] teacher_buttons;
    
    private JPanel frame, student_section, teacher_section,southern_section,save_and_exit;

    private FightingGUI fight;
    
    public ClassroomGUI(Player p, int room_no){
        player=p;
        room_number=room_no;
        no_teachers = (room_no==1)?1:(room_no==2)?2:2;
        no_students = (room_no==1)?2:(room_no==2)?3:4;
        no_items = (room_no==1)?2:3;
        
        String [] studentNames = {"Mob", "Reigen", "Saitama", "Genos", "Jerry"};
        String [] teacherNames = {"Mr Morty", "Ms Rick", "Ms Sanches", "Mr Smith", "Ms Holt"};
        
        student_names=studentNames;
        teacher_names=teacherNames;
        
        classroom = new Classroom(no_students,no_teachers,no_items,studentNames,teacherNames);
        
        main_frame = new JFrame();
        main_frame.setSize(880,580);
        main_frame.setTitle("Classroom");
        main_frame.setVisible(true);
        main_frame.getContentPane().setBackground( Color.PINK );
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame = new JPanel();
        frame.setLayout(new BorderLayout(8,6));
        frame.setBackground( Color.PINK );

        room_title = new JLabel("Classroom Number " +room_no);
        room_title.setFont(new java.awt.Font("Arial", 1, 22));
        room_title.setBackground(Color.ORANGE);
        frame.add("North",room_title);
        
        student_section = new JPanel();
        student_section.setLayout(new GridLayout(2,3,8,8));
        student_section.setBackground(Color.YELLOW);
        
        student_buttons = new JButton[no_students];
        
        for(int i=0; i<no_students;i++){
            student_buttons[i] = new JButton(""+studentNames[i]);
            if (i==0){
                student_buttons[i].addActionListener(new FightFriendlyListener());
                //student_buttons[i].setBackground(Color.MAGENTA);
                //student_buttons[i].setForeground(Color.WHITE);
            }
            else{
                student_buttons[i].addActionListener(new FightStudentListener());
                //student_buttons[i].setBackground(Color.ORANGE);
                //student_buttons[i].setForeground(Color.WHITE);
            }
            student_buttons[i].setFont(new java.awt.Font("Agency FB", 1, 20));
            student_section.add(student_buttons[i]);
        }
        student_section.setBorder(BorderFactory.createMatteBorder(6,6,6,6,Color.YELLOW));
        frame.add("Center",student_section);
        
        
        
        southern_section = new JPanel();
        southern_section.setLayout(new GridLayout(2,1,8,8));
        southern_section.setBackground(Color.PINK);
        
        teacher_section = new JPanel();
        teacher_section.setLayout(new GridLayout(1,3,8,8));
        teacher_section.setBackground(Color.PINK);
        
        teacher_buttons = new JButton[no_teachers];
        
        for(int i=0; i<no_teachers;i++){
            int j = i+1;
            teacher_buttons[i] = new JButton(""+teacherNames[i]);
            teacher_buttons[i].addActionListener(new FightTeacherListener());
            //teacher_buttons[i].setBackground(Color.CYAN);
            //teacher_buttons[i].setForeground(Color.BLACK);
            teacher_buttons[i].setFont(new java.awt.Font("Eras Demi ITC", 0, 20));
            teacher_section.add(teacher_buttons[i]);
        }
        teacher_section.setBorder(BorderFactory.createMatteBorder(8,8,8,8,Color.PINK));
        
        southern_section.add(teacher_section);
        
        save_and_exit = new JPanel();
        save_and_exit.setLayout(new GridLayout(1,2,8,8));
        save_and_exit.setBackground(Color.PINK);
        
        save = new JButton("Save Room");
        save.addActionListener(new SaveListener());
        //save.setBackground(Color.GREEN);
        //save.setForeground(Color.WHITE);
        save.setFont(new java.awt.Font("Arial",1,20));
        
        save_and_exit.add(save);
        
        exit = new JButton("Exit");
        exit.addActionListener(new ExitListener());
        //exit.setBackground(Color.RED);
        //exit.setForeground(Color.WHITE);
        exit.setFont(new java.awt.Font("Arial",1,20));
        
        save_and_exit.add(exit);
        
        southern_section.add(save_and_exit);
        
        frame.add ("South", southern_section);
        
        item = new JButton("Item");
        //item.setBackground(Color.MAGENTA);
        //item.setForeground(Color.WHITE);
        item.setFont(new java.awt.Font("Arial", 1, 20));
        item.addActionListener(new PickUpItemListener());
        
        frame.add("West",item);
        frame.add("East",(Box.createRigidArea(new Dimension(10, 0))));
        
        frame.validate();
        main_frame.add(frame);
        main_frame.validate();
    }

    class FightStudentListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String enemy_name=e.getActionCommand();
            int index=0;
            
            for(int i=0; i<no_students;i++){
                if(student_names[i].equals(enemy_name)){
                    index=i;
                }
            }
            
            
            student_buttons[index].setEnabled(false);
            frame.setVisible(false);
            Student enemy = classroom.attackStudent(enemy_name);
            FightingGUI fight = new FightingGUI("You challenged student "+enemy.nameGetter()+" to a convincing duel.", player, enemy);
                   
            
        }
    }
    
    class FightFriendlyListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String friend_name=e.getActionCommand();
            
            int index=0;
            for(int i=0; i<no_students;i++){
                if(student_names[i].equals(friend_name)){
                    index=i;
                }
            }
            
            FriendlyPlayer fplayer = new FriendlyPlayer(friend_name, 100, 100);
                       
            player.staminaSetter(fplayer.friendHelpGetter());
            player.staminaSetter(fplayer.attackPowerGetter(""));
                       
            classroom.defeatStudent(friend_name);
            if (classroom.numberOfEnemies()==0){
                main_frame.dispose();
                if (room_number>=3){
                    GameOverGUI goGUI = new GameOverGUI(true);
                }
                else{
                    player.cpointSetter(100);
                    player.staminaSetter(100);
                    ClassroomGUI crGUI = new ClassroomGUI(player,room_number+1);
                }
            }
            else{
                FriendHelpGUI fhGUI = new FriendHelpGUI(friend_name);
            }
           
            
            student_buttons[index].setEnabled(false);
            student_buttons[index].setBackground(Color.MAGENTA);
            
            
        }
    }
    
    class FightTeacherListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String enemy_name=e.getActionCommand();
            int index=-1;
            
            for(int i=0; i<no_students;i++){
                if(teacher_names[i].equals(enemy_name)){
                    index=i;
                }
            }
            
            
            teacher_buttons[index].setEnabled(false);
            frame.setVisible(false);
            Teacher enemy = classroom.attackTeacher(enemy_name);
            fight = new FightingGUI("You challenged student "+enemy.nameGetter()+" to a convincing duel.", player, enemy);
            
        }
    }
    
    public class PickUpItemListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Item pickedUpItem = classroom.retrieveItem();
            ItemType pick_item = pickedUpItem.itemGetter();
            
            int itemValue = pickedUpItem.itemValueGetter(pick_item);
            
            PickUpStatusGUI puGUI = new PickUpStatusGUI(itemValue);
            
            player.cpointSetter(itemValue);
            
            
            if(classroom.roomItemCount()<=0){
                item.setEnabled(false);
            }
        }
    }
    
    public class SaveListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                storeInformation();
            }
            catch(Exception er){
                System.out.println("Save Error Occured");
            }
            save.setEnabled(false);; 
        }
        
        public void storeInformation() throws IOException{
            PrintWriter outputStream = new PrintWriter(new FileWriter("player_info.txt"));
            outputStream.println(player.nameGetter());
            outputStream.println(room_number);
            outputStream.println(player.isSickGetter());
            outputStream.close();
        }
    }
    
    public class ExitListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            main_frame.dispose();
            System.exit(0);
        }
    }
    
    
    
    public class FightingGUI implements ActionListener
    {   
        
        private JLabel fight_data, player_name, player_CP, player_stamina, enemy_name, enemy_CP, enemy_stamina, game_info, center_image;
        
        private JButton move1,move2,move3,move4;
        
        private String currentMove;
        
        private JPanel frame1, movesPanel,northernPanel,westernPanel,easternPanel,southernPanel,centerPanel,infoPanel;
        
        
        private Player player;
        private NonPlayer enemy;
        
        private Timer timer,t1,t2,t3;
        private int loopCounter;
       
        public FightingGUI(String fight_dataa, Player main_player, NonPlayer main_enemy){
            
            main_frame.setTitle("The Duel");
            
            frame1 = new JPanel();
            frame1.setLayout(new BorderLayout(8,6));
            frame1.setVisible(true);
            frame1.setBackground( Color.PINK );
            
            
            player=main_player;
            enemy=main_enemy;
            
            fight_data = new JLabel(fight_dataa);
            fight_data.setFont(new java.awt.Font("Arial", 1, 14));
            northernPanel = new JPanel();
            northernPanel.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.BLACK));
            northernPanel.setBackground(Color.ORANGE);
            northernPanel.add(fight_data,SwingConstants.CENTER);
            frame1.add("North",northernPanel);
            
            player_name = new JLabel(" "+player.nameGetter());
            player_CP = new JLabel(" CP: " + player.cpointGetter()+"          ");
            player_stamina = new JLabel(" Stamina: " + player.staminaGetter());
            westernPanel = new JPanel();
            westernPanel.setLayout(new GridLayout(3,0,3,3));
            westernPanel.add(player_name);
            westernPanel.add(player_CP);
            westernPanel.add(player_stamina);
            westernPanel.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.GREEN));
            westernPanel.setBackground(Color.WHITE);
            
            JPanel westP = new JPanel();
            westP.setLayout(new GridLayout(4,0,3,3));
            westP.add(new JLabel(""));
            westP.add(new JLabel(""));
            westP.add(new JLabel(""));
            westP.add(westernPanel);
            westP.setBackground(Color.PINK);
            frame1.add("West",westP);
            
            
            enemy_name = new JLabel(" Opponent " + enemy.nameGetter()+ " ");
            enemy_CP = new JLabel(" CP: " + enemy.cpointGetter());
            enemy_stamina = new JLabel(" Stamina: " + enemy.cpointGetter());
            easternPanel = new JPanel();
            easternPanel.setLayout(new GridLayout(3,0,3,3));
            easternPanel.add(enemy_name);
            easternPanel.add(enemy_CP);
            easternPanel.add(enemy_stamina);
            easternPanel.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.RED));
            easternPanel.setBackground(Color.WHITE);
    
            
            JPanel eastP = new JPanel();
            eastP.setLayout(new GridLayout(4,0,3,3));
            eastP.add(new JLabel(""));
            eastP.add(new JLabel(""));
            eastP.add(new JLabel(""));
            eastP.add(easternPanel);
            eastP.setBackground(Color.PINK);
            frame1.add("East",eastP);
            
            
            center_image = new JLabel();
            center_image.setIcon(new ImageIcon("default.jpg"));
            centerPanel = new JPanel();
            centerPanel.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.BLACK));
            centerPanel.setBackground(Color.WHITE);
            centerPanel.add(center_image);
            frame1.add("Center",centerPanel);
            
            
            movesPanel = new JPanel();
            movesPanel.setLayout(new GridLayout(2,2,4,4));
            
            move1 = new JButton("Sore Throat");
            move1.addActionListener(this);
            movesPanel.add(move1);
    
            
            move2 = new JButton("Cough");
            move2.addActionListener(this);
            movesPanel.add(move2);
            
            move3 = new JButton("Dizzy Walk");
            move3.addActionListener(this);
            movesPanel.add(move3);
            
            move4 = new JButton("Rest");
            move4.addActionListener(this);
            movesPanel.add(move4);
            
            movesPanel.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.BLACK));
            movesPanel.setBackground(Color.BLACK);
            
            southernPanel = new JPanel();
            southernPanel.setLayout(new GridLayout(2,0,4,4));
            southernPanel.add(movesPanel);
            
            game_info= new JLabel("Your duel has started!");
            game_info.setFont(new java.awt.Font("Arial", 2, 24));
            
            infoPanel = new JPanel();
            infoPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
            infoPanel.setBackground(Color.WHITE);
            infoPanel.add(game_info,JLabel.CENTER);
            southernPanel.add(infoPanel,JLabel.CENTER);
            southernPanel.setBackground(Color.PINK);
            frame1.add("South",southernPanel);
            
            currentMove="";loopCounter=0;
            
            
            main_frame.add(frame1);
            main_frame.validate();
        }
    
        public void actionPerformed(ActionEvent e){
            movesPanel.setVisible(false);
            
            currentMove=e.getActionCommand();
            
            boolean isMovePossible=playerPicksMove(currentMove);
    
            
            if (isMovePossible==true){
                player.performAttack(currentMove,enemy);
                
                if(currentMove.equals("Rest")){
                    game_info.setText("You restored your stamina!");
                    player_stamina.setText(" Stamina: "+player.staminaGetter()+" ");
                }
                else{
                    game_info.setText("Your move did " + player.attackPowerGetter(currentMove) + " against " + enemy.nameGetter()+"!");            
                    enemy_CP.setText(" CP: "+enemy.cpointGetter()+" ");
                    player_stamina.setText(" Stamina: "+player.staminaGetter()+" ");
                    center_image.setIcon(new ImageIcon("playermove.jpg"));
                }  
                       
                updateCStatus();
                
                if (enemy.convincedGetter()==false){
                    current_enemy_move=enemyPicksMove();
                    enemy.performAttack(current_enemy_move,player);
                }
                
                updateCStatus();
                
                t1 = new Timer(1500, new AbstractAction(){
                    public void actionPerformed(ActionEvent ae){
                        if (enemy.convincedGetter()==false){
                            game_info.setText(enemy.nameGetter() +" used a move! They used " + current_enemy_move + ".");
                            player_CP.setText(" CP: "+player.cpointGetter()+" ");
                            enemy_stamina.setText(" Stamina: "+enemy.staminaGetter()+" ");
                            center_image.setIcon(new ImageIcon("enemymove.jpg"));
                            t1.stop();
                            updateCStatus();
                            
                            if(player.caughtGetter()==false){
                                movesPanel.setVisible(true);
                            }
                        }
                    }                        
                    }
                    );
                //t1.start();
                
                updateCStatus();
            }
            else{
                movesPanel.setVisible(true);
            }
            
            updateCStatus();
            t2 = new Timer(3000, new AbstractAction(){
                public void actionPerformed(ActionEvent e){
                    if (player.caughtGetter()==true||enemy.convincedGetter()==true){
                        game_info.setText("A victor has been decided.");
                        t3 = new Timer(2000, new AbstractAction(){
                            public void actionPerformed(ActionEvent ae){
                                main_frame.remove(frame1);
                                
                                if(enemy.convincedGetter()==true){
                                    if(enemy instanceof Student){
                                        classroom.defeatStudent(enemy.nameGetter());
                                    }
                                    else if(enemy instanceof Teacher){
                                        classroom.defeatTeacher(enemy.nameGetter());
                                    }
                                    if (classroom.numberOfEnemies()==0){
                                        main_frame.dispose();
                                        if (room_number>=3){
                                            GameOverGUI goGUI = new GameOverGUI(true);
                                        }
                                        else{
                                            player.cpointSetter(100);
                                            player.staminaSetter(100);
                                            ClassroomGUI crGUI = new ClassroomGUI(player,room_number+1);
                                        }
                                    }
                                    else{
                                        frame.setVisible(true);
                                    }
                                }
                                else{
                                    main_frame.dispose();
                                    GameOverGUI goGUI = new GameOverGUI(false);
                                }
        
                                t3.stop();
                            }
                        });
                        t3.start();
                    
                    }
                    t2.stop();
                }
            });
            
            try{
                t1.start();}
            catch(Exception ae){
            }
            if (player.caughtGetter()==true||enemy.convincedGetter()==true){
                t2.start();
            }
        }
        
        
        public boolean playerPicksMove(String move){
            boolean isStaminaPossible=false;
            boolean doesMoveExist=false;
            boolean isMovePossible=false;
            
    
            int CPAfter = (player.staminaGetter())+(player.attackPowerGetter(move));
                
            isMovePossible = (CPAfter>=0)?true:false;
            doesMoveExist = player.attackExist(move);
            
            
                
            if ((isMovePossible==false)||(doesMoveExist==false)){
                game_info.setText("Move failed! Pick a move that doesn't use as much Stamina or Rest.");
            }
            else{
                isMovePossible=true;
            }
    
            
            return isMovePossible;
        }
        
        
        public String enemyPicksMove(){
            boolean isMovePossible=false;
            String move="";
            
            if (enemy.staminaGetter()<=9){
                move="Rest";
            }
            else{
                while(isMovePossible==false){
                    int CPAfter=0;
                    if (enemy instanceof Student){
                        move = ((new Random().nextDouble()) <= 0.25)?"Make Joke":((new Random().nextDouble()) <= 0.90)?"Questioning":"Rest";
                        
                        Student currentEnemy = (Student) enemy;
                        
                        CPAfter = (currentEnemy.staminaGetter())+(currentEnemy.attackPowerGetter(move));
                    }
                    else if (enemy instanceof Teacher){
                        move = ((new Random().nextDouble()) <= 0.20)?"Intense Glare":((new Random().nextDouble()) <= 0.60)?"Make Joke":((new Random().nextDouble()) <= 0.90)?"Questioning":"Rest";
                    
                        Teacher currentEnemy = (Teacher) enemy;
                        
                        CPAfter = (currentEnemy.staminaGetter())+(currentEnemy.attackPowerGetter(move));
                    }
                    isMovePossible = (CPAfter>=0)?true:false;
                }
            }
            
            return move;
        }
        
        public void updateCStatus(){
            if (player.cpointGetter()<=0){
                player.caughtSetter(true);
            }
            else if(enemy.cpointGetter()<=0){
                enemy.convincedSetter(true);
            }
        }
        
    }
    
    
    
}
