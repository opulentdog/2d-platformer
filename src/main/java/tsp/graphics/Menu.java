package tsp.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;


public class Menu {

	// dimensions du bouton start qui sert à lancer la boucle de jeu
    private static double btnX = 250;
    private static double btnY = 200;
    private static double btnWidth = 200;
    private static double btnHeight = 60;

    public static void render(Window window) {

        GraphicsContext gc = window.getGC();
        
        Texture background = new Texture("/images/fond_menu.jpg", window.getWidth(), window.getHeight());
        background.setBG(window);
        //gc.setFill(Color.BLACK);
        //gc.fillRect(0,0,window.getWidth(),window.getHeight());

        gc.setFill(Color.ORANGE);
        gc.fillRect(btnX,btnY,btnWidth,btnHeight);

        gc.setFill(Color.BLACK);
        gc.fillText("PLAY", btnX+80, btnY+35);
    }

    /**
     * Indique si la souris est superposée au bouton start ( n'indique pas directement que le bouton start est cliqué )
     * @param x position de la souris selon l'horizontale
     * @param y position de la souris selon la verticale
     * @return
     */
    public static boolean isClicked(double x,double y) {
        return x >= btnX && x <= btnX+btnWidth &&
               y >= btnY && y <= btnY+btnHeight;
    }
}