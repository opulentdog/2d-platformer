package tsp.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Overlay du Menu d'accueil : affichage de tous les éléments :
 * joueur, numéro de seed, titre, bouton de sélection et de lancement
 */
public class Menu {
	
	/*-------------------------------------------------------------------------------------------------------------------------------------
	 * ------------------- Fields -------------------
	 * -------------------------------------------------------------------------------------------------------------------------------------
	 */
	
    private final static double MIN = Math.min(Constants.WINDOWHEIGHT, Constants.WINDOWWIDTH);

	/**
     * Dimensions du bouton PLAY : image carrée donc seul un côté est paramétré
     */
    private final static double PLAYSIDE = 0.2 * MIN;
	/**
	 * Coordonnées des boutons PLAY dans la fenêtre
	 */
    private final static double PLAY_Y = 0.673 * Constants.WINDOWHEIGHT;
    private final static double SOLO_X = ( 0.75*Constants.WINDOWWIDTH - PLAYSIDE )/2;
    private final static double MULTI_X = ( 1.25*Constants.WINDOWWIDTH - PLAYSIDE )/2;

    /**
     * Dimensions des boutons flèches (image carrée)
     */
    private final static double ARROW_SIDE = 0.096 * MIN;

    /**
     * Ordonnée des flèches de skin dans la fenêtre
     */
    private final static double ARROW_Y = 0.5 * Constants.WINDOWHEIGHT;
    
    /**
     * Ordonnée des flèches de skin dans la fenêtre
     */
    private final static double SEED_ARROW_Y = 0.4 * Constants.WINDOWHEIGHT;
    /**
     * Abscisse des flèches
     */
    private final static double LEFTARROW_X = ( 0.75 * Constants.WINDOWWIDTH - ARROW_SIDE )/2;
    private final static double RIGHTARROW_X = ( 1.25 * Constants.WINDOWWIDTH - ARROW_SIDE )/2;

    
    /**
     * Dimensions de l'image du Titre
     */
    private final static double TITLE_WIDTH = Constants.WINDOWWIDTH/1.735;
    private final static double TITLE_HEIGHT = TITLE_WIDTH * 0.75;

    
    // Création des boutons
    private Button playSolo = new Button(SOLO_X, PLAY_Y,PLAYSIDE,PLAYSIDE,Constants.SOLONORMAL_PATH,Constants.SOLOHOVER_PATH);
    private Button playMulti = new Button(MULTI_X, PLAY_Y,PLAYSIDE,PLAYSIDE,Constants.MULTINORMAL_PATH,Constants.MULTIHOVER_PATH);

    private Button leftarrow = new Button(LEFTARROW_X, ARROW_Y,ARROW_SIDE,ARROW_SIDE,Constants.LEFTARROW_PATH,Constants.LEFTARROWHOVER_PATH);
    private Button rightarrow = new Button(RIGHTARROW_X, ARROW_Y,ARROW_SIDE,ARROW_SIDE,Constants.RIGHTARROW_PATH,Constants.RIGHTARROWHOVER_PATH);

    private Button seedLeftArrow = new Button(LEFTARROW_X, SEED_ARROW_Y,ARROW_SIDE,ARROW_SIDE,Constants.LEFTARROW_PATH,Constants.LEFTARROWHOVER_PATH);
    private Button seedRightArrow = new Button(RIGHTARROW_X, SEED_ARROW_Y,ARROW_SIDE,ARROW_SIDE,Constants.RIGHTARROW_PATH,Constants.RIGHTARROWHOVER_PATH);

    
    /*-------------------------------------------------------------------------------------------------------------------------------------
	 * ------------------- Methods -------------------
	 * -------------------------------------------------------------------------------------------------------------------------------------
	 */
	
