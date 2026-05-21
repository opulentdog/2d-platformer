package tsp.graphics.render;

import java.util.EnumMap;

import tsp.engine.Generation;
import tsp.engine.Player;
import tsp.engine.Tower;
import tsp.engine.platforms.Platform;
import tsp.engine.platforms.Platform.PlatformType;
import tsp.graphics.Constants;
import tsp.graphics.Texture;
import tsp.graphics.Window;

public class PlatformRender extends Render<Platform> {
	
	/***
	 * ------------------- Fields -------------------
	 */
	
	Platform platform;
	Window window;
	Tower tower;
	double rotation = 0;
	Platform[] platforms;
	Generation generator;
	EnumMap<PlatformType, Texture> textureMap;
	
	/***
	 * ------------------- Setters -------------------
	 */
	
	public void setPlatforms(Platform[] platforms) {
	    this.platforms = platforms;
	}
	
	/***
	 * ------------------- Constructor -------------------
	 */
	
	public PlatformRender(Window window, Tower tower, Platform[] platforms, Generation generator) {
		super(platforms[0], Constants.BASICPLATFORM_PATH);
		this.window = window;
		this.tower = tower;
		this.platforms = platforms;
		this.generator = generator;
		
		textureMap = new EnumMap<>(PlatformType.class);

        textureMap.put(PlatformType.BASIC, new Texture(Constants.BASICPLATFORM_PATH));
        textureMap.put(PlatformType.LAVA, new Texture(Constants.LAVAPLATFORM_PATH));
        textureMap.put(PlatformType.SPRING, new Texture(Constants.BASICPLATFORM_PATH));
        textureMap.put(PlatformType.FALLING, new Texture(Constants.BASICPLATFORM_PATH));
        textureMap.put(PlatformType.INVISIBLE, new Texture(Constants.INVISIBLEPLATFORM_PATH));

	}
	
	/***
	 * ------------------- Methods -------------------
	 */
	
	/**
	 * Calcule en fonction de la positio de la tour la largeur que doivent avoir les plateformces et les déplace
	 */
	@Override
	public void render() {
		double rotation = tower.getRotation();
		double towercenterx = window.getWidth()/2;

		double cos=Math.cos(rotation*2*3.14159/360);
		double sin=Math.sin(rotation*2*3.14159/360);
		
		int i=0;
		
		for(Platform platform1 : platforms) {
		    int relRotation = ((int)rotation - generator.getPlatformAngles()[i] + 360) % 360;
		    double cosRel = Math.cos(relRotation * 2 * 3.14159 / 360);
		    double sinRel = Math.sin(relRotation * 2 * 3.14159 / 360);
		    
		    if(relRotation > 0 && relRotation < 180) {
		        double platformWidth = Math.abs(sinRel) * 100;
		        double newX = towercenterx + cosRel * tower.getWidth() - platformWidth * (1 - cosRel) / 2;
		        
		        platform1.setPosition(newX, platform1.getY());
		        this.renderPlatform(platform1, platformWidth, platform1.getHeight());
		    }
		    i++;
		}
		
	}
	
	//Rend l'image et permet aussi de redimentionner en temps réel
	public void renderPlatform(Platform platform, double width, double height) {
		window.getGC().drawImage(textureMap.get(platform.getType()).getImage(), platform.getX(), platform.getY() - window.getCamY(), width, height);
	}

}
