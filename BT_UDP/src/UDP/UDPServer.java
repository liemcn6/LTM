package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer {
	DatagramSocket myServer = null;
	String input;
	int port = 5000;

	// Mo mot server socket
	public void openServer() {
		try {
			myServer = new DatagramSocket(port);
		} catch (SocketException e) {
			System.out.println(e);
		}
	}

	public void listening() {
		byte[] receiveData = new byte[1024];
		int sendLengthData;
		while (true) {
			try {
				// Nhan du lieu
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				myServer.receive(receivePacket);
				
				
				input = new String(receivePacket.getData());
				
				System.out.println("Nhan du lieu: "+input);
				System.out.println("tu cong "+ receivePacket.getPort()+" may "+ receivePacket.getAddress().getHostName());
				
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
		
				String input1 = "Server da nhan duoc du lieu, ket thuc";
				DatagramPacket sendPacket = new 
						DatagramPacket(input1.getBytes(), input1.length(), 
								IPAddress, port);
				
				// Gui du lieu ve client
				myServer.send(sendPacket);
				
			} catch (SocketException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
	public static void main(String[] args) {
		UDPServer server = new UDPServer();
			server.openServer();
			server.listening();
	}
}
