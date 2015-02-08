package engine.math;

public class Matrix4
{
	private float[][] m;
	
	public Matrix4()
	{
		m=new float[4][4];
	}
	
	public Matrix4 identity()
	{		
		m[0][0]=1;m[0][1]=0;m[0][2]=0;m[0][3]=0;
		m[1][0]=0;m[1][1]=1;m[1][2]=0;m[1][3]=0;
		m[2][0]=0;m[2][1]=0;m[2][2]=1;m[2][3]=0;
		m[3][0]=0;m[3][1]=0;m[3][2]=0;m[3][3]=1;
		
		return this;
	}
	
	public Matrix4 translate(float x,float y,float z)
	{
		m[0][0]=1;m[0][1]=0;m[0][2]=0;m[0][3]=x;
		m[1][0]=0;m[1][1]=1;m[1][2]=0;m[1][3]=y;
		m[2][0]=0;m[2][1]=0;m[2][2]=1;m[2][3]=z;
		m[3][0]=0;m[3][1]=0;m[3][2]=0;m[3][3]=1;
		
		return this;
	}
	
	public Matrix4 rotate(float x,float y,float z)
	{
		Matrix4 rx=new Matrix4();
		Matrix4 ry=new Matrix4();
		Matrix4 rz=new Matrix4();
		
		float tx=(float)Math.toRadians(x);
		float ty=(float)Math.toRadians(y);
		float tz=(float)Math.toRadians(z);
				
		rx.m[0][0]=1					;rx.m[0][1]=0						;rx.m[0][2]=0						;rx.m[0][3]=0;
		rx.m[1][0]=0					;rx.m[1][1]=(float)Math.cos(tx)		;rx.m[1][2]=-(float)Math.sin(tx)	;rx.m[1][3]=0;
		rx.m[2][0]=0					;rx.m[2][1]=(float)Math.sin(tx)		;rx.m[2][2]=(float)Math.cos(tx)		;rx.m[2][3]=0;
		rx.m[3][0]=0					;rx.m[3][1]=0						;rx.m[3][2]=0						;rx.m[3][3]=1;
		
		ry.m[0][0]=(float)Math.cos(ty)	;ry.m[0][1]=0						;ry.m[0][2]=-(float)Math.sin(ty)	;ry.m[0][3]=0;
		ry.m[1][0]=0					;ry.m[1][1]=1						;ry.m[1][2]=0						;ry.m[1][3]=0;
		ry.m[2][0]=(float)Math.sin(ty)	;ry.m[2][1]=0						;ry.m[2][2]=(float)Math.cos(ty)		;ry.m[2][3]=0;
		ry.m[3][0]=0					;ry.m[3][1]=0						;ry.m[3][2]=0						;ry.m[3][3]=1;
		
		rz.m[0][0]=(float)Math.cos(tz)	;rz.m[0][1]=-(float)Math.sin(tz)	;rz.m[0][2]=0						;rz.m[0][3]=0;
		rz.m[1][0]=(float)Math.sin(tz)	;rz.m[1][1]=(float)Math.cos(tz)		;rz.m[1][2]=0						;rz.m[1][3]=0;
		rz.m[2][0]=0					;rz.m[2][1]=0						;rz.m[2][2]=1						;rz.m[2][3]=0;
		rz.m[3][0]=0					;rz.m[3][1]=0						;rz.m[3][2]=0						;rz.m[3][3]=1;
					
		m=rz.mul(ry.mul(rx)).m();
		
		return this;
	}
	
	public Matrix4 scale(float x,float y,float z)
	{
		m[0][0]=x;m[0][1]=0;m[0][2]=0;m[0][3]=0;
		m[1][0]=0;m[1][1]=y;m[1][2]=0;m[1][3]=0;
		m[2][0]=0;m[2][1]=0;m[2][2]=z;m[2][3]=0;
		m[3][0]=0;m[3][1]=0;m[3][2]=0;m[3][3]=1;
		
		return this;
	}
	
	public Matrix4 project(float width,float height,float fov,float zNear,float zFar)
	{
		float aspect=width/height;
		float tanHalfFOV=(float)Math.tan(Math.toRadians(fov/2));
		float zRange=zNear-zFar;
		
		m[0][0]=1.0f/(tanHalfFOV*aspect);m[0][1]=0				;m[0][2]=0						;m[0][3]=0;
		m[1][0]=0						;m[1][1]=1.0f/tanHalfFOV;m[1][2]=0						;m[1][3]=0;
		m[2][0]=0						;m[2][1]=0				;m[2][2]=(-zNear-zFar)/zRange	;m[2][3]=2*zFar*zNear/zRange;
		m[3][0]=0						;m[3][1]=0				;m[3][2]=1						;m[3][3]=0;
		
		return this;
	}

    public Matrix4 orthograph(float left,float right,float bottom,float top,float near,float far)
    {
        float w,h,d;
        w=right-left;
        h=top-bottom;
        d=far-near;

        m[0][0]=2f/w;m[0][1]=0;m[0][2]=0;m[0][3]=-(right+left)/w;
        m[1][0]=0;m[1][1]=2f/h;m[1][2]=0;m[1][3]=-(top+bottom)/h;
        m[2][0]=0;m[2][1]=0;m[2][2]=-(2f/d);m[2][3]=-(far+near)/d;
        m[3][0]=0;m[3][1]=0;m[3][2]=0;m[3][3]=1;

        return this;
    }
	
	public Matrix4 rotate(Vector3 forward, Vector3 up)
	{
		Vector3 f=forward.normalised();
		
		Vector3 r=up.cross(f).normalised();
		
		Vector3 u=f.cross(r);
		
		m[0][0]=r.x()	;m[0][1]=r.y()		;m[0][2]=r.z()		;m[0][3]=0;
		m[1][0]=u.x()	;m[1][1]=u.y()		;m[1][2]=u.z()		;m[1][3]=0;
		m[2][0]=f.x()	;m[2][1]=f.y()		;m[2][2]=f.z()		;m[2][3]=0;
		m[3][0]=0		;m[3][1]=0			;m[3][2]=0			;m[3][3]=1;
		
		return this;
	}
	
	public Matrix4 mul(Matrix4 r)
	{
		Matrix4 res=new Matrix4();
		
		for (int i=0;i<4;i+=1)
		for (int i2=0;i2<4;i2+=1)
		{
			res.set(i,i2,m[i][0]*r.get(0,i2)+m[i][1]*r.get(1,i2)+m[i][2]*r.get(2,i2)+m[i][3]*r.get(3,i2));
		}
		
		return res;
	}

	public void set(int x,int y,float value)
	{
		m[x][y]=value;
	}
	
	public float get(int x,int y)
	{
		return m[x][y];
	}
	
	public float[][] m()
	{
		return m;
	}

	public void m(float[][] m)
	{
		this.m = m;
	}

	
}
