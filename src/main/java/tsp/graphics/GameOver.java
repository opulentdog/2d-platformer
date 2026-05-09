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
private final static double BTN_X = 0.36 * Constants.WINDOWWIDTH;
private final static double BTN_Y = 0.388 * Constants.WINDOWHEIGHT;
/**
 * Dimensions du bouton
 */
private final static double BTN_WIDTH = 0.288 * Constants.WINDOWWIDTH;//200
private final static double BTN_HEIGHT = 0.3 * BTN_WIDTH;

public static void render(Window window, Canvas overlayCanvas, int score) {
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
    gc.setFont(Font.font("Krungthep", FontWeight.BOLD, 40));
    gc.fillText("GAME OVER", 0.327 * w, 0.35 * h);

    gc.setFill(Color.WHITE);
    gc.setFont(Font.font("Krungthep", FontWeight.MEDIUM, 20));
    gc.fillText("Score: " + score, 0.428 * w, 0.7 * h);
    gc.fillText("Click to retry", 0.4 * w, 0.57 * h);
	

    gc.setFill(Color.RED);
    gc.fillRect(BTN_X,BTN_Y,BTN_WIDTH,BTN_HEIGHT);

    gc.setFill(Color.BLACK);
    gc.setFont(Font.font("Krungthep", FontWeight.BOLD, 30));
    gc.fillText("RETRY", BTN_X+ 0.275*BTN_WIDTH, BTN_Y+ 0.67 * BTN_HEIGHT);
    
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
