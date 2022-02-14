package org.duckdns.fossilfind.twistory.input.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.duckdns.fossilfind.twistory.Twistory;
import org.duckdns.fossilfind.twistory.input.InputAction;

public class Button
{
	private final BufferedImage[] texture;
	private final int x, y;
	private final int xSize, ySize;
	private final InputAction action;
	
	public Button(BufferedImage[] texture, int x, int y, int xSize, int ySize, InputAction action)
	{
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
		this.action = action;
		
		Twistory.INSTANCE.getInput().addButtonListener(this);
	}
	
	public void render(Graphics g)
	{
		if(hovering(Twistory.INSTANCE.getInput().mouseX, Twistory.INSTANCE.getInput().mouseY))
			g.drawImage(texture[1], x, y, xSize, ySize, null);
		else
			g.drawImage(texture[0], x, y, xSize, ySize, null);
	}
	
	public boolean hovering(int mouseX, int mouseY)
	{
		return mouseX > x && mouseX < x + xSize && mouseY > y && mouseY < y + ySize;
	}
	
	public InputAction getAction()
	{
		return action;
	}
}