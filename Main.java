//package f2.spw;

//import java.awt.BorderLayout;

import javax.swing.JFrame;

import java.awt.Color;

public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame("Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 700);
	
		frame.getContentPane().setBackground(Color.PINK);
		frame.setVisible(true);
	}
}
