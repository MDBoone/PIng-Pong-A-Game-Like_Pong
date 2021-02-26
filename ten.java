import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ten extends Applet implements Runnable, KeyListener{
	
	final int WIDTH = 700, HEIGHT = 600;
	
	Thread thread;
	HumPad p1;
	AiPad p2;
	Ball b1;
	boolean gameStarted;
	Graphics gfx;
	Image img;
	
	public void init() {
		this.resize(WIDTH, HEIGHT);
		gameStarted = false;
		this.addKeyListener(this);
		p1 = new HumPad(1);
		b1 = new Ball();
		p2 = new AiPad(2, b1);
		img = createImage (WIDTH, HEIGHT);
		gfx = img.getGraphics();
		thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics g) {
		int player1 = 0;
		int aiScore = 0;
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, WIDTH, HEIGHT);
		if(b1.getX() < -10 || b1.getX() > 710) {
			gfx.setColor(Color.CYAN);
			//gameStarted = false;
			//gfx.drawString("game Over", 320, 250);
			//gfx.drawString("Press Enter to Begin..", 290, 150);
		}
		if(b1.getX() >= 710) {
			player1++;
			gfx.drawString("PLAYER SCORES!", 320, 250);
			gfx.drawString("P1 score: " + player1, 320, 120);	
		}
		if(b1.getX() <= -10) {
			aiScore++;
			gfx.drawString("COMPUTER SCORES!", 290, 150);
			gfx.drawString("Comp score: " + aiScore, 320, 120);
		}
		else {
			p1.draw(gfx);
			b1.draw(gfx);
			p2.draw(gfx);
		}
		
		if(gameStarted != true && aiScore == 0) {
			gfx.setColor(Color.white);
			gfx.drawString("PINGPING", 320, 120);
			gfx.drawString("Press Enter to Begin..", 290, 150);
		}
		
		g.drawImage(img, 0, 0, this);
		
		
	}
	
	public void update(Graphics g) {
		paint(g);
	}

	public void run() {
		for(;;) {
			if(gameStarted) {
				p1.move();
				p2.move();
				b1.move();
				b1.checkPaddleCollision(p1, p2);
			}
			repaint();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			p1.setUpAcc(true);
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p1.setDownAcc(true);
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			gameStarted = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			p1.setUpAcc(false);
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p1.setDownAcc(false);
		}
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
