package socket.calculator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {
	// khai bao socket cho client, luong vao-ra
		Socket mySocket = null;
		DataOutputStream os = null;
		DataInputStream is = null;

		// Tao ket noi
		public void connection() {
			try {
				mySocket = new Socket("localhost", 9999);
				os = new DataOutputStream(mySocket.getOutputStream());
				is = new DataInputStream(mySocket.getInputStream());
			} catch (UnknownHostException e) {
				System.err.println(e);
			} catch (IOException e) {
				System.err.println(e);
			}
		}

		public void send(int value) { // gui du lieu den server if (mySocket !=
										// null && os != null) {
			try {
				os.writeInt(value);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println(e);
			}
		}

		

		public int receive() { // nhan du lieu tra ve tu server
			if (mySocket != null && is != null) {
				try {
					int value;
					value = is.readInt();
						return value;
				} catch (UnknownHostException e) {
					System.err.println(e);
				} catch (IOException e) {
					System.err.println(e);
				}
			}
			return 0;
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
			System.out.println("Nhap a: ");
			Scanner scan = new Scanner(System.in);
			
			int a = scan.nextInt();
			System.out.println("Nhap b: ");
			
			int b = scan.nextInt();
			
			client.send(a);
			client.send(b);
			
			System.out.println("Tong la " + client.receive());
			
		}
}
