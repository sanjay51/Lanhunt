package searchPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class LogWindow extends JFrame {

	private JPanel contentPane;
	static JTextArea txtLog = new JTextArea();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogWindow frame = new LogWindow();
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
	public LogWindow() {
		setIconImage(Gui.getIconImage());
		setTitle("LanHunt Log");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(800, 200, 439, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 11, 432, 251);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(txtLog);
		
		txtLog.setText("LanHunt v"+Constants.getVersion()+"\r\nLOG\r\n");
	}
	
	public static void addLog(String msg){
		LogWindow.txtLog.append("\r\n"+msg+"\n");
	}
	
	public static void addErrorLog(String logmsg){
		LogWindow.txtLog.append("\r\n-------------\n"+logmsg+"\n-------------");
	}
}