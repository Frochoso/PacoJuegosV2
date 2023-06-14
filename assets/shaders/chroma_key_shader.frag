#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D u_texture;
uniform vec3 chromaKeyColor;
varying vec2 v_texCoords;

void main() {
    vec4 textureColor = texture2D(u_texture, v_texCoords);
    float distance = distance(textureColor.rgb, chromaKeyColor);
    float threshold = 0.3;

    if (distance < threshold) {
        discard;
    }

    gl_FragColor = textureColor;
}
