package rmi.registration;

import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
//import java.util.Scanner;

import control.IRegistration;
import model.Answer;
import model.Student;

public class Client {
	public static void main(String args[]) throws Exception {
		IRegistration i = null;
		int port = 1099;
		try {

			String ipHost = "localhost";
			Registry reg = LocateRegistry.getRegistry(ipHost, port); 
			String l[] = reg.list();
			String name = "";
			for(int j = 0; j < l.length; j++) {
				name += l[j];
			}
			
			InetAddress addr = InetAddress.getLocalHost();

		    String ipLocal = addr.getHostAddress();
			
			Student s = new Student("B18DCCN325", "Nguyen Quang Liem", ipLocal, 4);
			Answer ans = new Answer(s);
			i = (IRegistration) reg.lookup(name);
			
			System.out.println(i.register(s));
			
			System.out.println(i.answer(ans));
			
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
	}
}
