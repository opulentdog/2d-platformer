package tsp.platformer;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;


public class Tile {
	private double w;
	private double h;
	private Image img;
	
	Tile(Image img, double w, double h) {
		this.img = img;
		this.w = w;
		this.h = h;
	}
	
	public Image tileWithCanvas(int repeatX, int repeatY) {

		  Canvas canvas = new Canvas(w * repeatX, h * repeatY);
		  GraphicsContext gc = canvas.getGraphicsContext2D();

		  // On "tamponne" l'image en quadrillage
		  for (int y = 0; y < repeatY; y++) {
		    for (int x = 0; x < repeatX; x++) {
		      gc.drawImage(img, x * w, y * h);
		    }
		  }

		  // Convertit le Canvas en Image
		  WritableImage out = new WritableImage((int) (w * repeatX), (int) (h * repeatY));
		  canvas.snapshot(new SnapshotParameters(), out);
		  return out;
		}


}