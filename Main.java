
import java.awt.*;
import javax.swing.JFrame;


public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame("Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 600);
		frame.getContentPane().setLayout(new BorderLayout());
		

		SpaceShip s = new SpaceShip(180, 500, 20, 20);
		GamePanel g = new GamePanel();
		g.sprites.add(s);
 		g.updateGameUI();
		frame.getContentPane().add(g, BorderLayout.CENTER);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setVisible(true);
	}
}
