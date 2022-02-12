package org.duckdns.fossilfind.twistory.entity;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Entity
{
	protected double x = 0, y = 0;
	protected double xMove, yMove;
	protected final int sizeX, sizeY;
	protected final double speed;
	
	public Entity(int sizeX, int sizeY, double speed)
	{
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.speed = speed;
	}
	
	public void update(double delta)
	{
		final double slow = delta / 3;
		
		if(xMove > speed)
			xMove = speed;
		else if(xMove < -speed)
			xMove = -speed;
		else if(xMove <= speed && xMove > 0)
		{
			if(xMove - slow < 0)
				xMove = 0;
			else
				xMove -= slow;
		}
		else if(xMove >= -speed && xMove < 0)
		{
			if(xMove + slow > 0)
				xMove = 0;
			else
				xMove += slow;
		}
		
		if(yMove > speed)
			yMove = speed;
		else if(yMove < -speed)
			yMove = -speed;
		else if(yMove <= speed && yMove > 0)
		{
			if(yMove - slow < 0)
				yMove = 0;
			else
				yMove -= slow;
		}
		else if(yMove >= -speed && yMove < 0)
		{
			if(yMove + slow > 0)
				yMove = 0;
			else
				yMove += slow;
		}
		
		x += xMove;
		y += yMove;
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, sizeX, sizeY);
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
}