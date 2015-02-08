package engine.math;

public class Vector3
{
	private float x=0,y=0,z=0;
    public static final Vector3 up=new Vector3(0,1,0);
	
	public Vector3(float x,float y,float z)
	{
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public String toString()
	{
		return "("+x+","+y+","+z+")";
	}
	
	public float length()
	{
		return (float)Math.sqrt(x*x+y*y+z*z);
	}
	
	public float dot(Vector3 r)
	{
		return x*r.x+y+r.y+z*r.z;
	}
	
	public Vector3 cross(Vector3 r)
	{
		return new Vector3(y*r.z-z*r.y,z*r.x-x*r.z,x*r.y-y*r.x);
	}

    public Vector3 lerp(Vector3 destination,float factor)
    {
        return destination.sub(this).mul(factor).add(this);
    }

    public Vector3 copy()
    {
        return new Vector3(-x,-y,-z);
    }

    public boolean like(Vector3 r)
    {
        return (r.x==x && r.y==y && r.z==z);
    }

    public Vector3 inverse()
    {
        return new Vector3(-x,-y,-z);
    }

	public Vector3 normalised()
	{
		return new Vector3(x/length(),y/length(),z/length());
	}
	
	public Vector3 rotate(float angle, Vector3 axis)
	{
		float sinHalfAngle=(float)Math.sin(Math.toRadians(angle/2));
		float cosHalfAngle=(float)Math.cos(Math.toRadians(angle/2));
		
		float rX,rY,rZ,rW;
		rX=axis.x*sinHalfAngle;
		rY=axis.y*sinHalfAngle;
		rZ=axis.z*sinHalfAngle;
		rW=cosHalfAngle;
		
		Quaternion rotation=new Quaternion(rX,rY,rZ,rW);		
		Quaternion w=rotation.mul(this).mul(rotation.conjugate());
		
		//x=w.x();
		//y=w.y();
		//z=w.z();
		
		return new Vector3(w.x(),w.y(),w.z());
	}
	
	public Vector3 add(Vector3 r)
	{
		return new Vector3(x+r.x, y+r.y,z+r.z);
	}
	
	public Vector3 add(float rx,float ry,float rz)
	{
		return new Vector3(x+rx,y+ry,z+rz);
	}

    public Vector3 add(float r)
    {
        return new Vector3(x+r,y+r,z+r);
    }
	
	public Vector3 sub(Vector3 r)
	{
		return new Vector3(x-r.x, y-r.y,z-r.z);
	}
	
	public Vector3 sub(float rx,float ry,float rz)
	{
		return new Vector3(x-rx, y-ry,z-rz);
	}

    public Vector3 sub(float r)
    {
        return new Vector3(x-r,y-r,z-r);
    }
	
	public Vector3 mul(Vector3 r)
	{
		return new Vector3(x*r.x, y*r.y,z*r.z);
	}
	
	public Vector3 mul(float rx,float ry,float rz)
	{
		return new Vector3(x*rx, y*ry,z*rz);
	}

    public Vector3 mul(float r)
    {
        return new Vector3(x*r, y*r,z*r);
    }
	
	public Vector3 div(Vector3 r)
	{
		return new Vector3(x/r.x, y/r.y,z/r.z);
	}
	
	public Vector3 div(float rx,float ry,float rz)
	{
		return new Vector3(x/rx, y/ry,z/rz);
	}

    public Vector3 div(float r)
    {
        return new Vector3(x/r, y/r,z/r);
    }
	
	public void set(float x,float y,float z)
	{
		this.x=x;
		this.y=y;
		this.z=z;
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
	
	public float z()
	{
		return z;
	}

	public void z(float z)
	{
		this.z = z;
	}

    public Vector2 xy()
    {
        return new Vector2(x,y);
    }

    public Vector2 xz()
    {
        return new Vector2(x,z);
    }

    public Vector2 yx()
    {
        return new Vector2(y,x);
    }

    public Vector2 yz()
    {
        return new Vector2(y,z);
    }

    public Vector2 zx()
    {
        return new Vector2(z,x);
    }

    public Vector2 zy()
    {
        return new Vector2(z,y);
    }
}
