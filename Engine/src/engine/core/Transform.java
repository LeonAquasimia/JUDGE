package engine.core;

import engine.math.Matrix4;
import engine.math.Vector3;
import engine.entity.component.Camera;

public class Transform
{
	private Vector3 position,rotation,scale;
	
	public Transform()
	{			
		position=new Vector3(0,0,0);
		rotation=new Vector3(0,0,0);
		scale=new Vector3(1,1,1);
	}
	
	public Matrix4 transformation()
	{
		Matrix4 t=new Matrix4(),r=new Matrix4(),s=new Matrix4();
		
		t.translate(position.x(),position.y(),position.z());
		r.rotate(rotation.x(),rotation.y(),rotation.z());
		s.scale(scale.x(),scale.y(),scale.z());
		
		return t.mul(r.mul(s));
	}
	
	public Matrix4 combined(Camera camera)
	{
		Matrix4 transformation=transformation();
		
		return camera.project().mul(transformation);
	}
	
	public Vector3 position()
	{
		return position;
	}

	public void position(Vector3 position)
	{
		this.position = position;
	}
	
	public void position(float x, float y, float z)
	{
		this.position = new Vector3(x,y,z);
	}

	public Vector3 rotation()
	{
		return rotation;
	}

	public void rotation(Vector3 rotation)
	{
		this.rotation = rotation;
	}
	
	public void rotation(float x, float y, float z)
	{
		this.rotation = new Vector3(x,y,z);
	}
	
	public Vector3 scale()
	{
		return scale;
	}

	public void scale(Vector3 scale)
	{
		this.scale = scale;
	}
	
	public void scale(float x, float y, float z)
	{
		this.scale = new Vector3(x,y,z);
	}
}
