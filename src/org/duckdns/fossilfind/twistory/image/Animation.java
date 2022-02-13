package org.duckdns.fossilfind.twistory.image;

import java.awt.image.BufferedImage;

public class Animation
{
	private final BufferedImage[] frames;
	private final int speed;
	
	private int index = 0;
	private long last = System.currentTimeMillis();
	
	public Animation(BufferedImage[] frames, int speed)
	{
		this.frames = frames;
		this.speed = speed;
	}
	
	public void update()
	{
		if(System.currentTimeMillis() - last >= speed)
		{
			last = System.currentTimeMillis();
			index++;
			
			if(index >= frames.length)
				index = 0;
		}
	}
	
	public BufferedImage getCurrentFrame()
	{
		return frames[index];
	}
}