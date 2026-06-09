package tsp.graphics;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	
	/*-------------------------------------------------------------------------------------------------------------------------------------
	 * ------------------- Fields -------------------
	 * -------------------------------------------------------------------------------------------------------------------------------------
	 */
	
	private Clip clip;
	private URL soundURL;
	private boolean on;
	
	/*-------------------------------------------------------------------------------------------------------------------------------------
	 * ------------------- Constructor -------------------
	 * -------------------------------------------------------------------------------------------------------------------------------------
	 */
	
	public Sound(String chemin) {
		soundURL = getClass().getResource(chemin);
		on = false;

	}
	
	/***
	 * ------------------- Methods -------------------
	 */
	
	/**
	 *  Charge le fichier audio associé à l'objet Sound et prépare sa lecture.
	 */

	public void setFile() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL);
			clip = AudioSystem.getClip();
			clip.open(ais);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * lance le son
	 */
	public void play() {
		clip.start();
	}
	
	/**
	 * lance le son en boucle 
	 */
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	/**
	 * arrete le son
	 */
	public void stop() {
		clip.stop();
	}
	
	/**
	 * Convertit un pourcentage de volume linéaire en gain audio (en décibels)
	 * et applique ce gain au clip sonore.
	 * 
	 * @param volumePercent ratio de volume compris entre 0.0 (minimum) et 1.0 (maximum)
	 */
	public void volume(float volumePercent) {

		FloatControl volume = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);

		float min = volume.getMinimum();
		float max = volume.getMaximum();

		float gain = min + (max - min) * volumePercent;
		volume.setValue(gain);
	}
	
	/**
	 * Lance la musique ( et non un simple son ) en boucle
	 */
	public void playMusic() {
		if (! on) {
			this.setFile();
			this.play();
			this.loop();
			on = true;
		}
	}
	/**
	 * Arrete la musique
	 */
	public void stopMusic() {
		if (on) {
			this.stop();
			on = false;
		}
	}
	 
	/**
	 * Lance un son ( sound effect )
	 */
	public void playSE() {
		this.setFile();
		this.play();
	 }	

}
