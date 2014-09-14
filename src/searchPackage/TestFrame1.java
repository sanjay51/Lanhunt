package searchPackage;

import java.net.*;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestFrame1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame1 frame = new TestFrame1();
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
	public TestFrame1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				InetAddress localhost = InetAddress.getLocalHost();
				// this code assumes IPv4 is used
				byte[] ip = localhost.getAddress();
				for (int i = 1; i <= 254; i++)
				{
				    ip[3] = (byte)i;
				    InetAddress address = InetAddress.getByAddress(ip);
				    if (address.isReachable(1000))
				    {
				        // machine is turned on and can be pinged
				    }
				    else if (!address.getHostAddress().equals(address.getHostName()))
				    {
				        // machine is known in a DNS lookup
				    }
				    else
				    {
				        // the host address and host name are equal, meaning the host name could not be resolved
				    }
				}
				}catch(Exception e){
					
				}
			}
		});
		btnNewButton.setBounds(10, 11, 191, 40);
		contentPane.add(btnNewButton);
	}
}
