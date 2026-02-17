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
	
	/**
	 * 
	 * @param imgPath
	 * @param width
	 * @param height
	 */
	public Texture(String imgPath, double width, double height) {
		String imageStr = getClass().getResource(imgPath).toString();
		img = new Image(imageStr, width, height, false, true);
	}
	
	public Texture(String imgPath) {
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
	public Texture(String imgPath, int width, int height, int repeatX, int repeatY) {
		Image texture = new Image(getClass().getResource(imgPath).toString(), height/repeatX, width/repeatY, false, false);
		double w = texture.getWidth();
		double h = texture.getHeight();
		Tile tile = new Tile(texture, w, h);
		Image tiledTexture = tile.tileWithCanvas(repeatX, repeatY);
	}
	
	public void applyTexture(Shape3D shape, Image tiledTexture) {
		PhongMaterial mat = new PhongMaterial();
		mat.setDiffuseMap(tiledTexture);
		shape.setMaterial(mat);
	}
	
	public Image getImage() {
		return img;
	}

}
