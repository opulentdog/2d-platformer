package tsp.graphics.render;
import tsp.graphics.Constants;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.scene.AmbientLight;
import javafx.scene.paint.Color;
import javafx.scene.shape.Cylinder;
import tsp.engine.Tower;
import tsp.graphics.Window;
/**
 * gère le rendu visuel de la tour en 3D.
 * <p>
 * elle classe crée un cylindre JavaFX, lui applique une texture répétée (tiling)
 * et synchronise sa rotation avec l'état logique de tower.<p>
 * gère également l'effet de défilement vertical infini de la tour.
 * 
 */
public class TowerRender extends Render<Tower>{
	
	/**
	 * ------------------- Fields ----------------------
	 */
	
	private Tower tower;
    Window window;
	private Cylinder cyl;
	private int textureRepetition = 5 ;
	

	/**
	 * ------------------- Constructor ----------------------
	 */
	
	public TowerRender(Window window, Tower tower) {
		super(tower, Constants.WALL_PATH);
		/*
		Scene scene = window.getScene();
		window.getStage().setScene(scene);
		PerspectiveCamera camera = new PerspectiveCamera(true);
		camera.setTranslateZ(-800);
		camera.setNearClip(0.1);
		camera.setFarClip(5000);
		scene.setCamera(camera);
		*/
		
        //Test du support materiel pour le rendu 3d (-Dprism.forceGPU=true)
        Boolean Support3D = Platform.isSupported(ConditionalFeature.SCENE3D);
        if(!Support3D){
                System.out.println("JavaFX version: " + System.getProperty("javafx.version"));
                System.out.println("Le support 3D est désactivé !!!");
                throw new Error();
        }
		
		this.tower = tower;
		Cylinder cyl = new Cylinder(tower.getWidth(), tower.getHeight());
		this.cyl = cyl;
		getTexture().tileTexture(textureRepetition, textureRepetition);
		getTexture().applyTexture(cyl);

        AmbientLight ambient = new AmbientLight(Color.color(1, 1, 1));
		window.getGameGroup().getChildren().add(ambient);
		
		window.getGameGroup().getChildren().add(cyl);
		cyl.setTranslateX(window.getWidth() / 2.0);
		cyl.setTranslateY(window.getHeight() / 2.0);
		
		cyl.setRotationAxis(javafx.scene.transform.Rotate.Y_AXIS);
		cyl.setRotate(0);
		this.window=window;
	}

	/**
	 * ------------------- Methods ----------------------
	 */
	
	/**
	 * Met à jour l'affichage visuel de la tour pour la frame actuelle.
	 * <p>
	 * étapes :<p>
	 * 
	 * Calcule un décalage vertical ({@code offsetY}) basé sur la position de la caméra 
	 * et la taille des tuiles de texture pour créer un effet de boucle infinie.<p>
	 * Applique la rotation actuelle de la tour au cylindre 3D.
	
	 */
	@Override
	public void render() {
	    double tileHeight = tower.getHeight() / textureRepetition; 
	    // On module par la taille de la tuile de texture, pas par la taille de la fenêtre	
	    double offsetY = (window.getCamY() % tileHeight);
	    cyl.setTranslateY( -offsetY);

	    cyl.setRotate(tower.getRotation());
	    tower.updatePosition();
	}

}
