package engine.rendering;

public class Environment
{
	private Colour ambientLightColour;
	
	public Environment()
	{
		ambientLightColour=new Colour(0.005f,0.01f,0.02f,1);//new Colour(0,0.0025f,0.005f,1);
	}

	public Colour ambientLightColour()
	{
		return ambientLightColour;
	}

	public void ambientLightColour(Colour ambientLightColour)
	{
		this.ambientLightColour = ambientLightColour;
	}
}
