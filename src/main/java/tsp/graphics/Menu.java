package tsp.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Menu {
	/**
     * Dimensions du bouton PLAY : image carrée donc seul un côté est paramétré
     */
    private final static double PLAYSIDE = 100;
	/**
	 * Coordonnées du bouton dans la fenêtre
	 */
    private final static double PLAY_X = ( Constants.WINDOWWIDTH - PLAYSIDE )/2;
    private final static double PLAY_Y = 350;

    /**
     * Dimensions des boutons flèches (image carrée)
     */
    private final static double ARROW_SIDE = 50;

    /**
     * Ordonnée des flèches dans la fenêtre
     */
    private final static double ARROW_Y = 250;
    /**
     * Abscisse des flèches
     */
    private final static double LEFTARROW_X = ( 0.8 * Constants.WINDOWWIDTH - ARROW_SIDE )/2;
    private final static double RIGHTARROW_X = ( 1.2 * Constants.WINDOWWIDTH - ARROW_SIDE )/2;

    
    /**
     * Dimensions de l'image du Titre
     */
    private final static double TITLE_WIDTH = Constants.WINDOWWIDTH/1.735;
    private final static double TITLE_HEIGHT = TITLE_WIDTH * 0.75;

    
    // Création des boutons
    private Button play = new Button(PLAY_X, PLAY_Y,PLAYSIDE,PLAYSIDE,Constants.PLAYNORMAL_PATH,Constants.PLAYHOVER_PATH);
    private Button leftarrow = new Button(LEFTARROW_X, ARROW_Y,ARROW_SIDE,ARROW_SIDE,Constants.LEFTARROW_PATH,Constants.LEFTARROWHOVER_PATH);
    private Button rightarrow = new Button(RIGHTARROW_X, ARROW_Y,ARROW_SIDE,ARROW_SIDE,Constants.RIGHTARROW_PATH,Constants.RIGHTARROWHOVER_PATH);

    
    /**
     * Affiche le Menu et ses éléments : Méthode non-static pour pouvoir utiliser les méthodes non-static de Button
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
        
        //Affichage du bouton PLAY en version normal ou "survolé"
        play.setImgPath(sourix,sourisy);
        gc.drawImage(play.getImage(), PLAY_X , PLAY_Y);
        
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
        return play.isHoveredCirc(x, y);
    }
}
