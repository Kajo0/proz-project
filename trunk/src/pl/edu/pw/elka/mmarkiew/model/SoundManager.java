package pl.edu.pw.elka.mmarkiew.model;

import java.applet.Applet;
import java.applet.AudioClip;

public class SoundManager implements Runnable {
	private static AudioClip backgroundMusic;
	private static AudioClip explosionSound;
	private static AudioClip exitSound;
	private static AudioClip bonusSound;
	private static AudioClip killSound;
	
	public SoundManager() {
		try {
			backgroundMusic = Applet.newAudioClip(getClass().getResource("/background.wav"));
			explosionSound = Applet.newAudioClip(getClass().getResource("/explosion.wav"));
			exitSound = Applet.newAudioClip(getClass().getResource("/exit.wav"));
			bonusSound = Applet.newAudioClip(getClass().getResource("/bonus.wav"));
			killSound = Applet.newAudioClip(getClass().getResource("/kill.wav"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void playExplosion() {
		explosionSound.stop();
		explosionSound.play();
	}
	
	public static void playExit() {
		exitSound.play();
	}
	
	public static void playBonus() {
		bonusSound.stop();
		bonusSound.play();
	}
	
	public static void playKill() {
		killSound.play();
	}

	@Override
	public void run() {
		backgroundMusic.loop();
	}
}
