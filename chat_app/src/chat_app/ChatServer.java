package chat_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;

public class ChatServer {

    /**
     * The port that the server listens on.
     */
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

    /**
     * A handler thread class.  Handlers are spawned from the listening
     * loop and are responsible for a dealing with a single client
     * and broadcasting its messages.
     */
    private static class Handler extends Thread {
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private ObjectOutputStream outObj;

        /**
         * Constructs a handler thread, squirreling away the socket.
         * All the interesting work is done in the run method.
         */
        public Handler(Socket socket) {
            this.socket = socket;
        }

        /**
         * Services this thread's client by repeatedly requesting a
         * screen name until a unique one has been submitted, then
         * acknowledges the name and registers the output stream for
         * the client in a global set, then repeatedly gets inputs and
         * broadcasts them.
         */
        public void run() {
            try {

                // Create character streams for the socket.
                in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                
                outObj = new ObjectOutputStream(socket.getOutputStream());

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

                // Now that a successful name has been chosen, add the
                // socket's print writer to the set of all writers so
                // this client can receive broadcast messages.
                
                out.println("NAMEACCEPTED");
                System.out.println(name + " đã tham gia");
                writers.put(name, out);

                // Accept messages from this client and broadcast them.
                // Ignore other clients that cannot be broadcasted to.
                while (true) {
                    String input = in.readLine();
                    
                    if (input == null) {
                        return;
                    }
                    
                    if(input.startsWith("SENDTO")){
                    	String s = input.substring(7).trim();
                        String sendTo = s.substring(0,s.indexOf(" "));
                        
                        PrintWriter print = writers.get(sendTo);
                        String mess = s.substring(s.indexOf(" ")+1).trim();
                        
                        out.println("MESSAGE to " + sendTo + ": " + mess);
                        print.println("MESSAGE from " + name + ": " + mess);
                        continue;
                    }
                    
                    if (input.equals("UPDATECLIENT")) {
                    	out.println("UPDATECLIENT");
                    	outObj.writeObject(names);
                    	outObj.reset();
                    	out.println("MESSAGE ");
                    	continue;
                    }
                    
                    for (String s: names) {
                    	PrintWriter writer = writers.get(s);
                    	writer.println("MESSAGE " + name + ": " + input);
                    }
                }
            } catch (IOException e) {
                System.out.println(name + " đã thoát");
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
