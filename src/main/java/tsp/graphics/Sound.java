package tsp.graphics;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	private Clip clip;
	private URL soundURL;
	
<<<<<<< nathan
	public Sound(String chemin) {
		soundURL = getClass().getResource(chemin);
=======
	public Sound() {
		soundURL[0] = getClass().getResource("/sounds/music/track1.wav");
		soundURL[1] = getClass().getResource("/sounds/music/track2.wav");
		soundURL[2] = getClass().getResource("/sounds/sound_effect/SFX_Jump_42.wav.wav");
>>>>>>> main
	}
	
	public void setFile() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL);
			clip = AudioSystem.getClip();
			clip.open(ais);
		}catch(Exception e) {
		}
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
	public void volume_percent_to_gain(float volumePercent) {

		FloatControl volume = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);

		float min = volume.getMinimum();
		float max = volume.getMaximum();

		float gain = min + (max - min) * volumePercent;
		volume.setValue(gain);
	}
<<<<<<< nathan
	
	public void playMusic() {
		this.setFile();
		this.play();
		this.loop();
	}
	public void stopMusic() {
		this.stop();
	}
	 
	public void playSE() {
		this.setFile();
		this.play();
	 }	
	public void volume(float i) {
		this.volume_percent_to_gain(i);
	 }	
=======
>>>>>>> main
}
