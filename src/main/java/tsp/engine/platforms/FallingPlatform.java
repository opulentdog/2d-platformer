package tsp.engine.platforms;

import tsp.engine.Player;
import tsp.engine.platforms.Platform.PlatformType;

/**
 * plateforme qui tombe après que le joueur ait rebondit dessus
 * TODO implémenter
 */
public class FallingPlatform extends Platform{
	
	public FallingPlatform() {
		super(100,30);
		this.type = PlatformType.FALLING;

	}
	
	public void effect(Player player) {}


}
