package tsp.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Shape3D;

/**
 * Sert à importer les images
 * 2 constructeurs différents selon si image "répétée" sur une surface ou utilisée directement comme "sprite"
 */
public class Texture {
	/**
	 * 
	 */
	private double width;
	
	/**
	 * 
	 */
	private double height;
	
	/**
	 * 
	 */
	private Image img;

	private String imgPath;
	

	/**
	 * 
	 * @param imgPath
	 * @param width
	 * @param height
	 */
	public Texture(String imgPath, double width, double height) {
		this.imgPath = imgPath;
		String imageStr = getClass().getResource(imgPath).toString();
		img = new Image(imageStr, width, height, false, true);
	}
	
	public Texture(String imgPath) {
		this.imgPath = imgPath;
		String imageStr = getClass().getResource(imgPath).toString();
		img = new Image(imageStr, width, height, false, true);
	}
	
	/**
	 * Texture pour un cylindre (À généraliser plus tard éventuellement pour n'importe quelle forme)
	 * @param imgPath
	 * @param width
	 * @param height
	 * @param repeatX
	 * @param repeatY
	 */
	public void tileTexture(int repeatX, int repeatY) {
		img = new Image(getClass().getResource(imgPath).toString(), height, height, false, false);
		double w = img.getWidth();
		double h = img.getHeight();
		Tile tile = new Tile(img, w, h);
		img = tile.tileWithCanvas(repeatX, repeatY);
	}
	
	public void applyTexture(Shape3D shape) {
		PhongMaterial mat = new PhongMaterial();
		mat.setDiffuseMap(img);
		shape.setMaterial(mat);
	}
	
	public Image getImage() {
		return img;
	}

}
