package pl.edu.pw.elka.mmarkiew.model.entities;

import java.awt.Image;
import java.util.ArrayList;

/**
 * Class represent simply animation
 * @author Acer
 *
 */
public class Animation {
	private ArrayList<AnimFrame> frames;
	private int currentImage;
	private long animTime;
	private long duration;
	
	/**
	 * Creates new 'null' animation<br>
	 * and set it started
	 */
	public Animation() {
		this.frames = new ArrayList<AnimFrame>();
		this.currentImage = 0;
		start();
	}
	
	/**
	 * Copy constructor to clone animation
	 * @param frames - List of frames
	 * @param duration - Animation duration
	 */
	private Animation(ArrayList<AnimFrame> frames, long duration) {
		this.frames = frames;
		this.duration = duration;
		start();
	}
	
	/**
	 * Creates instance of .this animation<br>
	 * contain shared images
	 * @return cloned Animation object
	 */
	public Animation clone() {
		return new Animation(frames, duration);
	}
	
	/**
	 * Set animation started
	 */
	public synchronized void start() {
		this.currentImage = 0;
		this.animTime = 0;
	}
	
	/**
	 * Adds frame image into end of animation<br>
	 * which is display for duration time
	 * @param image - Frame image
	 * @param duration - Display time
	 */
	public synchronized void addFrame(Image image, long duration) {
		this.duration += duration;
		this.frames.add(new AnimFrame(image, this.duration));
	}

	/**
	 * Update animation's frame state
	 * @param elapsedTime - Elapsed time from last update
	 */
	public synchronized void update(long elapsedTime) {
		if (frames.size() > 1) {
			animTime += elapsedTime;
			
			if (animTime >= duration) {
				animTime %= duration;
				currentImage = 0;
			}
			
			while (animTime > getFrame(currentImage).endTime) {
				currentImage++;
			}
		}
	}
	
	/**
	 * 
	 * @return image if exists, null otherwise
	 */
	public synchronized Image getImage() {
		if (frames.size() == 0) {
			return null;
		}
		else return getFrame(currentImage).image;
	}

	/**
	 * Gets frame
	 * @param index - frame index
	 * @return Frame on given index
	 */
	private AnimFrame getFrame(int index) {
		return frames.get(index);
	}
	
	/**
	 * Helper class to contain image and duration
	 * @author Acer
	 *
	 */
	private class AnimFrame {
		Image image;
		long endTime;
		
		public AnimFrame(Image image, long endTime) {
			this.image = image;
			this.endTime = endTime;
		}
	}
}