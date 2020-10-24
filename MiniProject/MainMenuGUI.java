import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.util.ArrayList;
public class MainMenuGUI
{
    
    private JFrame frame;
    
    private JLabel game_title;
    
    private JButton play_game, continue_game, how_to_play, exit_btn;

    private JPanel title_section, main_section;
    
    
    
    public MainMenuGUI(){
        frame = new JFrame();
        frame.setTitle("Main Menu");
        frame.setSize(600,350);
        frame.setLayout(new BorderLayout(8,6));
        frame.setVisible(true);
        frame.getContentPane().setBackground( Color.BLUE );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        title_section = new JPanel();
        title_section.setLayout(new GridLayout(1,0,4,4));
        title_section.setBackground(Color.BLUE);
        
        
        main_section = new JPanel();
        main_section.setLayout(new BoxLayout(main_section, BoxLayout.Y_AXIS));
        main_section.setBackground(Color.GRAY);
        
        game_title = new JLabel("HOME SICK",SwingConstants.CENTER);
        game_title.setFont(new java.awt.Font("Arial", 1, 46));
        game_title.setForeground(Color.WHITE);
        title_section.add(game_title);
        
        main_section.add(Box.createRigidArea(new Dimension(0, 15)));
        
        play_game = new JButton("New game"){
            {
                setSize(120,30);
                setMaximumSize(getSize());
            }
        };
        play_game.setFont(new java.awt.Font("Arial", 1, 11));
        //play_game.setBackground(Color.ORANGE);
        //play_game.setForeground(Color.WHITE);
        play_game.setAlignmentX(Component.CENTER_ALIGNMENT);
        play_game.addActionListener(new NewGameListener());
        main_section.add(play_game);
        
        main_section.add(Box.createRigidArea(new Dimension(0, 20)));
        
        continue_game = new JButton("Continue game"){
            {
                setSize(120,30);
                setMaximumSize(getSize());
            }
        };
        continue_game.setFont(new java.awt.Font("Arial", 1, 11));
        //continue_game.setBackground(Color.ORANGE);
        //continue_game.setForeground(Color.WHITE);
        continue_game.setAlignmentX(Component.CENTER_ALIGNMENT);
        String player_existance = "";
        try{
            player_existance = checkPlayerExistance();
        }
        catch (Exception e){
        }
        if(player_existance==""){
            continue_game.setEnabled(false);
        }
        continue_game.addActionListener(new ContinueGameListener());
        main_section.add(continue_game);
        
        main_section.add(Box.createRigidArea(new Dimension(0,20)));
        
        how_to_play = new JButton("How to play"){
            {
                setSize(120,30);
                setMaximumSize(getSize());
            }
        };
        how_to_play.setFont(new java.awt.Font("Arial", 1, 11));
        //how_to_play.setBackground(Color.ORANGE);
        //how_to_play.setForeground(Color.WHITE);
        how_to_play.setAlignmentX(Component.CENTER_ALIGNMENT);
        how_to_play.addActionListener(new HowToPlayListener());
        main_section.add(how_to_play);
        
        main_section.add(Box.createRigidArea(new Dimension(0,20)));
        
        exit_btn = new JButton("Exit"){
            {
                setSize(120,30);
                setMaximumSize(getSize());
            }
        };
        exit_btn.setFont(new java.awt.Font("Arial", 1, 11));
        //exit_btn.setBackground(Color.ORANGE);
        //exit_btn.setForeground(Color.WHITE);
        exit_btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit_btn.addActionListener(new ExitListener());
        main_section.add(exit_btn);
        
        
        frame.add("North",title_section);
        frame.add("Center",main_section);
        
        frame.validate();
    }
    
    public String checkPlayerExistance() throws IOException{
        String fileData = "";
        try{
            BufferedReader inputStream = new BufferedReader(new FileReader("player_info.txt"));
            fileData = inputStream.readLine();
            inputStream.close();
        }
        catch(Exception e){
        }
        return fileData;
    }

    
    class NewGameListener implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            File player_file = new File("player_info.txt");
            try{
                player_file.delete();
            }
            catch(Exception e){
                System.out.println("Player unsuccessfully deleted.");           
            }
            frame.dispose();
            CreatePlayerGUI cpGUI = new CreatePlayerGUI();
        }
    }
    class ContinueGameListener implements ActionListener{
        public void actionPerformed (ActionEvent evt) {
            ArrayList<String> fileData = new ArrayList<String>();
            try{
                fileData=(checkPlayerExist());
            }
            catch(Exception e){
                fileData.add("File not found.");
            }
            
            String player_name = fileData.get(0);
            int player_classroom = Integer.parseInt(fileData.get(1));
            int player_sick_status = Integer.parseInt(fileData.get(2));
            
            Player player;
            if (player_sick_status==0){
                player = new SickPlayer(player_name,100,100,player_sick_status);
            }
            else if(player_sick_status==1){
                player = new NormalPlayer(player_name,100,100,player_sick_status);
            }
            else if(player_sick_status==2){
                player = new HealthyPlayer(player_name,100,100,player_sick_status);
            }
            else{
                player = new NormalPlayer("Error Occured",100,100,player_sick_status);
            }
            
            ClassroomGUI crGUI = new ClassroomGUI(player,player_classroom);
            frame.dispose();
        }
        public ArrayList checkPlayerExist() throws IOException{
            ArrayList<String> fileData = new ArrayList<String>();
            String line = "line";
            BufferedReader inputStream = new BufferedReader(new FileReader("player_info.txt"));
            while(line!=null){
                line=inputStream.readLine();
                if(line!=null){
                    fileData.add(line);
                }
            }
            inputStream.close();
            return fileData;
        }
    }
    class HowToPlayListener implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            HowToPlayGUI htpGUI = new HowToPlayGUI();
        }
    }
    class ExitListener implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            frame.dispose();
            System.exit(0);
        }
    }
}
