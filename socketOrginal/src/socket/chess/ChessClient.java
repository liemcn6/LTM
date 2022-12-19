package socket.chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import java.io.*;
import java.net.*;
import java.util.*;

/* Chess client application class */

public class ChessClient extends JFrame {
	private ChessBoard board;
	private BorderLayout layout;

	private JMenuBar bar = new JMenuBar();
	private JMenu gameMenu = new JMenu("Game");
	private JMenuItem resetItem = new JMenuItem("Reset");
	private JMenuItem exitItem = new JMenuItem("Exit");

	public ChessClient() {
		super("Chess Client");
		Container cont = this.getContentPane();

		layout = new BorderLayout(1, 1);
		cont.setLayout(layout);

		board = new ChessBoard(this);
		cont.add(board, BorderLayout.CENTER);

		setJMenuBar(bar);

		gameMenu.add(resetItem);
		gameMenu.add(exitItem);

		bar.add(gameMenu);

		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		resetItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.resetBoard();
			}
		});

		setResizable(false);
		setSize(400, 445);

	}

	public static void main(String[] args) {
		ChessClient cc = new ChessClient();

		cc.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		cc.show();
	}

}

interface ChessMen {
	public static int WKING = 0;
	public static int WQUEEN = 1;
	public static int WROOK = 2;
	public static int WBISHOP = 3;
	public static int WKNIGHT = 4;
	public static int WPAWN = 5;
	public static int BKING = 6;
	public static int BQUEEN = 7;
	public static int BROOK = 8;
	public static int BBISHOP = 9;
	public static int BKNIGHT = 10;
	public static int BPAWN = 11;
	public static int NOTHING = 12;
	public static int BLACK = 13;
	public static int WHITE = 14;
};

/* Chess board class */

class ChessBoard extends JPanel implements ImageObserver, MouseListener, MouseMotionListener {

	BufferedImage image_buffer;
	ChessServerConnection serverconnection;

	private int x, y;

	private ChessClient chessclient;

	private int myColor;

	private boolean myTurn;

	private int grabbed_piece, from_row, from_col, to_row, to_col;

	private int chess_matrix[][] = new int[8][8];

	private String chessmen_files[] = { "wking.gif", "wqueen.gif", "wrook.gif", "wbishop.gif", "wknight.gif",
			"wpawn.gif", "bking.gif", "bqueen.gif", "brook.gif", "bbishop.gif", "bknight.gif", "bpawn.gif" };

	private ImageIcon chessmen_images[] = new ImageIcon[12];

	public ChessBoard(ChessClient cc) {
		chessclient = cc;
		this.setSize(400, 400);
		CreateChessmenImages();
		image_buffer = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		setBoard();
		serverconnection = new ChessServerConnection(this);
		grabbed_piece = ChessMen.NOTHING;
	}

	public void resetBoard() {
		setBoard();
		repaint();
		String encoding = encodeBoard();
		serverconnection.send(encoding);
		serverconnection.send("@RESET");

	}

