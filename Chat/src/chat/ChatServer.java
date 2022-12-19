/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author asus
 */
public class ChatServer {
    private static final int PORT = 9001;
    private static HashSet<String> names = new HashSet<String>();
    private static HashMap<String,PrintWriter> writers = new HashMap<String,PrintWriter>();
    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }

    private static class Handler extends Thread{
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        public Handler(Socket socket) {
            this.socket = socket;
        }
        
        public void run() {
            try {

                // Create character streams for the socket.
                in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Request a name from this client.  Keep requesting until
                // a name is submitted that is not already used.  Note that
                // checking for the existence of a name and adding the name
                // must be done while locking the set of names.
                while (true) {
                    out.println("SUBMITNAME");
                    name = in.readLine();
                    if (name == null) {
                        return;
                    }
                    synchronized (names) {
                        if (!names.contains(name)) {
                            names.add(name);
                            break;
                        }
                    }
                }
                
                System.out.println(name + " join");

                // Now that a successful name has been chosen, add the
                // socket's print writer to the set of all writers so
                // this client can receive broadcast messages.
                out.println("NAMEACCEPTED");
                synchronized (writers){
                    writers.put(name,out);
                }

                // Accept messages from this client and broadcast them.
                // Ignore other clients that cannot be broadcasted to.
                while (true) {
                    String read = in.readLine();
                    System.out.println(read+"\n");
                    if(read.startsWith("SENDTO")){
                        String s = read.substring(7).trim();
                        String sendTo = s.substring(0,s.indexOf(" "));
                        PrintWriter print = writers.get(sendTo);
                        String mess = s.substring(s.indexOf(" ")+1).trim();
                        print.println("MESSAGE " + name + " " + mess);
                        System.out.println(name + " send to " + sendTo+"\n");
                    }
                    else if(read.startsWith("SENDTOALL")){
                        
                        String mess = read.substring(10).trim();
                        PrintWriter print;
                        for(String s:names){
                            if (s == name) continue;
                            else {
                                print = writers.get(s);
                                print.println("MESSAGEALL "+ name + mess);
                            } 
                        }
                        System.out.println(name + " send to All\n");
                    }
                    else if(read.startsWith("GETALL")){
                        String allName = "";
                        for(String s :names){
                           allName += s +" ";
                        }
                        out.println("SENDALL" + allName);
                        System.out.println(name + "get list onl\n");
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                // This client is going down!  Remove its name and its print
                // writer from the sets, and close its socket.
                if (name != null) {
                    names.remove(name);
                }
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
