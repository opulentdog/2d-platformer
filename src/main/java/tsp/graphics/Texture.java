package tsp.graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Shape3D;

/**
 * Sert à importer les images
 * 2 constructeurs différents selon si l' image "répétée" sur une surface ou utilisée directement comme "sprite"
 */
public class Texture {
	
	/*-------------------------------------------------------------------------------------------------------------------------------------
	 * ------------------- Fields -------------------
	 * -------------------------------------------------------------------------------------------------------------------------------------
	 */
	
	/**
	 * Largeur de la texture
	 */
	private double width;
	
	/**
	 * Hauteur de la texture
	 */
	private double height;
	
	/**
	 * Image de la texture
	 */
	private Image img;

	private String imgPath;
	
	/*##########################################################################################################

	 * ------------------- Constructors -------------------
	 */
	
	/**
	 * constructeur lorsque l'image est répétée sur une surface
	 * @param imgPath
	 * @param width
	 * @param height
	 */
	public Texture(String imgPath, double width, double height) {
		this.imgPath = imgPath;
		String imageStr = getClass().getResource(imgPath).toString();
		img = new Image(imageStr, width, height, false, true);
	}
	
	/**
	 * constructeur lorsque l'image est utilisée directement comme simple sprite
	 * @param imgPath
	 */
	public Texture(String imgPath) {
		this.imgPath = imgPath;
		String imageStr = getClass().getResource(imgPath).toString();
		img = new Image(imageStr, width, height, false, true);
	}
	
	/***
	 * ------------ Getters --------------------------
	 */
	public Image getImage() {
		return img;
	}
	
	public String getPath() {
		return imgPath;
	}
	
	public double getHeight() {
		return height;
	}
	
	public double getWidth() {
		return width;
	}
	
	/***
	 * ------------ Methods --------------------------
	 */
	
	public void setImgPath(String newPath, double width, double height) {
	    this.imgPath = newPath;
	    String imageStr = getClass().getResource(newPath).toString();
	    this.img = new Image(imageStr, width, height, false, true);
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
	
	/**
	 * Définit l'image comme fond d'écran pour la fenêtre avec ImageView
	 * @param window
	 */
	public void setBG(Window window) {
		ImageView bgView = new ImageView(img);
		bgView.setFitWidth(width);
		bgView.setFitHeight(height);
		bgView.setPreserveRatio(false);
		window.getGroup().getChildren().add(bgView);
		
	}

}
