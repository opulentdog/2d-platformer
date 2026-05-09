package tsp.graphics;

import java.util.HashSet;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Input {
	private Scene scene;
	private HashSet<KeyCode> PressedKeyset = new HashSet<>();
	
	public Input(Window window) {
		HashSet<KeyCode> PressedKeyset = new HashSet<>();
		this.scene = window.getScene();
	}
	
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
	}
	
	
	/**
	 * Récupère l'ensemble des touches actuellement enfoncées
	 * @param pressedKeyset
	 * @return un HashSet contenant les KeyCode des touches pressées
	 */
	public HashSet<KeyCode> getPressedKeyset() {
		return PressedKeyset;
	}

	/**
	 * Remplace l'ensemble des touches pressées par un nouvel ensemble
	 * * @param pressedKeyset le nouvel ensemble de keycode à utiliser
	 */
	public void setPressedKeyset(HashSet<KeyCode> pressedKeyset) {
		PressedKeyset = pressedKeyset;
	}

}
