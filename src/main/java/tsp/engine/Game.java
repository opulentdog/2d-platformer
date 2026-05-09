package tsp.engine;

import tsp.engine.platforms.Platform;
import tsp.graphics.Constants;
import tsp.graphics.Window;

public class Game {
	
	private GameState state = GameState.RUNNING;
	private Player player;
	private Tower tower;
	private Generation generator;
	private Platform[] platforms;
	
	public GameState getState() {
		return state;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Tower getTower() {
		return tower;
	}

	public void setTower(Tower tower) {
		this.tower = tower;
	}

	public Generation getGenerator() {
		return generator;
	}

	public void setGenerator(Generation generator) {
		this.generator = generator;
	}

	public Platform[] getPlatforms() {
		return platforms;
	}

	public void setPlatforms(Platform[] platforms) {
		this.platforms = platforms;
	}
	
	public void setState(GameState state) {
	    this.state = state;
	}

	/**
	 * génération du joueur, des plateformes et passage à l'écran du MENU
	 * @param window la fene^tre du jeu
	 */
	public Game(Window window) {
		reset(window);
		state = GameState.MENU;
	}

	public void update(double delta) {

	    switch(state) {
	    	// A l'ouvreture du jeu ou après un game over
	    	case MENU:
	    		updateMenu(delta);
	    		break;

	    	// jeu en cours
	        case RUNNING:
	            updateGame(delta);
	            if(player.isDead()) {
	                state = GameState.GAME_OVER;
	                System.out.println("IN GAME OVER");
	            }
	            break;

	        // géré dans graphics
	        case GAME_OVER:
	            break;
	    }
	}
	
	public void updateMenu(double delta) {
	}
	
	public void updateGame(double delta) {
		
	}
	
	public void updateGameOver(double delta) {
		
	}
	
	public void reset(Window window) {
	    generator = new Generation();
	    player = new Player(Constants.PLAYER_SIDE, Constants.PLAYER_SIDE);
	    tower = new Tower();

	    platforms = generator.randomPlatformGeneration(window.getWidth(), window.getHeight());
		player.setPostition((window.getWidth()-player.getWidth())/2, player.getHeight());

	    state = GameState.RUNNING;
	}
	
}
