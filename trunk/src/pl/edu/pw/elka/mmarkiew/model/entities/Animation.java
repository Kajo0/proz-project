package pl.edu.pw.elka.mmarkiew.model.entities;

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
	public synchronized void addFrame(int animFrame, long duration) {
		this.duration += duration;
		this.frames.add(new AnimFrame(animFrame, this.duration));
	}

	/**
	 * Update animation's frame state
	 * @param elapsedTime - Elapsed time from last update
	 */
	public void update(long elapsedTime) {
		if (frames.size() > 1) {
			animTime += elapsedTime;
			
			if (animTime >= duration) {
				animTime %= duration;
				currentImage = 0;
			}
			
			while (animTime > getFrame(currentImage).endTime) {
				++currentImage;
			}
		}
	}
	
	/**
	 * 
	 * @return Frame number if exists (can be 0), 0 otherwise
	 */
	public synchronized int getAnimFrame() {
		if (frames.size() == 0) {
			return 0;
		}
		else return getFrame(currentImage).animFrame;
	}
	
	/**
	 * 
	 * @return Count frames
	 */
	public int getFrameCount() {
		return frames.size();
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
		int animFrame;
		long endTime;
		
		public AnimFrame(int animFrame, long endTime) {
			this.animFrame = animFrame;
			this.endTime = endTime;
		}
	}
}