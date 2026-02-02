package tsp.platformer;

public class Platform extends Sprite{

	public Platform(String image, int width, int height) {
		super(image, width, height);
	}
	
	@Override
	public Boolean intersects(Sprite s) {
		return s.y+s.height>this.y && s.y+s.height<this.y+this.height
		&&s.x+s.width>this.x&&s.x<this.x+this.width;
	}
}
