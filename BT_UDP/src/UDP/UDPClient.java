package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPClient {
	// khai bao socket cho client, cong gui va nhan du lieu
		DatagramSocket mySocket = null;
		int port = 5000;

		// Tao ket noi
		public void connection() {
			try {

					mySocket = new DatagramSocket();
			} catch (SocketException e) {
				System.err.println(e);
			}
		}
		// gui du lieu den server
		public void send(String str) {
			if (mySocket != null) {
				byte[] sendData = new byte[1024]; // bo dem gui dl
			
				try {
					
					sendData =   str.getBytes();
					InetAddress address = InetAddress.getByName("localhost");
					mySocket.connect(address, port);
					
					DatagramPacket sendPacket = new 
							DatagramPacket(sendData, sendData.length);
					mySocket.send(sendPacket);
				} catch (SocketException e) {
					System.err.println(e);
				} catch (IOException e) {
					System.err.println(e);
				}
			}
		}
		// nhan du lieu tra ve tu server
		public String receive() {
			if (mySocket != null) {
				byte[] receiveData = new byte[10000]; // bo dem nhan dl
				try {
					
					DatagramPacket receivePacket = 
							new DatagramPacket(receiveData, receiveData.length);
					mySocket.receive(receivePacket);
					
					String result = new String(receivePacket.getData());
					return result;
				} catch (SocketException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return null;
		}

		// dong cac ket noi
		public void close() {
			if (mySocket != null) {
				{
					try {
						mySocket.close();
					} catch (Exception e) {

					}
				}
			}
		}
		public static void main(String[] args) {
			UDPClient client = new UDPClient();
			client.connection();
			Scanner scan = new Scanner(System.in);
			System.out.println("Nhap chuoi bat ky:");
			String str = scan.nextLine();
			client.send(str);
			System.out.println(client.receive());
			client.close();
		}
}
