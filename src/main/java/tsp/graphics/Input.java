package tsp.graphics;

import java.util.HashSet;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Classe qui écoute le clavier et la souris et transmet à Window
 */
public class Input {
	
	/*-------------------------------------------------------------------------------------------------------------------------------------
	 * ------------------- Fields -------------------
	 * -------------------------------------------------------------------------------------------------------------------------------------
	 */
	
	private Scene scene;
	private HashSet<KeyCode> PressedKeyset = new HashSet<>();
	
	/*-------------------------------------------------------------------------------------------------------------------------------------
	 * ------------------- Constructor -------------------
	 * -------------------------------------------------------------------------------------------------------------------------------------
	 */
	
	public Input(Window window) {
		this.scene = window.getScene();
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------
	 * ------------------- Methods -------------------
	 * -------------------------------------------------------------------------------------------------------------------------------------
	 */
	
	/**
	 * Lance la détection des touches 
	 */
	public void listen(Window window) {
		/* J'écoute les touches relachees */
		scene.setOnKeyReleased((KeyEvent e) -> {
			getPressedKeyset().remove(e.getCode());
		});
		/* J'écoute les touches enfoncees */
		scene.setOnKeyPressed((KeyEvent e) -> {
			getPressedKeyset().add(e.getCode());
		});
		/* J'écoute le clic souris */
		scene.setOnMouseClicked(e -> {
	        window.handleClick(e.getX(), e.getY());
		});
		/* J'écoute les mouvements de la souris */
	    scene.setOnMouseMoved(event -> {
	    	window.setSourisX(event.getX());
	    	window.setSourisY(event.getY());
	    });
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------
	 * ------------------- Getters -------------------
	 * -------------------------------------------------------------------------------------------------------------------------------------
	 */
	
	/**
	 * Récupère le HashSet des touches actuellement enfoncées
	 * @param pressedKeyset
	 * @return un HashSet contenant les KeyCode des touches pressées
	 */
	public HashSet<KeyCode> getPressedKeyset() {
		return PressedKeyset;
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------
	 * ------------------- Setters -------------------
	 * -------------------------------------------------------------------------------------------------------------------------------------
	 */
	
	/**
	 * Remplace l'ensemble des touches pressées par un nouvel ensemble
	 * * @param pressedKeyset le nouvel ensemble de keycode à utiliser
	 */
	public void setPressedKeyset(HashSet<KeyCode> pressedKeyset) {
		PressedKeyset = pressedKeyset;
	}

}
