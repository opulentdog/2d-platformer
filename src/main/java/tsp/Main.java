package tsp;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import tsp.engine.Generation;
import tsp.engine.Player;
import tsp.engine.Tower;
import tsp.engine.platforms.*;
import tsp.graphics.Input;
import tsp.graphics.Texture;
import tsp.graphics.Window;
import tsp.graphics.render.PlatformRender;
import tsp.graphics.render.PlayerRender;
import tsp.graphics.render.TowerRender;

class Main{
	private static final int PlatformSpacing = 300;
	int score = 0;
	
	public static void main(String[] args) {
		Window window = new Window();
		Window.launchApp(args);

		
		/*
		//Les images pour la platforme et le joueur
		Texture playerImage = new Texture("/images/player.png");
		Texture platformImage = getClass().getResource("/images/platform.png").toString();
		Texture platformLavaImage = getClass().getResource("/images/platform-lava.png").toString();		
		
		fenetre.launch();
		*/
		
		
		/*
		 * new window
		 * new input
		 * new Player
		 * new Tower
		 * platform[] = generation
		 * new rendermanager
		 * input.listen()
		 * new animation(fps) -> { 
		 * player.updatekeys(input.getkeys)
		 * tower.updatekeys(input.getkeys)
		 * 
		 * tower.updateposition()
		 * for(Platform p : platforms){
		 * 	platfortm.updateposition()
		 * }
		 * player.updateposition(platforms[])
		 * 
		 * tower.render()
		 * for(Platform p : platforms){
		 * 	platfortm.render()
		 * }
		 * player.render()
		 * 
		 * }
		 * 
		 */
		
		
	}
		
}
