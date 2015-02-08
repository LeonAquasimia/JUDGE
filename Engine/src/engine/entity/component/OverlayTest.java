package engine.entity.component;

import engine.entity.Entity;
import engine.geometry.Mesh;
import engine.rendering.Material;
import engine.rendering.shader.Shader;

/**
 * Created by Admin on 2/7/2015.
 */
public class OverlayTest extends Component
{
    private Mesh mesh;
    private Material material;

    public OverlayTest(Mesh mesh, Material material)
    {
        this.mesh = mesh;
        this.material = material;
    }

    public void draw(Camera camera, Shader shader, Entity entity)
    {
        shader.update(camera,entity.transform(),material,true);
        mesh.render();
    }
}
