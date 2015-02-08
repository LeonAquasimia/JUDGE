package engine.entity;

import engine.core.Transform;
import engine.entity.component.Component;
import engine.entity.component.Camera;
import engine.rendering.shader.Shader;

import java.util.ArrayList;

public class Entity
{
    private ArrayList<Entity> children;
    private ArrayList<Component> components;
    private Entity parent=null;
    private Transform transform;

    public Entity()
    {
        children=new ArrayList<>();
        components=new ArrayList<>();
        transform=new Transform();
    }

    public Entity addChild(Entity child)
    {
        child.parent=this;
        children.add(child);

        return this;
    }

    public Entity removeChild(Entity child)
    {
        if(children.contains(child))
        children.remove(child);
        else
        System.out.println(this+" does not contain child "+child+"!");

        return this;
    }

    public Entity addComponent(Component component)
    {
        component.entity=this;
        components.add(component);

        return this;
    }

    public Entity parent()
    {
        return parent;
    }

    public void parent(Entity parent)
    {
        this.parent = parent;
    }

    public Transform transform()
    {
        return transform;
    }

    public void transform(Transform transform)
    {
        this.transform = transform;
    }

    public void create()
    {
        for(Component component:components)
        {
            component.create(this);
        }

        for(Entity child:children)
        {
            child.create();
        }
    }

    public void destroy()
    {
        for(Component component:components)
        {
            component.destroy(this);
        }

        for(Entity child:children)
        {
            child.destroy();
        }
    }

    public void input(float delta)
    {
        for(Component component:components)
        {
            component.input(delta,this);
        }

        for(Entity child:children)
        {
            child.input(delta);
        }
    }

    public void update(float delta)
    {
        for(Component component:components)
        {
            component.update(delta,this);
        }

        for(Entity child:children)
        {
            child.update(delta);
        }
    }

    public void render(Camera camera, Shader shader)
    {
        for(Component component:components)
        {
            component.render(camera,shader,this);
        }

        for(Entity child:children)
        {
            child.render(camera,shader);
        }
    }

    public void draw(Camera camera, Shader shader)
    {
        for(Component component:components)
        {
            component.draw(camera,shader,this);
        }

        for(Entity child:children)
        {
            child.draw(camera,shader);
        }
    }


}
