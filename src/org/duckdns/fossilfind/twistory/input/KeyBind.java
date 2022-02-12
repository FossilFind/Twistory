package org.duckdns.fossilfind.twistory.input;

public class KeyBind
{
	private int key;
	private InputAction action;
	private boolean executed;
	
	public KeyBind(int key, InputAction action)
	{
		this.key = key;
		this.action = action;
	}
	
	public boolean isBindedWith(int key)
	{
		return key == this.key;
	}
	
	public int getKey()
	{
		return key;
	}
	
	public InputAction getAction()
	{
		return action;
	}
	
	public boolean wasExecuted()
	{
		return executed;
	}
	
	public void setExecuted()
	{
		executed = true;
	}
	
	public void resetExecuted()
	{
		executed = false;
	}
}