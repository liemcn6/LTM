/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Answer;
import model.Student;
/**
 *
 * @author DELL
 */
public class ClientServer {
    Socket socket = null;

	DataInputStream in;
	DataOutputStream out;
	ObjectInputStream inObj;
	ObjectOutputStream outObj;
	
	String ip = "14.177.241.173";
	int port = 11300;

    public ClientServer() {
        try {
            socket = new Socket(ip, port);
            outObj = new ObjectOutputStream(socket.getOutputStream());
            inObj = new ObjectInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            String ipLocal = String.valueOf(socket.getLocalAddress());
            Student stu= new Student("B18DCCN369","Vũ Hoàng Long" , ip, 4);
            Answer ans=new Answer(stu);
            out.writeInt(stu.getGroup());
            outObj.writeObject(stu);
            out.writeUTF(stu.getHovaten());
            out.writeUTF(stu.getMaSV()); 
                       
            System.out.println(in.readUTF());
        } catch (IOException ex) {
            ex.printStackTrace();
        } 	
    }
      
    public static void main(String[] args) {
        ClientServer cs= new ClientServer();
    }
}
