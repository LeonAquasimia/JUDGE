package engine.input;

import engine.math.Vector2;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Input
{
	private static int KEY_NUM=256,MOUSE_NUM=5;
    private static boolean[] keys=new boolean[KEY_NUM];
    private static boolean[] buttons=new boolean[MOUSE_NUM];
    private static Vector2 mousePos=new Vector2(0,0);
	
	public static void init()
	{
		try
		{
			Keyboard.create();
			Mouse.create();
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}
		
		
		for (int i=0;i<KEY_NUM;i++)
		{
			keys[i]=false;
		}
		
		for (int i=0;i<MOUSE_NUM;i++)
		{
            buttons[i]=false;
		}
	}
	
	public static void dispose()
	{
		Keyboard.destroy();
		Mouse.destroy();
	}

	public static boolean keyHeld(int keyCode)
    {
        return keyCode < KEY_NUM && (Keyboard.isKeyDown(keyCode));
    }
	
	public static boolean keyClear(int keyCode)
	{
        return keyCode < KEY_NUM && (!Keyboard.isKeyDown(keyCode));
    }
	
	public static boolean keyPressed(int keyCode)
    {
        return keyCode < KEY_NUM && (Keyboard.isKeyDown(keyCode) && !keys[keyCode]);
    }
	
	public static boolean keyReleased(int keyCode)
	{
        return keyCode < KEY_NUM && (!Keyboard.isKeyDown(keyCode) && keys[keyCode]);
	}

	public static boolean mouseHeld(int mouseCode)
	{
		return mouseCode<MOUSE_NUM && (Mouse.isButtonDown(mouseCode));
	}
	
	public static boolean mouseClear(int mouseCode)
	{
        return mouseCode<MOUSE_NUM && (!Mouse.isButtonDown(mouseCode));
	}
	
	public static boolean mousePressed(int mouseCode)
	{
        return mouseCode<MOUSE_NUM && (Mouse.isButtonDown(mouseCode) && !buttons[mouseCode]);
	}
	
	public static boolean mouseReleased(int mouseCode)
	{
        return mouseCode<MOUSE_NUM && (!Mouse.isButtonDown(mouseCode) && buttons[mouseCode]);
	}
	
	public static Vector2 mousePosition()
	{
		return new Vector2(Mouse.getX(),Mouse.getY());
	}
	
	public static void mousePosition(Vector2 pos)
	{
		Mouse.setCursorPosition((int)pos.x(),(int)pos.y());
	}
	
	public static void setMouseCursor(boolean enabled)
	{
		Mouse.setGrabbed(!enabled);
	}	
	
	public static void update()
	{
        for (int i=0;i<KEY_NUM;i++)
        {
            keys[i]=Keyboard.isKeyDown(i);
        }

        for (int i=0;i<MOUSE_NUM;i++)
        {
            buttons[i]=Mouse.isButtonDown(i);
        }
	}
}




