package pl.edu.pw.elka.mmarkiew.model.entities;

import java.awt.Image;
import java.util.ArrayList;

public class Animation {
	private ArrayList<AnimFrame> frames;
	private int currentImage;
	private long animTime;
	private long duration;
	
	public Animation() {
		this.frames = new ArrayList<AnimFrame>();
		this.currentImage = 0;
		start();
	}
	
	public synchronized void start() {
		this.currentImage = 0;
		this.animTime = 0;
	}
	
	public synchronized void addFrame(Image image, long duration) {
		this.duration += duration;
		this.frames.add(new AnimFrame(image, this.duration));
	}

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
	
	public synchronized Image getImage() {
		if (frames.size() == 0) {
			return null;
		}
		else return getFrame(currentImage).image;
	}

	public AnimFrame getFrame(int index) {
		return frames.get(index);
	}
	
	private class AnimFrame {
		Image image;
		long endTime;
		
		public AnimFrame(Image image, long endTime) {
			this.image = image;
			this.endTime = endTime;
		}
	}
}