package tsp.engine.platforms;

import tsp.engine.Player;
import tsp.engine.platforms.Platform.PlatformType;

public class LavaPlatform extends Platform {
	
	public LavaPlatform() {
		super("/images/platform-lava.png", 100, 30);
		this.type = PlatformType.LAVA;
	}
	
	public void effect(Player p) {
		p.kill();
	}
}
