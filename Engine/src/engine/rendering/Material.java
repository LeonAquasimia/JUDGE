package engine.rendering;

import engine.math.Vector2;

public class Material
{
	private Colour colour;
	private Texture texture;
    private Vector2 specularity;

	public Material(Colour colour,Texture texture,Vector2 specularity)
	{
		colour(colour);
		texture(texture);
        specularity(specularity);
	}

	public Colour colour()
	{
		return colour;
	}

	public void colour(Colour colour)
	{
		this.colour = colour;
	}

	public Texture texture()
	{
		return texture;
	}

	public void texture(Texture texture)
	{
		this.texture = texture;
	}

    public Vector2 specularity()
    {
        return specularity;
    }

    public void specularity(Vector2 specularity)
    {
        this.specularity=specularity;
    }

    public void specularity(float intensity,float power)
    {
        this.specularity=new Vector2(intensity,power);
    }
}
