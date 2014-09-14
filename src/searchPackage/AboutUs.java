package searchPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Toolkit;
import javax.swing.JEditorPane;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class AboutUs extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutUs frame = new AboutUs();
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
	public AboutUs() {
		setResizable(false);
		setTitle("About us - LanHunt v"+Constants.getVersion()+"");
		setIconImage(Gui.getIconImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 413, 426);
		contentPane = new JPanel();
		contentPane.setBackground(Gui.getThemeColor());
		//contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLanSearch = new JLabel("<html><u>LanHunt "+Constants.getVersion()+"</u></html>");
		lblLanSearch.setForeground(new Color(255, 255, 255));
		lblLanSearch.setIcon(new ImageIcon(AboutUs.class.getResource("/searchPackage/xml/size3.png")));
		lblLanSearch.setBackground(Color.CYAN);
		lblLanSearch.setFont(new Font("Calibri", Font.BOLD, 28));
		lblLanSearch.setBounds(10, 11, 302, 82);
		contentPane.add(lblLanSearch);
		
		JTextArea txtrDevelopedBySanjay = new JTextArea();
		txtrDevelopedBySanjay.setWrapStyleWord(true);
		txtrDevelopedBySanjay.setEditable(false);
		txtrDevelopedBySanjay.setForeground(new Color(255, 255, 255));
		txtrDevelopedBySanjay.setFont(new Font("Monospaced", Font.BOLD, 13));
		txtrDevelopedBySanjay.setBackground(new Color(0, 0, 153));
		txtrDevelopedBySanjay.setText("Developed by:\r\nSanjay Verma\r\nB.Tech, NIT Kurukshetra, India\r\nEmail: sanjay.verma.nitk@gmail.com\r\n\r\nCurrent Version 1.4\r\n\r\nFor any suggestions/comments/complaints, \r\nplease drop a mail to lanhuntofficial@gmail.com\r\n\r\nOfficial download site:\r\nhttp://lanhunt.picodomain.com");
		txtrDevelopedBySanjay.setBounds(10, 116, 387, 270);
		contentPane.add(txtrDevelopedBySanjay);
	}
}
