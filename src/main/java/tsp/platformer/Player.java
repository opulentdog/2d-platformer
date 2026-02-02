package tsp.platformer;

import java.util.HashSet;

import javafx.scene.input.KeyCode;

public class Player extends Sprite {
	private double xVelocity=0;
	private double yVelocity=0;
	private int gravity=1;
	private Boolean ground=true;
	
	public Player(String image, int width, int height) {
		super(image, width, height);
	}
	
	public void addxVelocity(double d) {
		this.xVelocity += d;
	}
	public void addyVelocity(double playeryVelocity) {
		this.yVelocity += playeryVelocity;
	}
	
	public void updatePosition(HashSet<KeyCode> pressedKeyset, double playerxVelocity, double playeryVelocity, int winWidth,
			int winHeight, Platform[] platforms) {
		if(pressedKeyset.contains(KeyCode.LEFT)) {
			if(this.ground) {
				this.addxVelocity(-playerxVelocity);
			}
			this.addxVelocity(-playerxVelocity);
		}
		if(pressedKeyset.contains(KeyCode.RIGHT)) {
			if(this.ground) {
				this.addxVelocity(playerxVelocity);
			}
			this.addxVelocity(playerxVelocity);
		}
		if(pressedKeyset.contains(KeyCode.UP) && this.ground) {
			this.addyVelocity(-playeryVelocity);
			this.ground=false;
		}
		if(pressedKeyset.contains(KeyCode.DOWN)) {
			this.addyVelocity(playeryVelocity);
		}
		yVelocity+=gravity;
		xVelocity = 0.97 * xVelocity;
		yVelocity = 0.97 * yVelocity;
		this.x = this.x + xVelocity;
		this.y = this.y + yVelocity;
		if (x < 0) {
			xVelocity = -xVelocity;

			//xVelocity=0;
			x = 0;
		}else if (x+width > winWidth){
			xVelocity = -xVelocity;

			x = winWidth-width;
			//xVelocity=0;
		}
		if(y < 0) {
			y=0;
			//yVelocity = -yVelocity;
			yVelocity=0;
		}else if (y+height > winHeight){
			y=winHeight-height;
			yVelocity=0;
			ground=true;
			//yVelocity = -yVelocity;
		}
		for(Platform platform : platforms) {
			if(platform.intersects(this)) {
				y=platform.y-this.height;
				if(yVelocity>0) {
				yVelocity=0;
				ground=true;
				}
			}
		}
	}

}
