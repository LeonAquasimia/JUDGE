package engine.entity.component;

import engine.entity.Entity;
import engine.geometry.Mesh;
import engine.rendering.Material;
import engine.rendering.shader.Shader;

public class MeshRenderer extends Component
{
    private Mesh mesh;
    private Material material;

    public MeshRenderer(Mesh mesh, Material material)
    {
        this.mesh = mesh;
        this.material = material;
    }

    public void render(Camera camera, Shader shader, Entity entity)
    {
        shader.update(camera,entity.transform(),material,false);
        mesh.render();
    }
}
