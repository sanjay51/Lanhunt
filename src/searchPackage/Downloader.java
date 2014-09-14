package searchPackage;

import java.awt.BorderLayout;
import java.io.*;
import java.text.DecimalFormat;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Downloader extends JFrame {

	private JPanel contentPane;
	private JTextField txtDownloadFileLocation;
	private JLabel lblFileNameHere = new JLabel("File name here");
	private JLabel lblNewLabel = new JLabel("file size here");
	private JProgressBar progressBar = new JProgressBar();
	private long filesize=0;
	private String location="";
	private String filename="";
	private String target="";
	JButton btnPause = new JButton("Pause");
	JButton btnContinue = new JButton("Continue");
	JButton btnStop = new JButton("Stop");
	JButton btnStartDownload = new JButton("Start Download");
	public JLabel lblDownloadspeed;
	public Thread th;
	copyEngine c;
	private final JLabel lblUpdatedatabase = new JLabel("Click here to update database.");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Downloader frame = new Downloader();
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
	public Downloader() {
		setTitle("LanHunt v"+Constants.getVersion()+"  Downloader");
		setIconImage(Gui.getIconImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		lblFileNameHere.setBounds(5, 5, 424, 14);
		contentPane.add(lblFileNameHere);
		
		
		lblNewLabel.setBounds(5, 22, 380, 14);
		contentPane.add(lblNewLabel);
		
		btnStartDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c=new copyEngine();
				c.setData(location.substring(6), txtDownloadFileLocation.getText(), filesize, getCurrentObject());
				setGui();
				th=new Thread(c);
				th.start();
				
			}
		});
		btnStartDownload.setBounds(10, 185, 151, 34);
		contentPane.add(btnStartDownload);
		progressBar.setStringPainted(true);
		progressBar.setBounds(5, 222, 414, 14);
		contentPane.add(progressBar);
		
		txtDownloadFileLocation = new JTextField();
		txtDownloadFileLocation.setText("Download file location here");
		txtDownloadFileLocation.setBounds(5, 95, 380, 27);
		contentPane.add(txtDownloadFileLocation);
		txtDownloadFileLocation.setColumns(10);
		
		JLabel lblDownloadTo = new JLabel("Download to:");
		lblDownloadTo.setBounds(5, 78, 380, 14);
		contentPane.add(lblDownloadTo);
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.pause=true;
				btnPause.setEnabled(false);
				btnContinue.setEnabled(true);
			}
		});
		
		btnPause.setEnabled(false);
		btnPause.setBounds(171, 192, 72, 27);
		contentPane.add(btnPause);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.pause=false;
				btnContinue.setEnabled(false);
				btnPause.setEnabled(true);
			}
		});
		
		btnContinue.setEnabled(false);
		btnContinue.setBounds(243, 192, 88, 27);
		contentPane.add(btnContinue);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.pause=true;
				getCurrentObject().setVisible(false);
			}
		});
		
		btnStop.setEnabled(false);
		btnStop.setBounds(331, 192, 72, 27);
		contentPane.add(btnStop);
		
		lblDownloadspeed = new JLabel("0.0 MB/s");
		lblDownloadspeed.setBounds(10, 156, 214, 14);
		contentPane.add(lblDownloadspeed);
		lblUpdatedatabase.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				DBUpdater u=new DBUpdater();
				getCurrentObject().setVisible(false);
				u.setVisible(true);
			}
		});
		lblUpdatedatabase.setForeground(Color.BLUE);
		lblUpdatedatabase.setBounds(5, 39, 358, 14);
		
		contentPane.add(lblUpdatedatabase);
		
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
	}
	
	public void setData(String location){
		this.location=location;
		filename=location.substring(location.lastIndexOf("/")+1, location.length());
		this.lblFileNameHere.setText(filename);
		//Gui.debug(location.substring(6));
		File f=new File(location.substring(6));
		filesize=f.length();
		if(filesize==0){
			Gui.debug("Error File not found");
			lblNewLabel.setForeground(Color.RED);
			this.btnStartDownload.setEnabled(false);
			lblNewLabel.setText("File NOT Found. Try updating your database.");
			return;
		}
		DecimalFormat df=new DecimalFormat("#.000");
		double fs=filesize/(1024.0*1024.0);
		this.lblNewLabel.setText(String.valueOf(df.format(fs))+" MB");
		this.target=new JFileChooser().getFileSystemView().getDefaultDirectory()+"\\"+filename;
		this.txtDownloadFileLocation.setText(target);
	}
	
	public void alterProgress(float val){
		this.progressBar.setValue((int)val);
	}
	
	public void alterSpeed(float val){
		lblDownloadspeed.setText(val+" MB/s");
	}
	
	public Downloader getCurrentObject(){
		return this;
	}
	
	public void setGui(){
		this.btnStartDownload.setEnabled(false);
		this.btnPause.setEnabled(true);
		this.btnStop.setEnabled(true);
		this.btnContinue.setEnabled(false);
	}
}
