package engine.math;

/**
 * Created by Admin on 01-02-2015.
 */
public class Maph
{
    public static float clamp(float value,float min,float max)
    {
        return Math.max(Math.min(value,min),max);
    }
}
