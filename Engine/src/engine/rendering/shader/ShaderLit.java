package engine.rendering.shader;

import engine.core.Transform;
import engine.math.Vector3;
import engine.entity.component.Camera;
import engine.rendering.Colour;
import engine.rendering.Material;
import engine.rendering.lighting.BaseLight;
import engine.rendering.lighting.DirectionLight;
import engine.rendering.lighting.PointLight;
import engine.rendering.lighting.SpotLight;

public class ShaderLit extends Shader
{
    public int maxDirectionLights=2;
    public int maxPointLights=4;
    public int maxSpotLights=4;

	public DirectionLight[] directionLights=new DirectionLight[]{};
    public PointLight[] pointLights=new PointLight[]{};
    public SpotLight[] spotLights=new SpotLight[]{};

	public ShaderLit()
	{
        setCompilerVariable("maxDirectionLights",maxPointLights);
        setCompilerVariable("maxPointLights",maxPointLights);
        setCompilerVariable("maxSpotLights",maxSpotLights);

        directionLights=new DirectionLight[]{ new DirectionLight(new BaseLight(new Colour(1,1,1,1),0.5f),new Vector3(1,1,1))};

		addVertexShader(Shader.loadShader("lit.vert"));
		addFragmentShader(Shader.loadShader("lit.frag"));
		
		compile();

        //Note to self: Look into specular lighting system. It seems broken.



		addUniform("transform");
		addUniform("projected");
		addUniform("baseColour");
		addUniform("ambientLight");
        addUniform("specularity");
        addUniform("eyePos");

        for(int i=0;i<maxDirectionLights;i+=1)
        {
            addUniform("directionLights["+i+"].base.colour");
            addUniform("directionLights["+i+"].base.intensity");
            addUniform("directionLights["+i+"].direction");
        }

        for(int i=0;i<maxPointLights;i+=1)
        {
            addUniform("pointLights["+i+"].base.colour");
            addUniform("pointLights["+i+"].base.intensity");
            addUniform("pointLights["+i+"].position");
            addUniform("pointLights["+i+"].attenuation");
            addUniform("pointLights["+i+"].range");
        }

        for(int i=0;i<maxSpotLights;i+=1)
        {
            addUniform("spotLights["+i+"].point.base.colour");
            addUniform("spotLights["+i+"].point.base.intensity");
            addUniform("spotLights["+i+"].point.position");
            addUniform("spotLights["+i+"].point.attenuation");
            addUniform("spotLights["+i+"].point.range");
            addUniform("spotLights["+i+"].direction");
            addUniform("spotLights["+i+"].cutOff");
        }
	}
	
	public void update(Camera camera,Transform transform,Material material)
	{
		material.texture().bind();
		
		setUniform("transform",transform.transformation());
		setUniform("projected", camera.project());
		setUniform("baseColour",material.colour());
        setUniform("specularity",material.specularity());
        setUniform("eyePos",camera.position());

        setUniform("ambientLight", Shader.environment.ambientLightColour());

        setUniform("pointLights",pointLights);
        setUniform("spotLights",spotLights);
        setUniform("directionLights",directionLights);
	}
}
