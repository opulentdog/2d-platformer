package tsp.engine;

import java.util.HashSet;

import tsp.graphics.Constants;
import tsp.graphics.Texture;
import tsp.graphics.Window;
import tsp.graphics.render.TowerRender;
import javafx.scene.input.KeyCode;

/**
 * classe de la tour contenant ses dimensions, sa texture.
 */
public class Tower extends Asset{
	/***
	 * ------------------- Fields -------------------
	 */
	
	private final static int cylinderWidth = 250; // largeur de la tour
	private static int cylinderHeight = 2000; // hauteur de la tour
	
	private double towerxVelocity; 
	double rotation; // position angulaire de la tour

	/***
	 * ------------------- Constructor -------------------
	 */
	
	public Tower() {
		super(cylinderWidth, cylinderHeight);

    }
	
	/***
	 * ------------------- Getters -------------------
	 */
	
	/*
	 * donne la position angulaire de la tour
	 */
	public double getRotation() {
		return rotation;
	}
    
	/***
	 * ------------------- Methods -------------------
	 */
	
	/**
	 * met à jour la position de la tour
	 * Applique un coefficient de friction à la vitesse de rotation,
	 * maintient la position angulaire dans l'intervalle [0,360[ 
	 */
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
