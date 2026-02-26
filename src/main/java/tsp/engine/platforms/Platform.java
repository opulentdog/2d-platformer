package tsp.engine.platforms;

import tsp.engine.Asset;
import tsp.engine.Generation;
import tsp.graphics.Sound;

public abstract class Platform extends Asset{
	protected Sound soundeff;

	public Platform(String image, int width, int height) {
		super(image, width, height);
	}
	
	public void rebond() {
		soundeff.playSE();
		soundeff.volume(0.8f);
	}
}
