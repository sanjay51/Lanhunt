package searchPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;

import javax.swing.JPanel;
import java.net.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.border.EtchedBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;

import jcifs.smb.SmbFile;
import java.awt.Toolkit;

public class OpenFileBox extends JFrame {

	private JPanel contentPane;
	OpenLocationEngine engine;
	String location="";
	JTextArea txtInfo;
	JButton btnDestroy;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpenFileBox frame = new OpenFileBox();
					frame.setVisible(true);
				} catch (Exception e) {
					LogWindow.addErrorLog("OpenFileBox(1): "+e.getLocalizedMessage());
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OpenFileBox(String location) {
		this();
		this.setVisible(true);
		this.location=location;
		engine=new OpenLocationEngine();
		engine.setFrame(this);
		engine.setLocation(location);
		btnDestroy.setEnabled(false);
		this.addMessage("Opening "+ location + "\n Please Wait...");
		engine.start();
	}
	
	public OpenFileBox() {
		setIconImage(Gui.getIconImage());
		setTitle("Open Location");
		//setType(Type.POPUP);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtInfo = new JTextArea();
		txtInfo.setLineWrap(true);
		txtInfo.setEditable(false);
		txtInfo.setBounds(10, 11, 414, 183);
		contentPane.add(txtInfo);
		
		btnDestroy = new JButton("Close");
		btnDestroy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnDestroy.setBounds(171, 214, 89, 35);
		contentPane.add(btnDestroy);	
	}
	
	public void addMessage(String message){
		this.txtInfo.setText(this.txtInfo.getText()+message+"\n-------------------------\n");
	}
	
	class OpenLocationEngine extends Thread{
		OpenFileBox olb;
		String location;
		public void explore(String loc){
			String temp=loc.substring(0,1);
				//the associated location is a directory
				temp=loc.substring(6);
				temp=temp.substring(0, temp.length());
				//temp=temp.substring(0,temp.lastIndexOf("/"));
				Gui.debug(temp);
				
			try {
				alternateLaunchFile((new File(temp)).getPath());
				olb.addMessage("Operation completed successfully");
				olb.dispose();
				olb.btnDestroy.setEnabled(true);
			} catch (Exception e) {
				olb.addMessage(e.getLocalizedMessage());
				LogWindow.addErrorLog("OpenFileBox(2): "+e.getLocalizedMessage());
				Gui.debug("Desktop opening error:"+e.getLocalizedMessage());
				olb.btnDestroy.setEnabled(true);
			}
		}
		
		public void alternateLaunchFile(String path){
		    //String s = "\\\\KUROSAVVAS-PC\\Users\\kuroSAVVAS\\Desktop\\New     Folder\\Warsaw    Panorama.jpg";
		    Path p = Paths.get(path);
		    try {
				Desktop.getDesktop().browse(p.toUri());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				launchFile(new File(path));
				e.printStackTrace();
			}
		}
		
		public void launchFile(File file)
		  {
		    if(!Desktop.isDesktopSupported()) return;
		    Desktop dt = Desktop.getDesktop();
		    try
		    {
		      dt.open(file);
		    } catch (IOException ex)
		    {
		      //this is sometimes necessary with files on other servers ie \\xxx\xxx.xls
		      launchFile(file.getPath());
		    }
		  }
		  
		  //this can launch both local and remote files
		  public void launchFile(String filePath)
		  {
		    if(filePath == null || filePath.trim().length() == 0) return;
		    if(!Desktop.isDesktopSupported()) return;
		    Desktop dt = Desktop.getDesktop();
		    try
		    {      
		       dt.browse(getFileURI(filePath));
		    } catch (Exception ex)
		    {
		    	LogWindow.addErrorLog("OpenFileBox(3): "+ex.getLocalizedMessage());
		    	olb.addMessage("Follwing error occured:\n"+ex.getLocalizedMessage());
		    	olb.btnDestroy.setEnabled(true);
		      ex.printStackTrace();
		     }
		   }

		  //generate uri according to the filePath
		  private URI getFileURI(String filePath)
		  {
		    URI uri = null;
		    filePath = filePath.trim();
		    if(filePath.indexOf("http") == 0 || filePath.indexOf("\\") == 0)
		    {
		      if(filePath.indexOf("\\") == 0) filePath = "file:" + filePath;
		      try
		      {
		        filePath = filePath.replaceAll(" ", "%20");
		        URL url = new URL(filePath);
		        uri = url.toURI();
		      } catch (MalformedURLException ex)
		      {
		        ex.printStackTrace();
		      }
		      catch (URISyntaxException ex)
		      {
		        ex.printStackTrace();
		      }
		    }
		    else
		    {
		      File file = new File(filePath);
		      uri = file.toURI();
		    }
		    return uri;
		  }

		@Override
		public void run() {
			this.explore(location);
		}
		
		public void setFrame(OpenFileBox o){
			this.olb=o;
		}
		public void setLocation(String location){
			this.location=location;
		}
	}
}
