package tsp.engine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit Test Class for {@link tsp.engine.Asset}
 */
public class TestAsset {

	protected Asset playerUnderTest1;
	protected Asset playerUnderTest2;

	@BeforeEach
	public void setUp() {
		playerUnderTest1 = new Player(70, 70);
		playerUnderTest2 = new Player(70, 70);
	}

	// --------------------------------------------------------------------
	// intersects : deux Player 70x70 dans toutes les configurations
	// Rappel : true  <=>  |Δx| < 70  ET  |Δy| < 70  (bords stricts)
	// On garde toujours playerUnderTest1 en (100,100) comme référence.
	// --------------------------------------------------------------------

	@Test
	@DisplayName("Test 1: Superposition exacte (même position)")
	void exactOverlap() {
		playerUnderTest1.setPosition(100, 100);
		playerUnderTest2.setPosition(100, 100);
		Assertions.assertTrue(playerUnderTest1.intersects(playerUnderTest2));
	}

	@Test
	@DisplayName("Test 2: Chevauchement partiel - other en bas à droite")
	void overlapBottomRight() {
		playerUnderTest1.setPosition(100, 100);
		playerUnderTest2.setPosition(140, 140); // Δ=(40,40) < 70
		Assertions.assertTrue(playerUnderTest1.intersects(playerUnderTest2));
	}

	@Test
	@DisplayName("Test 3: Chevauchement partiel - other en haut à gauche")
	void overlapTopLeft() {
		playerUnderTest1.setPosition(100, 100);
		playerUnderTest2.setPosition(60, 60); // Δ=(-40,-40), |Δ| < 70
		Assertions.assertTrue(playerUnderTest1.intersects(playerUnderTest2));
	}

	@Test
	@DisplayName("Test 4: Chevauchement partiel - other en haut à droite")
	void overlapTopRight() {
		playerUnderTest1.setPosition(100, 100);
		playerUnderTest2.setPosition(150, 50); // Δ=(50,-50)
		Assertions.assertTrue(playerUnderTest1.intersects(playerUnderTest2));
	}

	@Test
	@DisplayName("Test 5: Chevauchement partiel - other en bas à gauche")
	void overlapBottomLeft() {
		playerUnderTest1.setPosition(100, 100);
		playerUnderTest2.setPosition(50, 150); // Δ=(-50,50)
		Assertions.assertTrue(playerUnderTest1.intersects(playerUnderTest2));
	}

	@Test
	@DisplayName("Test 6: Limite - juste en chevauchement à droite (Δx=69)")
	void edgeJustOverlappingRight() {
		playerUnderTest1.setPosition(100, 100);
		playerUnderTest2.setPosition(169, 100); // Δx=69 < 70
		Assertions.assertTrue(playerUnderTest1.intersects(playerUnderTest2));
	}

	@Test
	@DisplayName("Test 7: Limite - contact exact bord droit (Δx=70) => false")
	void edgeExactTouchRight() {
		playerUnderTest1.setPosition(100, 100);
		playerUnderTest2.setPosition(170, 100); // Δx=70, bord strict
		Assertions.assertFalse(playerUnderTest1.intersects(playerUnderTest2));
	}

	@Test
	@DisplayName("Test 8: Limite - contact exact bord bas (Δy=70) => false")
	void edgeExactTouchBottom() {
		playerUnderTest1.setPosition(100, 100);
		playerUnderTest2.setPosition(100, 170); // Δy=70
		Assertions.assertFalse(playerUnderTest1.intersects(playerUnderTest2));
	}

	@Test
	@DisplayName("Test 9: Séparé à droite (Δx=100)")
	void separatedRight() {
		playerUnderTest1.setPosition(100, 100);
		playerUnderTest2.setPosition(200, 100);
		Assertions.assertFalse(playerUnderTest1.intersects(playerUnderTest2));
	}

	@Test
	@DisplayName("Test 10: Séparé à gauche (Δx=-100)")
	void separatedLeft() {
		playerUnderTest1.setPosition(100, 100);
		playerUnderTest2.setPosition(0, 100);
		Assertions.assertFalse(playerUnderTest1.intersects(playerUnderTest2));
	}

	@Test
	@DisplayName("Test 11: Séparé au-dessus (Δy=-100)")
	void separatedAbove() {
		playerUnderTest1.setPosition(100, 100);
		playerUnderTest2.setPosition(100, 0);
		Assertions.assertFalse(playerUnderTest1.intersects(playerUnderTest2));
	}

	@Test
	@DisplayName("Test 12: Séparé en dessous (Δy=100)")
	void separatedBelow() {
		playerUnderTest1.setPosition(100, 100);
		playerUnderTest2.setPosition(100, 200);
		Assertions.assertFalse(playerUnderTest1.intersects(playerUnderTest2));
	}

	@Test
	@DisplayName("Test 13: Séparé en diagonale (loin sur les deux axes)")
	void separatedDiagonally() {
		playerUnderTest1.setPosition(100, 100);
		playerUnderTest2.setPosition(300, 300);
		Assertions.assertFalse(playerUnderTest1.intersects(playerUnderTest2));
	}

	@Test
	@DisplayName("Test 14: Chevauche en x mais loin en y => false")
	void overlapXFarY() {
		playerUnderTest1.setPosition(100, 100);
		playerUnderTest2.setPosition(120, 300); // Δx=20<70 mais Δy=200>=70
		Assertions.assertFalse(playerUnderTest1.intersects(playerUnderTest2));
	}

	@Test
	@DisplayName("Test 15: Symétrie - a.intersects(b) == b.intersects(a)")
	void symmetryOfIntersects() {
		playerUnderTest1.setPosition(100, 100);
		playerUnderTest2.setPosition(140, 130);
		Assertions.assertEquals(
				playerUnderTest1.intersects(playerUnderTest2),
				playerUnderTest2.intersects(playerUnderTest1));
	}

	@AfterEach
	public void tearDown() {
		playerUnderTest1 = null;
		playerUnderTest2 = null;
	}
}