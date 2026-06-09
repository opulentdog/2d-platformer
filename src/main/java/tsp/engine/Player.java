package tsp.engine;

import java.util.HashSet;

import javafx.scene.input.KeyCode;
import tsp.engine.platforms.Platform;

/**
 * Joueur contrôlé dans le jeu
 * Il se déplace en rebondissant sur les plateformes, peut actualiser sa position, et meurt en cas de longue chute
 */
public class Player extends Asset {
	
	/*-------------------------------------------------------------------------------------------------------------------------------------
	 * ------------------- Fields -------------------
	 * -------------------------------------------------------------------------------------------------------------------------------------
	 */
	
	/**
	 * Indique si le Player est mort ou non
	 */
	private boolean dead;
	/**
	 * Vitesse gagnée par le Player
	 */
	private double xVelocity=0;
	private double yVelocity=0;
	/**
	 * Vitesse du Player
	 */
	double playeryVelocity=31;
	double playerxVelocity=3;
	/**
	 * Indice sur l'influence de la gravité
	 */
	private int gravity=1;
	/**
	 * Vitesse max de chute avant de mourrir
	 */
	final private int FALL_THRESHOLD=29;
	private Boolean ground=true;
	
	/*-------------------------------------------------------------------------------------------------------------------------------------
	 * ------------------- Constructor -------------------
	 * -------------------------------------------------------------------------------------------------------------------------------------
	 */
	
	public Player(int width, int height) {
		super(width, height);
		dead = false;
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------
	 * ------------------- Methods -------------------
	 * -------------------------------------------------------------------------------------------------------------------------------------
	 */
	
	/**
	 * Indique si le joueur est mort
	 * @return
	 */
	public boolean isDead() {
		return dead;
	}
	
	/**
	 * Tue le Player
	 */
	public void kill() {
		dead = true;
	}
 	
	/**
	 * Réssucite le Player
	 */
	public void revive() {
	    this.dead = false;
	}
	
	/**
	 * Augmente la vitesse horizontale du Player
	 * @param delta_xVelocity
	 */
	public void addxVelocity(double delta_xVelocity) {
		 this.xVelocity += delta_xVelocity;
	}
	
	/**
	 * Augmente la vitesse verticale du joueur
	 * @param delta_yVelocity
	 */
	public void addyVelocity(double delta_yVelocity) {
		this.yVelocity += delta_yVelocity;
	}
	
	/**
	 * Traduit les entrées clavier en mouvement
	 * si la touche gauche ou droite est pressée, une accélération est ajoutée à la vitesse horizontale.
	 * @param pressedKeyset l'ensemble des touches actuellement maintenues enfoncées
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
	 * Calcule et applique le déplacement du joueur pour la prochaine frame</p>
	 * 
	 * étapes :</p>
	 * Applique la gravité et la friction (horizontale et verticale).</p>
	 * Gère les collisions avec les bords latéraux de la fenêtre.</p>
	 * Rebond si le joueur touche le bas de l'écran.</p>
	 * Détecte les collisions avec les plateformes et déclenche leurs effets </p>
	 * 
	 * @param windowsWidth  La largeur de la zone de jeu pour la collision latérale.
	 * @param windowsHeight La hauteur de la zone de jeu pour le rebond au sol.
	 * @param platforms     Le tableau des plateformes présentes dans le niveau.
	 */
	public void calculatePosition(int windowsWidth,
			int windowsHeight, Platform[] platforms) {

		// Calcule la position du joueur à la prochaine frame.
		yVelocity+=gravity;
		xVelocity = 0.7 * xVelocity; // doit être identique au coefficient dans controlTower de tower.java
		yVelocity = 0.97 * yVelocity;
		
		// Bordure de l'écran et gestion des collisions
		if (x < 0) {
			xVelocity = 0;
			x = 0;
		}else if (x+width > windowsWidth){
			xVelocity = 0;
			x = windowsWidth-width;
		}

		if (y+height > windowsHeight){
			if (yVelocity>FALL_THRESHOLD) {			// dégâts de chute (sol)
				this.kill();
			} else {
				y=windowsHeight-height;
				yVelocity=-playeryVelocity;
				ground=true;
			}
		}
		
		
		// Collision avec les platformes
		for(Platform platform : platforms) {
			if(platform.intersects(this)) {
				if (yVelocity>FALL_THRESHOLD) {		// dégâts de chute (plateforme)
					this.kill();
				} else if (yVelocity>0){
					yVelocity=-playeryVelocity;
					ground=true;
					platform.effect(this);
				}

			}
		}
		this.x = this.x + xVelocity;
		this.y = this.y + yVelocity;
	}

}
