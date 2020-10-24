import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CreatePlayerGUI
{
    private JFrame frame;
    
    private JLabel page_instructions;
    
    private JTextField text_field;
    
    private JButton submit_name, play_sick, play_not_sick, play_healthy;
    
    private String name;
    private int sick_status;
    
    private JPanel main_section, section2;

    public CreatePlayerGUI(){
        frame = new JFrame();
        frame.setTitle("Create Player");
        frame.setSize(600,350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground( Color.PINK );
        frame.setVisible(true);
        
        main_section = new JPanel();
        
        
        page_instructions = new JLabel("Enter your name:");
        page_instructions.setBackground(Color.PINK);
        page_instructions.setFont(new java.awt.Font("Arial", 1, 20));
        main_section.add(page_instructions);
        
        main_section.add(Box.createRigidArea(new Dimension(0, 15)));
        main_section.setBackground( Color.PINK );
        
        text_field = new JTextField(30);
        text_field.setBackground(Color.WHITE);
        main_section.add(text_field);
        
        submit_name = new JButton("Submit"){
            {
                setSize(120,30);
                setMaximumSize(getSize());
            }
        };
        submit_name.setFont(new java.awt.Font("Arial", 1, 11));
        submit_name.setAlignmentX(Component.CENTER_ALIGNMENT);
        submit_name.addActionListener(new SubmitNameListener());
        //submit_name.setBackground(Color.GREEN);
        submit_name.setSize(100,20);
        main_section.add(submit_name);
        
        
        section2 = new JPanel();
        section2.setLayout(new BoxLayout(section2, BoxLayout.Y_AXIS));
        
        play_sick = new JButton("Sick (EASIER)"){
            {
                setSize(220,30);
            }
        };
        play_sick.setFont(new java.awt.Font("Arial", 1, 18));
        play_sick.setAlignmentX(Component.CENTER_ALIGNMENT);
        play_sick.addActionListener(new PlaySickListener());
        //play_sick.setBackground(Color.GREEN);
        
        play_not_sick = new JButton("Not Sick (HARDER)"){
            {
                setSize(220,30);
            }
        };
        play_not_sick.setFont(new java.awt.Font("Arial", 1, 18));
        play_not_sick.setAlignmentX(Component.CENTER_ALIGNMENT);
        play_not_sick.addActionListener(new PlayNormalListener());
        //play_not_sick.setBackground(Color.ORANGE);
        
        play_healthy = new JButton("Very Healthy (HARDEST)"){
            {
                setSize(220,30);
            }
        };
        play_healthy.setFont(new java.awt.Font("Arial", 1, 18));
        play_healthy.setAlignmentX(Component.CENTER_ALIGNMENT);
        play_healthy.addActionListener(new PlayHealthyListener());
        //play_healthy.setBackground(Color.RED);
        
        section2.add(Box.createRigidArea(new Dimension(0, 35)));
        section2.add(play_sick);
        section2.add(Box.createRigidArea(new Dimension(0, 35)));
        section2.add(play_not_sick);
        section2.add(Box.createRigidArea(new Dimension(0, 35)));
        section2.add(play_healthy);
        section2.add(Box.createRigidArea(new Dimension(0, 35)));
        section2.setBackground( Color.PINK );
        
        frame.add(main_section);
    }
    
    public void createEnvironment(){
        Player player;
        if (sick_status==0){
            player = new SickPlayer(name,100,100,sick_status);
        }
        else if(sick_status==1){
            player = new NormalPlayer(name,100,100,sick_status);
        }
        else if(sick_status==2){
            player = new HealthyPlayer(name,100,100,sick_status);
        }
        else{
            player = new NormalPlayer("Error Occured",100,100,sick_status);
        }
        
        frame.dispose();

        ClassroomGUI crGUI = new ClassroomGUI(player,1);
    }

    class SubmitNameListener implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
           name = text_field.getText();
                   
           main_section.setVisible(false);
           
           frame.add(section2);
           frame.validate();
        }
    }
    class PlaySickListener implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            sick_status=0;
            createEnvironment();
        }
    }
    class PlayNormalListener implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            sick_status=1;
            createEnvironment();
        }
    }
    class PlayHealthyListener implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            sick_status=2;
            createEnvironment();
        }
    }
}

