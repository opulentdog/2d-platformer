package tsp.graphics;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	private Clip clip;
	private URL soundURL[] = new URL[30];
	
	public Sound() {
		soundURL[0] = getClass().getResource("/sounds/music/track1.wav");
		soundURL[1] = getClass().getResource("/sounds/music/track2.wav");
		soundURL[2] = getClass().getResource("/sounds/sound_effect/SFX_Jump_42.wav.wav");
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
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
}
