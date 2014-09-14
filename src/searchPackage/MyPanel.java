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
import java.awt.Font;

public class MyPanel extends JPanel {
	private JTextField txtFullAddress;
	private JLabel lblFileNameHere;
	public int fileType=1; //1 for directory, 2 for file
	String location="";
	JButton btnDownload = new JButton("Download");
	JButton btnOpen = new JButton("Open");
	/**
	 * Create the panel.
	 */
	public MyPanel() {
		//setBackground(new Color(175, 238, 238));
		setBackground(Gui.getThemeColorLight());
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		btnDownload.addActionListener(new ActionListener() {
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
		lblFileNameHere.setFont(new Font("Tahoma", Font.BOLD, 11));
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
		
		JLabel lblFileSize = new JLabel("");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFileNameHere, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
							.addGap(16)
							.addComponent(btnOpen, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDownload)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 474, Short.MAX_VALUE)
							.addComponent(lblFileSize)
							.addGap(128))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtFullAddress, GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnOpenLocation)
							.addContainerGap())))
		);
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					OpenFileBox olc=new OpenFileBox(location);
					}catch(Exception e){
						LogWindow.addErrorLog(e.getLocalizedMessage());
					}
			}
		});
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFileNameHere, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDownload)
						.addComponent(btnOpen))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFullAddress, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFileSize)
						.addComponent(btnOpenLocation))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

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
	

	  
	  public void setFileType(int filetype){
		this.fileType=filetype;
		if(filetype==1){
			this.btnDownload.setVisible(false);
			this.btnOpen.setVisible(false);
		}
		}
	  public void toggleBackground(){
			this.setBackground(Gui.getThemeColorLight2());
		}
		
}
