package org.duckdns.fossilfind.twistory.state;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.duckdns.fossilfind.twistory.entity.player.Player;
import org.duckdns.fossilfind.twistory.image.Camera;

public class GameState extends State
{
	private BufferedImage ground;
	private Player player;
	
	public GameState()
	{
		try
		{
			ground = ImageIO.read(new File("./textures/test/test_map.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		player = new Player();
	}
	
	@Override
	public void update(double delta)
	{
		player.update(delta);
	}
	
	@Override
	public void render(Graphics g)
	{
		g.drawImage(ground, -Camera.xOffset(player), -Camera.yOffset(player), 3000, 3000, null);
		player.render(g);
	}
}