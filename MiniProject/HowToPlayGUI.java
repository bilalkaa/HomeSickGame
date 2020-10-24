import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HowToPlayGUI
{
    private JFrame frame;
    
    private JLabel how_to_play, m1, m2, m3, m4, m5, m6, m7;
    
    private JButton close;

    private JPanel main_section;

    public HowToPlayGUI(){
        frame = new JFrame();
        frame.setTitle("How To Play");
        frame.setSize(850,500);
        frame.getContentPane().setBackground( Color.BLUE );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(8,8));
        frame.setVisible(true);
        
        
        how_to_play = new JLabel("How to play...");
        how_to_play.setFont(new java.awt.Font("Arial", 1, 22));
        how_to_play.setForeground(Color.WHITE);
        frame.add("North",how_to_play);
        
        main_section = new JPanel();
        main_section.setLayout(new BoxLayout(main_section, BoxLayout.Y_AXIS));
        main_section.setBackground(Color.PINK);
        main_section.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.PINK));
        

        for (int i=0; i<8; i++){
            m1 = new JLabel("");
           
            if(i==0){
                m1.setText("This is an Adventure Game where you're a student at school who wants to go home.");
            }
            else if(i==1){
                m1.setText("Your objective is to convince all the teachers and student's that you're sick.");
            }
            else if(i==2){
                m1.setText("Once all teachers and students in all three rooms are convinced, you win!");
            }
            else if(i==3){
                m1.setText("CP acts as health points and stamina allows you use moves.");
            }
            else if(i==4){
                m1.setText("Moves cost different amount of stamina.");
            }
            else if(i==5){
                m1.setText("Use your moves to convince the enemy that you're sick, taking down their CP.");
            }
            else if(i==6){
                m1.setText("It plays like Pokemon (if that's any help)!");
            }
            else if(i==7){
                m1.setText("Take all opponents CP down to 0 to win!");
            }
            
            m1.setForeground(Color.MAGENTA);
            m1.setFont(new java.awt.Font("Arial", 1, 14));
            main_section.add(m1);
            main_section.add(Box.createRigidArea(new Dimension(0, 20)));
        }
        
        
        frame.add("Center",main_section);
        frame.add("West",Box.createRigidArea(new Dimension(0, 20)));
        frame.add("East",Box.createRigidArea(new Dimension(0, 20)));
        
        close = new JButton("Close");
        //close.setBackground(Color.RED);
        //close.setForeground(Color.WHITE);
        close.addActionListener(new ExitListener());
        
        frame.add("South", close);
    }

    class ExitListener implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            frame.dispose();
        }
    }
    
}
