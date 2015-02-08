package engine.rendering;

public class Colour
{
	private float r,g,b,a;
	
	public Colour(float r,float g,float b,float a)
	{
		this.r=r;
		this.g=g;
		this.b=b;
		this.a=a;
	}

	public float r()
	{
		return r;
	}

	public void r(float r)
	{
		this.r = r;
	}

	public float g()
	{
		return g;
	}

	public void g(float g)
	{
		this.g = g;
	}

	public float b()
	{
		return b;
	}

	public void b(float b)
	{
		this.b = b;
	}

	public float a()
	{
		return a;
	}

	public void a(float a)
	{
		this.a = a;
	}
	
	
}
