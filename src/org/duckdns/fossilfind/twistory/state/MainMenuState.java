package org.duckdns.fossilfind.twistory.state;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.duckdns.fossilfind.twistory.Twistory;
import org.duckdns.fossilfind.twistory.input.ui.Button;

public class MainMenuState extends State
{
	private Button startButton;
	private Button settingsButton;
	private Button quitButton;
	
	public MainMenuState()
	{
		try
		{
			BufferedImage startButtonTexture = ImageIO.read(new File("./textures/test/test_button.png"));
			BufferedImage settingsButtonTexture = ImageIO.read(new File("./textures/test/settings_button.png"));
			BufferedImage quitButtonTexture = ImageIO.read(new File("./textures/test/quit_button.png"));
			
			startButton = new Button(new BufferedImage[] { startButtonTexture.getSubimage(0, 0, 128, 32), startButtonTexture.getSubimage(0, 32, 128, 32) }, 100, 100, 512, 128, () -> State.setCurrentState(new GameState()));
			settingsButton = new Button(new BufferedImage[] { settingsButtonTexture.getSubimage(0, 0, 128, 32), settingsButtonTexture.getSubimage(0, 32, 128, 32) }, 100, 300, 512, 128, () -> System.out.println("Settings"));
			quitButton = new Button(new BufferedImage[] { quitButtonTexture.getSubimage(0, 0, 128, 32), quitButtonTexture.getSubimage(0, 32, 128, 32) }, 100, 500, 512, 128, () -> System.exit(0));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void close()
	{
		Twistory.INSTANCE.getInput().removeButtonListener(startButton);
		Twistory.INSTANCE.getInput().removeButtonListener(settingsButton);
		Twistory.INSTANCE.getInput().removeButtonListener(quitButton);
	}
	
	@Override
	public void update(double delta)
	{
	}
	
	@Override
	public void render(Graphics g)
	{
		startButton.render(g);
		settingsButton.render(g);
		quitButton.render(g);
	}
}