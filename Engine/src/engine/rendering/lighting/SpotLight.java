package engine.rendering.lighting;

import engine.math.Vector3;

public class SpotLight
{
	private PointLight point;
	private Vector3 direction;
    private float cutOff;

	public SpotLight(PointLight point, Vector3 direction, float cutOff)
	{
		this.point=point;
        this.direction=direction;
        this.cutOff=cutOff;
	}

	public PointLight point()
    {
		return point;
	}

	public void point(PointLight point)
    {
		this.point = point;
	}

	public Vector3 direction()
    {
		return direction;
	}

	public void direction(Vector3 direction)
    {
		this.direction = direction;
	}

    public float cutOff()
    {
        return cutOff;
    }

    public void cutOff(float cutOff)
    {
        this.cutOff = cutOff;
    }
}
