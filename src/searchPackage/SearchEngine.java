package searchPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SearchEngine implements Runnable {
	private String keyword="";
	private SearchWindow s;
	private int filter=0;
	private String extensions="";
	private int onlanResults=0;
	private int offlanResults=0;
	public void run(){
		search(keyword,s);
	}
	
	public void setData(String keyword, int filter, SearchWindow s){
		this.keyword=keyword;
		this.s=s;
		this.filter=filter;
		
		Constants c=new Constants();
		if(filter!=0){
			switch(filter){
			case 1:
				extensions=c.getVideoFileExtensions();
				break;
			case 2:
				extensions=c.getAudioFileExtensions();
				break;
			case 3:
				extensions=c.getDocumentFileExtensions();
				break;
			case 4:
				extensions=c.getExecutableFileExtensions();
				break;
			case 5:
				extensions=c.getCompressedFileExtensions();
				break;
			default:
				filter=0;
				extensions="";
			}
		}
	}
	public void search(String keyword, SearchWindow s){
		s.repaint();
		File f=new File("data");
		File file;
		BufferedReader bf;
		File[] list=f.listFiles();
		String temp;
		boolean onlan=false;
		boolean flag=false;
		int length=list.length;
		//searchWindow.panel.removeAll();
		for(int i=0;i<length;i++){
			file=list[i];
			flag=false;
			onlan=false;
			temp=file.getName().substring(0,file.getName().indexOf(".dat"));
			Gui.debug(temp);
			
				//Gui.debug("Onlan:"+temp);
				try{
					bf=new BufferedReader(new FileReader("data/"+file.getName()));
					int lineCount=0;
					String line;
					
					while((line=bf.readLine())!=null){
						lineCount++;
						boolean searchFlag=false;
						int index=line.toLowerCase().indexOf(keyword.toLowerCase());
						if(index>-1){
							
							if(!flag)
							onlan=isOnLan(temp);
							
							if(!onlan){
								this.offlanResults++;
								s.updateResultLabel(onlanResults, offlanResults);
								break;
							}
							
							if(filter!=0){
								String ext=line.toLowerCase().substring(line.lastIndexOf(".")+1);
								if(extensions.indexOf(ext)>=0)
									searchFlag=true;
							}
							else searchFlag=true;
							
							if(!searchFlag){
								continue;
							}
							
							if(onlan){
								flag=true;
								this.onlanResults++;
								s.updateResultLabel(onlanResults, offlanResults);
								s.addSearchResult(line);
								s.repaint();
							}
					//	System.out.println("Match: "+line);
						}
						
					}
					bf.close();
					s.alterProgressBar((100*(i+1))/(float)length);
					s.repaint();
				}catch(Exception e){
					Gui.debug("SearchEngine:Error:File Not found exception");
				}
				
			//searchWindow.panel.repaint();
			//searchWindow.panel.repaint();
				
		}
		if(onlanResults==0){
			//LogWindow.txtLog.append("\r\n Onlan Results:"+onlanResults);
			s.setNoResultsMessage();
			LogWindow.txtLog.append("\r\n Onlan Results:"+onlanResults);
		}
		s.button.setEnabled(true);
		s.btnCancel.setEnabled(false);
		s.comboBox.setEnabled(true);
		s.textField.setEnabled(true);
		s.button.setEnabled(true);
		s.repaint();
	}
	
	public boolean isOnLan(String ip) {
		boolean onlan=false;
		//check if the remote host is on-lan
		try{
			Socket socket=new Socket();
			socket.connect(new InetSocketAddress(ip, 445), 1000);
			//Gui.debug("It's onlan");
			onlan=true;
			
		}catch(Exception e){
			Gui.debug("SystemScan-Exception:"+e.getLocalizedMessage());
		}
		
		return onlan;
	}
}
