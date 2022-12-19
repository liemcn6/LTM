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

public class ClientServer {
	Socket socket = null;

	ObjectOutputStream outObj;
	DataOutputStream out;
	ObjectInputStream inObj;
	DataInputStream in;
	
	

	String ip = "14.226.25.185";
	int port = 10300;

	public ClientServer() {
		try {
			socket = new Socket(ip, port);

			outObj = new ObjectOutputStream(socket.getOutputStream());
			out = new DataOutputStream(socket.getOutputStream());
			inObj = new ObjectInputStream(socket.getInputStream());
			in = new DataInputStream(socket.getInputStream());

			String ipLocal = String.valueOf(socket.getLocalAddress());
			Student stu = new Student("B18DCCN325", "Nguyen Quang Liem", ipLocal, 4);
			Answer ans = new Answer(stu);

			
            outObj.writeObject(stu);
            out.writeUTF(stu.getHovaten());
            out.writeUTF(stu.getMaSV()); 
            out.writeInt(stu.getGroup());
                       
            System.out.println(in.readUTF());
		}
		 catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ClientServer cs = new ClientServer();
	}
}
