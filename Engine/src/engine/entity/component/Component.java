package engine.entity.component;

import engine.entity.Entity;
import engine.rendering.shader.Shader;

public abstract class Component
{
    public Entity entity;

    public void create(Entity entity){}
    public void destroy(Entity entity){}
    public void input(float delta, Entity entity){}
    public void render(Camera camera, Shader shader, Entity entity){}
    public void draw(Camera camera, Shader shader, Entity entity){}
    public void update(float delta, Entity entity){}
}
