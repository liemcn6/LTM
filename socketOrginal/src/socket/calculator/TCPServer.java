package socket.calculator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPServer {
	// Khai bao server socket, luong vao-ra, va doi tuong socket
		ServerSocket myServer = null;
		int a,b;
		DataInputStream is;
		DataOutputStream os;
		Socket clientSocket = null;

		// Mo mot server socket
		public void openServer() {
			try {
				myServer = new ServerSocket(9999);
			} catch (IOException e) {
				System.out.println(e);
			}
		}

		// Chap nhan ket noi va xu li du lieu
		public void listening() {
			try {
				// Xu li du lieu nhan duoc va tra ve
				while (true) {
					clientSocket = myServer.accept();
					is = new DataInputStream(clientSocket.getInputStream());
					os = new DataOutputStream(clientSocket.getOutputStream());
					// doc du lieu vao
					a = is.readInt();
					b = is.readInt();
					
					os.writeInt(a+b);
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}

		public void close(){
			try {
				myServer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void main(String[] args) {
			TCPServer server  = new TCPServer();
			server.openServer();
			server.listening();
			
		}
}
