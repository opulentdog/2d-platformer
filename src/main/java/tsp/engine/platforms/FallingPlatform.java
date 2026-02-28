package tsp.engine.platforms;

import tsp.engine.platforms.Platform.PlatformType;

public class FallingPlatform extends Platform{
	
	public FallingPlatform() {
		super("/images/platform.png",100,30);
		this.type = PlatformType.FALLING;

	}

}
