package engine.entity.component;

import engine.entity.Entity;
import engine.math.Vector3;
import engine.rendering.Colour;
import engine.rendering.shader.ForwardDirectionalLightShader;
import engine.rendering.shader.ForwardPointLightShader;
import engine.rendering.shader.Shader;

public class PointLight extends BaseLight
{
	private Vector3 position;
    private Vector3 attenuation;
    private float range;

    private static Shader shader;

    public PointLight()
    {
        super();
        this.position=new Vector3(0,0,0);
        this.attenuation=new Vector3(0,0,1);
        this.range=0;
    }

	public PointLight(Colour colour,float intensity,Vector3 attenuation,float range)
	{
        super(colour, intensity);
        this.position=new Vector3(0,0,0);
		this.attenuation=attenuation;
        this.range=range;
	}

    public void create(Entity entity)
    {
        entity.theatre().environment().addLight(this);
    }

    public void update(float delta, Entity entity)
    {
        position=entity.transform().position();
    }

	public Vector3 position()
    {
		return position;
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

    public Shader shader()
    {
        if(shader==null)
        {
            shader=new ForwardPointLightShader();
        }

        return shader;
    }
}
