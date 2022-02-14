package org.duckdns.fossilfind.twistory.state;

import java.awt.Graphics;

public abstract class State
{
	private static State currentState;
	
	public static State getCurrentState()
	{
		return currentState;
	}
	
	public static void setCurrentState(State state)
	{
		if(currentState != null)
			currentState.close();
		currentState = state;
	}
	
	public void close() { }
	
	public abstract void update(double delta);
	
	public abstract void render(Graphics g);
}