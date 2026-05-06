package tsp.graphics;

public class Button {
	private double width;
	private double height;
	private double x;
	private double y;
	
	public boolean isHoover(double x,double y) {
        return x >= this.x && x <= this.x+this.width &&
               y >= this.y && y <= this.y+this.height ;
    }
	
}
