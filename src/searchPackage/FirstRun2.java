package searchPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;

public class FirstRun2{

	private JFrame frmWelcomeLanhunt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstRun2 window = new FirstRun2();
					window.frmWelcomeLanhunt.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FirstRun2() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWelcomeLanhunt = new JFrame();
		frmWelcomeLanhunt.getContentPane().setBackground(new Color(102, 0, 102));
		frmWelcomeLanhunt.setTitle("Welcome - LanHunt 1.4");
		frmWelcomeLanhunt.setBounds(100, 100, 484, 300);
		frmWelcomeLanhunt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWelcomeLanhunt.getContentPane().setLayout(null);
		frmWelcomeLanhunt.getContentPane().setBackground(Gui.getThemeColor());
		
		JButton btnNewButton = new JButton("Go");
		//btnNewButton.setBackground(new Color(102, 255, 0));
		btnNewButton.setBounds(234, 203, 140, 47);
		frmWelcomeLanhunt.getContentPane().add(btnNewButton);
		
		JLabel lblWelcome1 = new JLabel("Welcome to the LanHunt 1.4 First Run Wizard.");
		lblWelcome1.setForeground(new Color(255, 255, 255));
		lblWelcome1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblWelcome1.setBounds(174, 31, 266, 14);
		frmWelcomeLanhunt.getContentPane().add(lblWelcome1);
		
		JLabel lblFollowTheseThree = new JLabel("Three steps, and you can start Hunting!");
		lblFollowTheseThree.setForeground(new Color(255, 255, 255));
		lblFollowTheseThree.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFollowTheseThree.setBounds(174, 62, 266, 30);
		frmWelcomeLanhunt.getContentPane().add(lblFollowTheseThree);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setForeground(new Color(255, 255, 255));
		lblIcon.setFont(new Font("Arial", Font.PLAIN, 12));
		lblIcon.setIcon(new ImageIcon(FirstRun2.class.getResource("/searchPackage/xml/size2.png")));
		lblIcon.setBounds(10, 31, 154, 187);
		frmWelcomeLanhunt.getContentPane().add(lblIcon);
		
		JLabel lblNewLabel = new JLabel("1");
		//lblNewLabel.setBackground(new Color(0, 204, 255));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel.setBounds(174, 114, 42, 41);
		frmWelcomeLanhunt.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("2");
		label.setBackground(new Color(0, 204, 255));
		label.setForeground(new Color(255, 255, 255));
		label.setFont(new Font("Arial", Font.BOLD, 17));
		label.setBounds(287, 114, 42, 41);
		frmWelcomeLanhunt.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("3");
		label_1.setBackground(new Color(0, 204, 255));
		label_1.setForeground(new Color(255, 255, 255));
		label_1.setFont(new Font("Arial", Font.BOLD, 17));
		label_1.setBounds(398, 114, 42, 41);
		frmWelcomeLanhunt.getContentPane().add(label_1);
		
		JLabel lblSetIpRange = new JLabel("Set IP range");
		lblSetIpRange.setForeground(new Color(255, 255, 255));
		lblSetIpRange.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSetIpRange.setBounds(161, 158, 74, 14);
		frmWelcomeLanhunt.getContentPane().add(lblSetIpRange);
		
		JLabel lblCreateDb = new JLabel("Create DB");
		lblCreateDb.setForeground(new Color(255, 255, 255));
		lblCreateDb.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCreateDb.setBounds(275, 158, 74, 14);
		frmWelcomeLanhunt.getContentPane().add(lblCreateDb);
		
		JLabel lblFinalize = new JLabel("Finalize!");
		lblFinalize.setForeground(new Color(255, 255, 255));
		lblFinalize.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFinalize.setBounds(384, 158, 74, 14);
		frmWelcomeLanhunt.getContentPane().add(lblFinalize);
	}
}
