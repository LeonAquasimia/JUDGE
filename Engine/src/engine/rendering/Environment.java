package engine.rendering;

import engine.entity.component.BaseLight;
import engine.entity.component.DirectionLight;
import engine.entity.component.PointLight;
import engine.entity.component.SpotLight;

import java.util.ArrayList;

public class Environment
{
	private Colour ambientLightColour;
    public ArrayList<BaseLight> lights=new ArrayList<>();
	
	public Environment()
	{
		ambientLightColour=new Colour(0.005f,0.01f,0.02f,1);//new Colour(0,0.0025f,0.005f,1);
	}

    public void addLight(BaseLight light)
    {
        lights.add(light);
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
