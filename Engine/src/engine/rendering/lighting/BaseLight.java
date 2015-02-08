package engine.rendering.lighting;

import engine.rendering.Colour;

public class BaseLight
{
	private Colour colour;
	private float intensity;
	
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
	
	
}
