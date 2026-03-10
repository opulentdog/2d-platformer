package tsp.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Menu {

    private static double btnX = 250;
    private static double btnY = 200;
    private static double btnWidth = 200;
    private static double btnHeight = 60;

    public static void render(Window window) {

        GraphicsContext gc = window.getGC();

        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,window.getWidth(),window.getHeight());

        gc.setFill(Color.ORANGE);
        gc.fillRect(btnX,btnY,btnWidth,btnHeight);

        gc.setFill(Color.BLACK);
        gc.fillText("PLAY", btnX+80, btnY+35);
    }

    public static boolean isClicked(double x,double y) {
        return x >= btnX && x <= btnX+btnWidth &&
               y >= btnY && y <= btnY+btnHeight;
    }
}