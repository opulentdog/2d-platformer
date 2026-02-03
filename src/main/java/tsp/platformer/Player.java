package tsp.platformer;

import java.util.HashSet;

import javafx.scene.input.KeyCode;

public class Player extends Sprite {
	private double xVelocity=0;
	private double yVelocity=0;
	double playeryVelocity=30;
	double playerxVelocity=1;
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
	public void controlPlayer(HashSet<KeyCode> pressedKeyset) {
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
	}
	
	public void calculatePosition(int windowsWidth,
			int windowsHeight, Platform[] platforms) {


		yVelocity+=gravity;
		xVelocity = 0.97 * xVelocity;
		yVelocity = 0.97 * yVelocity;
		
		/*Bordure de l'écran gestion des collisions*/
		if (x < 0) {
			xVelocity = 0;
			x = 0;
		}else if (x+width > windowsWidth){
			xVelocity = 0;
			x = windowsWidth-width;
		}
		if (y+height > windowsHeight){
			y=windowsHeight-height;
			yVelocity=-playeryVelocity;
			ground=true;
		}
		
		
		/*Collision avec les platformes*/
		for(Platform platform : platforms) {
			if(platform.intersects(this)) {
				//	y=platform.y-this.height;
				if(yVelocity>0) {
				yVelocity=-playeryVelocity;
				ground=true;
				}
			}
		}
		this.x = this.x + xVelocity;
		this.y = this.y + yVelocity;
	}

}
