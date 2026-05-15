package tsp.graphics;

import java.util.HashSet;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Input {
	
	/***
	 * ------------------- Fields -------------------
	 */
	
	private Scene scene;
	private HashSet<KeyCode> PressedKeyset = new HashSet<>();
	
	/***
	 * ------------------- Constructor -------------------
	 */
	
	public Input(Window window) {
		this.scene = window.getScene();
	}
	
	/***
	 * ------------------- Methods -------------------
	 */
	
	/**
	 * Lance la détection des touches
	 */
	public void listen(Window window) {
		/*J'écoute les touches enfoncees*/
		scene.setOnKeyReleased((KeyEvent e) -> {
			getPressedKeyset().remove(e.getCode());
		});
		scene.setOnKeyPressed((KeyEvent e) -> {
			getPressedKeyset().add(e.getCode());
		});
		scene.setOnMouseClicked(e -> {
	        window.handleClick(e.getX(), e.getY());
		});
		//On lit la position de la souris
	    scene.setOnMouseMoved(event -> {
	    	window.setSourisX(event.getX());
	    	window.setSourisY(event.getY());
	    });
	}
	
	/***
	 * ------------------- Getters -------------------
	 */
	
	/**
	 * Récupère l'ensemble des touches actuellement enfoncées
	 * @param pressedKeyset
	 * @return un HashSet contenant les KeyCode des touches pressées
	 */
	public HashSet<KeyCode> getPressedKeyset() {
		return PressedKeyset;
	}

	/***
	 * ------------------- Setters -------------------
	 */
	
	/**
	 * Remplace l'ensemble des touches pressées par un nouvel ensemble
	 * * @param pressedKeyset le nouvel ensemble de keycode à utiliser
	 */
	public void setPressedKeyset(HashSet<KeyCode> pressedKeyset) {
		PressedKeyset = pressedKeyset;
	}

}
