package tsp.engine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import tsp.engine.platforms.Platform;
import tsp.engine.platforms.BasicPlatform;

/**
 * Unit Test Class for {@link tsp.engine.Asset}
 */
public class TestAsset {
	/**
	* The {@link Asset} object to be tested. It must be initialised to a concrete
	* instance in the {@link #setUp()} method.
	*/
	protected Asset playerUnderTest1;

	 /**
	 * The {@link Asset} object to be tested. It must be initialised to a concrete
	 * instance in the {@link #setUp()} method.
	 */
	 protected Asset playerUnderTest2;
	 
	 protected Platform platformUnderTest1;
	
	  @BeforeEach
	  public void setUp() {
		  playerUnderTest1= new Player("/images/player.png", 70, 70);
		  platformUnderTest1 = new BasicPlatform();
	  }
	  
	  /**
	   * Test1: Contact entre un Player et une plateforme
	   */
	  @Test
	  @DisplayName("Test 1: Contact entre un Player et une plateforme")
	  void platformOverPlayer() {
		playerUnderTest1.x=50;
		playerUnderTest1.y=50;
		platformUnderTest1.x=50;
		platformUnderTest1.y=50;
	    Assertions.assertTrue(platformUnderTest1.intersects(playerUnderTest1));
	  }
	  
	  /**
	   * Test2: Player et plateforme éloignés
	   */
	  @Test
	  @DisplayName("Test 2: Player et plateforme éloignés")
	  void platformFarFromPlayer() {
		playerUnderTest1.x=50;
		playerUnderTest1.y=400;
		platformUnderTest1.x=500;
		platformUnderTest1.y=50;
	    Assertions.assertFalse(platformUnderTest1.intersects(playerUnderTest1));
	  }
	  
	  /**
	   * Set the bankAccountUnderTest to null; for garbage collection.
	   */
	  @AfterEach
	  public void tearDown() {
		  playerUnderTest2 = null;
	  }


}