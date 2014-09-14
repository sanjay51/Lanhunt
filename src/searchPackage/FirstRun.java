package searchPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FirstRun extends JFrame {

	private JPanel contentPane,pane1,pane2,pane3;
	mainWindow m;
	int step=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstRun frame = new FirstRun();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FirstRun() {
		setTitle("LanHunt 1.4 First Run Wizard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		contentPane.setBackground(new Color(102, 0, 102));
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		contentPane.setLayout(null);
		contentPane.setBackground(Gui.getThemeColor());
		
		final JLabel lbl1 = new JLabel("1");
		final JLabel lbl11 = new JLabel("Set IP range");
		final JLabel lbl2 = new JLabel("2");
		final JLabel lbl22 = new JLabel("Create DB");
		final JLabel lbl3 = new JLabel("3");
		final JLabel lbl33 = new JLabel("Finalize!");

		final JButton btnGo = new JButton("Go");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch(step){
				case 0:
					SettingsWindow s=new SettingsWindow();
					s.initializeIpRange();
					s.setVisible(true);
					
					btnGo.setText("Step 2");
					step++;
					lbl1.setForeground(Gui.getThemeColorLight());
					lbl11.setForeground(Gui.getThemeColorLight());
					break;
					
				case 1:
					DBUpdater u=new DBUpdater();
					u.startUpdate();
					u.setVisible(true);
					
					btnGo.setText("Finish!");
					step++;
					lbl2.setForeground(Gui.getThemeColorLight());
					lbl22.setForeground(Gui.getThemeColorLight());
					break;
					
				case 2:
				setVisible(false);
				m.frmLanhuntVersion.setVisible(true);
				}
				
			}
		});
		//btnNewButton.setBackground(new Color(102, 255, 0));
		btnGo.setBounds(227, 203, 140, 47);
		contentPane.add(btnGo);
		
		JLabel lblWelcome1 = new JLabel("Welcome to the LanHunt 1.4 First Run Wizard.");
		lblWelcome1.setForeground(new Color(255, 255, 255));
		lblWelcome1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblWelcome1.setBounds(174, 31, 266, 14);
		contentPane.add(lblWelcome1);
		
		JLabel lblFollowTheseThree = new JLabel("Three steps, and you can start Hunting!");
		lblFollowTheseThree.setForeground(new Color(255, 255, 255));
		lblFollowTheseThree.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFollowTheseThree.setBounds(174, 62, 266, 30);
		contentPane.add(lblFollowTheseThree);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setForeground(new Color(255, 255, 255));
		lblIcon.setFont(new Font("Arial", Font.PLAIN, 12));
		lblIcon.setIcon(new ImageIcon(FirstRun2.class.getResource("/searchPackage/xml/size2.png")));
		lblIcon.setBounds(10, 31, 154, 187);
		contentPane.add(lblIcon);
		
		//JLabel lbl1 = new JLabel("1");
		//lblNewLabel.setBackground(new Color(0, 204, 255));
		lbl1.setForeground(new Color(255, 255, 255));
		lbl1.setFont(new Font("Arial", Font.BOLD, 17));
		lbl1.setBounds(174, 114, 42, 41);
		contentPane.add(lbl1);
		
		//JLabel lbl2 = new JLabel("2");
		lbl2.setBackground(new Color(0, 204, 255));
		lbl2.setForeground(new Color(255, 255, 255));
		lbl2.setFont(new Font("Arial", Font.BOLD, 17));
		lbl2.setBounds(287, 114, 42, 41);
		contentPane.add(lbl2);
		
		//JLabel lbl3 = new JLabel("3");
		lbl3.setBackground(new Color(0, 204, 255));
		lbl3.setForeground(new Color(255, 255, 255));
		lbl3.setFont(new Font("Arial", Font.BOLD, 17));
		lbl3.setBounds(398, 114, 42, 41);
		contentPane.add(lbl3);
		
		//JLabel lbl11 = new JLabel("Set IP range");
		lbl11.setForeground(new Color(255, 255, 255));
		lbl11.setFont(new Font("Arial", Font.PLAIN, 12));
		lbl11.setBounds(161, 158, 74, 14);
		contentPane.add(lbl11);
		
		//JLabel lbl22 = new JLabel("Create DB");
		lbl22.setForeground(new Color(255, 255, 255));
		lbl22.setFont(new Font("Arial", Font.PLAIN, 12));
		lbl22.setBounds(275, 158, 74, 14);
		contentPane.add(lbl22);
		
		//JLabel lbl33 = new JLabel("Finalize!");
		lbl33.setForeground(new Color(255, 255, 255));
		lbl33.setFont(new Font("Arial", Font.PLAIN, 12));
		lbl33.setBounds(384, 158, 74, 14);
		contentPane.add(lbl33);
	}
	public void setMainWindow(mainWindow ma){
		this.m=ma;
	}
}
