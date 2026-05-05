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

	    // ─── Tuning constants ──────────────────────────────────────────────────────

	    // Total number of platforms to generate (basic + lava combined)
	    final int    PLATFORM_COUNT          = 50;

	    // At altitude N, the chance of spawning a BasicPlatform is:
	    //   BASE_BASIC_CHANCE / (altitude / ALTITUDE_SCALE)
	    // So the deeper you go, the rarer basic platforms become.
	    final double BASE_BASIC_CHANCE        = 0.5;
	    final double ALTITUDE_SCALE           = 5.0;

	    // Max angle deviation (±degrees) between consecutive basic platform angles
	    final int    BASIC_ANGLE_SPREAD       = 100;

	    // Lava platforms are forced to appear at least MIN_LAVA_ANGLE_OFFSET degrees
	    // away from the previous angle, and at most MAX_LAVA_ANGLE_OFFSET degrees away
	    final double MIN_LAVA_ANGLE_OFFSET    = 45;
	    final double LAVA_ANGLE_RANGE         = 70;  // offset = MIN + LAVA_ANGLE_RANGE * random()

	    // Lava platforms float between (altitude - 1) and (altitude - 0.5) steps high,
	    // i.e. they are vertically sandwiched between the flanking basic platforms
	    final double LAVA_ALTITUDE_VARIANCE   = 0.5;   // size of the floating range
	    final double LAVA_ALTITUDE_BASE       = 0.5;   // minimum offset below the next step

	    // ──────────────────────────────────────────────────────────────────────────

	    Platform[] platforms     = new Platform[PLATFORM_COUNT];
	    this.platformAngles       = new int[PLATFORM_COUNT];
	    Random random             = new Random(1);

	    // ── First platform: centred vertically, random angle ──────────────────────
	    platformAngles[0] = (int) (360 * random.nextDouble());
	    platforms[0]      = new BasicPlatform();
	    platforms[0].setPostition(0, windowHeight / 2.0);

	    // altitude counts how many BasicPlatforms have been placed so far;
	    // it drives both vertical position and the lava-spawn probability.
	    int     altitude         = 1;
	    boolean previousWasLava  = true; // force the very first generated platform to be basic

	    for (int k = 1; k < PLATFORM_COUNT; k++) {

	        // Spawn a BasicPlatform when:
	        //  (a) the last platform was lava (never allow two lava in a row), OR
	        //  (b) a random roll beats the altitude-scaled threshold
	        double basicChance = BASE_BASIC_CHANCE / (altitude / ALTITUDE_SCALE);
	        if (previousWasLava || random.nextDouble() < basicChance) {

	            // ── Basic platform ────────────────────────────────────────────────

	            // Angle stays within ±BASIC_ANGLE_SPREAD of the previous platform's angle
	            int angleOffset    = (int) (2 * BASIC_ANGLE_SPREAD * random.nextDouble()) - BASIC_ANGLE_SPREAD;
	            platformAngles[k]  = (platformAngles[k - 1] + angleOffset) % 360;

	            platforms[k]       = new BasicPlatform();
	            double posX        = 0;//(windowWidth - platforms[k].getWidth()) * random.nextDouble();
	            double posY        = windowHeight / 2.0 - altitude * PlatformSpacing;
	            altitude++;
	            platforms[k].setPostition(posX, posY);
	            previousWasLava    = false;

	        } else {

	            // ── Lava platform ─────────────────────────────────────────────────

	            // Angle is offset by a random amount in [MIN, MIN+RANGE], on a random side
	            double angleOffset = MIN_LAVA_ANGLE_OFFSET + LAVA_ANGLE_RANGE * random.nextDouble();
	            double sign        = 2 * Math.ceil(random.nextDouble() - 0.5) - 1; // −1 or +1 with equal probability
	            platformAngles[k]  = (int) ((platformAngles[k - 1] + angleOffset * sign) % 360);

	            platforms[k]       = new LavaPlatform();

	            // Lava floats between altitude-1 and altitude-0.5 steps above the midpoint,
	            // keeping it visually sandwiched between the two surrounding basic platforms
	            double altitudeOffset = LAVA_ALTITUDE_BASE + LAVA_ALTITUDE_VARIANCE * random.nextDouble();
	            double posX           = 0;//(windowWidth - platforms[k].getWidth()) * random.nextDouble();
	            double posY           = windowHeight / 2.0 - (altitude - altitudeOffset) * PlatformSpacing;
	            platforms[k].setPostition(posX, posY);
	            previousWasLava       = true;
	        }
	    }

	    return platforms;
	}
	
	public int[] getPlatformAngles() {
		return platformAngles;
	}
}