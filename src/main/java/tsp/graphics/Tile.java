package tsp.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;

/**
 * classe représentant une tuile de texture
 */
public class Tile {
	
	/***
	 * ------------------- Fields -------------------
	 */
	
	private double width;
	private double height;
	private int repeatX = 5;   // nombre de tuiles horizontalement (autour du cylindre)
	private int repeatY = 5;   // nombre de tuiles verticalement (hauteur)
	private Image img;
	
	/***
	 * ------------------- Constructor -------------------
	 */
	
	protected Tile(Image img, double w, double h) {
		this.img = img;
		this.width = w;
		this.height = h;
	}
	
	/***
	 * ------------------- Methods -------------------
	 */
	
	/**
	 * Crée une nouvelle image en répétant l'image actuelle sous forme de grille 
	 * Cette méthode utilise un {@link javafx.scene.canvas.Canvas} pour dessiner l'image 
	 * source autant de fois que spécifié par les paramètres de répétition.
	 * 
	 * @param repeatX Le nombre de fois que l'image doit être répétée horizontalement.
	 * @param repeatY Le nombre de fois que l'image doit être répétée verticalement.
	 * @return Une nouvelle instance de {@link Image} contenant le résultat de la mosaïque.
	 */
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