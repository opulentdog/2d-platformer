	module tsp.alien {
		exports tsp.graphics;
	
		requires javafx.base;
		requires javafx.controls;
		requires javafx.fxml;
		requires javafx.graphics;
		
		opens tsp.graphics to javafx.graphics, javafx.fxml;
	}
