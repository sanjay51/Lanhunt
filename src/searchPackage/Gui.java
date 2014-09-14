package searchPackage;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import org.jdom2.Element;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import jcifs.smb.SmbFile;
public class Gui {
	public boolean isFirstRun=false;
	public static String[] getIpRange() {
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
			debug(e.getLocalizedMessage()+" Gui:getIpRange-Exception");
		}
		return s;
	}
	
	public static boolean isFirstRun() {
		SAXBuilder builder=new SAXBuilder();
		File xmlFile=new File("xml/settings.xml");
		try{
		Document document=(Document)builder.build(xmlFile);
		Element rootNode=document.getRootElement();

		Element proxy=rootNode.getChild("firstrun");
		String value=proxy.getChildText("value");
		
		if(value.equals("1")){
			updateFirstRun();
			return true;
		}
		else return false;
		
		}catch(Exception e){
			debug(e.getLocalizedMessage()+" Gui:getIpRange-Exception");
		}
		return false;
	}
	
	public static void updateFirstRun(){
		try {
			 
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File("xml/settings.xml");
	 
			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();
			
			Element proxy = rootNode.getChild("firstrun");
	 
			// update salary value
			proxy.getChild("value").setText("0");
			//proxy.getChild("high").setText(high);
			
			
			XMLOutputter xmlOutput = new XMLOutputter();
	 
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("xml/settings.xml"));
	 
			System.out.println("File updated!");
		  } catch (IOException io) {
			io.printStackTrace();
			LogWindow.addLog(io.getLocalizedMessage());
		  } catch (JDOMException e) {
			e.printStackTrace();
			LogWindow.addLog(e.getLocalizedMessage());
		  }
	}
	
	public static String getNextIpAddress(String ip) {
	    String[] nums = ip.split("\\.");
	    int i = (Integer.parseInt(nums[0]) << 24 | Integer.parseInt(nums[2]) << 8
	          |  Integer.parseInt(nums[1]) << 16 | Integer.parseInt(nums[3])) + 1;

	    // If you wish to skip over .255 addresses.
	    if ((byte) i == -1) i++;

	    return String.format("%d.%d.%d.%d", i >>> 24 & 0xFF, i >> 16 & 0xFF,
	                                        i >>   8 & 0xFF, i >>  0 & 0xFF);
	}
	
	public static void debug(String msg){
		System.out.println(msg+"\n");
		LogWindow.txtLog.append("\r\n"+msg);
	}
	
	public static void updateIpRange(String low, String high){
		try {
			 
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File("xml/settings.xml");
	 
			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();
			
			Element proxy = rootNode.getChild("proxy");
	 
			// update salary value
			proxy.getChild("low").setText(low);
			proxy.getChild("high").setText(high);
			
			
			XMLOutputter xmlOutput = new XMLOutputter();
	 
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("xml/settings.xml"));
	 
			System.out.println("File updated!");
		  } catch (IOException io) {
			io.printStackTrace();
			LogWindow.addLog(io.getLocalizedMessage());
		  } catch (JDOMException e) {
			e.printStackTrace();
			LogWindow.addLog(e.getLocalizedMessage());
		  }
	}
	
	public static int getTotalNodeCount(){
		int nodeCount=0;
		String[] str=getIpRange();
		do{
			nodeCount++;
			str[0]=getNextIpAddress(str[0]);
		}while(! str[0].equals(str[1]));
		//LogWindow.txtLog.append("\r\n Total Nodes:" + totalNodes);
		return nodeCount;
	}
	
	public static Image getIconImage(){
		try{
		return Toolkit.getDefaultToolkit().getImage(mainWindow.class.getResource("/searchPackage/xml/size3.png"));
		}catch(Exception e){
			return null;
		}
	}
	
	public static Image getBackgroundImage(){
		try{
		return Toolkit.getDefaultToolkit().getImage(mainWindow.class.getResource("/searchPackage/xml/bg4.jpg"));
		}catch(Exception e){
			return null;
		}
	}
	
	public static Color getThemeColor(){
		return new Color(0, 32, 133);
	}
	
	public static Color getThemeColorLight(){
		return new Color(0, 68, 245);
	}
	
	public static Color getThemeColorLight2(){
		return new Color(0, 130, 245);
	}
	
}
