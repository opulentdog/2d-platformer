package tsp.engine.platforms;

import tsp.engine.Player;
import tsp.engine.platforms.Platform.PlatformType;

/**
 * Plateforme à ressort
 */
public class SpringBoardPlatform extends Platform{

	/***
	 * ------------------- Constructor ----------------------
	 */
	
	public SpringBoardPlatform() {
		super(100,30);
		this.type = PlatformType.SPRING;
	}

	/***
	 * ------------------- Methods ----------------------
	 */
	
	public void effect(Player player) {}


}