	private void setBoard() {

		for (int i = 2; i < 6; i++)
			for (int j = 0; j < 8; j++)
				chess_matrix[i][j] = ChessMen.NOTHING;

		chess_matrix[0][0] = ChessMen.BROOK;
		chess_matrix[0][1] = ChessMen.BKNIGHT;
		chess_matrix[0][2] = ChessMen.BBISHOP;
		chess_matrix[0][3] = ChessMen.BQUEEN;
		chess_matrix[0][4] = ChessMen.BKING;
		chess_matrix[0][5] = ChessMen.BBISHOP;
		chess_matrix[0][6] = ChessMen.BKNIGHT;
		chess_matrix[0][7] = ChessMen.BROOK;

		for (int i = 0; i < 8; i++) {
			chess_matrix[1][i] = ChessMen.BPAWN;
			chess_matrix[6][i] = ChessMen.WPAWN;
		}

		chess_matrix[7][0] = ChessMen.WROOK;
		chess_matrix[7][1] = ChessMen.WKNIGHT;
		chess_matrix[7][2] = ChessMen.WBISHOP;
		chess_matrix[7][3] = ChessMen.WQUEEN;
		chess_matrix[7][4] = ChessMen.WKING;
		chess_matrix[7][5] = ChessMen.WBISHOP;
		chess_matrix[7][6] = ChessMen.WKNIGHT;
		chess_matrix[7][7] = ChessMen.WROOK;

	}

	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return true;
	}

	public void paint(Graphics g) {
		Graphics2D gfx = (Graphics2D) g;
		drawOffscreen();
		gfx.drawImage(image_buffer, 0, 0, this);

	}

	private void drawOffscreen() {
		Graphics2D gfx = image_buffer.createGraphics();
		renderChessBoard(gfx);
		if (grabbed_piece != ChessMen.NOTHING)
			gfx.drawImage(chessmen_images[grabbed_piece].getImage(), x - 22, y - 22, this);

	}

	private void renderChessBoard(Graphics2D gfx) {
		int x = 0, y = 0;
		boolean b = false;

		for (int i = 0; i < 8; i++) {
			x = 0;
			b = i % 2 == 0;
			for (int j = 0; j < 8; j++) {
				if (b)
					gfx.setColor(Color.white);
				else
					gfx.setColor(Color.gray);
				b = !b;
				gfx.fillRect(x, y, 50, 50);
				paintChessMan(chess_matrix[i][j], x, y, gfx);
				x += 50;

			}
			y += 50;
		}
	}

	private void paintChessMan(int piece, int x, int y, Graphics2D gfx) {
		if (piece < 0 || piece >= ChessMen.NOTHING)
			return;
		gfx.drawImage(chessmen_images[piece].getImage(), x + 2, y + 2, this);

	}

	private void CreateChessmenImages() {
		for (int i = 0; i < chessmen_images.length; i++)
			chessmen_images[i] = new ImageIcon(chessmen_files[i]);
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		from_row = e.getY() / 50;
		from_col = e.getX() / 50;

		if (from_row < 0 || from_row > 7)
			return;
		if (from_col < 0 || from_col > 7)
			return;

		grabbed_piece = chess_matrix[from_row][from_col];

		if ((getPieceType(grabbed_piece) != myColor) || !myTurn) {
			grabbed_piece = ChessMen.NOTHING;
			return;
		}

		chess_matrix[from_row][from_col] = ChessMen.NOTHING;
		x = e.getX();
		y = e.getY();

		repaint();
	}

	public void mouseReleased(MouseEvent e) {
		if (grabbed_piece == ChessMen.NOTHING)
			return;

		to_row = e.getY() / 50;
		to_col = e.getX() / 50;

		if (to_row < 0 || to_row > 7 || to_col < 0 || to_col > 7) {
			chess_matrix[from_row][from_col] = grabbed_piece;
			grabbed_piece = ChessMen.NOTHING;
			repaint();
			return;
		}

		if ((from_row == to_row && from_col == to_col)
				|| !isLegalMove(grabbed_piece, from_row, from_col, to_row, to_col)) {
			chess_matrix[from_row][from_col] = grabbed_piece;
			grabbed_piece = ChessMen.NOTHING;
			repaint();
			return;
		}

		if (isLegalMove(grabbed_piece, from_row, from_col, to_row, to_col))
			chess_matrix[to_row][to_col] = grabbed_piece;
		else
			chess_matrix[from_row][from_col] = grabbed_piece;

		grabbed_piece = ChessMen.NOTHING;

		repaint();

		String encoding = encodeBoard();
		serverconnection.send(encoding);

		serverconnection.send("@TOKEN");

		myTurn = false;

	}

	public void mouseDragged(MouseEvent e) {
		if (grabbed_piece == ChessMen.NOTHING)
			return;

		x = e.getX();
		y = e.getY();
		repaint();
	}

	public void mouseMoved(MouseEvent e) {
	}

	public int getPieceType(int piece) {
		switch (piece) {
		case ChessMen.WKING:
		case ChessMen.WQUEEN:
		case ChessMen.WROOK:
		case ChessMen.WBISHOP:
		case ChessMen.WKNIGHT:
		case ChessMen.WPAWN:
			return ChessMen.WHITE;
		case ChessMen.BKING:
		case ChessMen.BQUEEN:
		case ChessMen.BROOK:
		case ChessMen.BBISHOP:
		case ChessMen.BKNIGHT:
		case ChessMen.BPAWN:
			return ChessMen.BLACK;
		}
		return ChessMen.NOTHING;
	}

	boolean isLegalMove(int piece, int from_row, int from_col, int to_row, int to_col) {

		if (getPieceType(piece) == getPieceType(chess_matrix[to_row][to_col]))
			return false;

		switch (piece) {
		case ChessMen.WPAWN:
			if ((from_row - to_row) == 1 && from_col == to_col)
				return true;
			return false;
		case ChessMen.BPAWN:
		}

		return true;
	}

	public synchronized void receiveData(String line) {
		if (line.charAt(0) == '@') {
			processCommand(line);
			return;
		}
		decodeBoard(line);
		repaint();

	}

	private void processCommand(String command) {
		if (command.compareTo("@BLACK") == 0) {
			myColor = ChessMen.BLACK;
			chessclient.setTitle("Chess Client - BLACK");
			resetBoard();
		} else if (command.compareTo("@WHITE") == 0) {
			System.out.println("I am WHITE");
			myColor = ChessMen.WHITE;
			chessclient.setTitle("Chess Client - WHITE");
			resetBoard();
			myTurn = true;
		}
		if (command.compareTo("@RESET") == 0) {
			if (myColor == ChessMen.WHITE)
				myTurn = true;
		} else if (command.compareTo("@TOKEN") == 0)
			myTurn = true;
	}

	/*
	 * 
	 * Piece codes:
	 * 
	 * A - wking B - wqueen C - wrook D - wbishop E - wknight F - wpawn G -
	 * bking H - bqueen I - brook J - bbishop K - bknight L - bpawn M - nothing
	 * 
	 * 
	 */

	public String encodeBoard() {
		String encoding = "";
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				switch (chess_matrix[i][j]) {
				case ChessMen.WKING:
					encoding += "A";
					break;
				case ChessMen.WQUEEN:
					encoding += "B";
					break;
				case ChessMen.WROOK:
					encoding += "C";
					break;
				case ChessMen.WBISHOP:
					encoding += "D";
					break;
				case ChessMen.WKNIGHT:
					encoding += "E";
					break;
				case ChessMen.WPAWN:
					encoding += "F";
					break;
				case ChessMen.BKING:
					encoding += "G";
					break;
				case ChessMen.BQUEEN:
					encoding += "H";
					break;
				case ChessMen.BROOK:
					encoding += "I";
					break;
				case ChessMen.BBISHOP:
					encoding += "J";
					break;
				case ChessMen.BKNIGHT:
					encoding += "K";
					break;
				case ChessMen.BPAWN:
					encoding += "L";
					break;
				case ChessMen.NOTHING:
					encoding += "M";
					break;
				}
			}

		return encoding;
	}

	public void decodeBoard(String encoding) {
		int row, col;
		char piece;

		if (encoding.length() < 64)
			return;

		for (int i = 0; i < 64; i++) {
			row = i / 8;
			col = i % 8;
			piece = encoding.charAt(i);

			switch (piece) {
			case 'A':
				chess_matrix[row][col] = ChessMen.WKING;
				break;
			case 'B':
				chess_matrix[row][col] = ChessMen.WQUEEN;
				break;
			case 'C':
				chess_matrix[row][col] = ChessMen.WROOK;
				break;
			case 'D':
				chess_matrix[row][col] = ChessMen.WBISHOP;
				break;
			case 'E':
				chess_matrix[row][col] = ChessMen.WKNIGHT;
				break;
			case 'F':
				chess_matrix[row][col] = ChessMen.WPAWN;
				break;
			case 'G':
				chess_matrix[row][col] = ChessMen.BKING;
				break;
			case 'H':
				chess_matrix[row][col] = ChessMen.BQUEEN;
				break;
			case 'I':
				chess_matrix[row][col] = ChessMen.BROOK;
				break;
			case 'J':
				chess_matrix[row][col] = ChessMen.BBISHOP;
				break;
			case 'K':
				chess_matrix[row][col] = ChessMen.BKNIGHT;
				break;
			case 'L':
				chess_matrix[row][col] = ChessMen.BPAWN;
				break;
			case 'M':
				chess_matrix[row][col] = ChessMen.NOTHING;
				break;
			}

		}

	}

}

