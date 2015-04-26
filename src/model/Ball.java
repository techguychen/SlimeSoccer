package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Ball {
	public int x, y, yBottom;
	public double velocityX = 0, velocityY = 0; 
	BufferedImage ballImage;
	private Integer bounceAccelerationY, bounceAccelerationX;
	private Integer numTimesUpdateWasCalledSinceLastXDecceleration;
	private Game game;
	
	// constants
	public final int width = 24, height = 24, radius = 12;
	public final int mass = 1;
	final private int decceleration = 1; 
	final private double bounceMultiplier = 0.8;

	Ball(int x, int y, Game game) {
		this.x = x;
		this.y = y;
		this.game = game;
		bounceAccelerationY = null;
		bounceAccelerationX = null;
		numTimesUpdateWasCalledSinceLastXDecceleration = 0;
		ballImage = game.imgBall;
	}
	
	public void update() {
		yBottom = y + (height/2);
		
		// Keep ball within bottom boundary
		// 1) If ball is about to hit ground, make sure it doesn't go beneath ground level
		if ( (yBottom + velocityY >= game.groundLevel) && bounceAccelerationY == null) {
			bounceAccelerationY = (int)((double)velocityY * bounceMultiplier);
			velocityY = game.groundLevel - yBottom;
    	} else {
    		velocityY += decceleration;
    	}
		// 2) If ball just hit ground, make it bounce
		if (yBottom >= game.groundLevel && bounceAccelerationY != null) {
			velocityY = -bounceAccelerationY;
			bounceAccelerationY = null;
		}

		// Keep ball within left boundary
		// 1) If ball is about to hit left boundary, make sure it doesn't go past it
		if ( velocityX < 0 && (x - width/2 + velocityX <= game.leftBoundary) && bounceAccelerationX == null) {
			bounceAccelerationX = (int)((double)-velocityX * bounceMultiplier);
			velocityX = game.leftBoundary - (x - width/2);
    	}
		// 2) If ball just hit ground, make it bounce
		if (x - width/2 <= game.leftBoundary && bounceAccelerationX != null) {
			velocityX = bounceAccelerationX;
			bounceAccelerationX = null;
		}

		// Keep ball within right boundary
		// 1) If ball is about to hit right boundary, make sure it doesn't go past it
		if ( velocityX > 0 && (x + width/2 + velocityX >= game.rightBoundary) && bounceAccelerationX == null) {
			bounceAccelerationX = (int)((double)-velocityX * bounceMultiplier);
			velocityX = (game.rightBoundary - x - width/2);
    	}
		// 2) If ball just hit ground, make it bounce
		if (x + width/2 >= game.rightBoundary && bounceAccelerationX != null) {
			velocityX = bounceAccelerationX;
			bounceAccelerationX = null;
		}
		
		if (numTimesUpdateWasCalledSinceLastXDecceleration > 25) {
			if (velocityX > 0) {
				velocityX -= decceleration;
			} else if (velocityX < 0) {
				velocityX += decceleration;
			}
			numTimesUpdateWasCalledSinceLastXDecceleration = 0;
		}

        // Moves the ball
        x += velocityX;
        y += velocityY;
        
        numTimesUpdateWasCalledSinceLastXDecceleration++;
	}
	
	public void callMove() {
        x += velocityX;
        y += velocityY;
	}

	public void paint(Graphics g) {
//		g.setColor(Color.GREEN);
//		g.fillArc(getXforPaint(), getYforPaint(), this.width, this.height, 0, 360);
		g.drawImage(ballImage, getXforPaint(), getYforPaint(), null);
	}

	private int getXforPaint() {
		return (this.x - (width/2));
	}
	private int getYforPaint() {
		return (this.y - (height/2));
	}

}
