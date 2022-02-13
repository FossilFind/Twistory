package org.duckdns.fossilfind.twistory.state;

import java.awt.Graphics;

import org.duckdns.fossilfind.twistory.entity.player.Player;

public class GameState extends State
{
	private Player player;
	
	public GameState()
	{
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
		player.render(g);
	}
}