package engine.rendering.shader;

import engine.entity.Entity;
import engine.entity.component.Camera;
import engine.math.Vector3;
import engine.rendering.Colour;
import engine.rendering.Material;
import engine.entity.component.DirectionLight;

public class ForwardDirectionalLightShader extends Shader
{
    public ForwardDirectionalLightShader()
	{
        //setCompilerVariable("maxDirectionLights",maxDirectionLights);

        light=new DirectionLight(new Colour(1,1,1,1),1f,new Vector3(1,1,1));

		addVertexShader(Shader.loadShader("directionLight.vert"));
		addFragmentShader(Shader.loadShader("directionLight.frag"));
		
		compile();

        //Note to self: Look into specular lighting system. It seems broken.



		addUniform("transform");
		addUniform("projected");
        addUniform("specularity");
        addUniform("eyePos");

        addUniform("directionLight.base.colour");
        addUniform("directionLight.base.intensity");
        addUniform("directionLight.direction");
	}
	
	public void update(Camera camera,Entity entity,Material material,boolean orthogonal)
	{
		material.texture().bind();

		setUniform("transform",entity.transform().transformation());
		setUniform("projected", camera.project());
        setUniform("specularity",material.specularity());
        setUniform("eyePos",camera.position());
        //entity.theatre().environment().directionLights.toArray(directionLights)
        setUniform("directionLight",(DirectionLight)light);
	}
}
