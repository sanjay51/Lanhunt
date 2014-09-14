package searchPackage;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Dylan Kiss
 */

public class Demo {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame myFrame = new JFrame("Demo");
                    JPanel sideBar = new JPanel();
                    JPanel centerPanel = new JPanel();
                    JButton btn=new JButton("Add New");
                    centerPanel.add(btn);
                    
                    
                    final JPanel buttonContainer = new JPanel();
                    JButton myButton = null;


                    sideBar.setLayout(new BorderLayout(0, 0));

                    JScrollPane scrollPane = new JScrollPane(buttonContainer);

                    sideBar.add(scrollPane);

                    for (int i = 0; i < 20; i++) {
                        buttonContainer.setLayout(new GridLayout(20, 1, 0, 0));
                        myButton = new JButton("This is my button nr. " + i);
                        buttonContainer.add(myButton);
                    }
                    
                    btn.addActionListener(new ActionListener() {
            			public void actionPerformed(ActionEvent arg0) {
            				buttonContainer.add(new JButton("Sppp"));
            			}
            		});
                    
                    myFrame.getContentPane().add(sideBar, BorderLayout.WEST);
                    myFrame.getContentPane().add(centerPanel, BorderLayout.CENTER);

                    myFrame.setLocationByPlatform(true);
                    myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    myFrame.pack();
                    myFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}