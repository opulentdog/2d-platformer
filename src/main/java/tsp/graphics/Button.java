package tsp.graphics;

import javafx.scene.image.Image;

public class Button {
	private double width;
	private double height;
	private double x;
	private double y;
	private Image imgNormal;
	private Image imgHover;
	private Image currentImg;

	
	public Button(double x, double y, double width, double height, String normalPath, String hoverPath) {
		this.width=width;
		this.height=height;
		this.x=x;
		this.y=y;
		this.imgNormal = new Texture(normalPath, width, height).getImage();
        this.imgHover  = new Texture(hoverPath,  width, height).getImage();
        this.currentImg = imgNormal;

	}
	
	public Image getImage() {
		return currentImg;
	}
	
	public void setImgPath(double sourisx, double sourisy) {
        if (isHoveredCirc(sourisx, sourisy)) {
            currentImg = imgHover;
        } else {
            currentImg = imgNormal;
        }
    }
	
	/**
	 * Test si un point est inclus dans l'image carrée du bouton
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isHovered(double x,double y) {
        return x >= this.x && x <= this.x+this.width &&
               y >= this.y && y <= this.y+this.height ;
    }
	
	/**
	 * Test si un point est inclus dans le cercle de l'image du bouton
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isHoveredCirc(double x,double y) {
		//On prend un rayon plus petit que l'image car le bouton l'est aussi
        double radius = 0.85 * this.width / 2; 
        double cx = this.x + radius;
        double cy = this.y + radius;
        double distance_carrée = (x - cx) * (x - cx) + (y - cy) * (y - cy);
        return distance_carrée <= radius*radius ;
    }
	
}
