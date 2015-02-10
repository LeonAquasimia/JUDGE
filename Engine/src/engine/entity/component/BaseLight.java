package engine.entity.component;

import engine.entity.component.Component;
import engine.rendering.Colour;
import engine.rendering.shader.Shader;

public abstract class BaseLight extends Component
{
    private Colour colour;
    private float intensity;

    public BaseLight()
    {
        colour=new Colour(0,0,0,1);
        intensity=0;
    }

    public BaseLight(Colour colour,float intensity)
    {
        this.colour=colour;
        this.intensity=intensity;
    }

    public Colour colour() {
        return colour;
    }

    public void colour(Colour colour) {
        this.colour = colour;
    }

    public float intensity() {
        return intensity;
    }

    public void intensity(float intensity) {
        this.intensity = intensity;
    }

    public Shader shader()
    {
        return null;
    }
}