package engine.core;

import engine.entity.Entity;
import engine.rendering.Environment;
import engine.rendering.shader.ForwardAmbientShader;
import engine.rendering.shader.Shader;
import engine.rendering.shader.NullShader;

public class Theatre
{
	private Environment environment=new Environment();

    protected Entity root;
    protected Shader shader;

    protected Theatre()
    {
        Engine engine=new Engine("Playable",800,600,50000,this);
        engine.start();
    }

    public void init()
    {
        root=new Entity(this);
        root.create();
    }

    public void start()
    {

    }

    public void create(Entity entity)
    {
        root.addChild(entity);
        entity.create();
    }

    public void destroy(Entity entity)
    {
        root.removeChild(entity);
        entity.destroy();
    }

	public void input(float delta)
	{
        root.input(delta);
	}

	public void update(float delta)
	{
        root.update(delta);
	}

    public Environment environment()
    {
        return environment;
    }

    public void environment(Environment environment) {
        this.environment = environment;
    }
}
