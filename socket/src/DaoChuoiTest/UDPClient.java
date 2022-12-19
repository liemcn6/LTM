package DaoChuoiTest;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPClient {
	DatagramSocket mySocket = null;
	int port = 9099;

	// Tao ket noi
	public void connection() {
		try {
			mySocket = new DatagramSocket();
		} catch (SocketException e) {
			System.err.println(e);
		}
	}

	public void send(String str) {
		if (mySocket != null) {
			byte[] sendData = new byte[1024]; // bo dem gui dl
			try {
				InetAddress IPAddress = InetAddress.getByName("localhost");
				mySocket.connect(IPAddress, port);

				sendData = str.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
				mySocket.send(sendPacket);
			} catch (SocketException e) {
				System.err.println(e);
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}

	public String receive() {
		if (mySocket != null) {
			byte[] receiveData = new byte[1024]; // bo dem nhan dl
			try {
				DatagramPacket receivePacket =

						new DatagramPacket(receiveData, receiveData.length);

				mySocket.receive(receivePacket);
				return new String(receivePacket.getData());
			} catch (SocketException e) {
				System.err.println(e);

			} catch (IOException e) {

				System.err.println(e);

			}

		}
		return null;
	}

	// dong cac ket noi
	public void close() {
		if (mySocket != null) {

			try {
				mySocket.close();
			} catch (Exception e)

			{
				System.err.println(e);

			}
		}
	}
	public static void main(String[] args) {
		UDPClient client = new UDPClient();
		client.connection();
		
		System.out.println("Nhap xau can dao");
		Scanner scan = new Scanner(System.in);
		
		String str = scan.nextLine();
		
		client.send(str);
		
		System.out.println("Xau dao nhan tu server la " + client.receive());
	}

}
