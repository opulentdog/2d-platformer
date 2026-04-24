package tsp.engine.platforms;
import tsp.engine.Player;
import tsp.graphics.Sound;

/**
 * plateforme sur laquelle le joueur peut rebondir, sans effets particuliers
 */
public class BasicPlatform extends Platform{
	
	public BasicPlatform() {
		super(100, 30);
		this.soundeff = new Sound("/sounds/sound_effect/SFX_Jump_42.wav");
		this.type = PlatformType.BASIC;
	}
	

	@Override
	public void effect(Player player) {
		this.rebond();
	}

}
