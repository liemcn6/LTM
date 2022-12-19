package socket.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class StudentSocket {
	Socket socket = null;

	ObjectInputStream inp;
	ObjectOutputStream outp;
	DataInputStream in;
	DataOutputStream out;
	String ip = "14.177.7.124";
	int port = 11100;

	String response[];
	public StudentSocket() {

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
	public StudentSocket(String hostname) {
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
	
}
