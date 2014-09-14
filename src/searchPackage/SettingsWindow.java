package searchPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.jdom2.input.SAXBuilder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class SettingsWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtLow1;
	private JTextField txtLow2;
	private JTextField txtLow3;
	private JTextField txtLow4;
	private JLabel lblTo;
	private JTextField txtHigh4;
	private JTextField txtHigh3;
	private JTextField txtHigh2;
	private JTextField txtHigh1;
	private JButton btnSaveChanges;
	JButton btnShowLog;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingsWindow frame = new SettingsWindow();
					frame.setVisible(true);
					frame.initializeIpRange();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SettingsWindow() {
		setIconImage(Gui.getIconImage());
		setTitle("LanHunt v"+Constants.getVersion()+" Settings");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 450, 309);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIpRange = new JLabel("Ip Range:");
		lblIpRange.setBounds(10, 23, 67, 14);
		contentPane.add(lblIpRange);
		
		txtLow1 = new JTextField();
		KeyAdapter l=new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				getCurrentObject().btnSaveChanges.setEnabled(true);
			}
		};
		txtLow1.setBounds(67, 20, 49, 30);
		contentPane.add(txtLow1);
		txtLow1.setColumns(10);
		
		txtLow2 = new JTextField();
		txtLow2.setColumns(10);
		txtLow2.setBounds(126, 20, 49, 30);
		contentPane.add(txtLow2);
		
		txtLow3 = new JTextField();
		txtLow3.setColumns(10);
		txtLow3.setBounds(185, 20, 49, 30);
		contentPane.add(txtLow3);
		
		txtLow4 = new JTextField();
		txtLow4.setColumns(10);
		txtLow4.setBounds(244, 20, 49, 30);
		contentPane.add(txtLow4);
		
		lblTo = new JLabel("to");
		lblTo.setBounds(173, 51, 46, 14);
		contentPane.add(lblTo);
		
		txtHigh4 = new JTextField();
		txtHigh4.setColumns(10);
		txtHigh4.setBounds(244, 70, 49, 30);
		contentPane.add(txtHigh4);
		
		txtHigh3 = new JTextField();
		txtHigh3.setColumns(10);
		txtHigh3.setBounds(185, 70, 49, 30);
		contentPane.add(txtHigh3);
		
		txtHigh2 = new JTextField();
		txtHigh2.setColumns(10);
		txtHigh2.setBounds(126, 70, 49, 30);
		contentPane.add(txtHigh2);
		
		txtHigh1 = new JTextField();
		txtHigh1.setColumns(10);
		txtHigh1.setBounds(67, 70, 49, 30);
		contentPane.add(txtHigh1);
	
		txtLow1.addKeyListener(l);
		txtLow2.addKeyListener(l);
		txtLow3.addKeyListener(l);
		txtLow4.addKeyListener(l);
		txtHigh1.addKeyListener(l);
		txtHigh2.addKeyListener(l);
		txtHigh3.addKeyListener(l);
		txtHigh4.addKeyListener(l);
		
		btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getCurrentObject().saveIpRange();
			}
		});
		btnSaveChanges.setEnabled(false);
		btnSaveChanges.setBounds(62, 227, 150, 32);
		contentPane.add(btnSaveChanges);
		
		JButton btnDiscardChanges = new JButton("Discard Changes");
		btnDiscardChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getCurrentObject().setVisible(false);
			}
		});
		btnDiscardChanges.setBounds(222, 227, 150, 32);
		contentPane.add(btnDiscardChanges);
		
		JLabel lblShowLogWindow = new JLabel("Show Log Window:");
		lblShowLogWindow.setBounds(10, 127, 142, 14);
		contentPane.add(lblShowLogWindow);
		
		btnShowLog = new JButton("Click Here");
		btnShowLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogWindow l=new LogWindow();
				l.setVisible(true);
				setDisabledShowLogButton();
			}
		});
		btnShowLog.setBounds(162, 127, 150, 19);
		contentPane.add(btnShowLog);
			
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
	}
	
	public SettingsWindow getCurrentObject(){
		return this;
	}
	
	public void setDisabledShowLogButton(){
		this.btnShowLog.setEnabled(false);
	}
	
	/*public String[] getIpRange() {
		String[] s=new String[2];
		SAXBuilder builder=new SAXBuilder();
		File xmlFile=new File("xml/settings.xml");
		try{
		Document document=(Document)builder.build(xmlFile);
		Element rootNode=document.getRootElement();

		Element proxy=rootNode.getChild("proxy");
		String low=proxy.getChildText("low");
		String high=proxy.getChildText("high");
		s[0]=low;
		s[1]=high;
		}catch(Exception e){
			Gui.debug(e.getLocalizedMessage()+" Gui:getIpRange-Exception");
		}
		return s;
	}*/
	
	public void updateIpRange(String lowIp, String highIp){
		String filepath = "xml/settings.xml";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			
			Node low=doc.getElementsByTagName("low").item(0);
			low.setTextContent(lowIp);
			Node high=doc.getElementsByTagName("high").item(0);
			high.setTextContent(highIp);
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);
	 
			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveIpRange(){
		if(Integer.parseInt(txtLow4.getText())>254)
		txtLow4.setText("254");
		
		if(Integer.parseInt(txtHigh4.getText())>254)
			txtHigh4.setText("254");
		
		String low=txtLow1.getText()+"."+txtLow2.getText()+"."+txtLow3.getText()+"."+txtLow4.getText();
		String high=txtHigh1.getText()+"."+txtHigh2.getText()+"."+txtHigh3.getText()+"."+txtHigh4.getText();
		
		Gui.updateIpRange(low, high);
		this.setVisible(false);
	}
	
	public void initializeIpRange(){
		String[] ip=Gui.getIpRange();
		String[] low=ip[0].split("\\.");
		String[] high=ip[1].split("\\.");
		
		txtLow1.setText(low[0]);
		txtLow2.setText(low[1]);
		txtLow3.setText(low[2]);
		txtLow4.setText(low[3]);
		
		txtHigh1.setText(high[0]);
		txtHigh2.setText(high[1]);
		txtHigh3.setText(high[2]);
		txtHigh4.setText(high[3]);
	}
}
