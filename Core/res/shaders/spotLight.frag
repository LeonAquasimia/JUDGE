#version 330

in vec2 texCoord0;
in vec3 normal0;
in vec3 worldPos0;
out vec4 fragColour;

uniform vec3 eyePos;
uniform vec2 specularity;

struct BaseLight
{
	vec4 colour;
	float intensity;
};

struct PointLight
{
	BaseLight base;
	vec3 attenuation;
	vec3 position;
	float range;
};

struct SpotLight
{
	PointLight point;
	vec3 direction;
	float cutOff;
};


uniform sampler2D diffuse;
uniform SpotLight spotLight;

vec4 calcLight(BaseLight base, vec3 direction, vec3 normal)
{
	float diffuseFactor=dot(normal,-direction);
	
	vec4 diffuseColour=vec4(0,0,0,1);
	vec4 specularColour=vec4(0,0,0,1);
	
	
	if(diffuseFactor>0)
	{
		diffuseColour=base.colour*base.intensity*diffuseFactor;
		
		vec3 directionToEye=normalize(eyePos-worldPos0);
		vec3 reflectDirection=normalize(reflect(direction,normal));
			
		float specularFactor=dot(directionToEye,reflectDirection);
		specularFactor=pow(specularFactor,specularity.y);
		
		if(specularFactor>0)
		{
			specularColour=(base.colour*specularity.x*specularFactor);
		}		
	}	

	return diffuseColour+specularColour;
}

vec4 calcPointLight(PointLight point, vec3 normal)
{
	vec3 dir=worldPos0-point.position;
	float distanceToPoint=length(dir);
	
	if(distanceToPoint>point.range)
	{
		return vec4(0,0,0,0);
	}
	float attenuation;
	dir=normalize(dir);
	
	vec4 colour=calcLight(point.base,dir,normal);
	attenuation=point.attenuation.x+point.attenuation.y*distanceToPoint+point.attenuation.z*distanceToPoint*distanceToPoint+0.0001;
	
	return colour/attenuation;
}

vec4 calcSpotLight(SpotLight spot, vec3 normal)
{
	vec3 lightDir=worldPos0-spot.point.position;
	
	float spotFactor=dot(lightDir,spot.direction);
	
	vec4 colour=vec4(0,0,0,0);
	
	if(spotFactor>spot.cutOff)
	{
		colour=calcPointLight(spot.point,normal)*(1.0-(1.0-spotFactor)/(1.0-spot.cutOff));
	}
	
	return colour;
}

void main()
{
	vec4 light=vec4(0,0,0,1);
	
	light+=calcSpotLight(spotLight,normal0);
	
	fragColour=texture(diffuse,texCoord0)*light;
}