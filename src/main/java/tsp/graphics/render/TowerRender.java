package tsp.graphics.render;

import javafx.scene.AmbientLight;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Cylinder;
import tsp.engine.Tower;
import tsp.graphics.Window;

public class TowerRender extends Render<Tower>{
	private Tower tower;
    Window window;
	private Cylinder cyl;
	
	public TowerRender(Window window, Tower tower) {
		/* Scene scene = new Scene(group1, width, height, true, SceneAntialiasing.BALANCED);
		stage.setScene(scene);
		PerspectiveCamera camera = new PerspectiveCamera(true);
		camera.setTranslateZ(-800);
		camera.setNearClip(0.1);
		camera.setFarClip(5000);
		scene.setCamera(camera);*/
		super(tower);
		this.tower = tower;
		Cylinder cyl = new Cylinder(tower.getWidth(), tower.getHeight());
		this.cyl = cyl;

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
    /* Trace le cylindre à une certaine rotation et position de la caméra */
        cyl.setRotate(tower.getRotation());
		// Le modulo permet d'avoir l'illusion d'un cylindre de taille infini
        cyl.setTranslateY(((-window.getCamY())%(window.getHeight())));
        
	}

}
