package tsp.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * écran de game over
 */
public class GameOver {
	public static void render(Window window, int score) {
	    GraphicsContext gc = window.getGC();
	    double w = window.getCanvas().getWidth();
	    double h = window.getCanvas().getHeight();

	    gc.setGlobalAlpha(0.6);
	    gc.setFill(Color.BLACK);
	    gc.fillRect(0, 0, w, h);
	    gc.setGlobalAlpha(1.0);

	    gc.setFill(Color.WHITE);
	    gc.setFont(Font.font("Helvetica", FontWeight.MEDIUM, 20));
	    gc.fillText("Score: " + score, w*0.5 - 40, h*0.35 + 30);
	    gc.fillText("R: Restart    ESC: Quit", w*0.5 - 80, h*0.35 + 70);
	    
	    gc.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
	    gc.setFill(Color.RED);
	    gc.setLineWidth(3);
	    gc.fillText("GAME OVER", w*0.45 - 50, h*0.35);


	}

}
