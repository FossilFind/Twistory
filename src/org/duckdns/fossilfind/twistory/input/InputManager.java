package org.duckdns.fossilfind.twistory.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

public class InputManager implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener
{
	public boolean[] pressingKey = new boolean[256];
	public boolean[] mouseButton = new boolean[3];
	public int mouseX, mouseY;
	public int mouseWheel;
	
	private ArrayList<KeyBind> keyBinds = new ArrayList<KeyBind>();
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		mouseWheel = e.getWheelRotation();
	}
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		mouseButton[e.getButton()] = true;
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		mouseButton[e.getButton()] = false;
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
		
	}
	
	@Override
	public void mouseExited(MouseEvent e)
	{
		
	}
	
	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		pressingKey[e.getKeyCode()] = true;
		
		for(KeyBind bind : keyBinds)
		{
			if(bind.wasExecuted())
				continue;
			
			if(bind.isBindedWith(e.getKeyCode()))
			{
				bind.getAction().run();
				bind.setExecuted();
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		pressingKey[e.getKeyCode()] = false;
		
		for(KeyBind bind : keyBinds)
			if(bind.isBindedWith(e.getKeyCode()))
				bind.resetExecuted();
	}
	
	public void addAction(int key, InputAction action)
	{
		keyBinds.add(new KeyBind(key, action));
	}
}