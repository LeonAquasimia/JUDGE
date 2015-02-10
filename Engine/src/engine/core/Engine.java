package engine.core;

import engine.input.Input;
import engine.rendering.Renderer;
import engine.util.Time;

public class Engine
{
	private int width=800,height=600;
    private double frameTime=1.0/5000.0;
    private boolean isRunning;
    public static Theatre theatre;
    private String title="Engine";

    public Engine(String title,int width, int height,int frameCap,Theatre theatre)
    {
        this.title=title;
        this.width=width;
        this.height=height;
        this.frameTime=1.0/(double)frameCap;
        this.theatre = theatre;
    }

	public void start()
	{
		if(!isRunning)
		{
			isRunning=true;
            Window.createWindow(width,height,title);
            System.out.println(Renderer.getGLVersion());
			Renderer.init();
            Input.init();
            theatre.init();
			run();
		}		
	}
	
	public void stop()
	{
		if(isRunning)
		{
			isRunning=false;
		}
	}
	
	private void run()
	{
		double lastTime= Time.second();
		double unprocessedTime=0;
		double frameCounter=0;
		int frames=0;

        theatre.start();

		while (isRunning)
		{
			boolean render=false;
			
			if(Window.isCloseRequested())
			stop();		
				
			double startTime=Time.second();
			double passedTime=startTime-lastTime;
			lastTime=startTime;
			
			unprocessedTime+=passedTime;
			frameCounter+=passedTime;
			
			while (unprocessedTime>frameTime)
			{
				render=true;
				unprocessedTime-=frameTime;
				
				//Time.delta(frameTime);

                theatre.input((float)frameTime);
                Input.update();
                theatre.update((float)frameTime);

				if(frameCounter>=1.0)
				{
					System.out.println(frames);
					frames=0;
					frameCounter=0;
				}
			}
			
			if(render)
			{
				frames+=1;
				render();
			}
			else
			try
			{
				Thread.sleep(1);
			}
			catch (InterruptedException e)
			{			
				e.printStackTrace();
			}
		}
		
		dispose();
	}
	
	private void render()
	{
        Renderer.render(theatre);
		Window.render();
	}
	
	private void dispose()
	{
		Input.dispose();
		Window.dispose();		
	}
}
