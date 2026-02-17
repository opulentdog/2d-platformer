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
	
	public void listen() {
		/*J'écoute les touvhes enfoncees*/
		scene.setOnKeyReleased((KeyEvent e) -> {
			getPressedKeyset().remove(e.getCode());
		});
		scene.setOnKeyPressed((KeyEvent e) -> {
			getPressedKeyset().add(e.getCode());
		});
	}

	public HashSet<KeyCode> getPressedKeyset() {
		return PressedKeyset;
	}

	public void setPressedKeyset(HashSet<KeyCode> pressedKeyset) {
		PressedKeyset = pressedKeyset;
	}

}
