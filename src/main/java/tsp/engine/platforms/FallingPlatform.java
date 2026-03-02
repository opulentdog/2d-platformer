package tsp.engine.platforms;

import tsp.engine.Player;
import tsp.engine.platforms.Platform.PlatformType;

public class FallingPlatform extends Platform{
	
	public FallingPlatform() {
		super("/images/platform.png",100,30);
		this.type = PlatformType.FALLING;

	}
	
	public void effect(Player p) {}


}
