	module tsp.alien {
		exports tsp.platformer;
	
		requires javafx.base;
		requires javafx.controls;
		requires javafx.fxml;
		requires javafx.graphics;
		
		opens tsp.platformer to javafx.graphics, javafx.fxml;
	}
