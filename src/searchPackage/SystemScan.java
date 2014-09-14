package searchPackage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import jcifs.smb.*;

public class SystemScan {
	String ip="0.0.0.0";
	BufferedWriter writer;
	static DBUpdater dbUpdater;
	SystemScan(String ipaddr){
		this.ip=ipaddr;
	}
	public void setIpAddress(String ipaddr){
		this.ip=ipaddr;
	}
	public void setGuiInstance(DBUpdater d){
		dbUpdater=d;
	}
	
	public void scan() {
		
		boolean onlan=false;
		//check if the remote host is on-lan
		try{
			Socket socket=new Socket();
			socket.connect(new InetSocketAddress(ip, 445), 1000);
			//Gui.debug("It's onlan");
			onlan=true;
			
		}catch(Exception e){
			LogWindow.addErrorLog("SystemScan(1): "+e.getLocalizedMessage());
			Gui.debug("SystemScan-Exception:"+e.getLocalizedMessage());
		}
		
		if(onlan) {
			//add entry to xml file about current host if not already present
			try{
				writer=new BufferedWriter(new FileWriter("data/"+ip+".dat",false));
				}catch(Exception e){
					LogWindow.addErrorLog("SystemScan(2): "+e.getLocalizedMessage());
					Gui.debug("SystemScan Error: displayIt: Cannot create output file");
					return;
				}
			
			//scan the root of host
			try{
				SmbFile f=new SmbFile("smb://"+ip);
				SmbFile[] list=f.listFiles();
				
				for(int i=0;i<list.length;i++){
					displayIt(list[i],"smb://"+ip+"/"+list[i].getName(),writer);
				}
				}
				catch(Exception e){
					LogWindow.addErrorLog("SystemScan(3): "+e.getLocalizedMessage());
					System.out.println(e.getLocalizedMessage()+" Exception occured in GUI.java\n");
				}
			
			try{
			writer.close();
			}catch(Exception e){
				LogWindow.addErrorLog("SystemScan(4): "+e.getLocalizedMessage());
				//do nothing
			}
		}
	}
	static String[] t;
	static String filename;
	static int index=0;
	public static void displayIt(SmbFile node,String addr, BufferedWriter writer){
		
		try{
		if(node.isDirectory()){
			SmbFile[] subNote = node.listFiles();
			writer.write("1 "+addr); //1 stands for directory
			dbUpdater.updateItemsCount();
			
			//Add filename to dictionary
			t=addr.split("/");
			//System.out.println("\n->"+t[t.length-1]+"<-----------\n");
			//dbUpdater.addToDictionary(t[t.length-1]);
			
			writer.newLine();
			for(SmbFile filename : subNote){
				displayIt(filename,addr+filename.getName(),writer);
			}
		}
		else{
			writer.write("2 " + addr); //2 stands for file
			
			System.out.println(2+"-----------\n");
			t=addr.split("/");
			filename=t[t.length - 1];
			
			/*
			index=filename.lastIndexOf(".");
			
			if(index==0 || index==-1)
			{
				dbUpdater.addToDictionary(filename);
				//System.out.println("\n->"+filename+"<-----------\n");
			}
			else
			{
			dbUpdater.addToDictionary(filename.substring(0, filename.lastIndexOf(".")));
			//System.out.println("\n->"+filename.substring(0, filename.lastIndexOf(".")-1)+"<-----------\n");
			}
			*/
		}
		writer.newLine();
		}catch(Exception e){
			LogWindow.addErrorLog("SystemScan(5): "+e.getLocalizedMessage());
			Gui.debug("SystemScan-Error in displayIt:"+e.getLocalizedMessage());
		}
 
	}
}
