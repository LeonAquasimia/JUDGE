package engine.rendering.shader;

import engine.core.Transform;
import engine.entity.component.Camera;
import engine.rendering.Material;

public class ShaderDefault extends Shader
{
	public ShaderDefault()
	{
		addVertexShader(Shader.loadShader("default.vert"));
		addFragmentShader(Shader.loadShader("default.frag"));
		
		compile();
		
		addUniform("transform");
		addUniform("colour");
	}
	
	public void update(Camera camera,Transform transform,Material material,boolean orthogonal)
	{
		material.texture().bind();
		if(!orthogonal)
        {
            setUniform("transform",transform.combined(camera));
        }
        else
        {
            setUniform("transform",camera.orthogonal());
        }

		setUniform("colour",material.colour());
	}
}
