package engine.entity.component;

import engine.entity.Entity;
import engine.entity.component.PointLight;
import engine.math.Vector3;
import engine.rendering.Colour;
import engine.rendering.shader.ForwardDirectionalLightShader;
import engine.rendering.shader.ForwardSpotLightShader;
import engine.rendering.shader.Shader;

public class SpotLight extends PointLight
{
	private Vector3 direction;
    private float cutOff;

    private static Shader shader;

    public SpotLight()
    {
        super();
        direction(new Vector3(0, 0, 1));
        cutOff(0);
    }

    public SpotLight(Colour colour,float intensity,Vector3 attenuation,float range,Vector3 direction, float cutOff)
    {
        super(colour,intensity,attenuation,range);
        direction(direction);
        cutOff(cutOff);
    }

    public void create(Entity entity)
    {
        entity.theatre().environment().addLight(this);
    }

    public void update(float delta, Entity entity)
    {
        super.update(delta, entity);
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

    public Shader shader()
    {
        if(shader==null)
        {
            shader=new ForwardSpotLightShader();
        }

        return shader;
    }
}
