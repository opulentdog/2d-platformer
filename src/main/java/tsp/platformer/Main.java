package tsp.platformer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Main extends Application {
	int score = 0;
	HashSet<KeyCode> PressedKeyset = new HashSet<>();

	
	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage stage) {
		int width = 694	; //Largeur de la fenêtre
		int height = 520; // Hauteur de la fenête
		
		
		//Elements de la scène
		Group group = new Group();
		Canvas canva = new Canvas(width,height);
		GraphicsContext gc = canva.getGraphicsContext2D();
		Scene scene = new Scene(group);
		
		
		
		//Les images pour la platforme et le joueur
		String playerImage = getClass().getResource("/images/player.png").toString();
		String platformImage = getClass().getResource("/images/platform.png").toString();
		String platformLavaImage = getClass().getResource("/images/platform-lava.png").toString();
		
		
		//Je crée l'objet player.
		Player player = new Player(playerImage, 70, 70);
		
		/*J'écoute on key presses*/
		scene.setOnKeyReleased((KeyEvent e) -> {
			PressedKeyset.remove(e.getCode());
		});
		scene.setOnKeyPressed((KeyEvent e) -> {
			PressedKeyset.add(e.getCode());
		});

		
		// Créer 50 platformes avec des coordonées horizontale random
		Platform[] platforms = new Platform[50];
		int i=0;
		for(int c=0; c< 50;c++) {
			if(Math.random() < 0.75) {
				platforms[c]=new Platform(platformImage, 100, 30);
			}else {
				platforms[c]=new Platform(platformLavaImage, 100, 30);
			}
			double posititionx=(width-platforms[c].width)*Math.random();
			double posititiony=height/2-i*150+i*i/100;
			platforms[c].setPostition(posititionx, posititiony);
			i++;
		}
		
		AnimationTimer animation = new AnimationTimer() {
			long lastTime = 0;
			double ycamera = 0;

		    @Override
		    public void handle(long now) {
		        if (lastTime == 0) {
		            lastTime = now;
		            return;
		        }

		        double delta = (now - lastTime) / 1_000_000_000.0; // seconds
		        update(delta,now);
		    }

			private void update(double delta, long now) {
				if (delta < 1.0/40) return;
				if (delta > 2.0 / 40 ) {
					System.out.println("Dropped frame");
					lastTime = now;
					return;
				}
		        lastTime = now;
				gc.clearRect(0, 0, canva.getWidth(), canva.getHeight());
				
				ycamera=player.y-height/2;
				player.controlPlayer(PressedKeyset);
				player.calculatePosition(width, height, platforms);
				for(Platform platform1 : platforms) {
					platform1.render(gc,ycamera);
				}
				
				//On dessine le joueur en dernier pour etre au premier plan
				player.render(gc,ycamera);

				

				//gc.strokeText("FPS: "+1/delta, 540, 36);		
				return ;				
			}
		};
		animation.start();
		
				
		group.getChildren().add(canva);
		
		stage.setScene(scene);
		stage.setResizable(false);
		//stage.setTitle("Alien vs Pinapples");
		stage.show();

	}
}
