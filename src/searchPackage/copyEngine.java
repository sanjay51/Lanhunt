package searchPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class copyEngine implements Runnable {
	private String source;
	private String destination;
	public Downloader d;
	public float progress=0, prevProgress=0;
	private float filesize;
	public boolean pause=false;
	public javax.swing.Timer t;
	public void run(){
		InputStream inStream = null;
		OutputStream outStream = null;
	 this.startTimer();
		d.alterProgress(progress);
	    	 try{
	 
	    	    File afile =new File(source);
	    	    File bfile =new File(destination);
	 
	    	    inStream = new FileInputStream(afile);
	    	    outStream = new FileOutputStream(bfile);
	 
	    	    byte[] buffer = new byte[1024];
	 
	    	    int length;
	    	    float step=(100*1024)/((float)filesize);
	    	    //copy the file content in bytes 
	    	    length=1;
	    	    while (pause || (length = inStream.read(buffer)) > 0){
	    	    	if(pause)
	    	    		continue;
	    	    	
	    	    	outStream.write(buffer, 0, length);
	    	    	progress+=step;
	    	    	d.alterProgress(progress);
	    	    	System.out.println(step);
	 
	    	    }
	 
	    	    inStream.close();
	    	    outStream.close();
	 
	    	    System.out.println("File copied successfully!");
	    	    d.alterProgress(100);
	    	    t.stop();
	    	    d.alterSpeed(0);
	 
	    	}catch(IOException e){
	    		e.printStackTrace();
	    	}
	    	 d.btnPause.setEnabled(false);
	    	 d.btnStop.setEnabled(false);
	    	 d.btnPause.setEnabled(false);
	}
	
	public void setData(String source, String dest,long filesize, Downloader downloader){
		this.source=source;
		this.destination=dest;
		this.filesize=filesize;
		this.d=downloader;
	}
	
	public void startTimer(){
		t = new javax.swing.Timer(1000,new ActionListener(){
			int time=0;
			
			public void actionPerformed(ActionEvent e){
				time++;
				d.alterSpeed((((progress/(100*time))*(((float)filesize)/1024))/(1024)));
				//System.out.println("***speed="+(((progress/(100*time))*(((float)filesize)/1024))/(1024))+"progress="+(progress)+"filesize="+(filesize)+" time="+time+" *****");
			}
		});
		t.start();
	}
	
	public copyEngine getCurrentObject(){
		return this;
	}
}
