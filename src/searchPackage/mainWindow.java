package searchPackage;

import java.awt.Desktop;
import java.awt.EventQueue;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UIManager.*;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JSeparator;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.List;
import javax.swing.Icon;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

public class mainWindow {

	public JFrame frmLanhuntVersion;
	private JTextField textField;
	public static SearchWindow sWindow=new SearchWindow();
	static mainWindow window;
	//public static MessageBox messageBox;
	JButton btnSearch = new JButton("Hunt");
	JComboBox comboBox = new JComboBox();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
					    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					        if ("Nimbus".equals(info.getName())) {
					            UIManager.setLookAndFeel(info.getClassName());
					            break;
					        }
					    }
					} catch (Exception e) {
					    // If Nimbus is not available, you can set the GUI to another look and feel.
					}
					window = new mainWindow();
					//messageBox=new MessageBox();
					window.frmLanhuntVersion.setVisible(true);
					window.textField.requestFocusInWindow();
					
					if(Gui.isFirstRun())
					{
						window.frmLanhuntVersion.setVisible(false);
						FirstRun f=new FirstRun();
						f.setBounds(200, 200, f.bounds().width, f.bounds().height);
						f.setMainWindow(window);
						f.setVisible(true);
					}
					//LogWindow l=new LogWindow();
					//l.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainWindow() {
		initialize();
	}
	
	public static void showWindow() {
		window.frmLanhuntVersion.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLanhuntVersion = new JFrame();
		frmLanhuntVersion.getContentPane().setBackground(new Color(102, 51, 0));
		frmLanhuntVersion.setResizable(false);
		frmLanhuntVersion.setIconImage(Toolkit.getDefaultToolkit().getImage(mainWindow.class.getResource("/searchPackage/xml/size3.png")));
		frmLanhuntVersion.setTitle("LanHunt "+Constants.getVersion());
		frmLanhuntVersion.setBounds(100, 100, 700, 610);
		frmLanhuntVersion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLanhuntVersion.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sWindow.setSearchText(textField.getText());
				sWindow.comboBox.setSelectedIndex(comboBox.getSelectedIndex());
				sWindow.setVisible(true);
				sWindow.search(textField.getText(),comboBox.getSelectedIndex());
				window.frmLanhuntVersion.setVisible(false);
				getCurrentObject().frmLanhuntVersion.setVisible(false);
			}
		});
		textField.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField.setForeground(new Color(51, 0, 102));
		textField.setBackground(new Color(255, 255, 255));
		
		
		JLabel lblCheckForUpdates = new JLabel("<html><u>Check for Updates, click here.</u></html>");
		lblCheckForUpdates.setBackground(Color.GREEN);
		lblCheckForUpdates.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckForUpdates.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Desktop.getDesktop().browse(new URI("http://lanhunt.picodomain.com"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBackground(new Color(51, 153, 51));
		panel_1.setBounds(155, 0, 356, 55);
		frmLanhuntVersion.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel((Icon) null);
		label.setBounds(46, 16, 0, 0);
		panel_1.add(label);
		
		JButton btnUpdateDb = new JButton("Update DB");
		btnUpdateDb.setBounds(21, 11, 105, 34);
		//btnUpdateDb.setForeground(new Color(255, 255, 255));
		//btnUpdateDb.setBackground(new Color(153, 0, 255));
		panel_1.add(btnUpdateDb);
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.setBounds(126, 11, 105, 34);
		//btnSettings.setForeground(new Color(255, 255, 255));
		//btnSettings.setBackground(new Color(153, 0, 255));
		panel_1.add(btnSettings);
		
		JButton btnAbout = new JButton("About");
		btnAbout.setBounds(230, 11, 105, 34);
		
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AboutUs a=new AboutUs();
				a.setDefaultCloseOperation(a.HIDE_ON_CLOSE);
				a.setVisible(true);
			}
		});
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SettingsWindow w=new SettingsWindow();
				w.initializeIpRange();
				w.setVisible(true);
			}
		});
		btnUpdateDb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBUpdater u=new DBUpdater();
				u.setVisible(true);
			}
		});
		//btnAbout.setForeground(new Color(255, 255, 255));
		//btnAbout.setBackground(new Color(153, 0, 255));
		panel_1.add(btnAbout);
		lblCheckForUpdates.setForeground(Color.BLACK);
		lblCheckForUpdates.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCheckForUpdates.setBounds(155, 361, 369, 14);
		frmLanhuntVersion.getContentPane().add(lblCheckForUpdates);
		
		textField.setBounds(155, 186, 368, 29);
		frmLanhuntVersion.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblLanSearch = new JLabel("");
		lblLanSearch.setFont(new Font("Tekton Pro", Font.BOLD, 44));
		lblLanSearch.setBounds(520, 74, 28, 68);
		frmLanhuntVersion.getContentPane().add(lblLanSearch);
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBackground(new Color(153, 0, 255));
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sWindow.setSearchText(textField.getText());
				sWindow.comboBox.setSelectedIndex(comboBox.getSelectedIndex());
				sWindow.setVisible(true);
				sWindow.search(textField.getText(),comboBox.getSelectedIndex());
				window.frmLanhuntVersion.setVisible(false);
				
				getCurrentObject().frmLanhuntVersion.setVisible(false);
			}
		});
		btnSearch.setBounds(344, 226, 103, 27);
		frmLanhuntVersion.getContentPane().add(btnSearch);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 539, 672, 2);
		frmLanhuntVersion.getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("For feedback, please drop a mail to lanhuntofficial@gmail.com");
		lblNewLabel.setForeground(new Color(255, 204, 204));
		lblNewLabel.setBounds(10, 548, 672, 22);
		frmLanhuntVersion.getContentPane().add(lblNewLabel);
		//comboBox.setForeground(new Color(255, 255, 255));
		//comboBox.setBackground(new Color(153, 0, 255));
		
		comboBox.addItem("All Files");
		comboBox.addItem("Video Files");
		comboBox.addItem("Audio Files");
		comboBox.addItem("Document Files");
		comboBox.addItem("Executable Files");
		comboBox.setBounds(225, 226, 106, 27);
		frmLanhuntVersion.getContentPane().add(comboBox);
		
		JPanel logoPanel = new JPanel();
		logoPanel.setOpaque(false);
		logoPanel.setBackground(new Color(135, 206, 250));
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("xml/st1.flr"));
			JLabel picLabel = new JLabel(new ImageIcon( myPicture ));
			logoPanel.add( picLabel );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		logoPanel.setBounds(168, 45, 343, 130);
		frmLanhuntVersion.getContentPane().add(logoPanel);
		
		JButton btnLike = new JButton("");
		btnLike.setIcon(new ImageIcon(mainWindow.class.getResource("xml/like2.png")));
		btnLike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Desktop.getDesktop().browse(new URI("http://www.facebook.com/lanhuntofficialpage"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnLike.setBounds(550, 0, 144, 50);
		frmLanhuntVersion.getContentPane().add(btnLike);
		
		JButton btnDonate = new JButton("");
		btnDonate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Desktop.getDesktop().browse(new URI("http://lanhunt.picodomain.com/donate/donate.html"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDonate.setIcon(new ImageIcon(mainWindow.class.getResource("/searchPackage/xml/donate.jpg")));
		btnDonate.setBounds(550, 51, 144, 50);
		frmLanhuntVersion.getContentPane().add(btnDonate);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		/*BufferedImage myPicture1;
		try {
			myPicture1 = ImageIO.read(new File("xml/topbar.png"));
			JLabel picLabel = new JLabel(new ImageIcon( myPicture1 ));
			panel.add( picLabel );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		panel.setBounds(168, -11, 336, 62);
		frmLanhuntVersion.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel imgPanel = new JPanel();
		imgPanel.setBounds(0, -23, 694, 604);
		BufferedImage myPicture3;
		try {
			myPicture3 = ImageIO.read(new File("xml/bg4.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon( myPicture3 ));
			imgPanel.add( picLabel );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frmLanhuntVersion.getContentPane().add(imgPanel);
		
		
		//JLabel lblAlpha = new JLabel("beta");
		//lblAlpha.setBounds(logoPanel.getBounds().x +logoPanel.getWidth(),logoPanel.getBounds().y +50, 46, 14);
		//frmLanhuntVersion.getContentPane().add(lblAlpha);
	}
	
	//DefaultListModel list=new DefaultListModel();
	BufferedReader bf;
	
	public void showSuggestions(){
		String term=this.textField.getText().toLowerCase();
		char c;
		int lineCount=0;
		String line=null,originalTerm=term;
		int length=0;
		//list.addElement(term);
		
		//list.clear();
		//this.suggestionList.clear();
		//suggestionList.setVisible(false);
		if(term.length()<3)
			return;
		
		try
		{
			c=term.charAt(0);
			if(c>='a' || c<='z')
				bf=new BufferedReader(new FileReader("dict/"+c+".dict"));
			else bf=new BufferedReader(new FileReader("dict/others.dict"));
			
			
			while((line=bf.readLine())!=null){
				length=term.length();
				//term=originalTerm;
				if(line.length()<term.length())
					continue;
					//term=term.substring(0,line.length());
				//length=term.length();
				
				LogWindow.addLog("Term:"+term+" line:"+line + " substring:"+line.substring(0, length));
				if(line.substring(0,length).equals(term))
				{
					//this.suggestionList.add(line);
					//this.suggestionList.setVisible(true);
				}
			}
			bf.close();
			
		}catch(Exception e)
		{
			LogWindow.addErrorLog("Error: mainWindow->showSuggestions: "+e.getMessage());
			return;
		}
		
		//Set to the current suggestionList
		//this.suggestionList.setModel(list);
	}
	
	public mainWindow getCurrentObject(){
		return this;
	}
}
