package playable;

import engine.core.Theatre;
import engine.entity.Entity;
import engine.entity.component.*;
import engine.geometry.Mesh;
import engine.geometry.Vertex;
import engine.math.Vector2;
import engine.math.Vector3;
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

        ArrayList<Integer> indices2=new ArrayList<>();
        indices2.add(2);
        indices2.add(1);
        indices2.add(0);
        //mesh.addVertices(vertices,indices);
        material=new Material(new Colour(1,1,1,1), Texture.loadTexture("Test2.png"),new Vector2(8,128));

        Entity entity=new Entity();

        entity.addComponent(new MeshRenderer(new Mesh().addVertices(vertices,indices,true),material));
        entity.transform().position(0,0,10);

        Entity entity2=new Entity();
        entity2.addComponent(new MeshRenderer(new Mesh().addVertices(vertices,indices,true),material));
        entity2.transform().position(1,0,10);

        Entity entity3=new Entity();
        entity3.addComponent(new MeshRenderer(new Mesh().addVertices(vertices,indices,true),material));
        entity3.transform().position(-1,0,10);

        Entity floor=new Entity();

        floor.addComponent(new MeshRenderer(new Mesh().addVertices(vertices,indices2,true),material));
        floor.transform().position(0,0,0);
        floor.transform().scale(100,10,100);


        create(entity);
        create(entity2);
        create(entity3);
        create(floor);

        entity.addComponent(new PointLight(new Colour(1,1,0,1),5f,new Vector3(0,0,1),5));
        entity2.addComponent(new PointLight(new Colour(0,1,1,1),5,new Vector3(0,0,1),5));
        entity3.addComponent(new SpotLight(new Colour(1,0,1,1),400,new Vector3(0,0,1),5,new Vector3(0,1,0),0)).addComponent(new CamMoveTest());

        //entity.addComponent(new OverlayTest(new Mesh().addVertices(vertices,indices),material));

        Entity camera=new Entity();
        Camera com=new Camera(800,600,90,0.1f,1000);
        com.yaw(0);
        camera.addComponent(com);
        camera.transform().position(0, 1, 5);
        //camera.addComponent(new CamMoveTest());
        camera.addComponent(new DirectionLight(new Colour(0,1,0,1),1f,new Vector3(1,1,1)));

        create(camera);

        environment().ambientLightColour(new Colour(0.05f,0.05f,0.05f,1));

        System.out.println("What the deuce!");
    }
}