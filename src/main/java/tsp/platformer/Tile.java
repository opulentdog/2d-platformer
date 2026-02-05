package tsp.platformer;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;


public class Tile {
	private double width;
	private double height;
	private Image img;
	
	Tile(Image img, double w, double h) {
		this.img = img;
		this.width = w;
		this.height = h;
	}
	
	public Image tileWithCanvas(int repeatX, int repeatY) {

		  Canvas canvas = new Canvas(width * repeatX, height * repeatY);
		  GraphicsContext gc = canvas.getGraphicsContext2D();

		  // On "tamponne" l'image en quadrillage
		  for (int y = 0; y < repeatY; y++) {
		    for (int x = 0; x < repeatX; x++) {
		      gc.drawImage(img, x * width, y * height);
		    }
		  }

		  // Convertit le Canvas en Image
		  WritableImage out = new WritableImage((int) (width * repeatX), (int) (height * repeatY));
		  canvas.snapshot(new SnapshotParameters(), out);
		  return out;
		}


}