package tsp.graphics.render;
import tsp.graphics.Constants;
import tsp.engine.Player;
import tsp.graphics.Window;

public class PlayerRender extends Render<Player>{
	
	private Player player;
	private Window window;
	static String[] skinsList = {
		    Constants.PLAYER_PATH,
		    Constants.SKINEARTH_PATH,
		    Constants.SKINFIRE_PATH,
		    Constants.SKINWATER_PATH,
		    Constants.SKINWIND_PATH
		};
	private int currentSkinIndex = 0;
	
	/**
	 * Constructeur de PlayerRender qui crée une nouvelle image avec le construteur de Render
	 * @param window
	 * @param player
	 */
	public PlayerRender(Window window, Player player) {
		super(player, skinsList[0]);
		this.player = player;
		this.window = window;
	}
	
	/**
	 * ------------------- Getter et Setter -------------------
	 */
	public int getCurrentSkinIndex() {
	    return currentSkinIndex;
	}
	
	public String getCurrentSkinPath() {
		return skinsList[getCurrentSkinIndex()];
	}
	
	public void setSkinIndex(int index) {
	    this.currentSkinIndex = index;
	    this.getTexture().setImgPath(skinsList[currentSkinIndex], player.getWidth(), player.getHeight());
	}
	
	/**
	 * ------------------- Méthodes -------------------
	 */
	@Override
	public void render() {
		window.getGC().drawImage(this.getTexture().getImage(), player.getX(), player.getY()-window.getCamY());
	}
	
	public void nextSkin() {
	    currentSkinIndex = (currentSkinIndex + 1) % skinsList.length;
	    this.getTexture().setImgPath(skinsList[currentSkinIndex],player.getWidth(),player.getHeight());
	}

	public void previousSkin() {
	    currentSkinIndex = (currentSkinIndex - 1 + skinsList.length) % skinsList.length;
	    this.getTexture().setImgPath(skinsList[currentSkinIndex],player.getWidth(),player.getHeight());
	}
		
}
