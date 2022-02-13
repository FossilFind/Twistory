package org.duckdns.fossilfind.twistory.entity.player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.duckdns.fossilfind.twistory.Twistory;
import org.duckdns.fossilfind.twistory.entity.Entity;
import org.duckdns.fossilfind.twistory.image.Animation;
import org.duckdns.fossilfind.twistory.image.Camera;

public class Player extends Entity
{
	private double followX, followY;
	private Animation[] animations;
	private BufferedImage[] idles;
	private int currentAnim = 0;
	
	private BufferedImage main;
	
	public Player()
	{
		super(150, 150, 3);
		
		x = 1000;
		y = 800;
		
		try
		{
			main = ImageIO.read(new File("./textures/test/gavin_test_sprite.png"));
			
			idles = new BufferedImage[] { main.getSubimage(0, 0, 63, 63), main.getSubimage(0, 64, 63, 63), main.getSubimage(0, 128, 63, 63), main.getSubimage(0, 192, 63, 63) };
			
			animations = new Animation[]
			{
				new Animation(new BufferedImage[] {main.getSubimage(0, 0, 63, 63), main.getSubimage(64, 0, 63, 63), main.getSubimage(0, 0, 63, 63), main.getSubimage(128, 0, 63, 63)}, 200),
				new Animation(new BufferedImage[] {main.getSubimage(0, 64, 63, 63), main.getSubimage(64, 64, 63, 63), main.getSubimage(0, 64, 63, 63), main.getSubimage(128, 64, 63, 63)}, 200),
				new Animation(new BufferedImage[] {main.getSubimage(0, 128, 63, 63), main.getSubimage(64, 128, 63, 63), main.getSubimage(0, 128, 63, 63), main.getSubimage(128, 128, 63, 63)}, 200),
				new Animation(new BufferedImage[] {main.getSubimage(0, 192, 63, 63), main.getSubimage(64, 192, 63, 63), main.getSubimage(0, 192, 63, 63), main.getSubimage(128, 192, 63, 63)}, 200)
			};
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(double delta)
	{
		if(Twistory.INSTANCE.getInput().pressingKey[KeyEvent.VK_W])
			yMove -= speed * delta;
		
		if(Twistory.INSTANCE.getInput().pressingKey[KeyEvent.VK_A])
			xMove -= speed * delta;
		
		if(Twistory.INSTANCE.getInput().pressingKey[KeyEvent.VK_S])
			yMove += speed * delta;
		
		if(Twistory.INSTANCE.getInput().pressingKey[KeyEvent.VK_D])
			xMove += speed * delta;
		
		super.update(delta);
		
		updateFollower();
		
		if(yMove > 0)
			currentAnim = 0;
		if(yMove < 0)
			currentAnim = 1;
		if(xMove > 0)
			currentAnim = 2;
		if(xMove < 0)
			currentAnim = 3;
		
		if(xMove != 0 || yMove != 0)
			animations[currentAnim].update();
	}
	
	private void updateFollower()
	{
		double followXMove = 0;
		double followYMove = 0;
		
		double xDist = x - followX;
		double yDist = y - followY;
		
		int followDist = 150;
		
		if(Math.abs(xDist) > followDist)
		{
			if(xDist > 0)
			{
				followXMove += (xDist - followDist) / 4;
				
				if(followX + followXMove > x - followDist)
					followXMove = xDist - followDist;
			}
			else
			{
				followXMove += (xDist + followDist) / 4;
				
				if(followX + followXMove < x + followDist)
					followXMove = xDist + followDist;
			}
		}
		
		if(Math.abs(yDist) > followDist)
		{
			if(yDist > 0)
			{
				followYMove += (yDist - followDist) / 4;
				
				if(followY + followYMove > y - followDist)
					followYMove = yDist - followDist;
			}
			else
			{
				followYMove += (yDist + followDist) / 4;
				
				if(followY + followYMove < y + followDist)
					followYMove = yDist + followDist;
			}
		}
		
		if(yMove != 0)
		{
			if(xDist > 0)
				followXMove += xDist / 100;
			else
				followXMove += xDist / 100;
		}
		
		if(xMove != 0)
		{
			if(yDist > 0)
				followYMove += yDist / 100;
			else
				followYMove += yDist / 100;
		}
		
		followX += followXMove;
		followY += followYMove;
	}
	
	@Override
	public void render(Graphics g)
	{
		g.setColor(Color.blue);
		g.fillRect((int) (followX - Camera.xOffset(this)), (int) (followY - Camera.yOffset(this)), sizeY, sizeX);
		
		if(xMove != 0 || yMove != 0)
			g.drawImage(animations[currentAnim].getCurrentFrame(), (int) (x - Camera.xOffset(this)), (int) (y - Camera.yOffset(this)), sizeX, sizeY, null);
		else
			g.drawImage(idles[currentAnim], (int) (x - Camera.xOffset(this)), (int) (y - Camera.yOffset(this)), sizeX, sizeY, null);
	}
}