package engine.rendering.shader;

import engine.entity.Entity;
import engine.entity.component.Camera;
import engine.rendering.Material;

public class ForwardAmbientShader extends Shader
{
    public ForwardAmbientShader()
    {
        addVertexShader(Shader.loadShader("ambient.vert"));
        addFragmentShader(Shader.loadShader("ambient.frag"));

        compile();

        addUniform("projTrans");
        addUniform("ambientColour");
    }

    public void update(Camera camera,Entity entity,Material material,boolean orthogonal)
    {
        material.texture().bind();
        if(!orthogonal)
        {
            setUniform("projTrans",entity.transform().combined(camera));
        }
        else
        {
            setUniform("projTrans",camera.orthogonal());
        }

        setUniform("ambientColour", entity.theatre().environment().ambientLightColour());
    }
}
