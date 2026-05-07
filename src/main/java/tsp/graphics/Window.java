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
	private int windowWidth = Constants.WINDOWWIDTH;
	
	/**
	 * hauteur de la fenetre
	 */
	private int windowHeight = Constants.WINDOWHEIGHT;
	
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
	/**
	 * Espacement vertical entre chaque plateforme
	 */
	private static final int PlatformSpacing = 300;
	
	/**
	 * menuBackground permet de calculer une seule fois les snapshots de MENU
	 */
	private WritableImage menuBackground = null;
    private Menu menu;

	/**
	 * Coordonnées de la position de la souris dans Window
	 */
	private double sourisx;
    private double sourisy;


	

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
	        if (menu.isPlayClicked(e.getX(), e.getY())) {
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
	    menuBackground = null; // ✅ Force le recalcul du fond flouté au prochain menu

	
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
	    this.menu = new Menu();

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
	 *  Creation du menu avec les calques flous et nets
	 */
	private void setMenu(GaussianBlur menuBlur) {
		// On ne calcule le snapshot flou qu'une seule fois
	    if (menuBackground == null) {
	    	// On vide le graphical Context
	        this.getGC().clearRect(0, 0, this.getWidth(), this.getHeight());
	        // On dessine le fond et la tour
	        this.getGC().drawImage(bg.getImage(), 0, 0, this.getWidth(), this.getHeight());
	        towerRender.render();
	        
	        SnapshotParameters params = new SnapshotParameters();
	        params.setFill(Color.TRANSPARENT);

	        WritableImage snapshotTower = gameGroup.snapshot(params, null);
	        WritableImage snapshotCanvas = this.getCanvas().snapshot(params, null);

	        // On fusionne les deux snapshots floutés dans menuBackground
	        menuBackground = new WritableImage(this.getWidth(), this.getHeight());
	        Canvas tempCanvas = new Canvas(this.getWidth(), this.getHeight());
	        GraphicsContext tempGc = tempCanvas.getGraphicsContext2D();
	        tempGc.setEffect(menuBlur);
	        tempGc.drawImage(snapshotCanvas, 0, 0);
	        tempGc.drawImage(snapshotTower, 0, 0);
	        tempGc.applyEffect(menuBlur);
	        SnapshotParameters p2 = new SnapshotParameters();
	        p2.setFill(Color.TRANSPARENT);
	        tempCanvas.snapshot(p2, menuBackground); // On sauvegarde le résultat flouté
	    }

	    // Chaque frame : on redessine juste le fond mémorisé, pas de snapshot
	    this.getGC().clearRect(0, 0, this.getWidth(), this.getHeight());
	    this.getGC().drawImage(menuBackground, 0, 0);  // Aucun snapshot, aucune condition de course
	    // On place le joueur au milieu de la fenetre
	    game.getPlayer().setPostition(game.getPlayer().getX(), windowHeight / 2);
	    
	    menuCanvas.setVisible(true);
	    playerRender.render();
	    
	    //On lit la position de la souris
	    menuCanvas.setOnMouseMoved(event -> {
	        sourisx = event.getX() ;
	        sourisy = event.getY();
	    }
	    );
	    Menu menu = new Menu();
		//Affichage du Menu : bouton + Titre
	    menu.render(this, menuCanvas, sourisx, sourisy);
	}
	
	/**
	 *  Creation de lavec les calques flous et nets
	 */
	private void setRunning() {
		this.getCanvas().setEffect(null);		   // Supprime le flou	
		menuCanvas.setVisible(false);		       // Cache le canvas menu pendant la partie	
		menuCanvas.getGraphicsContext2D().clearRect(0,0,windowWidth,windowHeight);// Efface le canvas menu
	    
		this.getGC().clearRect(0, 0, this.getCanvas().getWidth(), this.getCanvas().getHeight());
		this.setCam(game.getPlayer().getY()-this.getHeight()/2);

        game.getPlayer().calculatePosition(this.getWidth(), this.getHeight(), game.getPlatforms());
        game.getTower().controlTower(input.getPressedKeyset());

        towerRender.render();
        platformRender.render();
        playerRender.render();		//On dessine le joueur en dernier pour etre au premier plan
        
        gc.save();			// Etat où restore() va revenir
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 30)); // police Arial, gras, taille 24
        gc.setFill(Color.WHITE);
        gc.fillText("Score : "+(int)-this.getCamY()/PlatformSpacing, this.getHeight()-220, 50);
        gc.restore(); // retire le gras et revient à la police précédente
        
        //window.getGC().strokeText("Score: "+(int)-window.getCamY()/PlatformSpacing, window.getHeight()-100, 20);

        //gc.strokeText("FPS: "+1/delta, 540, 36);
        
        //on gère les musiques  
        
    	soundgame.playMusic();
		soundgame.volume(0.7f);
    	
	}
	
	private void setGameOver() {
		// on redessine une dernière image figée :
        this.getGC().clearRect(0, 0, this.getCanvas().getWidth(), this.getCanvas().getHeight());
        towerRender.render();
        platformRender.render();
        playerRender.render();
        
        //on gère les musiques
        soundgame.stopMusic();
    	soundDeath.playMusic();
    	soundDeath.volume(0.8f);

    
    	// puis l’overlay game over
        menuCanvas.setVisible(true);
        GameOver.render(this, menuCanvas, (int)-this.getCamY()/PlatformSpacing);
        System.out.println("IN GAME OVER");
	}
	
	/**
	 * TODO segmenter
	 * 
	 */
	@Override
	public void start(Stage stage) {		
		
		// Construire tout les éléments de la fenêtre
		startScene(stage);
		game = new Game(this);
		this.input = new Input(this);
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
		
			//private static final int PlatformSpacing = 300;

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
			        	setMenu(menuBlur);
			            return;
			            
		        	case RUNNING:
		        		setRunning();
		        		return ;
						
		        	case GAME_OVER:
		        		setGameOver();
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
