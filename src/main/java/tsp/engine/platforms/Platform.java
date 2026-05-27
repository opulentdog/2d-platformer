package tsp.engine.platforms;

import tsp.engine.Asset;
import tsp.engine.Player;
import tsp.graphics.Sound;

public abstract class Platform extends Asset{

	/***
	 * ------------------- Fields ----------------------
	 */
	
	protected Sound soundeff;
	protected PlatformType type;
	
	/**
	 * Sert à stocker facilement le type précis de la Plateforme
	 */
	public enum PlatformType {
	    BASIC,
	    LAVA,
	    SPRING,
	    FALLING,
	    INVISIBLE
	}

	/***
	 * ------------------- Constructor ----------------------
	 */
	
	public Platform(int width, int height) {
		super(width, height);
	}

	/***
	 * ------------------- Getters ----------------------
	 */
	
	public PlatformType getType() {
        return type;
    }

	/***
	 * ------------------- Methods ----------------------
	 */
	
	/**
	 * Surcharge de la methode mere pour ajouter l'effet de la plateforme
	 * @param s: asset à comparer avec this
	 * @return true si this et s en contact, false sinon
	 */
	@Override
	public Boolean intersects(Asset other) {
		double relativex=other.getX()-this.x;
		double relativey=other.getY()-this.y+other.getHeight();
		
		return relativex < this.width 
			&& relativey < this.height 
			&& -relativex < other.getWidth() 
			&& -relativey < 10 //other.getHeight()
				
				;
	
		/*
		boolean isInContact = super.intersects(asset);
		if (! (asset instanceof Player)) {
			// Pour les non joueur
		}
		Player player = (Player) asset;
		if (isInContact) {
			// this.effect(player);
		}
		return isInContact;*/
	}
	
	/**
	 * joue les effets liés au rebond
	 */
	public void rebond() {
		soundeff.playSE();
		soundeff.volume(0.8f);
	}

	/**
	 * joue les effets liés au rebond
	 * @param player
	 */
	public abstract void effect(Player player);
	
}
