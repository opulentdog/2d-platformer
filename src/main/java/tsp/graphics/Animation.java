/*package tsp.graphics;

import javafx.animation.AnimationTimer;

public class Animation {
	
	public Animation(Window window) {
		AnimationTimer animation = new AnimationTimer() {
			long lastTime = 0;
			double rotation=0;
			
	
		    @Override
		    public void handle(long now) {
		    	//Fonction qui est appelé à chaque frame pour dessiner la scène
		        if (lastTime == 0) {
		            lastTime = now;
		            return;
		        }
	
		        //Delta c'est le temps en milliseconde qui s'est écoulé entre deux frames
		        //Ca que le joueur bouge tjrs à la même vitesse même si il y a du lag
		        double delta = (now - lastTime) / 1_000_000_000.0; // seconds
		        update(delta,now);
		    }
	
			private void update(double delta, long now) {
				if (delta < 1.0/40) return; // On limite les fps à 40 frames par seconds
				if (delta > 2.0 / 40 ) { // On a passé plus de deux frames c'est le cas si on a du lag
					System.out.println("Dropped frame");
					lastTime = now;
					return;
				}
				//On met à jour le dernier temps de dessin
		        lastTime = now;
		        
				window.getGC().clearRect(0, 0, window.getCanvas().getWidth(), window.getCanvas().getHeight());
				
				window.setCam(player.y-window.getHeight()/2);
				//player.controlPlayer(PressedKeyset);
				tower.controlTower(PressedKeyset);
				tower.render(ycamera);
				rotation = tower.rotation;
				double towercenterx = windowWidth/2;
	
				
				int i=0;
				for(Platform platform1 : platforms) {
					int relRotation = ((int)rotation - platformAngles[i] + 360)%360;
					double cos=Math.cos(relRotation*2*3.14159/360);
					double sin=Math.sin(relRotation*2*3.14159/360);
					if(relRotation>0 && relRotation < 180) {
						platform1.render(gc,ycamera,Math.abs(sin)*100,platform1.height);
						platform1.setPostition(towercenterx+cos*TowerWidth,platform1.y);
					}
					i++;
				}
				double cos=Math.cos(rotation*2*3.14159/360);
				double sin=Math.sin(rotation*2*3.14159/360);
				player.calculatePosition(windowWidth, windowHeight, platforms);
	
				
				//On dessine le joueur en dernier pour etre au premier plan
				double x=(player.x-windowWidth/2)/TowerWidth;
				player.render(gc,ycamera,Math.sqrt(1-x*x)*player.height,player.height);
				
				gc.strokeText("Score: "+(int)-ycamera/PlatformSpacing, windowHeight-100, 10);
	
				//gc.strokeText("FPS: "+1/delta, 540, 36);		
				return ;				
			}
		};
		animation.start();
		
				
		group.getChildren().add(canva);
		
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	
	}

}*/
