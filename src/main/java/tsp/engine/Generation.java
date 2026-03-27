package tsp.engine;

import java.util.Random;

import tsp.engine.platforms.*;

public class Generation {
	private static final int PlatformSpacing = 300;
	private int[] platformAngles;

	/**
	 * genere des plateformes sur la tour de maniere aleatoire
	 * @param windowWidth largeur fenetre
	 * @param windowHeight hauteur fenetre
	 */
	public Platform[] randomPlatformGeneration(int windowWidth, int windowHeight) {
		// Créer 50 platformes avec des coordonées horizontale random
		Platform[] platforms = new Platform[50];
		this.platformAngles = new int[50];
		platformAngles[0]=(int) (360*Math.random());
		for(int k=1;k<platformAngles.length;k++) {
			platformAngles[k]=(platformAngles[k-1]+(int)(250*Math.random())-125)%360;
		}
		int i=0;
        Random random = new Random();
		for(int c=0; c<50;c++) {
			if(random.nextDouble() < 0.9) {
				platforms[c]=new BasicPlatform();
			}else {
				platforms[c]=new LavaPlatform();
			}
			double posititionx=(windowWidth-platforms[c].getWidth())*Math.random();
			double posititiony=windowHeight/2.-i*PlatformSpacing-(i*i);
			platforms[c].setPostition(posititionx, posititiony);
			i++;
		}
		return platforms;
	}	
	
	public int[] getPlatformAngles() {
		return platformAngles;
	}
}