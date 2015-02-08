package engine.geometry;

import engine.math.Vector2;
import engine.math.Vector3;

public class Vertex
{
	public static int size=8;
	
	private Vector3 pos;
	private Vector2 texCoord;
	private Vector3 normal;
	
	public Vertex(Vector3 pos)
	{
		this(pos,new Vector2(0,0),new Vector3(0,0,0));
	}
	
	public Vertex(Vector3 pos,Vector2 texCoord)
	{
		this(pos,texCoord,new Vector3(0,0,0));
	}
	
	public Vertex(Vector3 pos,Vector2 texCoord,Vector3 normal)
	{
		this.pos=pos;
		this.texCoord=texCoord;
		this.normal=normal;
	}

	public Vector3 position()
	{
		return pos;
	}

	public void position(Vector3 pos)
	{
		this.pos=pos;
	}
	
	public Vector2 texCoord()
	{
		return texCoord;
	}

	public void texCoord(Vector2 texCoord)
	{
		this.texCoord = texCoord;
	}
	
	public Vector3 normal()
	{
		return normal;
	}

	public void normal(Vector3 normal)
	{
		this.normal=normal;
	}
}
