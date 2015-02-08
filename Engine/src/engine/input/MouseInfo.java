package engine.input;

import engine.math.Vector2;


public class MouseInfo
{
	private int code=-1;
	private Vector2 pos=new Vector2(0,0);
	private MouseState state=MouseState.CLEAR; 
	
	public MouseInfo(int code)
	{
		this.code=code;
	}
		
	public int code()
	{
		return code;
	}

	public void code(int code)
	{
		this.code = code;
	}

	public Vector2 position()
	{
		return pos;
	}

	public void position(float x,float y)
	{
		pos.set(x,y);
	}

	public MouseState state()
	{
		return state;
	}

	public void state(MouseState state)
	{
		this.state = state;
	}

	public enum MouseState
	{
		PRESS,RELEASE,HELD,CLEAR;
	}
}
