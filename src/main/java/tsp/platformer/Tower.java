package tsp.platformer;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.SceneAntialiasing;
import javafx.scene.AmbientLight;


public class Tower{

	private double width = 694;
	private double height = 520;
    Cylinder cyl;

    public Tower(Group group1) {
		/* Scene scene = new Scene(group1, width, height, true, SceneAntialiasing.BALANCED);
		stage.setScene(scene);
		PerspectiveCamera camera = new PerspectiveCamera(true);
		
		camera.setTranslateZ(-800);
		camera.setNearClip(0.1);
		camera.setFarClip(5000);
		scene.setCamera(camera);*/

        AmbientLight ambient = new AmbientLight(Color.color(1, 1, 1));
		group1.getChildren().add(ambient);
		
		Image texture = new Image(getClass().getResource("/images/Stone Wall.png").toString(), 200, 200, false, false);
		double w = texture.getWidth();
		double h = texture.getHeight();
		Tile tile = new Tile(texture, w, h);
		int repeatX = 3;   // nombre de tuiles horizontalement (autour du cylindre)
		int repeatY = 2;   // nombre de tuiles verticalement (hauteur)
		Image tiledTexture = tile.tileWithCanvas(repeatX, repeatY);

		
		Cylinder cyl = new Cylinder(100, 520);
//		cyl.setFill(new ImagePattern(texture, 0, 0, tileW, tileH, false));
		PhongMaterial mat = new PhongMaterial();
		mat.setDiffuseMap(tiledTexture);
		cyl.setMaterial(mat);
		group1.getChildren().add(cyl);
		cyl.setTranslateX(width / 2.0);
		cyl.setTranslateY(height / 2.0);
		
		cyl.setRotationAxis(javafx.scene.transform.Rotate.Y_AXIS);
		cyl.setRotate(0);
		this.cyl = cyl;
    }
    

    public void rotate(float rotation) {

		/*
		PointLight light = new PointLight(Color.WHITE);
		light.setTranslateX(-200);
		light.setTranslateY(-200);
		light.setTranslateZ(-400);
		group1.getChildren().add(light);*/

        cyl.setRotate(rotation);        
	}
}
