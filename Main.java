
import java.awt.*;
import javax.swing.JFrame;


public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame("Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 600);
		frame.getContentPane().setLayout(new BorderLayout());
		

		SpaceShip s = new SpaceShip(180, 500, 20, 20);
		GamePanel gp = new GamePanel();
		GameEngine ge = new GameEngine(gp, s);
		
		frame.addKeyListener(ge);
		frame.getContentPane().add(gp, BorderLayout.CENTER);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setVisible(true);
		ge.start();
	}
}
