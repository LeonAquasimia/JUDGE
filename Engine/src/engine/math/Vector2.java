package engine.math;

public class Vector2
{
	private float x=0,y=0;
	
	public Vector2(float x,float y)
	{
		this.x=x;
		this.y=y;
	}
	
	public String toString()
	{
		return "("+x+","+y+")";
	}
	
	public float length()
	{
		return (float)Math.sqrt(x*x+y*y);
	}
	
	public float dot(Vector2 r)
	{
		return x*r.x+y+r.y;
	}

    public float cross(Vector2 r)
    {
        return x-r.y*y-r.x;
    }

    public Vector2 lerp(Vector2 destination,float factor)
    {
        return destination.sub(this).mul(factor).add(this);
    }

    public Vector2 copy()
    {
        return new Vector2(-x,-y);
    }

    public boolean like(Vector2 r)
    {
        return (r.x==x && r.y==y);
    }

    public Vector2 inverse()
    {
        return new Vector2(-x,-y);
    }
	
	public Vector2 normalised()
	{
		return new Vector2(x/length(),y/length());
	}
	
	public Vector2 round()
	{
		return new Vector2(Math.round(x),Math.round(y));
	}
	
	public Vector2 rotate(float angle)
	{
		double rad=Math.toRadians(angle);
		double cos=Math.cos(rad);
		double sin=Math.sin(rad);
		
		return new Vector2((float)(x*cos-y*sin),(float)(x+sin+y*cos));
	}
	
	public Vector2 add(Vector2 r)
	{
		return new Vector2(x+r.x, y+r.y);
	}
	
	public Vector2 add(float rx,float ry)
	{
		return new Vector2(x+rx, y+ry);
	}

    public Vector2 add(float r)
    {
        return new Vector2(x+r, y+r);
    }
	
	public Vector2 sub(Vector2 r)
	{
		return new Vector2(x-r.x, y-r.y);
	}
	
	public Vector2 sub(float rx,float ry)
	{
		return new Vector2(x-rx, y-ry);
	}

    public Vector2 sub(float r)
    {
        return new Vector2(x-r, y-r);
    }
	
	public Vector2 mul(Vector2 r)
	{
		return new Vector2(x*r.x, y*r.y);
	}
	
	public Vector2 mul(float rx,float ry)
	{
		return new Vector2(x*rx, y*ry);
	}

    public Vector2 mul(float r)
    {
        return new Vector2(x*r, y*r);
    }
	
	public Vector2 div(Vector2 r)
	{
		return new Vector2(x/r.x, y/r.y);
	}
	
	public Vector2 div(float rx,float ry)
	{
		return new Vector2(x/rx, y/ry);
	}

    public Vector2 div(float r)
    {
        return new Vector2(x/r, y/r);
    }
	
	public void set(float x,float y)
	{
		this.x=x;
		this.y=y;
	}

	public float x()
	{
		return x;
	}

	public void x(float x)
	{
		this.x = x;
	}

	public float y()
	{
		return y;
	}

	public void y(float y)
	{
		this.y = y;
	}
}
