package tsp.graphics;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tsp.engine.Game;
import tsp.engine.Generation;
import tsp.engine.Player;
import tsp.engine.Tower;
import tsp.engine.platforms.Platform;
import tsp.graphics.render.PlatformRender;
import tsp.graphics.render.PlayerRender;
import tsp.graphics.render.TowerRender;
import tsp.graphics.Menu;


/**
 * Classe pour ce qui est lié à la fenetre
 */
public class Window extends Application{
// ---------------- ATTRIBUTS -----------------------------------------------------------------------------------------------------------------
	/**
	 * largeur de la fenetre
	 */
	private int windowWidth = 694;
	
	/**
	 * hauteur de la fenetre
	 */
	private int windowHeight = 520;
	
	private Game game;
	
	/**
	 * stage javafx
	 */
	private Stage stage;
	
	/**
	 * éléments javafx
	 */
	private Scene scene;
	private Group group;
	private Canvas canvas;
	private GraphicsContext gc;
	
	/**
	 * Son de la fenêtre
	 */
	private Sound sound;
	
	/**
	 * Position verticale de la "caméra" (caméra virtuelle)
	 */
	double ycamera = 0;

	

// ----------- GETTER/SETTER/CONSTRUCTEUR ---------------------------------------------------------------------------------------------------	
	public void setSize(int windowWidth, int windowHeight) {
		 this.windowWidth = windowWidth;
		 this.windowHeight = windowHeight;
		 }
	
	public int getWidth() {
		return windowWidth;
	}
	
	public int getHeight() {
		return windowHeight;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public Group getGroup() {
		return group;
	}

	public Canvas getCanvas() {
		return canvas;
	}
	
	public GraphicsContext getGC() {
		return gc;
	}
	
	public double getCamY() {
		return ycamera;
	}
	
	public void setCam(double ycamera) {
		this.ycamera = ycamera;
	}
// --------------- METHODES --------------------------------------------------------------------------------------------------------------------
	private void changeState(MouseEvent e) {

	    if(game.getState() == Game.GameState.MENU) {

	        if(Menu.isClicked(e.getX(), e.getY())) {
	            game.setState(Game.GameState.RUNNING);
	        }
	    }
		
	}
	
	
	/**
	 * 
	 */
	@Override
	public void start(Stage stage) {		
		
		//Elements de la scène
		this.group = new Group();
		this.canvas = new Canvas(windowWidth,windowHeight);
		this.gc = canvas.getGraphicsContext2D();
		this.scene = new Scene(group, windowWidth, windowHeight);
		this.stage = stage;
		
		this.sound = new Sound("/sounds/music/track1.wav");
		
		Window window = this;
				
		Input input = new Input(window);
		game = new Game(this);
		input.listen();
		
		Texture bg = new Texture("/images/space.jpg", windowWidth, windowHeight);
		bg.setBG(this);
		
		TowerRender towerRender = new TowerRender(window, game.getTower());
		PlayerRender playerRender = new PlayerRender(window,game.getPlayer());
		PlatformRender platformRender = new PlatformRender(window, game.getTower(), game.getPlatforms(), game.getGenerator());	

		// Passer dans input
		canvas.setOnMouseClicked(e -> changeState(e));
	

		AnimationTimer animation = new AnimationTimer() {
		
			private static final int PlatformSpacing = 300;

			long lastTime = 0;			
	
		    @Override
		    public void handle(long now) {
		    	//Fonction qui est appelé à chaque frame pour dessiner la scène
		        if (lastTime == 0) {
		            lastTime = now;
		            return;
		        }
	
		        //Delta c'est le temps en milliseconde qui s'est écoulé entre deux frames
		        //Ca permet que le joueur bouge tjrs à la même vitesse même si il y a du lag
		        double delta = (now - lastTime) / 1_000_000_000.0; // seconds
		        update(delta,now);
		    }
//	Refactor
		    
			private void update(double delta, long now) {
				if (delta < 1.0/40) return; // On limite les fps à 40 frames par seconds
				if (delta > 2.0 / 40 ) { // On a passé plus de deux frames c'est le cas si on a du lag
					System.out.println("Dropped frame");
					lastTime = now;
					return;
				}
				//On met à jour le dernier temps de dessin
		        lastTime = now;
		        
		        game.update(delta);
		        switch(game.getState()) {
		        	case RUNNING:
                window.getGC().clearRect(0, 0, window.getCanvas().getWidth(), window.getCanvas().getHeight());
                window.setCam(game.getPlayer().getY()-window.getHeight()/2);

                game.getPlayer().calculatePosition(window.getWidth(), window.getHeight(), game.getPlatforms());
                game.getTower().controlTower(input.getPressedKeyset());

                towerRender.render();
                platformRender.render();
                playerRender.render();		//On dessine le joueur en dernier pour etre au premier plan

                window.getGC().strokeText("Score: "+(int)-window.getCamY()/PlatformSpacing, window.getHeight()-100, 10);

                //gc.strokeText("FPS: "+1/delta, 540, 36);
						    return ;
						
		        	case GAME_OVER:
		                // on redessine une dernière image figée :
		                window.getGC().clearRect(0, 0, window.getCanvas().getWidth(), window.getCanvas().getHeight());
		                towerRender.render();
		                platformRender.render();
		                playerRender.render();

		                // puis l’overlay game over
		                GameOver.render(window, (int)-window.getCamY()/PlatformSpacing);
		                System.out.println("IN GAME OVER");
		                return;				
		        }

			}
		};
		animation.start();
		//On lance la musique
		sound.playMusic();
		sound.volume(0.7f);

		window.getGroup().getChildren().add(window.getCanvas());
		window.getStage().setScene(window.getScene());
		window.getStage().setResizable(false);
		window.getStage().show();
		
	}

protected void update(double delta, long now) {
		// TODO Auto-generated method stub
		
	}

// --------------- Lancement du jeu ------------------------

    public static void launchApp(String[] args) {
        launch(args);
    }

}