package engine.core;

import engine.math.Vector2;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window
{
	public static void createWindow(int width,int height,String title)
	{
		try
		{
			Display.setTitle(title);
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}		
	}
	
	public static void render()
	{
		Display.update();
	}
	
	public static boolean isCloseRequested()
	{
		return Display.isCloseRequested();
	}
	
	public static int width()
	{
		return Display.getWidth();
	}
	
	public static int height()
	{
		return Display.getHeight();
	}

    public static Vector2 centre()
    {
        return new Vector2(width()/2, height()/2);
    }
	
	public static String title()
	{
		return Display.getTitle();
	}

	public static void dispose()
	{
		Display.destroy();
	}
}
