package chattingcode;

import java.awt.BorderLayout;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChattFrame extends JFrame {
	
	JPanel northPanel, southPanel;
	
	public ChattFrame() {
		setTitle("MyChat1.0");
		setSize(300, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		northPanel = new JPanel();
		southPanel = new JPanel();
		add(northPanel, BorderLayout.NORTH);
		add(southPanel, BorderLayout.SOUTH);
		   JButton btnNorth = new JButton("����");
//	        JButton btnEast = new JButton("����");
//	        JButton btnWest = new JButton("����");
	        JButton btnSouth = new JButton("����");
//	  //      JButton btnCenter = new JButton("���");

//	        panel.add(btnNorth, BorderConstant.NORTH);
//	        panel.add(btnEast, BorderConstant.EAST);
//	        panel.add(btnWest, BorderConstant.WEST);
//	        panel.add(btnSouth, BorderConstant.SOUTH);
//	        panel.add(btnCenter, BorderConstant.CENTER);

//	        panel.add(btnNorth, BorderLayout.NORTH);
//	    //    panel.add(btnEast, BorderLayout.EAST);
//	        panel.add(btnWest, BorderLayout.WEST);
//	    //    panel.add(btnSouth, BorderLayout.SOUTH);
//	        panel.add(btnCenter, BorderLayout.CENTER);

		
		setVisible(true);

	}

	public static void main(String[] args) {
		new ChattFrame();
	}
}