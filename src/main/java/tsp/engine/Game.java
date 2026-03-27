package tsp.engine;

import tsp.engine.platforms.Platform;
import tsp.graphics.Window;

public class Game {
	
	public enum GameState {
		MENU,
	    RUNNING,
	    GAME_OVER
	}
	
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

	public Game(Window window) {
		generator = new Generation();
		player = new Player("/images/player.png", 70, 70);
		tower = new Tower();
		
		platforms = generator.randomPlatformGeneration(window.getWidth(), window.getHeight());
		player.setPostition((window.getWidth()-player.getWidth())/2, player.getHeight());
		state = GameState.RUNNING;
	}

	public void update(double delta) {

	    switch(state) {
	    	// A l'ouvreture du jeu ou après un game over
	    	case MENU:
	    		updateMenu(delta);

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
	
}
