package tsp.graphics;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import tsp.engine.Game;
import tsp.engine.GameState;
import tsp.engine.Generation;
import tsp.engine.Player;
import tsp.engine.Tower;
import tsp.engine.platforms.Platform;
import tsp.graphics.render.PlatformRender;
import tsp.graphics.render.PlayerRender;
import tsp.graphics.render.TowerRender;

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
	private Group gameGroup;
	private Canvas canvas; // Canva du jeu flouté pendant le menu
	private Canvas menuCanvas; // Canvas transparent superposé au canvas jeu, réservé au rendu du menu
	private GraphicsContext gc;
	
	/**
	 * Position verticale de la "caméra" (caméra virtuelle)
	 */
	double ycamera = 0;
	
	/**
	 * Son de la fenêtre
	 */
	private Sound soundgame;
	/**
	 * Son du game over
	 */
	private Sound soundDeath;
	private Texture bg;
	
	private Input input;
	private TowerRender towerRender;
	private PlayerRender playerRender;
	private PlatformRender platformRender;

	

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
	public Group getGameGroup() { 
		return gameGroup; 
	}  // Getter du gameGroup pour y ajouter les formes de la tour


	public Canvas getCanvas() {
		return canvas;
	}
	public Canvas getMenuCanvas() {
		return menuCanvas; 
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
	
	/**
	 * Change l'état du jeu à RUNNING si on est dans le menu
	 * @param e un mouseEvent
	 */	
	private void changeState(MouseEvent e) {

	    if (game.getState() == GameState.MENU) {
	        if (Menu.isClicked(e.getX(), e.getY())) {
	            game.setState(GameState.RUNNING);
	        }
	    }

	    else if (game.getState() == GameState.GAME_OVER) {
	        if (GameOver.isRetryClicked(e.getX(), e.getY())) {
	            game.reset(this);
	            startRenders();
	            ycamera = 0;
	          //on coupe la musique
                soundDeath.stopMusic();
	        }
	    }
	}

	/**
	 * Crée ou recrée tout les renders des différents objets
	 */
	private void startRenders() {
	    towerRender = new TowerRender(this, game.getTower());
	    playerRender = new PlayerRender(this, game.getPlayer());
	    platformRender = new PlatformRender(this, game.getTower(), game.getPlatforms(), game.getGenerator());
	
	    canvas.toFront();
	    menuCanvas.toFront();
	}
	
	/**
	 * 
	 * @param stage: stage javafx
	 */
	private void startScene(Stage stage) {
		// Elements de la scene
	    this.group = new Group();
	    this.gameGroup = new Group();
	    this.canvas = new Canvas(windowWidth, windowHeight);
	    
	    this.menuCanvas = new Canvas(windowWidth, windowHeight);			// Création du canvas du menu aux mêmes dimensions que la fenêtre
	
	    this.gc = canvas.getGraphicsContext2D();
	    this.scene = new Scene(group, windowWidth, windowHeight);
	    this.stage = stage;
	
	    this.soundgame = new Sound(Constants.TRACK1_PATH);
	    this.soundDeath = new Sound(Constants.GAMEOVER_PATH);
	}
	
	/**
	 * Creation et affichage du fond d'ecran
	 */
	private void startBackground() {
	    this.bg = new Texture(Constants.SPACE_PATH, windowWidth, windowHeight);
	    ImageView bgView = new ImageView(bg.getImage());
	    bgView.setFitWidth(windowWidth);
	    bgView.setFitHeight(windowHeight);
	
	    gameGroup.getChildren().add(bgView);
	}
	
	/**
	 * TODO segmenter
	 * 
	 */
	@Override
	public void start(Stage stage) {		
		// Pour pouvoir appeler window dans la classe anonyme AnimationTimer
		Window window = this;
		
		// Construire tout les éléments de la fenêtre
		startScene(stage);
		Input input = new Input(window);
		game = new Game(this);
		input.listen();
		startBackground();
		startRenders();

		gameGroup.getChildren().add(canvas); // Canvas (joueur + plateformes) dans le gameGroup
		
		// Détection du click qui quitte le menu
		menuCanvas.setOnMouseClicked(e -> changeState(e));
		
		// Effet de flou appliqué au canvas de jeu pendant le menu
		GaussianBlur menuBlur = new GaussianBlur(Constants.FLOU);
		
		// Grande classe anonyme à décomoser en petits blocs
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
			        case MENU:
			            window.getGC().clearRect(0,0,window.getWidth(),window.getHeight());
			            
			            // Dessiner le fond sur le canvas
			            window.getGC().drawImage(bg.getImage(), 0, 0, window.getWidth(), window.getHeight());			            
			            towerRender.render();
			            
			            // Snapshot du gameGroup pour capturer la tour 3D
			            WritableImage snapshotTower = new WritableImage(window.getWidth(), window.getHeight());
			            SnapshotParameters params = new SnapshotParameters();
			            params.setFill(Color.TRANSPARENT); 			// Fond transparent pour voir le fond space derrière
			            gameGroup.snapshot(params, snapshotTower);			 // Photo de gameGroup --> snapshotTower
			            
			            // Snapshot du canvas pour capturer le fond space
			            WritableImage snapshotCanvas = new WritableImage(window.getWidth(), window.getHeight());
			            window.getCanvas().snapshot(params, snapshotCanvas);
			            
			            // Redessiner les deux snapshots floutées dans l'ordre
			            window.getGC().clearRect(0,0,window.getWidth(),window.getHeight()); // Efface le canvas
			            window.getGC().save(); 									// Sauvegarde l'état actuel du GC
			            window.getGC().setEffect(menuBlur); 				// Applique le flou gaussien
			            window.getGC().drawImage(snapshotCanvas, 0, 0);  // On dessine le fond flou
			            window.getGC().drawImage(snapshotTower, 0, 0);   // On dessine la tour floue par-dessus
			            window.getGC().restore();						// Retour à save() => annule le flou
			            
			            game.getPlayer().setPostition(game.getPlayer().getX(), windowHeight / 2);
			            menuCanvas.setVisible(true);
			            playerRender.render();							// On affiche le Player net
			            Menu.render(window, menuCanvas);				// On lance Menu qui affiche le bouton net
			            return;
		        	case RUNNING:
		        		window.getCanvas().setEffect(null);		   // Supprime le flou	
		        		menuCanvas.setVisible(false);		       // Cache le canvas menu pendant la partie	
		        		menuCanvas.getGraphicsContext2D().clearRect(0,0,windowWidth,windowHeight);// Efface le canvas menu
		        	    
		                window.getGC().clearRect(0, 0, window.getCanvas().getWidth(), window.getCanvas().getHeight());
		                window.setCam(game.getPlayer().getY()-window.getHeight()/2);
		
		                game.getPlayer().calculatePosition(window.getWidth(), window.getHeight(), game.getPlatforms());
		                game.getTower().controlTower(input.getPressedKeyset());
		
		                towerRender.render();
		                platformRender.render();
		                playerRender.render();		//On dessine le joueur en dernier pour etre au premier plan
		                
		                gc.save();			// Etat où restore() va revenir
		                gc.setFont(Font.font("Arial", FontWeight.BOLD, 30)); // police Arial, gras, taille 24
		                gc.setFill(Color.WHITE);
		                gc.fillText("Score : "+(int)-window.getCamY()/PlatformSpacing, window.getHeight()-220, 50);
		                gc.restore(); // retire le gras et revient à la police précédente
		                
		                //window.getGC().strokeText("Score: "+(int)-window.getCamY()/PlatformSpacing, window.getHeight()-100, 20);
		
		                //gc.strokeText("FPS: "+1/delta, 540, 36);
		                
		                //on gère les musiques  
		                
	                	soundgame.playMusic();
	            		soundgame.volume(0.7f);
	                	
		               return ;
						
		        	case GAME_OVER:
		                // on redessine une dernière image figée :
		                window.getGC().clearRect(0, 0, window.getCanvas().getWidth(), window.getCanvas().getHeight());
		                towerRender.render();
		                platformRender.render();
		                playerRender.render();
		                
		                //on gère les musiques
		                soundgame.stopMusic();
	                	soundDeath.playMusic();
	                	soundDeath.volume(0.8f);

	                
	                	// puis l’overlay game over
		                menuCanvas.setVisible(true);
		                GameOver.render(window, menuCanvas, (int)-window.getCamY()/PlatformSpacing);
		                System.out.println("IN GAME OVER");
		                return;				
		        }

			}
		};
		animation.start();

		//window.getGroup().getChildren().add(window.getCanvas());	// Ajout du canvas jeu (flouté)
		//window.getGroup().getChildren().add(menuCanvas);           // Ajout du canvas menu au-dessus du canvas jeu (ordre = profondeur)

		// Ajout au group principal
		group.getChildren().add(gameGroup);           // gameGroup --> group principal (fond + tour)
		group.getChildren().add(menuCanvas);          // Canvas menu --> group au-dessus du gameGroup

		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
	}

protected void update(double delta, long now) {
		// TODO Auto-generated method stub
		
	}

// --------------- Lancement du jeu ------------------------

/**
 * 
 * @param args
 */

    public static void launchApp(String[] args) {
        launch(args);
    }

}