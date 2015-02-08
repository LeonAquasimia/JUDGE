package engine.input;

public class KeyInfo
{
	private int code=-1;
	private KeyState state=KeyState.CLEAR; 
	
	public KeyInfo(int code)
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

	public KeyState state()
	{
		return state;
	}

	public void state(KeyState state)
	{
		this.state = state;
	}

	public enum KeyState
	{
		PRESS,RELEASE,HELD,CLEAR;
	}
}
