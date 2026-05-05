package tsp.engine;

import tsp.engine.Asset;
import tsp.engine.Generation;
import tsp.engine.Player;
import tsp.graphics.Sound;

public class Meteorite extends Asset{
	protected Sound soundeff;
	

	public Meteorite(int width, int height) {
		super(width, height);
	}
	
	
	/**
	 * Surcharge de la methode mere pour ajouter l'effet de la Meteorite
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
			// this.effect(meteorite);
		}
		return isInContact;
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
	 * @param meteorite
	 */
	public void effect(Meteorite meteorite) {	
		
	}
	
}
