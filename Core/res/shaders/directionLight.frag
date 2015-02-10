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

struct DirectionLight
{
	BaseLight base;
	vec3 direction;
};

uniform sampler2D diffuse;
uniform DirectionLight directionLight;

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

vec4 calcDirectionLight(DirectionLight dir, vec3 normal)
{
	return calcLight(dir.base,-dir.direction,normal);
}

void main()
{
	vec4 light=vec4(0,0,0,0);
	
	light+=calcDirectionLight(directionLight,normal0);
	
	fragColour=texture(diffuse,texCoord0)*light;
}