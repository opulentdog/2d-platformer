package tsp.graphics;

import javafx.scene.image.Image;

/**
 * Classe d'un bouton 
 */
public class Button {
	/*-------------------------------------------------------------------------------------------------------------------------------------
	 * ------------------- Fields -------------------
	 * -------------------------------------------------------------------------------------------------------------------------------------
	 */
	
	/**
	 * Dimensions du bouton
	 */
	private double width;
	private double height;
	
	/**
	 * Coordonnées du bouton
	 */
	private double x;
	private double y;
	
	/**
	 * Image du bouton non survolée par la souris
	 */
	private Image imgNormal;
	/**
	 * Image du bouton survolée par la souris
	 */
	private Image imgHover;
	/**
	 * Image du bouton chargée par le bouton
	 */
	private Image currentImg;
	/**
	 * Indique si le bouton était survolé à la frame précédente
	 */
	private boolean wasHovered = false;

	/*-------------------------------------------------------------------------------------------------------------------------------------
	 * ------------------- Constructor -------------------
	 * -------------------------------------------------------------------------------------------------------------------------------------
	 */
	
	/**
	 * 
	 * @param x : abscisse du bouton
	 * @param y : abscisse du bouton
	 * @param width : largeur du bouton
	 * @param height : hauteur du bouton
	 * @param normalPath : chemin de l'image non survolée du bouton
	 * @param hoverPath : chemin de l'image survolée du bouton
	 */
	public Button(double x, double y, double width, double height, String normalPath, String hoverPath) {
		this.width=width;
		this.height=height;
		this.x=x;
		this.y=y;
		this.imgNormal = new Texture(normalPath, width, height).getImage();
        this.imgHover  = new Texture(hoverPath,  width, height).getImage();
        this.currentImg = imgNormal;

	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------
	 * ------------------- Getters -------------------
	 * -------------------------------------------------------------------------------------------------------------------------------------
	 */
	
	public Image getImage() {
		return currentImg;
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------
	 * ------------------- Setters -------------------
	 * -------------------------------------------------------------------------------------------------------------------------------------
	 */
	
	/**
	 * 
	 * @param sourisx : Abscisse de la souris
	 * @param sourisy : Ordonnée de la souris
	 */
	public void setImgPath(double sourisx, double sourisy) {
        if (isHoveredCirc(sourisx, sourisy)) {
            this.currentImg = imgHover;
        } else {
            this.currentImg = imgNormal;
        }
    }
	
	/*-------------------------------------------------------------------------------------------------------------------------------------
	 * ------------------- Methods -------------------
	 * -------------------------------------------------------------------------------------------------------------------------------------
	 */
	
	/**
	 * Test si un point est inclus dans l'image carrée du bouton
	 * @param x
	 * @param y
	 * @return True si l'attribut et this se chevauchent
	 */
	public boolean isHovered(double x,double y) {
        return x >= this.x && x <= this.x+this.width &&
               y >= this.y && y <= this.y+this.height ;
    }
	
	/**
	 * Test si un point est inclus dans le cercle de l'image du bouton
	 * @param x : Abscisse de l'objet testé
	 * @param y : Ordonnée de l'objet testé
	 * @return Si la souris (point) est dans le cercle du bouton
	 */
	public boolean isHoveredCirc(double x,double y) {
		//On prend un rayon plus petit que l'image car le bouton l'est aussi
        double radius = 0.85 * this.width / 2; 
        double cx = this.x + radius;
        double cy = this.y + radius;
        double distance_carrée = (x - cx) * (x - cx) + (y - cy) * (y - cy);
        return distance_carrée <= radius*radius ;
    }
	
	/**
	 * Test si un bouton est franchi : non survolé maintenant alors que oui avant, et inversement
	 * @param sourisX
	 * @param sourisY
	 * @return s'il y a changement 
	 */
	public boolean isEnteringOrExiting(double sourisX, double sourisY) {
		boolean isHoveredNow = this.isHoveredCirc(sourisX, sourisY);
		if (this.wasHovered != isHoveredNow ) {
			wasHovered = isHoveredNow;
			return true;
		}
		return false;
	}
}
