package tsp.graphics;

import java.util.HashMap;

public final class Constants {
	private Constants() {}
	
	/**
	 * Images des éléments du jeu
	 */
    public static final String CLOUDS_PATH = "/images/fond_menu.jpg";
    public static final String SPACE_PATH = "/images/space.jpg";
    public static final String BASICPLATFORM_PATH = "/images/platform.png";
    public static final String LAVAPLATFORM_PATH = "/images/platform-lava3.png";
    public static final String INVISIBLEPLATFORM_PATH = "/images/stone-wallplatform.png";
    public static final String WALL_PATH = "/images/stone-wall.png";
    public static final String TITRE_JEU_1 = "/images/Titre_jeu_1.png";
    
    /**
     * Boutons du jeu : appuyés et normaux
     */
    public static final String SOLONORMAL_PATH = "/images/buttons/solo_normal.png";
    public static final String SOLOHOVER_PATH = "/images/buttons/solo_hover.png";
    public static final String MULTINORMAL_PATH = "/images/buttons/multi_normal.png";
    public static final String MULTIHOVER_PATH = "/images/buttons/multi_hover.png";
    public static final String PLAYNORMAL_PATH = "/images/buttons/play_normal.png";
    public static final String PLAYHOVER_PATH = "/images/buttons/play_hover.png";
    
    public static final String LEFTARROW_PATH = "/images/buttons/arrow_left_normal.png";
    public static final String LEFTARROWHOVER_PATH = "/images/buttons/arrow_left_hover.png";
    public static final String RIGHTARROW_PATH = "/images/buttons/arrow_right_normal.png";
    public static final String RIGHTARROWHOVER_PATH = "/images/buttons/arrow_right_hover.png";
    
    /**
     * Skins du personnage principal
     */
    public static final String PLAYER_PATH = "/images/skins/player.png";
    public static final String SKINEARTH_PATH = "/images/skins/character_fire.png";
    public static final String SKINFIRE_PATH = "/images/skins/character_earth.png";
    public static final String SKINWATER_PATH = "/images/skins/character_water.png";
    public static final String SKINWIND_PATH = "/images/skins/character_wind.png";
    /**
     * Skins tristes du perso affichés au GameOver
     */
    public static final String PLAYER_CRY_PATH = "/images/skins/sad/player_cry.png";
    public static final String SKINEARTH_CRY_PATH = "/images/skins/sad/character_fire_cry.png";
    public static final String SKINFIRE_CRY_PATH = "/images/skins/sad/character_earth_cry.png";
    public static final String SKINWATER_CRY_PATH = "/images/skins/sad/character_water_cry.png";
    public static final String SKINWIND_CRY_PATH = "/images/skins/sad/character_wind_cry.png";
    
    public static final HashMap<String, String> NORMALTOCRY = new HashMap<>();
    static {
        NORMALTOCRY.put(PLAYER_PATH, PLAYER_CRY_PATH);
        NORMALTOCRY.put(SKINEARTH_PATH, SKINEARTH_CRY_PATH);
        NORMALTOCRY.put(SKINFIRE_PATH, SKINFIRE_CRY_PATH);
        NORMALTOCRY.put(SKINWATER_PATH, SKINWATER_CRY_PATH);
        NORMALTOCRY.put(SKINWIND_PATH, SKINWIND_CRY_PATH);
    }

    /**
     * Sons du jeu : Musiques et effets sonores
     */
    public static final String TRACK1_PATH = "/sounds/music/track1.wav";
    public static final String TRACK2_PATH = "/sounds/music/track2.wav";
    public static final String GAMEOVER_PATH = "/sounds/music/GameOver.wav";
    public static final String EXPLOSIONPATH = "/sounds/sound_effect/8-bit-bomb-explosion-2811.wav";
    
    /**
     * Niveau du flou dans le Menu
     */
    public static final int FLOU = 20;
    
    /**
     * Dimensions de la fenêtre de jeu
     */
    public static final int WINDOWWIDTH = 694;
    public static final int WINDOWHEIGHT = 750;
    
    /**
     * Taille du Player
     */
    public static final int PLAYER_SIDE = 70;
    

}
