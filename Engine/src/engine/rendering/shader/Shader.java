package engine.rendering.shader;

import engine.entity.Entity;
import engine.entity.component.*;
import engine.math.Matrix4;
import engine.math.Vector2;
import engine.math.Vector3;
import engine.rendering.Colour;
import engine.rendering.Material;
import engine.util.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER;

public abstract class Shader
{
    public static Shader instance;
    public BaseLight light;

    private int program;
    private HashMap<String, Integer> uniformMap = new HashMap<String, Integer>();
    private HashMap<String, Object> compvarMap = new HashMap<String, Object>();

    public Shader()
    {
        program = glCreateProgram();

        if (program == 0)
        {
            System.err.println("Shader's fucked.");
            System.exit(1);
        }
    }

    public void bind(BaseLight light)
    {
        this.light=light;
        instance=this;
        glUseProgram(program);
    }

    public void update(Camera camera,Entity entity,Material material,boolean orthogonal)
    {

    }

    public void setCompilerVariable(String name,Object value)
    {
        compvarMap.put(name,value);
    }

	public void addUniform(String uniform)
	{
		int uniformLocation=glGetUniformLocation(program,uniform);
		
		if(uniformLocation==-1)
		{
			System.err.println("Shader's fucked. Uniform "+uniform+" is probably spelt wrong, dipshit.");
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		uniformMap.put(uniform,uniformLocation);
	}
	
	public void setUniform(String uniform,int value)
	{		
		glUniform1i(uniformMap.get(uniform),value);		
	}
	
	public void setUniform(String uniform,float value)
	{
		glUniform1f(uniformMap.get(uniform),value);		
	}
	
	public void setUniform(String uniform,Vector3 value)
	{
		glUniform3f(uniformMap.get(uniform),value.x(),value.y(),value.z());
	}

    public void setUniform(String uniform,Vector2 value)
    {
        glUniform2f(uniformMap.get(uniform), value.x(), value.y());
    }
	
	public void setUniform(String uniform,Colour colour)
	{
		glUniform4f(uniformMap.get(uniform),colour.r(),colour.g(),colour.b(),colour.a());		
	}
	
	public void setUniform(String uniform,Matrix4 value)
	{
		glUniformMatrix4(uniformMap.get(uniform), true, Util.createFlippedBufferM(value));
	}

    public void setUniform(String uniform,BaseLight baseLight)
    {
        setUniform(uniform + ".intensity", baseLight.intensity());
        setUniform(uniform + ".colour", baseLight.colour());
    }

    public void setUniform(String uniform,DirectionLight directionLight)
    {
        setUniform(uniform+".base",(BaseLight)directionLight);
        setUniform(uniform+".direction", directionLight.direction());
    }

    public void setUniform(String uniform,PointLight pointLight)
    {
        setUniform(uniform + ".base",(BaseLight)pointLight);
        setUniform(uniform+".attenuation", pointLight.attenuation());
        setUniform(uniform+".position", pointLight.position());
        setUniform(uniform+".range", pointLight.range());
    }

    public void setUniform(String uniform,SpotLight spotLight)
    {
        setUniform(uniform+".point",(PointLight)spotLight);
        //setUniform(uniform+".point.attenuation", spotLights[i].point().attenuation());
        //setUniform(uniform+".point.range", spotLights[i].point().range());
        setUniform(uniform+".direction",spotLight.direction());
        setUniform(uniform+".cutOff",spotLight.cutOff());
    }
	
	public void addGeometryShader(String string)
	{
		addProgram(parseShader(string),GL_GEOMETRY_SHADER);
	}

    public void addVertexShader(String string)
	{
		addProgram(parseShader(string),GL_VERTEX_SHADER);
	}
	
	public void addFragmentShader(String string)
	{
		addProgram(parseShader(string),GL_FRAGMENT_SHADER);
	}

    private String parseShader(String string)
    {
        Iterator<Map.Entry<String,Object>> iterator=compvarMap.entrySet().iterator();
        String temp=string;

        while(iterator.hasNext())
        {
            Map.Entry<String,Object> entry=iterator.next();

            temp=temp.replaceAll("\\$"+entry.getKey(),""+entry.getValue());
        }

        //System.out.println(temp);

        return temp;
    }
	
	public void compile()
	{
		glLinkProgram(program);
		
		if(glGetProgrami(program,GL_LINK_STATUS)==0)
		{
			System.err.println(glGetShaderInfoLog(program,1024));
			System.exit(1);
		}
		
		glValidateProgram(program);
		
		if(glGetProgrami(program,GL_VALIDATE_STATUS)==0)
		{
			System.err.println(glGetShaderInfoLog(program,1024));
			System.exit(1);
		}
	}
	
	private void addProgram(String string,int type)
	{
		int shader=glCreateShader(type);
		
		if(shader==0)
		{
			System.err.println("Shader's fucked.");
			System.exit(1);
		}
		
		glShaderSource(shader,string);
		glCompileShader(shader);
		
		if(glGetShaderi(shader,GL_COMPILE_STATUS)==0)
		{
			System.err.println(glGetShaderInfoLog(shader,1024));
			System.exit(1);
		}
		
		glAttachShader(program,shader);
	}

    public static String loadShader(String fileName)
    {
        StringBuilder shaderSource=new StringBuilder();
        BufferedReader shaderReader=null;


        try
        {
            shaderReader=new BufferedReader(new FileReader("./Core/res/shaders/"+fileName));

            String line;
            while((line=shaderReader.readLine())!=null)
            {
                shaderSource.append(line + "\n");
            }

            shaderReader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        return shaderSource.toString();
    }
}
