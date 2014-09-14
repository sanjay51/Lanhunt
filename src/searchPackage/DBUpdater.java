package searchPackage;

import java.awt.Color;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Pattern;

import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.Button;
import javax.swing.Icon;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class DBUpdater extends JFrame {

	private JPanel contentPane;
	public JProgressBar progressBar;
	public int nodeCount = 0, completedCount = 0, threadCount = 0, totalThreads=0,
			totalNodes = 0, itemsCount=0;
	JLabel lblCurrentlyExamining = new JLabel("Currently Examining: NA");
	JLabel lblNodesExamined = new JLabel("Nodes Examined: 0");
	JLabel lblThreadsCompleted,lblItemsCount;
	JButton btnStart = new JButton("Start");
	private final JLabel lblIpRange = new JLabel("Ip Range:");
	private final JLabel lblUpdatedatabase = new JLabel(
			"<html><u>(Click here to change Ip Range)</u></html>");
	innerUpdater updater = new innerUpdater();
	private JLabel lblItemsIndexed;
	private JPanel panel;
	JButton btnClear=new JButton("Clear Old DB");
	
	//Map<String,Integer> dictionary = new HashMap<String,Integer>();
	//List<String> list = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBUpdater frame = new DBUpdater();
					frame.setVisible(true);
				} catch (Exception e) {
					LogWindow.addErrorLog(e.getLocalizedMessage());
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DBUpdater() {
		setResizable(false);
		
		setTitle("LanHunt " + Constants.getVersion() + " DB update");
		setIconImage(Gui.getIconImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(180,180, 432, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBackground(Gui.getThemeColorLight2());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUpdate = new JLabel("Update Database");
		lblUpdate.setForeground(new Color(255, 255, 255));
		lblUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUpdate.setBounds(142, 11, 163, 34);
		
		contentPane.add(lblUpdate);
		//btnStart.setBackground(new Color(224, 255, 255));

		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				startUpdate();
				//lblTotalThreads.setText("Total Threads: " + i);
				// Thread th=new Thread(updater);
				// th.start();
			}
		});
		btnStart.setBounds(166, 108, 106, 34);
		contentPane.add(btnStart);
		lblNodesExamined.setForeground(new Color(255, 255, 255));

		lblNodesExamined.setBounds(10, 193, 189, 14);
		contentPane.add(lblNodesExamined);
		lblCurrentlyExamining.setForeground(new Color(255, 255, 255));

		lblCurrentlyExamining.setBounds(10, 238, 400, 14);
		contentPane.add(lblCurrentlyExamining);
		lblIpRange.setForeground(new Color(255, 255, 255));
		lblIpRange.setBounds(10, 45, 400, 14);
		
		JPanel pnl=new JPanel();
		pnl.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnl.setBackground(new Color(204, 204, 255));
		pnl.setBounds(180, 170, 232, 57);
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File dir=new File("data");
				for(File file: dir.listFiles()) file.delete();
				btnClear.setEnabled(false);
			}
		});
		JLabel lbl=new JLabel("(Clearing old database speeds up the search)");
		lbl.setFont(new Font("Arial", Font.PLAIN, 10));
		pnl.add(btnClear);
		pnl.add(lbl);
		contentPane.add(pnl);

		String[] ipRange = Gui.getIpRange();
		lblIpRange.setText("Ip Range: " + ipRange[0] + " - " + ipRange[1]);
		contentPane.add(lblIpRange);

		lblUpdatedatabase.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				SettingsWindow u = new SettingsWindow();
				u.initializeIpRange();
				u.setVisible(true);
			}
		});
		lblUpdatedatabase.setForeground(new Color(153, 204, 204));
		lblUpdatedatabase.setBounds(10, 58, 189, 14);

		contentPane.add(lblUpdatedatabase);

		progressBar = new JProgressBar();
		progressBar.setFont(new Font("Tahoma", Font.BOLD, 16));
		progressBar.setForeground(new Color(46, 139, 87));
		progressBar.setStringPainted(true);
		progressBar.setBounds(10, 263, 262, 47);
		contentPane.add(progressBar);

		lblThreadsCompleted = new JLabel("Threads Completed: 0");
		lblThreadsCompleted.setForeground(new Color(255, 255, 255));
		lblThreadsCompleted.setBounds(10, 168, 245, 14);
		contentPane.add(lblThreadsCompleted);
		
		lblItemsCount = new JLabel("0");
		lblItemsCount.setForeground(new Color(153, 204, 102));
		lblItemsCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemsCount.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 29));
		lblItemsCount.setBounds(282, 276, 152, 34);
		contentPane.add(lblItemsCount);
		
		lblItemsIndexed = new JLabel("Files Indexed:");
		lblItemsIndexed.setForeground(new Color(255, 255, 255));
		lblItemsIndexed.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemsIndexed.setBounds(282, 267, 152, 14);
		contentPane.add(lblItemsIndexed);
		
		panel = new JPanel();
		panel.setBounds(0,0, 500, 500);
		BufferedImage myPicture3;
		try {
			myPicture3 = ImageIO.read(new File("xml/blue_background.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon( myPicture3 ));
			panel.add( picLabel );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		contentPane.add(panel);

		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		
	}
	
	public void startUpdate(){
		btnStart.setEnabled(false);
		btnClear.setEnabled(false);
		//btnStop.setEnabled(true);
		totalNodes = Gui.getTotalNodeCount();
		//lblTotalNodes.setText("Total Nodes: " + totalNodes);
		LogWindow.txtLog.append("\r\n Total Nodes:" + totalNodes);
		Thread[] th;
		th = new Thread[550];
		innerUpdater[] updater;
		updater = new innerUpdater[550];

		String[] str = Gui.getIpRange();
		int i;
		for (i = 0; i < (550) + 1; i++) {
			updater[i] = new innerUpdater();
			String start = str[0];
			String end = str[0];
			for (int j = 0; j < totalNodes / 500; j++) {
				if (str[1].equals(end)) {
					LogWindow.addLog("Broke at" + str[1]);
					break;
				}
				end = Gui.getNextIpAddress(end);
			}

			LogWindow.txtLog.append("\r\n i:" + i + " Start:" + start
					+ "***End:" + end);

			updater[i].initializeUpdater(start, end, getCurrentObject());
			th[i] = new Thread(updater[i]);
			th[i].start();
			str[0] = Gui.getNextIpAddress(end);
			if (str[1].equals(end))
			{
				i++;
				break;
			}
		}
		totalThreads=i;
	}

	public void updateItemsCount(){
		this.itemsCount++;
		this.lblItemsCount.setText(""+this.itemsCount);
	}
	
	//TreeSet<String> ts=new TreeSet<String>();
	//List<TreeSet<String>> dict=new ArrayList<TreeSet<String>>();
	//String strParts[];
	/*
	int tt=1;
	Pattern pattern = Pattern.compile("[^a-z-_.]");
	public void addToDictionary(String str){
		if(tt==1)
		{
			
			for(int i=0;i<26;i++)
			dict.add(new TreeSet<String>());
			tt=0;
		}
		str=str.toLowerCase();
		//if(dictionary.get(str)==null)
		//{
			//dictionary.put(str, 1);
		//if()
		//LogWindow.addLog("---- Str2: "+str);
		strParts=str.split("\\s+");
		for(String str2:strParts)
		{
			//LogWindow.addLog("Str2: "+str2);
			if (! pattern.matcher(str2).find()) {
				if(str2.length()<6 || str2.length()>30) continue;
				dict.get(str2.charAt(0)-'a').add(str2);
			}
			//if(str2.contains("_")) continue;
			//if(str2.matches(".*\\d.*")) continue;
			
		}
		
		//LogWindow.addLog(ts.size()+"");
			//list.add(str);
		//}
	}
	
	public void saveListToFile(){
		BufferedWriter writer=null;
		char c,currFile=0;
		//java.util.Collections.sort(list);
		//String prevItem=null;
		for(int i=0;i<26;i++)
		{
			try{
				writer=new BufferedWriter(new FileWriter("dict/"+(char)(i+(int)'a')+".dict",false));
				
				//String firstItem=dict.get(i).first();
				for(String item:dict.get(i))
				{

					if(item==null || item.length()==0)
						continue;
					//else prevItem=item;


					writer.write(item);
					writer.newLine();
				}
				writer.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	*/
	public synchronized void incrementCompletedThreadCount()
	{
		completedCount++;
	}
	
	public synchronized void incrementNodeCount()
	{
		nodeCount++;
	}
	/*
	public void createSuggestionLibraries()
	{
		saveListToFile();
	}
	*/
	
	class innerUpdater implements Runnable {
		String[] strIp;
		DBUpdater dbUpdater;
		int innerCount = 0;

		public void run() {
			startUpdate();
		}

		public void initializeUpdater(String start, String end, DBUpdater d) {
			strIp = new String[2];
			strIp[0] = start;
			strIp[1] = end;
			dbUpdater=d;
		}

		public void startUpdate() {
			System.out.println(strIp[0] + " " + strIp[1]);
			SystemScan systemScanner = new SystemScan(strIp[0]);
			while (true) {
				System.out.println(strIp[0]);
				systemScanner.setIpAddress(strIp[0]);
				systemScanner.setGuiInstance(dbUpdater);
				lblCurrentlyExamining.setText("Last Examined: "
						+ strIp[0]);
				systemScanner.scan();

				innerCount++;
				incrementNodeCount();
				lblNodesExamined.setText("Nodes Examined: "
						+ String.valueOf(nodeCount)+"/"+totalNodes);

				if (nodeCount >= totalNodes){
					//lblTotalNodes.setText("Total Nodes: "+nodeCount+"/"+totalNodes);
				}

				if (strIp[0].equals(strIp[1]))
					break;
				strIp[0] = getNextIpAddress(strIp[0]);
			}
			
			incrementCompletedThreadCount();
			
			progressBar.setValue((completedCount) * 100
					/ (6 * (totalThreads - completedCount) + totalThreads));
			lblThreadsCompleted.setText("Threads Completed: " + completedCount + "/"+totalThreads );
			LogWindow.txtLog.append("\r\n Threads Completed:" + completedCount
					+ " Count:" + innerCount);
			
			//Create search suggestion libraries
			
			//LogWindow.addLog("threadCount: "+completedCount+"totalThreads: "+totalThreads);
			if(completedCount==totalThreads)
			{
				LogWindow.addLog("Search Completed");
				//createSuggestionLibraries();
			}
		}

		public String getNextIpAddress(String ip) {
			String[] nums = ip.split("\\.");
			int i = (Integer.parseInt(nums[0]) << 24
					| Integer.parseInt(nums[2]) << 8
					| Integer.parseInt(nums[1]) << 16 | Integer
					.parseInt(nums[3])) + 1;

			// If you wish to skip over .255 addresses.
			if ((byte) i == -1)
				i++;

			return String.format("%d.%d.%d.%d", i >>> 24 & 0xFF,
					i >> 16 & 0xFF, i >> 8 & 0xFF, i >> 0 & 0xFF);
		}
	}

	public DBUpdater getCurrentObject() {
		return this;
	}
}
