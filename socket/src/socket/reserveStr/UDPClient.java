package socket.reserveStr;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UDPClient {
	// khai bao socket cho client, cong gui va nhan du lieu
	DatagramSocket mySocket = null;
	int port = 8910;

	// Tao ket noi
	public void connection() {
		try {

//			try {
				mySocket = new DatagramSocket();
//				InetAddress address = InetAddress.getByName("10.170.77.49");
//				mySocket.connect(address, port);
//			} catch (UnknownHostException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		} catch (SocketException e) {
			System.err.println(e);
		}
	}

	// gui du lieu den server
	public void send(String str) {
		if (mySocket != null) {
			byte[] sendData = new byte[1024]; // bo dem gui dl
		
			try {
				
				//SV sv = new SV();
				//ByteArrayOutputStream a = new ByteArrayOutputStream();
				sendData =   str.getBytes();
				InetAddress address = InetAddress.getByName("localHost");
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
				
//				ByteArrayInputStream bis = new ByteArrayInputStream(receiveData);
//				ObjectInput in = null;
//				in = new ObjectInputStream(bis);
//				ReverseString str =  (ReverseString) in.readObject();
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
		System.out.println("Nhap xau can dao");
		String str = scan.nextLine();
		client.send(str);
		System.out.println("Xau dao la  " + client.receive());
	}
}