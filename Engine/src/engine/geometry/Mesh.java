package engine.geometry;

import engine.math.Vector2;
import engine.math.Vector3;
import engine.util.Grama;
import engine.util.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class Mesh
{
	private int vbo;
	private int ibo;
	private int size;
	
	public Mesh()
	{
		vbo=glGenBuffers();
		ibo=glGenBuffers();
		size=0;
	}
	
	public Mesh addVertices(ArrayList<Vertex> vertices,ArrayList<Integer> indices)
	{
		addVertices(vertices,indices,false);
        return this;
	}
	
	public void addVertices(ArrayList<Vertex> vertices,ArrayList<Integer> indices, boolean calcNormals)
	{
		size=indices.size();
		
		if(calcNormals)
		{
			calcNormals(vertices,indices);
		}
		
		glBindBuffer(GL_ARRAY_BUFFER,vbo);
		glBufferData(GL_ARRAY_BUFFER,Util.createFlippedBufferF(vertices),GL_STATIC_DRAW);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER,Util.createFlippedBufferI(indices),GL_STATIC_DRAW);
	}
	
	private void calcNormals(ArrayList<Vertex> vertices,ArrayList<Integer> indices)
	{
		for (int i=0;i<indices.size();i+=3)
		{
			int i0=indices.get(i);
			int i1=indices.get(i+1);
			int i2=indices.get(i+2);
			
			Vector3 v1=vertices.get(i1).position().sub(vertices.get(i0).position());
			Vector3 v2=vertices.get(i2).position().sub(vertices.get(i0).position());
			
			Vector3 normal=v1.cross(v2).normalised();
			
			vertices.get(i0).normal(vertices.get(i0).normal().add(normal));
            vertices.get(i1).normal(vertices.get(i1).normal().add(normal));
            vertices.get(i2).normal(vertices.get(i2).normal().add(normal));
		}
		
		for (int i=0;i<vertices.size();i++)
		{
			vertices.get(i).normal(vertices.get(i).normal().normalised());
		}
	}
	
	public void render()
	{
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
		
		glBindBuffer(GL_ARRAY_BUFFER,vbo);
		glVertexAttribPointer(0,3,GL_FLOAT,false,Vertex.size*4,0);
		glVertexAttribPointer(1,2,GL_FLOAT,false,Vertex.size*4,12);
		glVertexAttribPointer(2,3,GL_FLOAT,false,Vertex.size*4,20);

		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ibo);
		glDrawElements(GL_TRIANGLES,size,GL_UNSIGNED_INT,0);
		
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(2);
		
	}

    public static Mesh loadMesh(String fileName)
    {
        String[] split=fileName.split("\\.");

        String mask=split[split.length-1];

        if(mask.equals("obj"))
        {
            ArrayList<Vertex> vertices=new ArrayList<Vertex>();
            ArrayList<Integer> indices=new ArrayList<Integer>();

            BufferedReader meshReader=null;

            try
            {
                meshReader=new BufferedReader(new FileReader("./Core/res/models/"+fileName));

                String line;
                while((line=meshReader.readLine())!=null)
                {
                    String[] tokens=line.split(" ");
                    tokens= Grama.removeEmptyStrings(tokens);

                    if(tokens.length==0 || tokens[0].equals("#")) continue;
                    else
                    if(tokens[0].equals("v"))
                    {
                        vertices.add(new Vertex(new Vector3(
                                Float.valueOf(tokens[1]),
                                Float.valueOf(tokens[2]),
                                Float.valueOf(tokens[3]))));
                    }
                    else
                    if(tokens[0].equals("vt"))
                    {

                    }
                    else
                    if(tokens[0].equals("f"))
                    {
                        indices.add(Integer.parseInt(tokens[1].split("/")[0])-1);
                        indices.add(Integer.parseInt(tokens[2].split("/")[0])-1);
                        indices.add(Integer.parseInt(tokens[3].split("/")[0])-1);
                    }
                }

                meshReader.close();

                Mesh mesh=new Mesh();

                mesh.addVertices(vertices,indices);

                return mesh;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.exit(1);
            }
        }
        else
        {
            System.err.println(mask+" is not a proper mesh file extension, faggot.");
            new Exception().printStackTrace();
            System.exit(1);
        }

        return null;
    }

    public static Mesh loadOBJFormat(String fileName)
    {
        ArrayList<Vector3> positions=new ArrayList<>();
        ArrayList<Vector2> uvs=new ArrayList<>();
        ArrayList<Vector3> normals=new ArrayList<>();

        ArrayList<Integer> indicesp=new ArrayList<>();
        ArrayList<Integer> indicesuv=new ArrayList<>();
        ArrayList<Integer> indicesn=new ArrayList<>();




        String line;
        try
        {
            BufferedReader meshReader=new BufferedReader(new FileReader("./res/models/"+fileName));

            while((line=meshReader.readLine())!=null)
            {
                String[] tokens=line.split(" ");
                tokens=Grama.removeEmptyStrings(tokens);

                if(tokens.length==0 || tokens[0].equals("#")) continue;
                else
                if(tokens[0].equals("v"))
                {
                    positions.add(new Vector3(Float.valueOf(tokens[1]),Float.valueOf(tokens[2]),Float.valueOf(tokens[3])));
                }
                else
                if(tokens[0].equals("vt"))
                {
                    uvs.add(new Vector2(Float.valueOf(tokens[1]),Float.valueOf(tokens[2])));
                }
                else
                if(tokens[0].equals("vn"))
                {
                    normals.add(new Vector3(Float.valueOf(tokens[1]),Float.valueOf(tokens[2]),Float.valueOf(tokens[3])));
                }
                else
                if(tokens[0].equals("f"))
                {
                    for (int i=0;i<3;i++)
                    {
                        String[] temp=tokens[i+1].split("/");
                        indicesp.add(Integer.parseInt(temp[0])-1);
                        if (tokens.length>1)
                        {
                            indicesuv.add(Integer.parseInt(temp[1])-1);

                            if (tokens.length>2)
                            {
                                indicesn.add(Integer.parseInt(temp[2])-1);
                            }
                        }
                    }
                }
            }
            meshReader.close();

            Mesh mesh=new Mesh();
            ArrayList<Vertex> vertices=new ArrayList<>();
            //ArrayList<Integer> indics=new ArrayList<>();

            int i=0;

            for (Vector3 pos:positions)
            {
                vertices.add(new Vertex(pos,uvs.get(indicesuv.get(i))));
                i++;
            }

            mesh.addVertices(vertices,indicesp);

            return mesh;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
