package tsp.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * écran de game over
 */
public class GameOver {

/***
 * Coordonnées du bouton dans la fenêtre
 */
private final static double BTN_X = 250;
private final static double BTN_Y = 200;
/**
 * Dimensions du bouton
 */
private final static double BTN_WIDTH = 200;
private final static double BTN_HEIGHT = 60;

public static void render(Window window, Canvas overlayCanvas) {
    //GraphicsContext gc = window.getGC(); On le garde on sait jamais
	
	// Récupère le contexte du canvas overlay (et non celui du jeu)
	GraphicsContext gc = overlayCanvas.getGraphicsContext2D(); 
	// Efface le canvas menu à chaque frame pour éviter les superpositions
	gc.clearRect(0, 0, overlayCanvas.getWidth(), overlayCanvas.getHeight());

   // gc.setFill(Color.GREEN);
    //gc.fillRect(0,0,window.getWidth(),window.getHeight());

    gc.setFill(Color.RED);
    gc.fillRect(BTN_X,BTN_Y,BTN_WIDTH,BTN_HEIGHT);

    gc.setFill(Color.BLACK);
    gc.fillText("RETRY", BTN_X+80, BTN_Y+35);
    
}

/**
 * Indique si la souris est superposée au bouton start ( n'indique pas directement que le bouton start est cliqué )
 * @param x position de la souris selon l'horizontale
 * @param y position de la souris selon la verticale
 * @return
 */
public static boolean isRetryClicked(double x,double y) {
    return x >= BTN_X && x <= BTN_X+BTN_WIDTH &&
           y >= BTN_Y && y <= BTN_Y+BTN_HEIGHT;
           }
}
