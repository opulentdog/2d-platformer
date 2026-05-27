package tsp.engine.platforms;

import tsp.engine.Player;

/**
 * plateforme qui tombe après que le joueur ait rebondit dessus
 * TODO implémenter
 */
public class FallingPlatform extends Platform{

	/***
	 * ------------------- Constructor ----------------------
	 */
	
	public FallingPlatform() {
		super(100,30);
		this.type = PlatformType.FALLING;

	}

	/***
	 * ------------------- Methods ----------------------
	 */
	
	public void effect(Player player) {}


}
