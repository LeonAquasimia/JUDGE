package engine.core;

import engine.entity.component.Camera;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Admin on 2/7/2015.
 */
public class Renderer
{
    public static void render(Theatre theatre)
    {
        clearScreen();

        glEnable(GL_DEPTH_TEST);
        for(Camera camera:Camera.cameras)
        {
            theatre.root.render(camera, theatre.shader);
        }

        glDisable(GL_DEPTH_TEST);
        for(Camera camera:Camera.cameras)
        {
            theatre.root.draw(camera, theatre.shader);
        }
    }

    public static void clearScreen()
    {
        //Do stencil bullshit later
        glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
    }

    public static void setTextures(boolean enable)
    {
        if(enable)
            glEnable(GL_TEXTURE_2D);
        else
            glDisable(GL_TEXTURE_2D);
    }

    public static void clearTexture()
    {
        glBindTexture(GL_TEXTURE_2D,0);
    }

    public static void init()
    {
        glClearColor(0,0,0,1);

        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);

        //Do the depth clamp thingy at some point
        //glEnable(GL_DEPTH_CLAMP);
        glEnable(GL_TEXTURE_2D);
        //glEnable(GL_FRAMEBUFFER_SRGB);
    }

    public static String getGLVersion()
    {
        return glGetString(GL_VERSION);
    }
}
