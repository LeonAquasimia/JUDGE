package engine.util;

public class Time
{
	private static double delta;
	public static long second=1000000000L;
	
	public static long nano()
	{
		return System.nanoTime();
	}

    public static double second()
    {
        return (double)System.nanoTime()/(double)second;
    }
	
//	public static void delta(double delta)
//	{
//		Time.delta=delta;
//	}
//
//	public static double delta()
//	{
//		return delta;
//	}

}
