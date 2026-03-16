package tsp.engine;

import java.util.HashSet;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import tsp.engine.platforms.Platform;
import tsp.engine.platforms.Platform.PlatformType;
import tsp.engine.platforms.BasicPlatform;
import tsp.graphics.Window;
import tsp.graphics.render.PlayerRender;
import tsp.graphics.Sound;

public class Player extends Asset {
	private boolean dead;
	private double xVelocity=0;
	private double yVelocity=0;
	double playeryVelocity=31;
	double playerxVelocity=3;
	private int gravity=1;
	private Boolean ground=true;
	private PlayerRender playerRender;
//	private Sound soundeff = new Sound("/sounds/sound_effect/SFX_Jump_42.wav.wav");
	
	public Player(String image, int width, int height) {
		super(image, width, height);
		dead = false;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public void kill() {
		dead = true;
	}
 		
	public void addxVelocity(double d) {
		 this.xVelocity += d;
	}
	
	public void addyVelocity(double playeryVelocity) {
		this.yVelocity += playeryVelocity;
	}
	
	/**
	 * TODO Explication de la méthode.
	 * @param pressedKeyset
	 */
	public void controlPlayer(HashSet<KeyCode> pressedKeyset) {
		
		//Vérifie si les touche sont appuyé et modifie la vitesse du joueur
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
	
	/**
	 * TODO javadoc
	 * @param windowsWidth
	 * @param windowsHeight
	 * @param platforms
	 */
	public void calculatePosition(int windowsWidth,
			int windowsHeight, Platform[] platforms) {

		//Calcule la position du joueur à la prochaine frame.
		yVelocity+=gravity;
		xVelocity = 0.7 * xVelocity;
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
				if (yVelocity>0) {
					yVelocity=-playeryVelocity;
					ground=true;
					platform.effect(this);
				}

					
					if (platform.getType() == PlatformType.BASIC || platform.getType() == PlatformType.LAVA ) {
						platform.rebond();
			        }
				}
			}
		this.x = this.x + xVelocity;
		this.y = this.y + yVelocity;
	}

}
