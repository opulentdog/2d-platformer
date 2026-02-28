package tsp.engine.platforms;

import tsp.engine.Asset;
import tsp.engine.Generation;
import tsp.graphics.Sound;

public abstract class Platform extends Asset{
	protected Sound soundeff;
	protected PlatformType type;
	
	/**
	 * Sert à stocker facilement le type précis de la Plateforme
	 */
	public enum PlatformType {
	    BASIC,
	    LAVA,
	    SPRING,
	    FALLING
	}

	public Platform(String image, int width, int height) {
		super(image, width, height);
	}
	
	public PlatformType getType() {
        return type;
    }
	
	public void rebond() {
		soundeff.playSE();
		soundeff.volume(0.8f);
	}
}