    /**
     * Affiche le Menu et ses éléments : Méthode non-static 
     * pour pouvoir utiliser les méthodes non-static de Button
     * @param overlayCanvas
     * @param seed
     * @param sourisX
     * @param sourisY
     */
    public void render(Canvas overlayCanvas, int seed, int bestScore, double sourisX, double sourisY) {

    	// Récupère le contexte du canvas overlay (et non celui du jeu)
    	GraphicsContext gc = overlayCanvas.getGraphicsContext2D(); 
    	
    	double w = overlayCanvas.getWidth();
	    double h = overlayCanvas.getHeight();
	    
    	// Efface le canvas menu à chaque frame pour éviter les superpositions
    	gc.clearRect(0, 0, w, h);
    	
    	// Affichage de l'image du titre
        Texture titre = new Texture(Constants.TITRE_JEU_1, TITLE_WIDTH, TITLE_HEIGHT);
        gc.drawImage(titre.getImage(), ( w - TITLE_WIDTH)/2 , w/(-17.33) );
        
        //Affichage du bouton SOLO en version normal ou "survolé"
        playSolo.setImgPath(sourisX,sourisY);
        gc.drawImage(playSolo.getImage(), SOLO_X , PLAY_Y);
        
        //Affichage du bouton MULTI en version normal ou "survolé"
        playMulti.setImgPath(sourisX,sourisY);
        gc.drawImage(playMulti.getImage(), MULTI_X , PLAY_Y);
        
        //Affichage de la flèche gauche en version normal ou "survolé"
        leftarrow.setImgPath(sourisX,sourisY);
        gc.drawImage(leftarrow.getImage(), LEFTARROW_X , ARROW_Y);
        
        //Affichage de la flèche droite en version normal ou "survolé"
        rightarrow.setImgPath(sourisX,sourisY);
        gc.drawImage(rightarrow.getImage(), RIGHTARROW_X , ARROW_Y);
        
        //Affichage de la flèche SEEDgauche en version normal ou "survolé"
        seedLeftArrow.setImgPath(sourisX,sourisY);
        gc.drawImage(seedLeftArrow.getImage(), LEFTARROW_X , SEED_ARROW_Y);
        
        //Affichage de la flèche SEEDdroite en version normal ou "survolé"
        seedRightArrow.setImgPath(sourisX,sourisY);
        gc.drawImage(seedRightArrow.getImage(), RIGHTARROW_X , SEED_ARROW_Y);
        System.out.println("Refresh Menu");
        
        gc.setFill(Color.WHITE);
	    gc.setFont(Font.font("Krungthep", FontWeight.MEDIUM, 0.06*h));
	    gc.fillText(""+seed, 0.48 * w, SEED_ARROW_Y+0.7*ARROW_SIDE);
	    
	    // Affichage du meilleur score pour cette seed
	    gc.setFont(Font.font("Krungthep", FontWeight.BOLD, 0.035*h));
	    gc.fillText("Meilleur score : " + bestScore, 0.33 * w, SEED_ARROW_Y - 0.25 * ARROW_SIDE);
    }
    
    /**
     * Indique si un des boutons de menu est franchi par la souris
     * @param sourisX
     * @param sourisY
     * @return
     */
    public boolean doesMenubuttonsNeedsRedraw(double sourisX, double sourisY) {
    	if (		playSolo.isEnteringOrExiting(sourisX, sourisY)
    			|| playMulti.isEnteringOrExiting(sourisX, sourisY)
    			|| leftarrow.isEnteringOrExiting(sourisX, sourisY)
    			|| rightarrow.isEnteringOrExiting(sourisX, sourisY)
    			|| seedLeftArrow.isEnteringOrExiting(sourisX, sourisY)
    			|| seedRightArrow.isEnteringOrExiting(sourisX, sourisY)
    		) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Indique quel bouton était cliqué en indiquant si la souris est superposée sur le bouton
     * @param x : position de la souris selon l'horizontale
     * @param y : position de la souris selon la verticale
     * @return
     */
    public boolean isPlayClicked(double x,double y) {
        return playSolo.isHoveredCirc(x, y);
    }
    public boolean isLeftArrowClicked(double x,double y) {
        return leftarrow.isHoveredCirc(x, y);
    }
    public boolean isRightArrowClicked(double x,double y) {
        return rightarrow.isHoveredCirc(x, y);
    }
	public boolean isSeedLeftArrowClicked(double x, double y) {
		return seedLeftArrow.isHoveredCirc(x, y);
	}
	public boolean isSeedRightArrowClicked(double x, double y) {
		return seedRightArrow.isHoveredCirc(x, y);
	}
}
