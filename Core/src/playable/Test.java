package playable;

import engine.core.Theatre;
import engine.entity.Entity;
import engine.entity.component.MeshRenderer;
import engine.geometry.Mesh;
import engine.geometry.Vertex;
import engine.math.Vector2;
import engine.math.Vector3;
import engine.entity.component.Camera;
import engine.rendering.Colour;
import engine.rendering.Material;
import engine.rendering.Texture;

import java.util.ArrayList;

public class Test extends Theatre
{
    //Mesh mesh;
    private Material material;

    public void start()
    {
        //camera=new Camera(800,600,60,0.1f,1000);


        //mesh=new Mesh();
        ArrayList<Vertex> vertices=new ArrayList<>();
        vertices.add(new Vertex(new Vector3(-0.5f,0,-0.5f),new Vector2(0,4)));
        vertices.add(new Vertex(new Vector3(0.5f,0,-0.5f),new Vector2(4,4)));
        vertices.add(new Vertex(new Vector3(0,0,0.5f),new Vector2(4,4)));
        vertices.add(new Vertex(new Vector3(0,1,0),new Vector2(2,0)));

        ArrayList<Integer> indices=new ArrayList<>();
        indices.add(3);
        indices.add(1);
        indices.add(0);

        indices.add(3);
        indices.add(2);
        indices.add(1);

        indices.add(3);
        indices.add(0);
        indices.add(2);

        indices.add(0);
        indices.add(1);
        indices.add(2);
        //mesh.addVertices(vertices,indices);
        material=new Material(new Colour(1,1,1,1), Texture.loadTexture("Test2.png"),new Vector2(8,128));

        Entity entity=new Entity();

        entity.addComponent(new MeshRenderer(new Mesh().addVertices(vertices,indices),material));
        entity.transform().position(0,0,10);

        Entity entity2=new Entity();
        entity2.addComponent(new MeshRenderer(new Mesh().addVertices(vertices,indices),material));
        entity2.transform().position(1,0,10);

        Entity entity3=new Entity();
        entity3.addComponent(new MeshRenderer(new Mesh().addVertices(vertices,indices),material));
        entity3.transform().position(-1,0,10);

        create(entity);
        create(entity2);
        create(entity3);

        //entity.addComponent(new OverlayTest(new Mesh().addVertices(vertices,indices),material));

        Entity camera=new Entity();
        Camera com=new Camera(800,600,30,0.1f,1000);
        com.yaw(-40);
        camera.addComponent(com);
        camera.transform().position(5,1,5);

        create(camera);

        environment.ambientLightColour(new Colour(1,0,0,1));

        System.out.println("What the deuce!");
    }
}