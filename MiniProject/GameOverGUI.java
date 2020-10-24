import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.io.*;
public class GameOverGUI
{
    
    private JFrame frame;
    
    private JLabel endgame_message;
    
    private JButton play_again, quit;
    
    private JPanel main_section;

    /**
     * Constructor for objects of class GameOverGUI
     */
    public GameOverGUI(boolean win_lose){
        frame = new JFrame();
        frame.setTitle("GAME OVER");
        frame.setSize(600,350);
        frame.setLayout(new BorderLayout(8,6));
        frame.setVisible(true);
        frame.getContentPane().setBackground( Color.CYAN );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        main_section = new JPanel();
        main_section.setLayout(new BoxLayout(main_section, BoxLayout.Y_AXIS));
        main_section.setBackground(Color.CYAN);
        
        endgame_message = new JLabel("");
        if (win_lose){
            endgame_message.setText("YOU CONVINCED EVERYONE YOU'RE SICK! NICE.");
        }
        else{
            endgame_message.setText("You were caught... too bad!");
        }
        endgame_message.setFont(new java.awt.Font("Arial", 1, 20));
        endgame_message.setForeground(Color.MAGENTA);
        frame.add("North",endgame_message);
        
        main_section.add(Box.createRigidArea(new Dimension(0,20)));
        
        play_again = new JButton("Main Menu"){
            {
                setSize(220,30);
                setMaximumSize(getSize());
            }
        };
        play_again.setFont(new java.awt.Font("Arial", 1, 14));
        //play_again.setBackground(Color.GREEN);
        //play_again.setForeground(Color.WHITE);
        play_again.setAlignmentX(Component.CENTER_ALIGNMENT);
        play_again.addActionListener(new PlayAgainListener());
        main_section.add(play_again);
        
        main_section.add(Box.createRigidArea(new Dimension(0,20)));
        
        quit = new JButton("Quit"){
            {
                setSize(220,30);
                setMaximumSize(getSize());
            }
        };
        quit.setFont(new java.awt.Font("Arial", 1, 14));
        //quit.setBackground(Color.RED);
        //quit.setForeground(Color.WHITE);
        quit.setAlignmentX(Component.CENTER_ALIGNMENT);
        quit.addActionListener(new QuitListener());
        main_section.add(quit);
        
        frame.add("Center",main_section);
        
        frame.validate();
                
    }
    

    class PlayAgainListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            MainMenuGUI mmGUI = new MainMenuGUI();
        }
    }

    class QuitListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            System.exit(0);
        }
    }
}
