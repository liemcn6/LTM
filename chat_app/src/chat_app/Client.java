package chat_app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Client extends JFrame {

	private JPanel contentPane;
	private JTextField inputChat;
	private JComboBox<String> listClient = new JComboBox();
	private BufferedReader in;
	private PrintWriter out;
	private ObjectInputStream inObj;
	private static HashSet<String> names = new HashSet<String>();
	private JTextArea messageArea = new JTextArea();
	private JButton updateListClient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		
		Client client = new Client();
		client.setVisible(true);
		client.run();
		
	}

	/**
	 * Create the frame.
	 */
	public Client() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		inputChat = new JTextField();
		inputChat.setEditable(false);
		inputChat.setColumns(10);
		
		inputChat.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e) {
                out.println(inputChat.getText());
                inputChat.setText("");
            }
        });
		
		updateListClient = new JButton("C\u1EADp nh\u1EADt danh s\u00E1ch Client");
		updateListClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.println("UPDATECLIENT");
			}
		});
		
		JButton sendToOne = new JButton("Bấm để gửi tin nhắn đến");
		sendToOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = listClient.getSelectedItem().toString();
				out.println("SENDTO " + name + " " + inputChat.getText());
				inputChat.setText("");
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(messageArea, GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(sendToOne, Alignment.TRAILING)
						.addComponent(inputChat, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(listClient, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addComponent(updateListClient, Alignment.LEADING))
					.addGap(21))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(messageArea, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(inputChat, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
						.addComponent(updateListClient))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(listClient, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(sendToOne))
					.addContainerGap())
		);
		messageArea.setEnabled(false);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	private String getServerAddress() {
        return JOptionPane.showInputDialog(
        	contentPane,
            "Nhập địa chỉ IP của server:",
            "Welcome to the Chatter",
            JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Prompt for and return the desired screen name.
     */
    public String getName() {
        return JOptionPane.showInputDialog(
        	contentPane,
            "Nhập tên của bạn:",
            "Screen name selection",
            JOptionPane.PLAIN_MESSAGE);
    }
    
    private void run() throws IOException {

        // Make connection and initialize streams
        String serverAddress = getServerAddress();
        Socket socket = new Socket(serverAddress, 9001);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        
        inObj = new ObjectInputStream(socket.getInputStream());

        // Process all messages from server, according to the protocol.
        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {
                out.println(getName());
            } else if (line.startsWith("NAMEACCEPTED")) {
            	inputChat.setEditable(true);
            } else if (line.startsWith("MESSAGE")){
                messageArea.append(line.substring(8) + "\n");
            } else if (line.startsWith("UPDATECLIENT")) {
            	try {
					@SuppressWarnings("unchecked")
					HashSet<String> readObject = (HashSet<String>) inObj.readObject();
					names = readObject;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					//e.printStackTrace();
				}
            	
            	listClient.removeAllItems();
            	
            	for (String name : names) {
            		listClient.addItem(name);
            	}
            }
        }
    }
}
