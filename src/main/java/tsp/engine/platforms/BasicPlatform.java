package tsp.engine.platforms;
import tsp.engine.Player;
import tsp.graphics.Sound;

public class BasicPlatform extends Platform{
	
	public BasicPlatform() {
		super("/images/platform.png", 100, 30);
		this.soundeff = new Sound("/sounds/sound_effect/SFX_Jump_42.wav");
		this.type = PlatformType.BASIC;
	}
	
	public void effect(Player p) {}

}
