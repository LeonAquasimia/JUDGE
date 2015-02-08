package engine.rendering.lighting;

import engine.math.Vector3;

public class PointLight
{
	private BaseLight base;
	private Vector3 position;
    private Vector3 attenuation;

    private float range;

	public PointLight(BaseLight base,Vector3 position,Vector3 attenuation,float range)
	{
		this.base=base;
        this.position=position;
		this.attenuation=attenuation;
        this.range=range;
	}

	public BaseLight base()
    {
		return base;
	}

	public void base(BaseLight base)
    {
		this.base = base;
	}

	public Vector3 position()
    {
		return position;
	}

	public void position(Vector3 position)
    {
		this.position = position;
	}

    public Vector3 attenuation()
    {
        return attenuation;
    }

    public void attenuation(Vector3 attenuation)
    {
        this.attenuation = attenuation;
    }

    public float range()
    {
        return range;
    }

    public void range(float range)
    {
        this.range = range;
    }
}
