package tsp.platformer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
	Image img;
	double width;
	double height;
	double x = 0;
	double y = 0;
	

	public Sprite (String image, int width, int height) {
		this.width=width;
		this.height=height;
		img = new Image(image, width, height, false, true);
	}
	
	public Boolean intersects(Sprite s) {
		double relativex=s.x-this.x;
		double relativey=s.y-this.y;
		return relativex < this.width && relativey < this.height &&
				-relativex < s.width && -relativey < s.height;
	}
	public void setPostition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public void render(GraphicsContext gc) {
		gc.drawImage(img, x, y);
	}

}
