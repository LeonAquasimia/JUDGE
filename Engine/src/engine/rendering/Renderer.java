package engine.rendering;

import engine.core.Theatre;
import engine.entity.component.*;
import engine.rendering.shader.*;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Admin on 2/7/2015.
 */
public class Renderer
{
    static ForwardAmbientShader ambientShader;
    static Shader currentShader;

    public static void render(Theatre theatre)
    {
        clearScreen();

        if(ambientShader==null)
        {
            ambientShader=new ForwardAmbientShader();
        }

        glEnable(GL_DEPTH_TEST);
        for(Camera camera:Camera.cameras)
        {
            ambientShader.bind(null);
            theatre.root.render(camera,ambientShader);

            glEnable(GL_BLEND);
            glBlendFunc(GL_ONE,GL_ONE);
            glDepthMask(false);
            glDepthFunc(GL_EQUAL);

            for(BaseLight light:theatre.environment().lights)
            {
                currentShader=light.shader();
                currentShader.bind(light);

                //System.out.println(currentShader);

                theatre.root.render(camera,currentShader);
            }

            glDepthFunc(GL_LESS);
            glDepthMask(true);
            glDisable(GL_BLEND);
        }

        /*

        */

        glDisable(GL_DEPTH_TEST);
        for(Camera camera:Camera.cameras)
        {
            theatre.root.draw(camera,theatre.shader);
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
