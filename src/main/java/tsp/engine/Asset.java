package tsp.engine;

/**
 * Classe mère dont héritent Player et Platform
 */
public abstract class Asset {

	/*-------------------------------------------------------------------------------------------------------------------------------------
	 * ------------------- Fields -------------------
	 * -------------------------------------------------------------------------------------------------------------------------------------
	 */
	
	/**
	 * Dimensions de l'Asset
	 */
	protected double width;
	protected double height;
	/**
	 * Coordonnées de l'Asset
	 */
	protected double x = 0;
	protected double y = 0;
	

	/*-------------------------------------------------------------------------------------------------------------------------------------
	 * ------------------- Constructor -------------------
	 * -------------------------------------------------------------------------------------------------------------------------------------
	 */
	
	public Asset (int width, int height) {
		this.width=width;
		this.height=height;
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------
	 * ------------------- Getters -------------------
	 * -------------------------------------------------------------------------------------------------------------------------------------
	 */
	
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

	/*-------------------------------------------------------------------------------------------------------------------------------------
	 * ------------------- Setters -------------------
	 * -------------------------------------------------------------------------------------------------------------------------------------
	 */
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------
	 * ------------------- Methods -------------------
	 * -------------------------------------------------------------------------------------------------------------------------------------
	 */
	
	/**
	 * methode pour gerer les hitbox
	 * @param s: asset à comparer avec this
	 * @return true si this et s en contact, false sinon
	 */
	public Boolean intersects(Asset other) {
		double relativex=other.x-this.x;
		double relativey=other.y-this.y;
		return relativex < this.width && relativey < this.height &&
				-relativex < other.width && -relativey < other.height;
	}
	
}
