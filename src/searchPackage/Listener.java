package searchPackage;

import java.net.*;

public class Listener implements Runnable {
	DatagramSocket socket;
	byte[] buffer;
	DatagramPacket packet;
	String temp;
	
	public void run() {
		try{
		socket=new DatagramSocket(40000);
		buffer=new byte[10000];
		packet=new DatagramPacket(buffer,buffer.length);
		System.out.println("Starting listener..\n");
		
		while(true) {
			socket.receive(packet);
			temp=new String(packet.getData());
			System.out.println("Data Received:"+temp+"\n");
		}
		}catch(Exception e) {
			System.out.println("Error: Listener.java - socket not created.\n");
		}
	}
}
