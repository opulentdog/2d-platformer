package tsp.engine.platforms;
import tsp.engine.Player;
import tsp.graphics.Sound;

/**
 * plateforme sur laquelle le joueur peut rebondir, sans effets particuliers
 */
public class InvisiblePlatform extends Platform{

	/**
	 * ------------------- Constructor ----------------------
	 */
	
	public InvisiblePlatform() {
		super(100, 30);
		this.soundeff = new Sound("/sounds/sound_effect/SFX_Jump_42.wav");
		this.type = PlatformType.INVISIBLE;
	}

	/**
	 * ------------------- Methods ----------------------
	 */
	
	/**
	 * joue les effets liés au rebond
	 */
	public void effect(Player player) {
		this.rebond();
	}

}
