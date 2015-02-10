#version 330

in vec2 texCoord0;
out vec4 fragColour;

uniform vec4 ambientColour;
uniform sampler2D sampler;

void main()
{
	fragColour=texture(sampler,texCoord0)*ambientColour;
}