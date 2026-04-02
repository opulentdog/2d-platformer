package tsp.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Menu {

	/***
	 * Coordonnées du bouton dans la fenêtre
	 */
    private static double btnX = 250;
    private static double btnY = 200;
    /**
     * Dimensions du bouton
     */
    private static double btnWidth = 200;
    private static double btnHeight = 60;

    public static void render(Window window, Canvas overlayCanvas) {
        //GraphicsContext gc = window.getGC(); On le garde on sait jamais
    	
    	// Récupère le contexte du canvas overlay (et non celui du jeu)
    	GraphicsContext gc = overlayCanvas.getGraphicsContext2D(); 
    	// Efface le canvas menu à chaque frame pour éviter les superpositions
    	gc.clearRect(0, 0, overlayCanvas.getWidth(), overlayCanvas.getHeight());
  
       // gc.setFill(Color.GREEN);
        //gc.fillRect(0,0,window.getWidth(),window.getHeight());

        gc.setFill(Color.RED);
        gc.fillRect(btnX,btnY,btnWidth,btnHeight);

        gc.setFill(Color.BLACK);
        gc.fillText("PLAY", btnX+80, btnY+35);
        
    }

    /**
     * Indique si la souris est superposée au bouton start ( n'indique pas directement que le bouton start est cliqué )
     * @param x position de la souris selon l'horizontale
     * @param y position de la souris selon la verticale
     * @return
     */
    public static boolean isClicked(double x,double y) {
        return x >= btnX && x <= btnX+btnWidth &&
               y >= btnY && y <= btnY+btnHeight;
    }
}
