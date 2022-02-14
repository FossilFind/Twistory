package org.duckdns.fossilfind.twistory.state;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.duckdns.fossilfind.twistory.entity.player.Player;
import org.duckdns.fossilfind.twistory.image.Camera;

public class GameState extends State
{
	private BufferedImage ground;
	private Player player;
	
	private AudioInputStream stream;
	private Clip clip;
	
	public GameState()
	{
		try
		{
			ground = ImageIO.read(new File("./textures/test/test_map.png"));
			
			stream = AudioSystem.getAudioInputStream(new File("./textures/test/test_audio.wav"));
			clip = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, stream.getFormat()));
			
			clip.open(stream);
			
			((FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(-15);
			
			clip.start();
		}
		catch (IOException | UnsupportedAudioFileException | LineUnavailableException e)
		{
			e.printStackTrace();
		}
		
		player = new Player();
	}
	
	@Override
	public void close()
	{
		try
		{
			stream.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		clip.close();
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