package engine.rendering.shader;

import engine.entity.Entity;
import engine.entity.component.Camera;
import engine.entity.component.PointLight;
import engine.entity.component.SpotLight;
import engine.math.Vector3;
import engine.rendering.Colour;
import engine.rendering.Material;

public class ForwardSpotLightShader extends Shader
{
    public ForwardSpotLightShader()
	{
        //setCompilerVariable("maxDirectionLights",maxDirectionLights);

        light=new SpotLight(new Colour(0,0,0,1),0f,new Vector3(1,1,1),100,new Vector3(1,1,1),1);

		addVertexShader(Shader.loadShader("spotLight.vert"));
		addFragmentShader(Shader.loadShader("spotLight.frag"));
		
		compile();

        //Note to self: Look into specular lighting system. It seems broken.

		addUniform("transform");
		addUniform("projected");
        addUniform("specularity");
        addUniform("eyePos");

        addUniform("spotLight.point.base.colour");
        addUniform("spotLight.point.base.intensity");
        addUniform("spotLight.point.position");
        addUniform("spotLight.point.attenuation");
        addUniform("spotLight.point.range");
        addUniform("spotLight.direction");
        addUniform("spotLight.cutOff");
	}
	
	public void update(Camera camera,Entity entity,Material material,boolean orthogonal)
	{
		material.texture().bind();

		setUniform("transform",entity.transform().transformation());
		setUniform("projected", camera.project());
        setUniform("specularity",material.specularity());
        setUniform("eyePos",camera.position());
        setUniform("spotLight",(SpotLight)light);
	}
}
