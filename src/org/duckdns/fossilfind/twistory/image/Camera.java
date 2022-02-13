package org.duckdns.fossilfind.twistory.image;

import org.duckdns.fossilfind.twistory.entity.Entity;

public class Camera
{
	public static int xOffset(Entity centeredEntity)
	{
		int offset = (int) (centeredEntity.getX() - 1920 / 2 + centeredEntity.getSizeX() / 2);
		
		if(offset < 0)
			offset = 0;
		else if(offset > 3000 - 1920)
			offset = 3000 - 1920;
		
		return offset;
	}
	
	public static int yOffset(Entity centeredEntity)
	{
		int offset = (int) (centeredEntity.getY() - 1080 / 2 + centeredEntity.getSizeY() / 2);
		
		if(offset < 0)
			offset = 0;
		else if(offset > 3000 - 1080)
			offset = 3000 - 1080;
		
		return offset;
	}
}