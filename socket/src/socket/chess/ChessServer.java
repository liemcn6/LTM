package socket.chess;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChessServer {

	private static final int PORT = 1234; // TCP Port
	Vector client_socks = new Vector(1);

	public ChessServer() {

		try {
			ServerSocket serverSock = new ServerSocket(PORT);
			Socket clientSock;
			String cliAddr;

			while (true) {
				System.out.println("Waiting for a client...");
				clientSock = serverSock.accept();

				if (client_socks.size() >= 2) {
					System.out.println("Server connection capacity reached.");
					clientSock.close();
					continue;
				}

				client_socks.addElement(clientSock);

				sendIdentity();

				cliAddr = clientSock.getInetAddress().getHostAddress();
				new ClientHandler(clientSock, cliAddr, this).start();
			}
		}

		catch (Exception e) {
			System.out.println(e);
		}
	}

	private void sendIdentity() {
		PrintWriter out;
		Socket sock;

		System.out.println(client_socks.size());

		if (client_socks.size() >= 1) {

			sock = (Socket) client_socks.get(0);
			try {
				out = new PrintWriter(sock.getOutputStream(), true);
				out.println("@WHITE");
				out.flush();
			}

			catch (Exception e) {
				System.out.println(e);
			}
		}

		if (client_socks.size() == 2) {

			sock = (Socket) client_socks.get(1);
			try {
				out = new PrintWriter(sock.getOutputStream(), true);
				out.println("@BLACK");
				out.flush();

			}

			catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public synchronized void broadcastLine(Socket clientsock, String line)
         {
                 
                 Socket sock;
                 PrintWriter out; 
                 while (client_socks.elements().hasMoreElements())
                 {
                         sock = (Socket) client_socks.elements().nextElement();
                         if (sock != clientsock) 
                         { 
                                 try
                                 {
                                         out = new PrintWriter( sock.getOutputStream(), true );  
                                         out.println(line);
                                         out.flush();
                                         
                                 }
                                 
                                 catch(Exception e)
                         {  
                                         System.out.println(e);  
                         }
                         }
                         
                 }
         }

	public synchronized void removeClient(Socket clientSock) {
		client_socks.removeElement(clientSock);
	}

	public static void main(String args[]) {
		new ChessServer();
	}
}

class ClientHandler extends Thread {
	private Socket clientSock;
	private String cliAddr;
	private ChessServer server;

	public ClientHandler(Socket s, String cliAddr, ChessServer srv) {
		clientSock = s;
		this.cliAddr = cliAddr;
		System.out.println("Client connection from " + cliAddr);
		server = srv;
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
			PrintWriter out = new PrintWriter(clientSock.getOutputStream(), true);

			processClient(in, out);

			clientSock.close();
			System.out.println("Client (" + cliAddr + ") connection closed\n");
			server.removeClient(clientSock);
		}

		catch (Exception e) {
			System.out.println(e);
		}
	}

	private void processClient(BufferedReader in, PrintWriter out) {
		String line;
		boolean done = false;

		try {
			while (!done) {
				if ((line = in.readLine()) == null)
					done = true;
				else {
					System.out.println("Client msg: " + line);
					server.broadcastLine(clientSock, line);
				}
			}
		}

		catch (IOException e) {
			System.out.println(e);
		}

	}

}
