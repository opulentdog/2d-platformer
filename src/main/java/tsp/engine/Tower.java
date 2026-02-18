package tsp.engine;

import java.util.HashSet;

import tsp.graphics.Texture;
import tsp.graphics.Window;
import tsp.graphics.render.TowerRender;
import javafx.scene.input.KeyCode;


public class Tower extends Asset{
	private final static int cylinderWidth = 250;
	private static int cylinderHeight = 2000;
	
	private double towerxVelocity;
	double rotation;


	public Tower() {
		super("/images/stone-wall.png", cylinderWidth, cylinderHeight);

    }
	
	public double getRotation() {
		return rotation;
	}
    

	public void updatePosition() {
    	towerxVelocity*=0.70;
        rotation+=towerxVelocity;
		if(rotation >= 360) {
			rotation -= 360;
		}
		if(rotation < 0) {
			rotation += 360;
		}
	}

	/**
	 * Vérifie si les touche sont appuyé et modifie la vitesse du joueur
	 * @param pressedKeyset
	 */
	public void controlTower(HashSet<KeyCode> pressedKeyset) {
		if(pressedKeyset.contains(KeyCode.LEFT)) {
			towerxVelocity=-7;
		}
		if(pressedKeyset.contains(KeyCode.RIGHT)) {
			towerxVelocity=7;
		}		
		
	}
}
