package tsp.engine.platforms;

import tsp.engine.Asset;
import tsp.engine.Generation;
import tsp.engine.Player;
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
	
	/**
	 * Surcharge de la methode mere pour ajouter l'effet de la plateforme
	 * @param s: asset à comparer avec this
	 * @return true si this et s en contact, false sinon
	 */
	@Override
	public Boolean intersects(Asset asset) {
		boolean isInContact = super.intersects(asset);
		if (! (asset instanceof Player)) {
			// Pour les non joueur
		}
		Player player = (Player) asset;
		if (isInContact) {
			// this.effect(player);
		}
		return isInContact;
	}
	
	public void rebond() {
		soundeff.playSE();
		soundeff.volume(0.8f);
	}

	
	public abstract void effect(Player p);
	
}
