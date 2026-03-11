package tsp.graphics.render;

import javafx.scene.AmbientLight;
import javafx.scene.paint.Color;
import javafx.scene.shape.Cylinder;
import tsp.engine.Tower;
import tsp.graphics.Window;

public class TowerRender extends Render<Tower>{
	private Tower tower;
    Window window;
	private Cylinder cyl;
	private int textureRepetition = 5 ;
	
	public TowerRender(Window window, Tower tower) {
		super(tower);
		/*
		Scene scene = window.getScene();
		window.getStage().setScene(scene);
		PerspectiveCamera camera = new PerspectiveCamera(true);
		camera.setTranslateZ(-800);
		camera.setNearClip(0.1);
		camera.setFarClip(5000);
		scene.setCamera(camera);
		*/
		this.tower = tower;
		Cylinder cyl = new Cylinder(tower.getWidth(), tower.getHeight());
		this.cyl = cyl;
		getTexture().tileTexture(textureRepetition, textureRepetition);
		getTexture().applyTexture(cyl);

        AmbientLight ambient = new AmbientLight(Color.color(1, 1, 1));
		window.getGroup().getChildren().add(ambient);
		
		window.getGroup().getChildren().add(cyl);
		cyl.setTranslateX(window.getWidth() / 2.0);
		cyl.setTranslateY(window.getHeight() / 2.0);
		
		cyl.setRotationAxis(javafx.scene.transform.Rotate.Y_AXIS);
		cyl.setRotate(0);
		this.window=window;
	}

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
