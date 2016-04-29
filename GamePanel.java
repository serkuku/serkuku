

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;	
	Graphics2D big;
	Graphics2D space;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel() {

		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		space = (Graphics2D) bi.getGraphics();
	}

	public void updateGameUI(GameEngine reporter){
		big.clearRect(0, 0, 400, 600);
 		big.drawString(String.format("%08d", reporter.getScore()), 300, 20);
 		big.drawString(String.format("%d", reporter.getLifepoint()), 100, 20);
		for(Sprite s : sprites){
			if(s.width == 5)
				s.draw(big);
			if(s.width == 20)
				s.draw(space);


		}
		
		repaint();
	}



	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
