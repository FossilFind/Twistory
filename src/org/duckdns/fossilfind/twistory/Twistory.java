package org.duckdns.fossilfind.twistory;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import org.duckdns.fossilfind.twistory.input.InputManager;
import org.duckdns.fossilfind.twistory.state.GameState;
import org.duckdns.fossilfind.twistory.state.State;

public class Twistory implements Runnable
{
	public static final Twistory INSTANCE = new Twistory();
	
	public static void main(String[] args)
	{
		INSTANCE.start();
	}
	
	private Thread thread;
	private boolean running = false;
	
	private JFrame frame;
	private Canvas canvas;
	
	private BufferStrategy buffer;
	private Graphics g;
	
	private int width = 1920;
	private int height = 1080;
	
	private InputManager input;
	
	public Twistory()
	{
		frame = new JFrame("Twistory");
		frame.setSize(width, height);
//		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		input = new InputManager();
		
		frame.addKeyListener(input);
		frame.addMouseListener(input);
		frame.addMouseMotionListener(input);
		frame.addMouseWheelListener(input);
		
		canvas.addKeyListener(input);
		canvas.addMouseListener(input);
		canvas.addMouseMotionListener(input);
		canvas.addMouseWheelListener(input);
		
		frame.add(canvas);
		frame.pack();
	}
	
	public void update(double delta)
	{
		State.getCurrentState().update(delta);
	}
	
	public void render()
	{
		buffer = canvas.getBufferStrategy();
		if(buffer == null)
		{
			canvas.createBufferStrategy(3);
			return;
		}
		
		g = buffer.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		State.getCurrentState().render(g);
		
		buffer.show();
		g.dispose();
	}
	
	@Override
	public void run()
	{
		State.setCurrentState(new GameState());
		
		double tick = 1000 / 60;
		double delta = 0;
		long now;
		long last = System.currentTimeMillis();
		
		while(running)
		{
			now = System.currentTimeMillis();
			delta += (now - last) / tick;
			last = now;
			
			if(delta >= 1)
				delta--;
			
			update(delta);
			render();
		}
	}
	
	public synchronized void start()
	{
		if(running)
			return;
		
		running = true;
		
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop()
	{
		if(!running)
			return;
		
		running = false;
		
		try
		{
			thread.join();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public InputManager getInput()
	{
		return input;
	}
}