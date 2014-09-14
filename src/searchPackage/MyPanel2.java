package searchPackage;

import javax.swing.JPanel;
import java.net.*;
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

public class MyPanel2 extends JPanel {
	private JTextField txtFullAddress;
	private JLabel lblFileNameHere;
	public int fileType=1; //1 for directory, 2 for file
	String location="";
	JButton btnNewButton = new JButton("Download");
	/**
	 * Create the panel.
	 */
	public MyPanel2() {
		//setBackground(new Color(175, 238, 238));
		setBackground(Gui.getThemeColorLight());
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Downloader d=new Downloader();
				d.setVisible(true);
				try{
				String source=location.substring(6);
				d.setData(location);
				////String dest="z://"+lblFileNameHere.getText();
				//Gui.debug(source+"   "+dest);
				//copyFile("//172.16.144.135/Videos/Bhakti/SWASAN DI MAALA/AVSEQ02.avi","z://xyz.avi");
				////copyFile(source,dest);
				}catch(Exception e){
					Gui.debug("MyPanel-Copy error:"+e.getLocalizedMessage());
				}
			}
		});
		
		lblFileNameHere = new JLabel("file name here");
		lblFileNameHere.setForeground(new Color(255, 255, 255));
		lblFileNameHere.setToolTipText("hi there");
		final Dimension size=lblFileNameHere.getPreferredSize();
		lblFileNameHere.setMinimumSize(size);
		lblFileNameHere.setPreferredSize(size);
		
		JButton btnOpenLocation = new JButton("Open Location..");
		btnOpenLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				OpenLocationBox olc=new OpenLocationBox(location);
				}catch(Exception e){
					LogWindow.addErrorLog(e.getLocalizedMessage());
				}
			}
		});
		
		txtFullAddress = new JTextField();
		txtFullAddress.setText("full address goes here");
		txtFullAddress.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFileNameHere, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton)
							.addGap(5)
							.addComponent(btnOpenLocation))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtFullAddress, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFileNameHere, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton)
						.addComponent(btnOpenLocation))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFullAddress, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
	public void toggleBackground(){
		this.setBackground(Gui.getThemeColorLight2());
	}
	
	public void setFullAddress(String str){
		location=str;
		this.txtFullAddress.setText(str.substring(6).replace('/', '\\'));
		String[] t=str.split("/");
		this.lblFileNameHere.setText(t[t.length - 1]);
		this.lblFileNameHere.setToolTipText(t[t.length - 1]);
	}
	
	public void copyFile(String file1, String file2){
		InputStream inStream = null;
		OutputStream outStream = null;
	 
	    	try{
	 
	    	    File afile =new File(file1);
	    	    File bfile =new File(file2);
	 
	    	    inStream = new FileInputStream(afile);
	    	    outStream = new FileOutputStream(bfile);
	 
	    	    byte[] buffer = new byte[1024];
	 
	    	    int length;
	    	    //copy the file content in bytes 
	    	    while ((length = inStream.read(buffer)) > 0){
	 
	    	    	outStream.write(buffer, 0, length);
	 
	    	    }
	 
	    	    inStream.close();
	    	    outStream.close();
	 
	    	    System.out.println("File is copied successful!");
	 
	    	}catch(IOException e){
	    		e.printStackTrace();
	    	}
	}
	
	/*public void explore(String loc){
		String temp=loc.substring(0,1);
		if(temp.equalsIgnoreCase("1")){
			//the associated location is a directory
			temp=loc.substring(6);
			temp.replace("%20", " ");
			temp=temp.substring(0, temp.length());
			Gui.debug(temp);
		}
		
		if(temp.equalsIgnoreCase("2")){
			//the associated location is a directory
			temp=loc.substring(6);
			temp=temp.substring(0, temp.length());
			temp=temp.substring(0,temp.lastIndexOf("/"));
			Gui.debug(temp);
		}
		try {
			launchFile(new File(temp));
		} catch (Exception e) {
			Gui.debug("Desktop opening error:"+e.getLocalizedMessage());
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
	  }*/
	  
	  public void setFileType(int filetype){
		this.fileType=filetype;
		if(filetype==1)
		this.btnNewButton.setVisible(false);
		}
}
