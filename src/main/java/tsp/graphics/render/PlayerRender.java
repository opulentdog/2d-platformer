package tsp.graphics.render;

import tsp.engine.Player;
import tsp.graphics.Window;

public class PlayerRender extends Render<Player>{
	
	private Player player;
	Window window;
	
	public PlayerRender(Window window, Player player) {
		super(player);
		this.player = player;
		this.window = window;
	}
	
	@Override
	public void render() {
		window.getGC().drawImage(this.getTexture().getImage(), player.getX(), player.getY()-window.getCamY());
	}

}
