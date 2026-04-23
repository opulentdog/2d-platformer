package tsp.engine.platforms;

import tsp.engine.Player;
import tsp.engine.platforms.Platform.PlatformType;
import tsp.graphics.Sound;

/**
 * Platefome qu'il ne faut pas toucher sous peine de perdre la partie
 */
public class LavaPlatform extends Platform {
	
	public LavaPlatform() {
		super(100, 30);
		this.soundeff = new Sound("/sounds/sound_effect/8-bit-bomb-explosion-2811.wav");
		this.type = PlatformType.LAVA;
	}
	
	/**
	 * effets associés à la plateforme :
	 * l'état du joueur pass à "mort"
	 * joue les effets liés au rebond
	 */
	public void effect(Player player) {
		player.kill();
		this.rebond();
	}
}
