package searchPackage;

import java.awt.*;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SearchWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField textField;
	static int layoutCount=0;
	static int layoutMaxCount=20;
	GridLayout resultPanelLayout;
	public JPanel topMenu, resultPanel;
	public JScrollPane scrollPane;
	public int filter=0;
	JComboBox comboBox;
	JProgressBar progressBar;
	JPanel panel = new JPanel();
	JButton button;
	JButton btnCancel;
	JLabel lblResultstatus = new JLabel("0 onlan results (0 off-lan).");
	Thread th;
	public JLabel lblMessage = new JLabel("");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
					SearchWindow frame = new SearchWindow();
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
	public SearchWindow() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		    	LogWindow.addLog("1");
		        if ("Nimbus".equals(info.getName())) {
		        	LogWindow.addLog("2");
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
			LogWindow.addLog(e.getLocalizedMessage());
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		progressBar = new JProgressBar();
		comboBox = new JComboBox();
		button = new JButton("Hunt");
		btnCancel = new JButton("Cancel");
		
		setResizable(false);
		setTitle("Search - LanHunt "+Constants.getVersion()+"");
		setIconImage(Gui.getIconImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 600);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//Create top menu panel
		topMenu=new JPanel();
		topMenu.setBackground(new Color(175, 238, 238));
		topMenu.setBounds(0, 11, 692, 38);
		topMenu.setLayout(null);
		JButton btn=new JButton("\u2190 back");
		btn.setBackground(new Color(176, 224, 230));
		btn.setIcon(null);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				th.stop();
				mainWindow.showWindow();
				mainWindow.sWindow.setVisible(false);
			}
		});
		contentPane.setLayout(null);
		btn.setBounds(10,8,79,27);
		topMenu.add(btn);
		
		contentPane.add(topMenu);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search(textField.getText(),filter);
			}
		});
		textField.setBounds(95, 8, 347, 27);
		topMenu.add(textField);
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				search(textField.getText(),filter);
			}
		});
		button.setBounds(564, 8, 95, 27);
		topMenu.add(button);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filter=comboBox.getSelectedIndex();
			}
		});
		comboBox.setBounds(452, 8, 106, 27);
		comboBox.addItem("All Files");
		comboBox.addItem("Video Files");
		comboBox.addItem("Audio Files");
		comboBox.addItem("Document Files");
		comboBox.addItem("Executable Files");
		topMenu.add(comboBox);
		JScrollPane jsp=new JScrollPane(contentPane);
		panel.setBackground(new Color(175, 238, 238));
		panel.setBounds(0, 53, 692, 61);
		
		contentPane.add(panel);
		panel.setLayout(null);
		//progressBar.setBackground(new Color(0, 153, 0));
		
		progressBar.setStringPainted(true);
		progressBar.setBounds(10, 13, 549, 23);
		panel.add(progressBar);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stopSearch();
			}
		});
		
		btnCancel.setBounds(564, 11, 95, 25);
		btnCancel.setEnabled(false);
		panel.add(btnCancel);
		
		
		lblResultstatus.setBounds(10, 36, 344, 14);
		panel.add(lblResultstatus);
		
		resultPanel=new JPanel();
		resultPanel.setBackground(new Color(135, 206, 250));
		
		scrollPane = new JScrollPane(resultPanel);
		resultPanelLayout=new GridLayout(0,1);
		resultPanel.setLayout(resultPanelLayout);
		lblMessage.setVerticalAlignment(SwingConstants.TOP);
		
		resultPanel.add(lblMessage);
		
		scrollPane.setBounds(5, 125, 667, 423);
		contentPane.add(scrollPane);
		
		
		
		setContentPane(jsp);
		
		
	}
	
	public void search(String str, int filter){
		//this.contentPane.removeAll();
		//this.contentPane.add(topMenu);
		//this.contentPane.add(panel);
		//this.contentPane.add(resultPanel);
		//mainWindow.messageBox.showMessage("Error Opening", "There was trouble opening this node..\n contact sanjay verma");
		this.resultPanel.removeAll();
		this.button.setEnabled(false);
		this.textField.setEnabled(false);
		this.comboBox.setEnabled(false);
		this.btnCancel.setEnabled(true);
		updateResultLabel(0,0);
		this.repaint();
		layoutCount=0;
		SearchEngine se=new SearchEngine();
		se.setData(str, filter, this);
		th=new Thread(se);
		th.start();
		//se.search(str,this);
	}
	
	public void stopSearch(){
		this.th.stop();
		this.btnCancel.setEnabled(false);
		this.comboBox.setEnabled(true);
		this.textField.setEnabled(true);
		this.button.setEnabled(true);
		//this.resultPanel.add(this.lblMessage);
		this.repaint();
	}
	public void addSearchResult(String str){
		layoutCount++;
		/*if(layoutCount>=layoutMaxCount){
			layout.setRows(layoutMaxCount+20);
			layoutMaxCount+=20;
		}*/
		MyPanel temp=new MyPanel();
		temp.setFullAddress(str);
		temp.setFileType(str.substring(0, 1).equalsIgnoreCase("1")?1:2);
		//temp.setBounds(0, layoutCount * 90 +10, 585, 90);
		if(layoutCount%2==0) temp.toggleBackground();
		this.resultPanel.add(temp);
		this.repaint();
	}
	
	public void setNoResultsMessage(){
		this.resultPanel.add(lblMessage);
		this.lblMessage.setText("<html>" +
				"<div width=\"500\" style=\"margin:5px;\"><h2>No results found, what to do now?</h2><br/>" +
				"- Your database may be obsolete. Try updating your database by clicking 'Update Database' " +
				"button in the home screen.<br/><br/>" +
				"- Try specific keywords, e.g. 'deja' instead of 'deja vu'. Avoid spaces since " +
				"filenames do not have spaces genrally.<br/><br/>" +
				"- Check your proxy range. Click 'Settings' botton in the home screen." +
				"</div></html>");
		this.repaint();
	}
	public void setSearchText(String str){
		this.textField.setText(str);
	}
	
	public void alterProgressBar(float val){
		this.progressBar.setValue((int)val);
	}
	
	public void updateResultLabel(int onlanResults, int offlanResults){
		this.lblResultstatus.setText(onlanResults+" onlan results ("+offlanResults+" off-lan).");
	}
}