class ChessServerConnection {
	private static final int PORT = 1234;
	private static final String HOST = "localhost";
	InputHandlerThread inputhandler;

	ChessBoard chessboard;

	private Socket sock;
	private BufferedReader in;
	private PrintWriter out;

	public ChessServerConnection(ChessBoard cb) {
		chessboard = cb;

		try {
			sock = new Socket(HOST, PORT);
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintWriter(sock.getOutputStream(), true);
			inputhandler = new InputHandlerThread(this, in);
			inputhandler.start();
		}

		catch (Exception e) {
			System.out.println(e);
		}
	}

	public void send(String line) {
		out.println(line);
	}

	public synchronized void reply(String line) {
		chessboard.receiveData(line);
	}

}

class InputHandlerThread extends Thread {

	private BufferedReader in;
	ChessServerConnection srvcon;
	boolean done;

	public InputHandlerThread(ChessServerConnection con, BufferedReader i) {
		srvcon = con;
		done = false;
		in = i;
	}

	public void run() {
		String line;
		try {
			while (!done) {
				if ((line = in.readLine()) == null)
					done = true;
				else
					srvcon.reply(line);
				if (done == false)
					System.out.println("Received board data from server" + line);
			}
		}

		catch (Exception e) {
			System.out.println(e);
		}
	}

}
