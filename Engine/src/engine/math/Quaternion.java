package engine.math;

public class Quaternion
{
	private float x=0,y=0,z=0,w=0;
	
	public Quaternion(float x,float y,float z,float w)
	{
		this.x=x;
		this.y=y;
		this.z=z;
		this.w=w;
	}
	
	public String toString()
	{
		return "("+x+","+y+","+z+","+w+")";
	}
	
	public float length()
	{
		return (float)Math.sqrt(x*x+y*y+z*z+w*w);
	}
	
	public Quaternion normalise()
	{
		return new Quaternion(x/length(),y/length(),z/length(),w/length());
	}
	
	public Quaternion conjugate()
	{
		return new Quaternion(-x,-y,-z,w);
	}
	
	public Quaternion mul(Quaternion r)
	{
		return new Quaternion(x*r.w+w*r.x+y*r.z-z*r.y,y*r.w+w*r.y+z*r.x-x*r.z,z*r.w+w*r.z+x*r.y+y*r.x,w*r.w-x*r.x-y*r.y-z*r.z);
	}
	
	public Quaternion mul(Vector3 r)
	{
		return new Quaternion(w*r.x()+y*r.z()-z*r.y(),w*r.y()+z*r.x()-x*r.z(),w*r.z()+x*r.y()-y*r.x(),-x*r.x()-y*r.y()-z*r.z());
	}
	
	public void set(float x,float y,float z,float w)
	{
		this.x=x;
		this.y=y;
		this.z=z;
		this.w=w;
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
	
	public float w()
	{
		return w;
	}

	public void w(float w)
	{
		this.w = w;
	}
}
