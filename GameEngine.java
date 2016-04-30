
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.geom.Rectangle2D;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<BonusScore> bonuss = new ArrayList<BonusScore>();
	private SpaceShip v;	
	private long lifepoint = 5;
	private Timer timer;
	private long diff = 0;
	private boolean trigger = false;
	private double difficulty = 0.1;
	private long score = 0;
	private double bonus = 0.003;

	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}

	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30, 5, 10,"e");
		gp.sprites.add(e);
		enemies.add(e);
	}

	private void generateBonus(){
		BonusScore b = new BonusScore((int)(Math.random()*390), 30, 10, 10,"b");
 		gp.sprites.add(b);
 		bonuss.add(b);
 	}
	
	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}

		if(Math.random() < bonus){
			generateBonus();
  		}		  		
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();

			if(diff > score % 30)
				trigger = true;

			diff = score % 30;


			if(!e.isAlive()){
				if(e.width == 5){
					score += 1;
					if( ( timer.getDelay() >= 10 ) && ( trigger == true ) ){
						timer.setDelay(timer.getDelay() - 5);
						trigger = false;
					}
				}		

				e_iter.remove();
				gp.sprites.remove(e);

			}
		}

		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				if (e.getClashable() == true && e.width == 5)
 							lifepoint = lifepoint - 1;
 							e.setClashable(false);
				if (lifepoint < 1)
					die();
				return;
			}
		}


		Iterator<BonusScore> b_iter = bonuss.iterator();
		while(b_iter.hasNext()){
			BonusScore b = b_iter.next();
			b.proceed();
			if(!b.isAlive()){
					gp.sprites.remove(b);
					b_iter.remove();
			}
			
			er = b.getRectangle();
			if (er.intersects(vr)){
				gp.sprites.remove(b);
				b_iter.remove();
				score += 50;
			}

		}
		gp.updateGameUI(this);
		
		
	}
	
	public void die(){
		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1);
			break;
		}
	}
	
	public long getScore(){
 		return score;
 	}

 	public long getLifepoint(){
 		return lifepoint;
 	}

	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}

}