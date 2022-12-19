package socket.reserveStr;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {
	// khai bao socket cho client, luong vao-ra
	Socket mySocket = null;
	ObjectInputStream in=null;
	ObjectOutputStream out=null;
	DataOutputStream os = null;
	DataInputStream is = null;

	// Tao ket noi
	public void connection() {
		try {
//			InetAddress inet = InetAddress.getByName("localhost");
//			System.out.println(inet.getHostName());
			mySocket = new Socket("14.177.7.124", 11100);
			in= new ObjectInputStream(mySocket.getInputStream());
			out= new ObjectOutputStream(mySocket.getOutputStream());
			os = new DataOutputStream(mySocket.getOutputStream());
			is = new DataInputStream(mySocket.getInputStream());
		} catch (UnknownHostException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public void send(String str) { // gui du lieu den server if (mySocket !=
									// null && os != null) {
		try {
			
//			out.writeUTF(str);
			os.writeUTF(str);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public String receive() { // nhan du lieu tra ve tu server
		if (mySocket != null && is != null) {
			try {
				String responseStr;
				if ((responseStr = is.readUTF()) != null) {
					return responseStr;
				}
			} catch (UnknownHostException e) {
				System.err.println(e);
			} catch (IOException e) {
				System.err.println(e);
			}
		}
		return null;
	}

	// dong cac ket noi
	public void close() {
		if (mySocket != null && os != null && is != null) {
			try {
				os.close();
				is.close();
				mySocket.close();
			} catch (UnknownHostException e) {
				System.err.println(e);
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}

	public static void main(String[] args) {
		TCPClient client = new TCPClient();
		client.connection();
//		
//		System.out.println("Nhap xau can dao");
//		Scanner scan = new Scanner(System.in);
		Student stu= new Student("B18DCCN325", "Nguyen Quang Liem", 4);
		String str = stu.getMaSV();
		
		client.send(str);
		
//		System.out.println("Xau dao nhan tu server la " + client.receive());
		
	}
}
