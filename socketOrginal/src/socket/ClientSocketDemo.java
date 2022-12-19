package socket;

import java.net.*;
import java.io.*;

public class ClientSocketDemo {

	Socket socket = null;

	DataInputStream in;
	DataOutputStream out;
	String ip = "localhost";
	int port = 10745;

	String response[];

	public ClientSocketDemo() {

		try {

			socket = new Socket(ip, port);

			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());

			String ip = String.valueOf(socket.getLocalAddress());
			String port = String.valueOf(socket.getLocalPort());

			out.writeUTF("Xin chao server, thong baos tu  client");
			out.writeUTF(ip);
			out.writeUTF(port);

			response = new String[3];
			for (int i = 0; i < response.length; i++) {

				response[i] = in.readUTF();
				System.out.println(response[i]);

			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ClientSocketDemo(String hostname) {

		try {

			socket = new Socket(hostname, 10745);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());

			String ip = String.valueOf(socket.getLocalAddress());
			String port = String.valueOf(socket.getLocalPort());

			out.writeUTF("Hello Server.This connection is from client.");
			out.writeUTF(ip);
			out.writeUTF(port);

			response = new String[3];
			for (int i = 0; i < response.length; i++) {

				response[i] = in.readUTF();
				System.out.println(response[i]);

			}
			in.close();
			out.close();
			socket.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ClientSocketDemo(String hostname, String serverPort) {

		try {

			socket = new Socket(hostname, Integer.parseInt(serverPort));
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());

			String ip = String.valueOf(socket.getLocalAddress());
			String port = String.valueOf(socket.getLocalPort());

			out.writeUTF("Hello Server.This connection is from client.");
			out.writeUTF(ip);
			out.writeUTF(port);

			response = new String[3];
			for (int i = 0; i < response.length; i++) {

				response[i] = in.readUTF();
				System.out.println(response[i]);

			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		String comd[] = args;
		if (comd.length == 0) {

			System.out.println("Use localhost(127.0.0.1) and default port");
			ClientSocketDemo demo = new ClientSocketDemo();

		} else if (comd.length == 1) {

			System.out.println("Use default port");
			ClientSocketDemo demo = new ClientSocketDemo(args[0]);

		} else if (comd.length == 2) {

			System.out.println("Hostname and port are named by user");
			ClientSocketDemo demo = new ClientSocketDemo(args[0], args[1]);

		} else
			System.out.println("ERROR");

	}

}