package tsp.graphics.render;

import tsp.engine.Asset;
import tsp.graphics.Texture;

public abstract class Render<A extends Asset> {
	
	/***
	 * ------------------- Fields ----------------------
	 */
	
	private Texture texture;
	
	/***
	 * ------------------- Constructor ----------------------
	 */
	
	public Render(A a, String imgPath){
		this.texture = new Texture(imgPath, a.getWidth(), a.getHeight());
	}
	
	/***
	 * ------------------- Getter ----------------------
	 */
	
	public Texture getTexture() {
		return texture;
	}
	
	/***
	 * ------------------- Methods ----------------------
	 */
	
	public void render() {
	};
}