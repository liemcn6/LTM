package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import model.Answer;
import model.Student;

public class ClientSocket {

	Socket socket = null;

	DataInputStream in;
	DataOutputStream out;
	ObjectInputStream inObj;
	ObjectOutputStream outObj;
	
	String ip = "14.226.25.185";
	int port = 10300;
	
	public ClientSocket() {

		try {

			socket = new Socket(ip, port);
			
			outObj = new ObjectOutputStream(socket.getOutputStream());
			inObj = new ObjectInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
			
			String ipLocal = String.valueOf(socket.getLocalAddress());
			
			Student student = new Student("B18DCCN325", "Nguyen Quang Liem", ipLocal, 4);
			Answer ans = new Answer(student);
			
			outObj.writeObject(student);
			out.writeUTF(student.getMaSV()); // ma sinh vien
			out.writeUTF(student.getHovaten()); // ho va ten
			out.writeInt(student.getGroup()); // nhom

			System.out.println(inObj.readObject());
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ClientSocket cs = new ClientSocket();
	}

}
