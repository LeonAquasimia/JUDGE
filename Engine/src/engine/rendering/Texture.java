package engine.rendering;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;

public class Texture
{
	private int id,width,height;
	
	public Texture(int id,int width,int height)
	{
		this.id=id;
        this.width=width;
        this.height=height;
	}
	
	public void bind()
	{
		glBindTexture(GL_TEXTURE_2D,id);
	}
	
	private int id()
	{
		return id;
	}

    public int height()
    {
        return height;
    }

    public void height(int height)
    {
        this.height = height;
    }

    public int width()
    {
        return width;
    }

    public void width(int width)
    {
        this.width = width;
    }

    public static Texture loadTexture(String fileName)
    {
        try
        {
            String[] split=fileName.split("\\.");
            String mask=split[split.length-1];

            File file=new File("./Core/res/textures/" + fileName);

            System.out.println(file.getAbsolutePath());

            org.newdawn.slick.opengl.Texture tex= TextureLoader.getTexture(mask, new FileInputStream(file), GL11.GL_NEAREST);

            int id=tex.getTextureID();
            return new Texture(id,tex.getTextureWidth(),tex.getTextureHeight());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }
}
