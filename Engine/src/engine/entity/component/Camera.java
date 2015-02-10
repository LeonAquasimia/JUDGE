package engine.entity.component;

import engine.entity.Entity;
import engine.math.Matrix4;
import engine.math.Vector3;
import engine.rendering.Colour;

import java.util.ArrayList;

public class Camera extends Component
{
    public static ArrayList<Camera> cameras=new ArrayList<>();

    private float zNear=0.00001f,zFar=1000,width=800,height=600,fov=90,aspectRatio=0.75f;
	
	public Vector3 up,forward;
	private boolean resetUp=true;
    private Matrix4 perspective,orthogonal;
	
	public Camera(float width,float height,float fov,float zNear,float zFar)
	{
		up=new Vector3(0,1,0);
		forward=new Vector3(0,0,1);

        this.width=width;
        this.height=height;
        this.fov=fov;
        this.zNear=zNear;
        this.zFar=zFar;
        this.aspectRatio=width/height;
        perspective=new Matrix4().project(width,height,fov,zNear,zFar);
        orthogonal=new Matrix4().orthograph(-1,1,-1,1,-1,1);
	}

    public void create(Entity entity)
    {
        cameras.add(this);
    }

    public Matrix4 project()
    {
        Vector3 pos=entity.transform().position();

        Matrix4 camRot=new Matrix4().rotate(forward, up);
        Matrix4 camTrans=new Matrix4().translate(-pos.x(), -pos.y(), -pos.z());

        return perspective.mul(camRot.mul(camTrans));
    }
	
	public Vector3 left()
	{
		return (up.cross(forward)).normalised();
	}
	
	public Vector3 right()
	{
		return (forward.cross(up)).normalised();
	}
	
	public void pitch(float angle)
	{		
		Vector3 h;
		if(resetUp)
		{
			h=Vector3.up.cross(forward).normalised();
		}
		else
		{
			h=up.cross(forward).normalised();
		}
		forward=forward.rotate(angle,h);		
		up=forward.cross(h).normalised();
	}
	
	public void yaw(float angle)
	{
		Vector3 h;
		if(resetUp)
		{
			h=Vector3.up.cross(forward).normalised();
			forward=forward.rotate(angle,Vector3.up);
		}
		else
		{
			h=up.cross(forward).normalised();
			forward=forward.rotate(angle,up);
		}
		
		
		up=forward.cross(h).normalised();
	}
	
	public void roll(float angle)
	{
		//ector3 h=UP.cross(forward).normalise();
		
		up=up.rotate(angle,forward);
		//up=h.normalise();
	}

    public Vector3 position()
    {
        return entity.transform().position();
    }

    public float zNear() {
        return zNear;
    }

    public void zNear(float zNear) {
        this.zNear = zNear;
    }

    public float zFar() {
        return zFar;
    }

    public void zFar(float zFar) {
        this.zFar = zFar;
    }

    public float width() {
        return width;
    }

    public void width(float width) {
        this.width = width;
    }

    public float height() {
        return height;
    }

    public void height(float height) {
        this.height = height;
    }

    public float fov() {
        return fov;
    }

    public void fov(float fov) {
        this.fov = fov;
    }

    public float aspectRatio() {
        return aspectRatio;
    }

    public void aspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public Matrix4 perspective()
    {
        return perspective;
    }

    public void perspective(Matrix4 perspective)
    {
        this.perspective = perspective;
    }

    public Matrix4 orthogonal()
    {
        return orthogonal;
    }

    public void orthogonal(Matrix4 orthogonal)
    {
        this.orthogonal = orthogonal;
    }

    public Vector3 forward() {
        return forward;
    }

    public void forward(Vector3 forward) {
        this.forward = forward;
    }

    public Vector3 up() {
        return up;
    }

    public void up(Vector3 up) {
        this.up = up;
    }


}
