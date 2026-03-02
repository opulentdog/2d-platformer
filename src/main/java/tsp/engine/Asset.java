package tsp.engine;

public abstract class Asset {
	String imgPath;
	protected double width;
	protected double height;
	protected double x = 0;
	protected double y = 0;
	

	public Asset (String image, int width, int height) {
		this.width=width;
		this.height=height;
		this.imgPath = image;
	}
	
	/**
	 * getter width
	 * @return largeur de l'asset (double)
	 */
	public double getWidth() {
		return width;
	}
	
	/**
	 * getter height
	 * @return hauteur de l'asset (double)
	 */
	public double getHeight() {
		return height;
	}
	
	/**
	 * getter x
	 * @return abscisse de l'asset (double)
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * getter y
	 * @return ordonnee de l'asset (double)
	 */
	public double getY() {
		return y;
	}
	
	public String getImgPath() {
		return imgPath;
	}
	
	/**
	 * methode pour gerer les hitbox
	 * @param s: asset à comparer avec this
	 * @return true si this et s en contact, false sinon
	 */
	public Boolean intersects(Asset s) {
		double relativex=s.x-this.x;
		double relativey=s.y-this.y;
		return relativex < this.width && relativey < this.height &&
				-relativex < s.width && -relativey < s.height;
	}
	
	public void setPostition(double x, double y) {
		this.x = x;
		this.y = y;
	}
}
