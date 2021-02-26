import java.awt.Color;
import java.awt.Graphics;

public class HumPad implements Pad{
	
	double y, yVel;
	boolean upAcc, downAcc;
	int player, x;
	final double GRAVITY = 0.94;
	
	public HumPad(int player) {
		upAcc = false;
		downAcc = false;
		y = 210;
		yVel = 0;
		if(player == 1) {
			x = 20;
		}
		else {
			x = 660;
		}
	}
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, (int)y, 20, 80);
	}

	public void move() {
		if(upAcc) {
			yVel -= 2;
		}
		else if(downAcc) {
			yVel += 2;
		}
		else if(!upAcc && !downAcc) {
			yVel *= GRAVITY;
		}
		
		if(yVel >= 5) {
			yVel = 5;
		}
		
		else if(yVel <= -5) {
			yVel = -5;
		}
		
		y += yVel;
		
		if(y < 0) {
			y = 0;
		}
		
		if(y > 520) {
			y = 520;
		}
		
	}
	
	public void setUpAcc(boolean input) {
		upAcc = input;
	}
	
	public void setDownAcc(boolean input) {
		downAcc = input;
	}

	public int getY() {
		return (int)y;
	}

}
