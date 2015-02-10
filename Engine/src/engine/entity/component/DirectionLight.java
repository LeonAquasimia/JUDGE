package engine.entity.component;

import engine.entity.Entity;
import engine.math.Vector3;
import engine.rendering.Colour;
import engine.rendering.shader.ForwardDirectionalLightShader;
import engine.rendering.shader.Shader;

public class DirectionLight extends BaseLight
{
	private Vector3 direction;
    private static Shader shader;

    public DirectionLight()
    {
        super();
        this.direction=new Vector3(1,1,1);
    }

	public DirectionLight(Colour colour,float intensity,Vector3 direction)
	{
        super(colour,intensity);
		this.direction=direction;
	}

    public void create(Entity entity)
    {
        entity.theatre().environment().addLight(this);
    }

	public Vector3 direction() {
		return direction;
	}

	public void direction(Vector3 direction) {
		this.direction = direction;
	}

    public Shader shader()
    {
        if(shader==null)
        {
            shader=new ForwardDirectionalLightShader();
        }

        return shader;
    }
	
}
