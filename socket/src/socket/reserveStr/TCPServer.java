package socket.reserveStr;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer extends Thread {
	// Khai bao server socket, luong vao-ra, va doi tuong socket
	ServerSocket myServer = null;
	String input;
	DataInputStream is;
	DataOutputStream os;
	Socket clientSocket = null;

	// Mo mot server socket
	public void openServer() {
		try {
			myServer = new ServerSocket(11100);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	@Override
	public void run() {
		try {
			super.run();

			clientSocket = myServer.accept();
			System.out.println("Get new connection from " + clientSocket.getInetAddress().getHostName());

			is = new DataInputStream(clientSocket.getInputStream());
			os = new DataOutputStream(clientSocket.getOutputStream());
			// doc du lieu vao
			input = is.readUTF();
			System.out.println("Get string " + input + " from " + clientSocket.getInetAddress().getHostAddress());
			// xu li du lieu
			ReverseString str = new ReverseString(input);
			str.reverse();
			// tra ve du lieu
			os.writeUTF(str.get_string());
		} catch (Exception e) {
			try {
				clientSocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			System.out.println("Connection error");
			// TODO: handle finally clause
		}
	}

	// Chap nhan ket noi va xu li du lieu
	public void listening() {
		try {
			// Xu li du lieu nhan duoc va tra ve
			while (true) {
				try {
					clientSocket = myServer.accept();
					
					System.out.println("Get new connection from " + clientSocket.getInetAddress().getHostName());

					is = new DataInputStream(clientSocket.getInputStream());
					os = new DataOutputStream(clientSocket.getOutputStream());
					// doc du lieu vao
					input = is.readUTF();
					System.out
							.println("Get string " + input + " from " + clientSocket.getInetAddress().getHostAddress());
					// xu li du lieu
					ReverseString str = new ReverseString(input);
					str.reverse();
					// tra ve du lieu
					os.writeUTF(str.get_string());
				} catch (Exception e) {
					try {
						clientSocket.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
				} finally {
					System.out.println("Get new connection from " + clientSocket.getInetAddress().getHostName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			myServer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TCPServer server = new TCPServer();
		server.openServer();
		server.listening();
		// server.start();

	}
}
