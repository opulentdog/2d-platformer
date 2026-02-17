package tsp.graphics.render;

import tsp.engine.Asset;
import tsp.graphics.Texture;
import tsp.graphics.Window;

public abstract class Render<A extends Asset> {
	private Texture texture;
	
	public Render(A a){
		this.texture = new Texture(a.getImgPath(), a.getWidth(), a.getHeight());
	}
	
	public Texture getTexture() {
		return texture;
	}

	public void render() {
	};
}