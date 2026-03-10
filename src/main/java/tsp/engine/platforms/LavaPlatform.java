package tsp.engine.platforms;

import tsp.engine.Player;
import tsp.engine.platforms.Platform.PlatformType;
import tsp.graphics.Sound;

public class LavaPlatform extends Platform {
	
	public LavaPlatform() {
		super("/images/platform-lava.png", 100, 30);
		this.soundeff = new Sound("sounds/sound_effect/8-bit-bomb-explosion-2811.wav");
		this.type = PlatformType.LAVA;
	}
	
	public void effect(Player p) {
		p.kill();
	}
}
