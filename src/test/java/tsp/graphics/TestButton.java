package tsp.graphics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit Test Class for {@link tsp.graphics.Button}.
 * Nécessite l'initialisation du toolkit JavaFX (chargement des Texture).
 */
public class TestButton {

	private Button button;

	// Bouton de référence : x=100, y=100, width=200, height=200
	private static final double BX = 100;
	private static final double BY = 100;
	private static final double SIDE = 200;
	
	@BeforeAll
	public static void initJfx() throws InterruptedException {
		// Démarre le toolkit JavaFX une seule fois ; ignore si déjà démarré.
		try {
			javafx.application.Platform.startup(() -> {});
		} catch (IllegalStateException alreadyStarted) {
			// Toolkit déjà initialisé : OK
		}
	}
	
	@BeforeEach
	public void setUp() {
		button = new Button(BX, BY, SIDE, SIDE,
				Constants.PLAYNORMAL_PATH, Constants.PLAYHOVER_PATH);
	}

	// --------------------------------------------------------------------
	// isHovered : test du rectangle [100,300] x [100,300]
	// --------------------------------------------------------------------

	@Test
	@DisplayName("isHovered - Test 1: point au centre => true")
	void hoveredCenter() {
		Assertions.assertTrue(button.isHovered(200, 200));
	}

	@Test
	@DisplayName("isHovered - Test 2: coin haut-gauche exact => true")
	void hoveredTopLeftCorner() {
		Assertions.assertTrue(button.isHovered(100, 100));
	}

	@Test
	@DisplayName("isHovered - Test 3: coin bas-droit exact => true")
	void hoveredBottomRightCorner() {
		Assertions.assertTrue(button.isHovered(300, 300));
	}

	@Test
	@DisplayName("isHovered - Test 4: juste à gauche du bord => false")
	void hoveredJustLeft() {
		Assertions.assertFalse(button.isHovered(99, 200));
	}

	@Test
	@DisplayName("isHovered - Test 5: juste à droite du bord => false")
	void hoveredJustRight() {
		Assertions.assertFalse(button.isHovered(301, 200));
	}

	@Test
	@DisplayName("isHovered - Test 6: au-dessus => false")
	void hoveredAbove() {
		Assertions.assertFalse(button.isHovered(200, 50));
	}

	@Test
	@DisplayName("isHovered - Test 7: en dessous => false")
	void hoveredBelow() {
		Assertions.assertFalse(button.isHovered(200, 400));
	}

	@Test
	@DisplayName("isHovered - Test 8: loin en diagonale => false")
	void hoveredFarDiagonal() {
		Assertions.assertFalse(button.isHovered(0, 0));
	}

	// --------------------------------------------------------------------
	// isHoveredCirc : cercle de rayon 0.85*200/2 = 85,
	// centré en (100+85, 100+85) = (185, 185)
	// --------------------------------------------------------------------

	@Test
	@DisplayName("isHoveredCirc - Test 1: centre exact du cercle => true")
	void circCenter() {
		Assertions.assertTrue(button.isHoveredCirc(185, 185));
	}

	@Test
	@DisplayName("isHoveredCirc - Test 2: à l'intérieur (distance < 85) => true")
	void circInside() {
		Assertions.assertTrue(button.isHoveredCirc(185 + 50, 185)); // dist=50
	}

	@Test
	@DisplayName("isHoveredCirc - Test 3: sur le bord du cercle (distance = 85) => true")
	void circOnBoundary() {
		Assertions.assertTrue(button.isHoveredCirc(185 + 85, 185)); // dist=85, <=
	}

	@Test
	@DisplayName("isHoveredCirc - Test 4: juste hors du cercle (distance = 86) => false")
	void circJustOutside() {
		Assertions.assertFalse(button.isHoveredCirc(185 + 86, 185));
	}

	@Test
	@DisplayName("isHoveredCirc - Test 5: coin du bouton (dans le rect, hors du cercle) => false")
	void circCornerOutsideCircle() {
		// Coin haut-gauche (100,100) : distance au centre = sqrt(85²+85²) ≈ 120 > 85
		Assertions.assertFalse(button.isHoveredCirc(100, 100));
	}

	@Test
	@DisplayName("isHoveredCirc - Test 6: au-dessus, hors cercle => false")
	void circAbove() {
		Assertions.assertFalse(button.isHoveredCirc(185, 50));
	}

	@Test
	@DisplayName("isHoveredCirc - Test 7: en diagonale dans le cercle => true")
	void circDiagonalInside() {
		// décalage (50,50) : distance = sqrt(50²+50²) ≈ 70.7 < 85
		Assertions.assertTrue(button.isHoveredCirc(185 + 50, 185 + 50));
	}

	@Test
	@DisplayName("isHoveredCirc - Test 8: loin => false")
	void circFar() {
		Assertions.assertFalse(button.isHoveredCirc(1000, 1000));
	}
}