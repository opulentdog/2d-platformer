package tsp.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Menu {
	/**
	 * ------------------- Fields -------------------
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
     * Ordonnée des flèches dans la fenêtre
     */
    private final static double ARROW_Y = 0.5 * Constants.WINDOWHEIGHT;//=250
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

    /**
	 * ------------------- Methods -------------------
	 */
	
    /**
     * Affiche le Menu et ses éléments : Méthode non-static 
     * pour pouvoir utiliser les méthodes non-static de Button
     * @param window
     * @param overlayCanvas
     * @param sourix
     * @param sourisy
     */
    public void render(Window window, Canvas overlayCanvas, double sourix,double sourisy) {
        //GraphicsContext gc = window.getGC(); On le garde on sait jamais
    	
    	// Récupère le contexte du canvas overlay (et non celui du jeu)
    	GraphicsContext gc = overlayCanvas.getGraphicsContext2D(); 
    	// Efface le canvas menu à chaque frame pour éviter les superpositions
    	gc.clearRect(0, 0, overlayCanvas.getWidth(), overlayCanvas.getHeight());
    	
    	// Affichage de l'image du titre
        Texture titre = new Texture(Constants.TITRE_JEU_1, TITLE_WIDTH, TITLE_HEIGHT);
        gc.drawImage(titre.getImage(), ( overlayCanvas.getWidth() - TITLE_WIDTH)/2 , overlayCanvas.getWidth()/(-17.33) );
        
        //Affichage du bouton SOLO en version normal ou "survolé"
        playSolo.setImgPath(sourix,sourisy);
        gc.drawImage(playSolo.getImage(), SOLO_X , PLAY_Y);
        
        //Affichage du bouton MULTI en version normal ou "survolé"
        playMulti.setImgPath(sourix,sourisy);
        gc.drawImage(playMulti.getImage(), MULTI_X , PLAY_Y);
        
        //Affichage de la flèche gauche en version normal ou "survolé"
        leftarrow.setImgPath(sourix,sourisy);
        gc.drawImage(leftarrow.getImage(), LEFTARROW_X , ARROW_Y);
        
        //Affichage de la flèche droite en version normal ou "survolé"
        rightarrow.setImgPath(sourix,sourisy);
        gc.drawImage(rightarrow.getImage(), RIGHTARROW_X , ARROW_Y);
     
        
        /*
        // Couleurs qui changent selon le survol
        Color btnColor  = survolSouris ? Color.YELLOW : Color.BLUE;
        Color borderColor = survolSouris ? Color.ORANGE : Color.RED;
        Color textColor = survolSouris ? Color.BLUE   : Color.RED;

        
        // Création du bouton bleu
        gc.setFill(btnColor);
        gc.fillRect(BTN_X,BTN_Y,BTN_WIDTH,BTN_HEIGHT);
        // Cadre blanc autour du bouton bleu
        gc.setStroke(borderColor);
        gc.setLineWidth(3);
        gc.strokeRect(BTN_X, BTN_Y, BTN_WIDTH, BTN_HEIGHT);     
        // Ecriture du mot PLAY
        gc.save();			// Etat où restore() va revenir
        gc.setFont(Font.font("Krungthep", FontWeight.BOLD, 30)); // police Arial, gras, taille 24
        gc.setFill(textColor);
        gc.fillText("PLAY", BTN_X+60, BTN_Y+40);
        gc.restore(); // retire le gras et revient à la police précédente
       */

    }

    /**
     * Indique si la souris est superposée au bouton start ( n'indique pas directement que le bouton start est cliqué )
     * @param x position de la souris selon l'horizontale
     * @param y position de la souris selon la verticale
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
}
