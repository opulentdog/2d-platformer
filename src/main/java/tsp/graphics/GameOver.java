package tsp.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * écran de game over
 */
public class GameOver {
	
	/***
	 * ------------------- Fields -------------------
	 */
	
	/**
	 * Dimensions du bouton
	 */
	private final static double BTN_SIDE = 0.2 * Constants.WINDOWWIDTH;//200
	
	/**
	 * Coordonnées des boutons MENU et RETRY dans la fenêtre
	 */
	private final static double RETRY_X = 0.33 * Constants.WINDOWWIDTH -BTN_SIDE/2 ;
	private final static double MENU_X = 0.66 * Constants.WINDOWWIDTH - BTN_SIDE/2;
	private final static double BTN_Y = 0.5 * Constants.WINDOWWIDTH;

	
	/**
	 * Création des boutons
	 */
	private Button retry = new Button(RETRY_X, BTN_Y,BTN_SIDE,BTN_SIDE, Constants.RETRYNORMAL_PATH, Constants.RETRYHOVER_PATH);
    private Button menu = new Button(MENU_X, BTN_Y,BTN_SIDE,BTN_SIDE,Constants.MENUNORMAL_PATH,Constants.MENUHOVER_PATH);

	/***
	 * ------------------- Methods -------------------
	 */
	
	public void render(Window window, Canvas overlayCanvas, int score, String skinPath, double sourisx, double sourisy) {
	    //GraphicsContext gc = window.getGC(); On le garde on sait jamais
		
		// Récupère le contexte du canvas overlay (et non celui du jeu)
		GraphicsContext gc = overlayCanvas.getGraphicsContext2D(); 
		// Efface le canvas menu à chaque frame pour éviter les superpositions
		gc.clearRect(0, 0, overlayCanvas.getWidth(), overlayCanvas.getHeight());	
		
	    double w = window.getWidth();
	    double h = window.getHeight();
	    
	    // Overlay sombre
	    gc.save();
	    gc.setGlobalAlpha(0.6);
	    gc.setFill(Color.BLACK);
	    gc.fillRect(0, 0, w, h);
	    gc.restore();
	    
	 
	    // Texte
	    gc.setFill(Color.RED);
	    gc.setFont(Font.font("Krungthep", FontWeight.BOLD, 0.07*h));
	    gc.fillText("GAME OVER", (w-0.378*h)/2, 0.35 * h);
	
	    gc.setFill(Color.WHITE);
	    gc.setFont(Font.font("Krungthep", FontWeight.MEDIUM, 0.07*h));
	    gc.fillText("Score: " + score, 0.3 * w, 0.8 * h);
	    //gc.fillText("Click to retry", 0.4 * w, 0.7 * h);
		
	    // Affichage des boutons
        retry.setImgPath(sourisx,sourisy);
        gc.drawImage(retry.getImage(), RETRY_X , BTN_Y);
        menu.setImgPath(sourisx,sourisy);
        gc.drawImage(menu.getImage(), MENU_X , BTN_Y);

	    
	    // Perso triste
	    String cryPath = Constants.NORMALTOCRY.get(skinPath); 				// On récupère le chemin relatif associé
	    String imageStr = GameOver.class.getResource(cryPath).toString(); 	// On le convertit en absolu
	    double sadPlayerSide = 0.15*Constants.WINDOWHEIGHT;					// On crée la taille de l'image
	    Image sadPlayer = new Image(imageStr, sadPlayerSide, sadPlayerSide, false, true); // On crée l'image
		gc.drawImage(sadPlayer, 0.5*(w-sadPlayerSide), 0.1 * h );						  // On affiche l'image
	
        System.out.println("Printing GameOver");
	}
	
	/**
	 * Indique si la souris est superposée au bouton ( n'indique pas directement que le bouton start est cliqué )
	 * @param x position de la souris selon l'horizontale
	 * @param y position de la souris selon la verticale
	 * @return
	 */

	public boolean isRetryClicked(double x,double y) {
	    return retry.isHoveredCirc(x,y);
	}
	public boolean isMenuClicked(double x,double y) {
	    return menu.isHoveredCirc(x,y);
	}
}
