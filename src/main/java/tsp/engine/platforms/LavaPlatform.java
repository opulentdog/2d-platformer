package tsp.engine.platforms;

import tsp.engine.platforms.Platform.PlatformType;

public class LavaPlatform extends Platform {
	
	public LavaPlatform() {
		super("/images/platform-lava.png", 100, 30);
		this.type = PlatformType.LAVA;
	}

}
