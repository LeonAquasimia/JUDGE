package engine.util;

import engine.geometry.Vertex;
import engine.math.Matrix4;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

public class Util
{
	public static IntBuffer createIntBuffer(int size)
	{
		return BufferUtils.createIntBuffer(size);
	}
	
	public static FloatBuffer createFloatBuffer(int size)
	{
		return BufferUtils.createFloatBuffer(size);
	}
	
	public static IntBuffer createFlippedBufferI(ArrayList<Integer> indices)
	{
		IntBuffer buffer=createIntBuffer(indices.size());
		
		buffer.put(toIntArray(indices));
		buffer.flip();
		
		return buffer;
	}
	
	public static FloatBuffer createFlippedBufferF(ArrayList<Vertex> vertices)
	{
		FloatBuffer buffer=createFloatBuffer(vertices.size()*Vertex.size);
		
		for (Vertex vertex:vertices)
		{
			buffer.put(vertex.position().x());
			buffer.put(vertex.position().y());
			buffer.put(vertex.position().z());
			buffer.put(vertex.texCoord().x());
			buffer.put(vertex.texCoord().y());
			buffer.put(vertex.normal().x());
			buffer.put(vertex.normal().y());
			buffer.put(vertex.normal().z());
		}
		
		buffer.flip();
		
		return buffer;
	}
	
	public static FloatBuffer createFlippedBufferM(Matrix4 matrix)
	{
		FloatBuffer buffer=createFloatBuffer(4*4);
		
		for (int i=0;i<4;i++)
		for (int i2=0;i2<4;i2++)
		{
			buffer.put(matrix.get(i,i2));
		}
		
		buffer.flip();
		
		return buffer;
	}

	public static int[] toIntArray(ArrayList<Integer> integers)
	{
		int[] array=new int[integers.size()];
		
		for (int i=0;i<integers.size();i+=1)
		{
			array[i]=integers.get(i).intValue();
		}
		
		return array;
	}
}
