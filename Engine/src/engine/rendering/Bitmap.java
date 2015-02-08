package engine.rendering;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Bitmap
{
    private int width;
    private int height;
    private int[] pixels;

    public Bitmap(String fileName)
    {
        try
        {
            BufferedImage image = ImageIO.read(new File(fileName));

            width=image.getWidth();
            height=image.getHeight();
            pixels=new int[width*height];
            pixels=image.getRGB(0, 0, width, height, pixels, 0, width);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Bitmap(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.pixels = new int[width*height];
    }

    public int width()
    {
        return width;
    }

    public int height()
    {
        return height;
    }

    public int[] pixels()
    {
        return pixels;
    }

    public int pixel(int x,int y)
    {
        return pixels[x+y*width];
    }

    public void pixel(int x,int y,int value)
    {
        pixels[x+y*width]=value;
    }
}
