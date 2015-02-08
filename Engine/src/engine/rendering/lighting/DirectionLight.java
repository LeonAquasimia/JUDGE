package engine.rendering.lighting;

import engine.math.Vector3;

public class DirectionLight
{
	private BaseLight base;
	private Vector3 direction;
	
	public DirectionLight(BaseLight base,Vector3 direction)
	{
		this.base=base;
		this.direction=direction;
	}

	public BaseLight base() {
		return base;
	}

	public void base(BaseLight base) {
		this.base = base;
	}

	public Vector3 direction() {
		return direction;
	}

	public void direction(Vector3 direction) {
		this.direction = direction;
	}
	
}
