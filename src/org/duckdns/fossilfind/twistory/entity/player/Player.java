package org.duckdns.fossilfind.twistory.entity.player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import org.duckdns.fossilfind.twistory.Twistory;
import org.duckdns.fossilfind.twistory.entity.Entity;

public class Player extends Entity
{
	private double followX, followY;
	
	public Player()
	{
		super(50, 50, 6);
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
		
		double followXMove = 0;
		double followYMove = 0;
		
		double xDist = x - followX;
		double yDist = y - followY;
		
		if(Math.abs(xDist) > 70)
		{
			if(xDist > 0)
			{
				followXMove += (xDist - 70) / 4;
				
				if(followX + followXMove > x - 70)
					followXMove = xDist - 70;
			}
			else
			{
				followXMove += (xDist + 70) / 4;
				
				if(followX + followXMove < x + 70)
					followXMove = xDist + 70;
			}
		}
		
		if(Math.abs(yDist) > 70)
		{
			if(yDist > 0)
			{
				followYMove += (yDist - 70) / 4;
				
				if(followY + followYMove > y - 70)
					followYMove = yDist - 70;
			}
			else
			{
				followYMove += (yDist + 70) / 4;
				
				if(followY + followYMove < y + 70)
					followYMove = yDist + 70;
			}
		}
		
		if(yMove != 0)
		{
			if(xDist > 0)
				followXMove += xDist / 30;
			else
				followXMove += xDist / 30;
		}
		
		if(xMove != 0)
		{
			if(yDist > 0)
				followYMove += yDist / 30;
			else
				followYMove += yDist / 30;
		}
		
		followX += followXMove;
		followY += followYMove;
	}
	
	@Override
	public void render(Graphics g)
	{
		g.setColor(Color.blue);
		g.fillRect((int) followX, (int) followY, sizeY, sizeX);
		super.render(g);
	}
}