package searchPackage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;

import java.awt.ScrollPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Button;
import java.awt.Label;
import javax.swing.border.TitledBorder;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.Panel;

public class OldSearchWindow extends JFrame {

	public static JPanel contentPane;
	private JTextField txtSearchBox;
	public static int panelX=10,panelY=120;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OldSearchWindow frame = new OldSearchWindow();
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
	public OldSearchWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel resultPanel=new JPanel();
		resultPanel.setLayout(new GridLayout(20,1,0,0));
		resultPanel.add(new JButton("Sanjay"));
		
		JScrollPane jsp2=new JScrollPane(resultPanel);
		jsp2.setBounds(0, 276, 285, -276);
		contentPane.add(jsp2);
		
		txtSearchBox = new JTextField();
		txtSearchBox.setText("Search text here");
		txtSearchBox.setBounds(150, 27, 322, 32);
		contentPane.add(txtSearchBox);
		txtSearchBox.setColumns(10);
		
		JButton button = new JButton("<- Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mainWindow.sWindow.setVisible(false);
				mainWindow.showWindow();
			}
		});
		button.setBounds(10, 0, 117, 48);
		contentPane.add(button);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				search(txtSearchBox.getText());
			}
		});
		btnSearch.setBounds(482, 27, 95, 32);
		contentPane.add(btnSearch);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 80, 618, 7);
		contentPane.add(separator);
		
		JLabel lblSearchResults = new JLabel("Search Results:");
		lblSearchResults.setBounds(10, 95, 236, 24);
		contentPane.add(lblSearchResults);
	}
	
	void setSearchText(String txt){
		txtSearchBox.setText(txt);
	}
	
	public void search(String txt){
		SearchEngine se=new SearchEngine();
		//se.search(txt);
	}
	public static void addSearchResult(String txt){
		panelY=panelY+90;
		//myPanel temp=new myPanel(panelX,panelY);
		//temp.setData("sanjay", txt);
		Gui.debug(txt);
		//panel.add(new myPanel(panelX,panelY,txt,txt));
	}
}
