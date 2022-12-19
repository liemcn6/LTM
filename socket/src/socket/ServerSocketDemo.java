package socket;

import java.net.*;
import java.io.*;

public class ServerSocketDemo {

	ServerSocket serverSocket;

	public static final int PORT = 10745;

	DataInputStream in;
	DataOutputStream out;

	InetAddress ip = null;

	String request[];

	public ServerSocketDemo() {
		request = new String[3];
		try {

			ip = InetAddress.getByName("localhost");
			
			serverSocket = new ServerSocket(PORT);
			Socket socket = serverSocket.accept();
			
			System.out.println("This is server:" + String.valueOf(ip) + PORT);
			
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			
			request[0] = in.readUTF();
			request[1] = in.readUTF();
			request[2] = in.readUTF();
			
			System.out.println("Received messages form client is:");
			System.out.println(request[0]);
			System.out.println(request[1]);
			System.out.println(request[2]);

			out.writeUTF("xin chao client, thong bao tu server day!");
			out.writeUTF("Your ip is:" + request[1]);
			out.writeUTF("Your port is:" + request[2]);
			
			in.close();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		ServerSocketDemo demo = new ServerSocketDemo();

	}

}