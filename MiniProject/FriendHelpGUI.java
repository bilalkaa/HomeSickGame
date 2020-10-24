import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.io.*;
public class FriendHelpGUI
{
    private JFrame frame;
    
    private JLabel top_message, item_message;
    
    private JButton close;
    
    public FriendHelpGUI(String name){
        frame = new JFrame();
        frame.setTitle("Friend Helped");
        frame.setSize(600,350);
        frame.setLayout(new BorderLayout(8,6));
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.PINK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        top_message = new JLabel("Your friend helped you!");
        top_message.setFont(new java.awt.Font("Arial",0,14));
        top_message.setBackground(Color.ORANGE);
        top_message.setForeground(Color.BLACK);
        frame.add("North",top_message);
        
        item_message = new JLabel();
        item_message.setText("Your friend "+name+" cheered you on. 20 Stamina added and 5CP!");
        item_message.setForeground(Color.GREEN);
        item_message.setFont(new java.awt.Font("Arial", 1, 20));
        item_message.setBackground(Color.PINK);
        frame.add("Center",item_message);
        
        close = new JButton("Close");
        close.setFont(new java.awt.Font("Arial",0,14));
        //close.setBackground(Color.RED);
        //close.setForeground(Color.WHITE);
        close.addActionListener(new CloseListener());
        frame.add("South",close);
    }
    
    public class CloseListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            frame.dispose();                          
        }
    }
    
}
