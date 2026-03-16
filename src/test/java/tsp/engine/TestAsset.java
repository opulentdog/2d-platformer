package tsp.engine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;


/**
 * Unit Test Class for {@link tsp.engine.Asset}
 */
public class TestAsset {
	/**
	* The {@link Asset} object to be tested. It must be initialised to a concrete
	* instance in the {@link #setUp()} method.
	*/
	protected Asset assetUnderTest;

	  
	 /**
	 * The {@link Asset} object to be tested. It must be initialised to a concrete
	 * instance in the {@link #setUp()} method.
	 */
	 protected Asset assetUnderTest;
	
	  @BeforeEach
	  public void setUp() {
	    assetUnderTest= new Asset("/images/player.png", 70, 70);
	  }
	  
	  /**
	   * Set the bankAccountUnderTest to null; for garbage collection.
	   */
	  @AfterEach
	  public void tearDown() {
		  assetUnderTest = null;
	  }


}
