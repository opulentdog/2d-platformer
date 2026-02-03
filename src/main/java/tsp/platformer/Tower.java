package tsp.platformer;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.AmbientLight;


public class Tower{
	private int window_width;
	private int window_height;

	private int cylinder_width = 250;
	private int cylinder_height = 2000;
	
	private int repeatX = 5;   // nombre de tuiles horizontalement (autour du cylindre)
	private int repeatY = 5;   // nombre de tuiles verticalement (hauteur)
	
    private Cylinder cyl;

    public Tower(Group group1, int width, int height) {
    	window_width = width;
    	window_height = height;
    	
		/* Scene scene = new Scene(group1, width, height, true, SceneAntialiasing.BALANCED);
		stage.setScene(scene);
		PerspectiveCamera camera = new PerspectiveCamera(true);
		camera.setTranslateZ(-800);
		camera.setNearClip(0.1);
		camera.setFarClip(5000);
		scene.setCamera(camera);*/

        AmbientLight ambient = new AmbientLight(Color.color(1, 1, 1));
		group1.getChildren().add(ambient);
		
		/* Texturer le cylindre en repetant la texture */
		Image texture = new Image(getClass().getResource("/images/Stone Wall.png").toString(), 200, 200, false, false);
		double w = texture.getWidth();
		double h = texture.getHeight();
		Tile tile = new Tile(texture, w, h);
		Image tiledTexture = tile.tileWithCanvas(repeatX, repeatY);
		Cylinder cyl = new Cylinder(cylinder_width, cylinder_height);
		PhongMaterial mat = new PhongMaterial();
		mat.setDiffuseMap(tiledTexture);
		cyl.setMaterial(mat);
		
		group1.getChildren().add(cyl);
		cyl.setTranslateX(window_width / 2.0);
		cyl.setTranslateY(window_height / 2.0);
		
		cyl.setRotationAxis(javafx.scene.transform.Rotate.Y_AXIS);
		cyl.setRotate(0);
		this.cyl = cyl;
    }
    

    public void render(float rotation, double ycamera) {

		/*
		PointLight light = new PointLight(Color.WHITE);
		light.setTranslateX(-200);
		light.setTranslateY(-200);
		light.setTranslateZ(-400);
		group1.getChildren().add(light);*/

        cyl.setRotate(rotation);  
        cyl.setTranslateY(- (ycamera%(cylinder_height/repeatY)) + 300);
	}
}
