package tsp.graphics.render;

import tsp.engine.Generation;
import tsp.engine.Player;
import tsp.engine.Tower;
import tsp.engine.platforms.Platform;
import tsp.graphics.Window;

public class PlatformRender extends Render<Platform> {
	Platform platform;
	Window window;
	Tower tower;
	double rotation = 0;
	Platform[] platforms;
	Generation generator;

	
	public PlatformRender(Window window, Tower tower, Platform[] platforms, Generation generator) {
		super(platforms[0]);
		this.window = window;
		this.tower = tower;
		this.platforms = platforms;
		this.generator = generator;
	}
	
	@Override
	public void render() {
		double rotation = tower.getRotation();
		double towercenterx = window.getWidth()/2;

		double cos=Math.cos(rotation*2*3.14159/360);
		double sin=Math.sin(rotation*2*3.14159/360);
		
		int i=0;
		for(Platform platform1 : platforms) {
			int relRotation = ((int)rotation - generator.getPlatformAngles()[i] + 360)%360;
			double cosRel=Math.cos(relRotation*2*3.14159/360);
			double sinRel=Math.sin(relRotation*2*3.14159/360);
			if(relRotation>0 && relRotation < 180) {
				this.renderPlatform(platform1,Math.abs(sinRel)*100,platform1.getHeight());
				platform1.setPostition(towercenterx+cosRel*tower.getWidth(),platform1.getY());
				window.getGC().drawImage(this.getTexture().getImage(), platform.getX(),platform.getY()-window.getCamY());
			}
			i++;
		}
		
	}
	
	//Rend l'image et permet aussi de redimentionner en temps réel
	public void renderPlatform(Platform platform, double width, double height) {
		window.getGC().drawImage(this.getTexture().getImage(), platform.getX(), platform.getY() - window.getCamY(), width, height);
	}

}
