package engine.rendering.shader;

import engine.entity.Entity;
import engine.entity.component.Camera;
import engine.math.Vector3;
import engine.rendering.Colour;
import engine.rendering.Material;
import engine.entity.component.PointLight;

public class ForwardPointLightShader extends Shader
{
    public ForwardPointLightShader()
	{
        //setCompilerVariable("maxDirectionLights",maxDirectionLights);

        light=new PointLight(new Colour(0,0,0,1),0f,new Vector3(1,1,1),100);

		addVertexShader(Shader.loadShader("pointLight.vert"));
		addFragmentShader(Shader.loadShader("pointLight.frag"));
		
		compile();

        //Note to self: Look into specular lighting system. It seems broken.

		addUniform("transform");
		addUniform("projected");
        addUniform("specularity");
        addUniform("eyePos");

        addUniform("pointLight.base.colour");
        addUniform("pointLight.base.intensity");
        addUniform("pointLight.position");
        addUniform("pointLight.attenuation");
        addUniform("pointLight.range");
	}
	
	public void update(Camera camera,Entity entity,Material material,boolean orthogonal)
	{
		material.texture().bind();

		setUniform("transform",entity.transform().transformation());
		setUniform("projected", camera.project());
        setUniform("specularity",material.specularity());
        setUniform("eyePos",camera.position());
        setUniform("pointLight",(PointLight)light);
	}
}
