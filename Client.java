package control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import model.Answer;
import model.Student;

public class Client {

	Socket socket = null;

	DataInputStream input;
	DataOutputStream output;
	ObjectInputStream readObj;
	ObjectOutputStream outObj;
	
	String ip = "14.226.25.185";
	int port = 10300;
	
	public Client() {

		try {

			socket = new Socket(ip, port);
			
			readObj = new ObjectInputStream(socket.getInputStream());
			input = new DataInputStream(socket.getInputStream());
			outObj = new ObjectOutputStream(socket.getOutputStream());
			output = new DataOutputStream(socket.getOutputStream());
			
			String ipLocal = String.valueOf(socket.getLocalAddress());
			
			Student student = new Student("masv", "name", ipLocal, 4); 
			Answer ans = new Answer();
			
			String mes = input.readUTF();
			System.out.println(mes);
			
			outObj.writeObject(student); 

			input.readInt();
			
			output.writeUTF(student.getHovaten()); // Client, Server, ho va ten

			input.readUTF();
			
			output.writeUTF(student.getMaSV()); // Client, Server, masv
			
			// Server, Client, masv
			input.readUTF(); 
			
			output.writeInt(student.getGroup()); // Client, Server, nhom
			
			// Server, Client, nhom
			input.readInt();
			
			try {
				ans = (Answer) readObj.readObject(); // Server, Client, ans
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
						
			output.writeUTF("Answer"); // Client, Server, "Answer" - string

			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public static void main(String[] args) {
		Client cs = new Client();
	}

}
