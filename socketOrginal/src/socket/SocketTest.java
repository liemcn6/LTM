package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import socket.reserveStr.Student;

public class SocketTest {
	public SocketTest(){
		try {
			Socket client = new Socket("192.168.0.103",11005);
			ObjectInputStream in = (ObjectInputStream) client.getInputStream();
			ObjectOutputStream out = (ObjectOutputStream) client.getOutputStream();
			out.writeObject(new Student("B18DCCN12345", "Nguyễn Trọng Khánh", "192.168.0.103"));
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
