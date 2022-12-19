package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import socket.reserveStr.Student;

public class SocketTest {
	public SocketTest(){
		try {
			Socket client = new Socket("14.177.7.124",11100);
			ObjectInputStream in = (ObjectInputStream) client.getInputStream();
			ObjectOutputStream out = (ObjectOutputStream) client.getOutputStream();
			out.writeObject(new Student("B18DCCN325", "Nguyen Quang Liem", 4));
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
