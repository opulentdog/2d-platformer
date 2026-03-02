package tsp.engine.platforms;

import tsp.engine.Player;
import tsp.engine.platforms.Platform.PlatformType;

public class SpringBoardPlatform extends Platform{
	
	public SpringBoardPlatform() {
		super("/images/platform.png",100,30);
		this.type = PlatformType.SPRING;
	}
	
	public void effect(Player p) {}


}
