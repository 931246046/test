package storm_falcon.swing.socket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintWriter;

public class Window extends JFrame {

	private static final long serialVersionUID = -4560169860038231159L;

	private JTextArea mContent;
	private JTextField mInput;
	private JButton mSend;
	
	private final String name;
	private final PrintWriter out;

	Window(String name, PrintWriter out) {
		super(name);
		initFrame();
		
		this.name = name;
		this.out = out;
		
		sendMsg("%" + name + "& connect to the server.");
		
		setListener();
	}
	
	private void initFrame() {
		setLayout(new BorderLayout());
		
		mContent = new JTextArea();
		mContent.setEditable(false);
		mInput = new JTextField();
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel label = new JLabel();
		label.setText("Input:");
		mSend = new JButton();
		mSend.setText("Send");
		panel.add(label, BorderLayout.WEST);
		panel.add(mInput, BorderLayout.CENTER);
		panel.add(mSend, BorderLayout.EAST);
		
		add(mContent, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
		
		setSize(500, 500);
		setLocation(650, 100);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	
	private void setListener() {
		mInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String msg = mInput.getText();
					mInput.setText("");
					
					sendMsg(name + ":" + msg);
				}
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				out.close();
				setVisible(false);
				System.exit(0);
			}
		});
		
		mSend.addActionListener(e -> {
			String msg = mInput.getText();
			mInput.setText("");

			sendMsg(name + ":" + msg);
		});
	}
	
	void appendMsg(String msg) {
		mContent.append(msg + "\n");
	}
	
	private void sendMsg(String msg) {
		out.println(msg);
		out.flush();
	}
}
