package DaoChuoiTest;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.io.IOException;

public class UDPServer {
	DatagramSocket myServer = null;
	String input;
	int port = 9099;

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
		byte[] sendData = new byte[1024];
		while (true) {
			try {
				// Nhan du lieu
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				myServer.receive(receivePacket);
				input = new String(receivePacket.getData());
				// Xu li du lieu
				
				ReverseString str = new ReverseString(input);
				str.reverse();
				
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
				sendData = str.get_string().getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

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
