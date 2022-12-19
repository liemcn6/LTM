/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import View.Client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author asus
 */
public class ChatClient {

    private Client cl;
    private String sendTo;
    private String messFrom;
    BufferedReader in;
    PrintWriter out;

    public ChatClient(Client cl) {
        this.cl = cl;
        sendTo = "";
        messFrom = "";
        cl.addTextListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cl.getMess().trim()!=""){
                    if(sendTo != null||sendTo!=""){
                        out.println("SENDTO "+ sendTo + " " + cl.getMess().trim());
                        cl.setMess("Me", cl.getMess().trim());
                        cl.resetChatText();
                    }
                    else {
                        out.println("SENDTOAll " + cl.getMess().trim());
                        cl.setMessWorld("Me", cl.getMess().trim());
                        cl.resetChatText();
                    }
                }
            }
        });
        cl.addSendAllListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cl.getMess().trim()!=""){
                    out.println("SENDTOAll " + cl.getMess());
                cl.setMessWorld("Me", cl.getMess().trim());
                cl.resetChatText();
                }
            }
        });
        cl.addbtLIstener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("GETALL");
            }
        });
        cl.addNameTextListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendTo = cl.getNameText();
                cl.resetNameText();
                cl.setChattingTo("Chatting to: " + sendTo);
            }
        });
    }
    
    private String getName() {
        return JOptionPane.showInputDialog(
            cl,
            "Choose a screen name:",
            "Screen name selection",
            JOptionPane.PLAIN_MESSAGE);
    }
    
    private void run() throws IOException {

        // Make connection and initialize streams
        
        Socket socket = new Socket("localhost", 9001);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        
        // Process all messages from server, according to the protocol.
        while (true) {
            String line = in.readLine().trim();
            System.out.println(line+"\n");
            if (line.startsWith("SUBMITNAME")) {
                out.println(getName());
            } else if (line.startsWith("NAMEACCEPTED")) {
                JOptionPane.showMessageDialog(cl, "name accepted");
            } else if (line.startsWith("MESSAGE")) {
                String s = line.substring(line.indexOf(" ")).trim();
                messFrom = s.substring(0,s.indexOf(" "));
                String mess = s.substring(s.indexOf(" ")+1);
                cl.setMess(messFrom, mess);
            }
            else if(line.startsWith("MESSAGEALL")){
                String s = line.substring(line.indexOf(" ")).trim();
                messFrom = s.substring(0,s.indexOf(" "));
                String mess = s.substring(s.indexOf(" ")+1);
                cl.setMessWorld(messFrom, mess);
            }
            else if (line.startsWith("SENDALL")){
                String listAll = line.substring(line.indexOf(" ")+1);
                JOptionPane.showMessageDialog(cl, listAll);
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        ChatClient cl = new ChatClient(client);
        client.setVisible(true);
        cl.run();
    }
    
    
}
