package pl.edu.pw.elka.mmarkiew.model;

import java.applet.Applet;
import java.applet.AudioClip;

public class SoundManager implements Runnable {
	private static AudioClip backgroundMusic;
	private static AudioClip explosionSound;
	private static AudioClip exitSound;
	private static AudioClip bonusSound;
	private static AudioClip killSound;
	
	private static boolean backgroundOn;
	private static boolean soundEffectOn;
	private static boolean soundsOn;

	public static final int EXPLOSION	= 0;
	public static final int EXIT		= 1;
	public static final int BONUS		= 2;
	public static final int KILL		= 3;
	
	
	public SoundManager() {
		try {
			backgroundMusic = Applet.newAudioClip(getClass().getResource("/background.wav"));
			explosionSound = Applet.newAudioClip(getClass().getResource("/explosion.wav"));
			exitSound = Applet.newAudioClip(getClass().getResource("/exit.wav"));
			bonusSound = Applet.newAudioClip(getClass().getResource("/bonus.wav"));
			killSound = Applet.newAudioClip(getClass().getResource("/kill.wav"));

			soundEffectOn = true;
			backgroundOn = true;
			soundsOn = true;

		} catch (Exception e) {
			backgroundOn = false;
			soundEffectOn = false;
			soundsOn = false;
		}
	}
	
	/**
	 * Plays chosen sound
	 * @param sound - Sound chosen from:<br>
	 * EXLPOSION = 0<br>
	 * EXIT = 1<br>
	 * BONUS = 2<br>
	 * KILL = 3
	 */
	public static void playSound(int sound) {
		/*
		 * If sounds off, do nothing
		 */
		if (!soundEffectOn || !soundsOn)
			return;
		
		/*
		 * Else chode which sound to play
		 */
		try {
			switch (sound) {
				case EXPLOSION:	playExplosion();	break;
				case EXIT:		playExit();			break;
				case BONUS:		playBonus();		break;
				case KILL:		playKill();			break;
			}
		} catch (Exception e) {}
	}

	/**
	 * Plays bomb explosion sound
	 */
	private static void playExplosion() {
		explosionSound.stop();
		explosionSound.play();
	}

	/**
	 * Plays level up sound
	 */
	private static void playExit() {
		exitSound.stop();
		exitSound.play();
	}

	/**
	 * Plays bonus catch sound
	 */
	private static void playBonus() {
		bonusSound.stop();
		bonusSound.play();
	}
	
	/**
	 * Plays kill sound
	 */
	private static void playKill() {
		killSound.stop();
		killSound.play();
	}
	
	/**
	 * Switch background loop music on / off
	 */
	public static void switchBackgroundMusicEnable() {
		
		backgroundOn = backgroundOn ? false : true;
		
		/*
		 * If was playing, stop
		 * otherwise loop it
		 */
		if (!backgroundOn && soundsOn)
			backgroundMusic.stop();
		
		else if (backgroundOn && soundsOn)
			backgroundMusic.loop();
	}
	
	/**
	 * Switch sound effects on / off
	 */
	public static void switchSoundEffectsEnable() {
		soundEffectOn = soundEffectOn ? false : true;
	}

	@Override
	public void run() {
		try {
			backgroundMusic.loop();
		} catch (Exception e) {
			backgroundOn = false;
		}
	}
	
	public static boolean isBackgroundOn() {
		return backgroundOn;
	}
	
	public static boolean isSoundEffectOn() {
		return soundEffectOn;
	}
}
