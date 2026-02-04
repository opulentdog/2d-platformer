package tsp.platformer;

import java.util.HashSet;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.AmbientLight;


public class Tower{

	private int cylinder_width;
	private int cylinder_height;
	
	private int repeatX = 5;   // nombre de tuiles horizontalement (autour du cylindre)
	private int repeatY = 5/2;   // nombre de tuiles verticalement (hauteur)
	
    private Cylinder cyl;
	private int window_height;
	private double towerxVelocity;
	 double rotation;

    @SuppressWarnings("exports")
	public Tower(Group group1, int cylinder_width,int window_width, int window_height) {
    	this.cylinder_height=window_height*2;
    	this.cylinder_width=cylinder_width;
    	this.window_height=window_height;
    	
        AmbientLight ambient = new AmbientLight(Color.color(1, 1, 1));
		group1.getChildren().add(ambient);
		
		/* Texturer le cylindre en répetant la texture */
		Image texture = new Image(getClass().getResource("/images/Stone Wall.png").toString(), cylinder_height/repeatX, cylinder_height/repeatY, false, false);
		double w = texture.getWidth();
		double h = texture.getHeight();
		Tile tile = new Tile(texture, w, h);
		Image tiledTexture = tile.tileWithCanvas(repeatX, 2*repeatY);
		Cylinder cyl = new Cylinder(this.cylinder_width, cylinder_height);
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
    

    /*Trace le cylindre à une certaine rotation et position de la caméra*/
    public void render(double ycamera) {
        towerxVelocity*=0.50;
        rotation+=towerxVelocity;
		if(rotation >= 360) {
			rotation -= 360;
		}
		if(rotation < 0) {
			rotation += 360;
		}
        cyl.setRotate(rotation);
        //Le modulo permet d'avoir l'illusion d'un cylindre de taille infini
        cyl.setTranslateY(((-ycamera)%(window_height)));
    }


	public void controlTower(HashSet<KeyCode> pressedKeyset) {
		//Vérifie si les touche sont appuyé et modifie la vitesse du joueur
		if(pressedKeyset.contains(KeyCode.LEFT)) {

			towerxVelocity=+10;
		}
		if(pressedKeyset.contains(KeyCode.RIGHT)) {
			towerxVelocity=-10;
		}		
		
	}
}
