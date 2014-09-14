package searchPackage;

import java.net.*;

public class Sender {
	public void send(String msg) {
		try{
		DatagramSocket socket=new DatagramSocket();
		byte[] message=msg.getBytes();
		InetAddress host=InetAddress.getByName("localhost");
		DatagramPacket packet=new DatagramPacket(message,message.length,host,40000);
		socket.send(packet);
		}catch(Exception e) {
			System.out.println("Error: Sender.java - Error creating socket");
		}
	}
}
