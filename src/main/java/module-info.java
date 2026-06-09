module tsp.alien{
    exports tsp.graphics;
    exports tsp.engine;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;

    opens tsp.graphics to javafx.graphics, javafx.fxml;
}