package render.tex;

import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;
import javax.media.opengl.GL2GL3;
import javax.media.opengl.GL3;
import javax.media.opengl.GLException;

/**
 * Preserves a [ texture-unit, texture-target ] state.
 * <p>
 * The states keys are the retrieved active texture-unit and the given texture-target
 * for which the following states are being queried:
 * <pre>
 *   - texture-object
 *   - GL.GL_TEXTURE_MAG_FILTER
 *   - GL.GL_TEXTURE_MIN_FILTER
 *   - GL.GL_TEXTURE_WRAP_S
 *   - GL.GL_TEXTURE_WRAP_T
 * </pre>
 */
public class TexState {
    /**
     * Returns the <code>pname</code> to query the <code>textureTarget</code> currently bound to the active texture-unit.
     * <p>
     * Returns <code>0</code> is <code>textureTarget</code> is not supported.
     * </p>
     */
    public static final int getTexTargetQueryName(int textureTarget) {
        final int texBindQName;
        switch(textureTarget) {
            case GL.GL_TEXTURE_2D: texBindQName = GL.GL_TEXTURE_BINDING_2D; break;
            case GL.GL_TEXTURE_CUBE_MAP: texBindQName = GL.GL_TEXTURE_BINDING_CUBE_MAP; break;
            case GL2ES2.GL_TEXTURE_3D: texBindQName = GL2ES2.GL_TEXTURE_BINDING_3D; break;
            case GL2GL3.GL_TEXTURE_1D: texBindQName = GL2GL3.GL_TEXTURE_BINDING_1D; break;
            case GL2GL3.GL_TEXTURE_1D_ARRAY: texBindQName = GL2GL3.GL_TEXTURE_BINDING_1D_ARRAY; break;
            case GL2GL3.GL_TEXTURE_2D_ARRAY: texBindQName = GL2GL3.GL_TEXTURE_BINDING_2D_ARRAY; break;
            case GL2GL3.GL_TEXTURE_RECTANGLE: texBindQName = GL2GL3.GL_TEXTURE_BINDING_RECTANGLE; break;
            case GL2GL3.GL_TEXTURE_BUFFER: texBindQName = GL2GL3.GL_TEXTURE_BINDING_BUFFER; break;
            case GL3.GL_TEXTURE_2D_MULTISAMPLE: texBindQName = GL3.GL_TEXTURE_BINDING_2D_MULTISAMPLE; break;
            case GL3.GL_TEXTURE_2D_MULTISAMPLE_ARRAY: texBindQName = GL3.GL_TEXTURE_BINDING_2D_MULTISAMPLE_ARRAY; break;
            default: texBindQName = 0;
        }
        return texBindQName;
    }

    private final int target;
    /**
     * <pre>
     *   0 - unit
     *   1 - texture object
     *   2 - GL.GL_TEXTURE_MAG_FILTER
     *   3 - GL.GL_TEXTURE_MIN_FILTER
     *   4 - GL.GL_TEXTURE_WRAP_S
     *   5 - GL.GL_TEXTURE_WRAP_T
     * </pre>
     */
    private final int[] state = new int[] { 0, 0, 0, 0, 0, 0 };

    private static final String toHexString(int i) { return "0x"+Integer.toHexString(i); }

    private static final int activeTex(GL gl) {
        final int[] vi = { 0 };
        gl.glGetIntegerv(GL.GL_ACTIVE_TEXTURE, vi, 0);
        return vi[0];
    }

    /**
     * Creates a texture state for the retrieved active texture-unit and the given texture-target.
     * See {@link TexState}.
     * @param gl current GL context's GL object
     * @param textureTarget
     * @throws GLException if textureTarget is not supported
     */
    public TexState(GL gl, int textureTarget) throws GLException {
        this(gl, activeTex(gl), textureTarget);
    }

    /**
     * Creates a texture state for the given active texture-unit and the given texture-target.
     * See {@link TexState}.
     * @param gl current GL context's GL object
     * @param textureUnit  of range [ {@link GL#GL_TEXTURE0}.. ]
     * @param textureTarget
     * @throws GLException if textureTarget is not supported
     */
    public TexState(GL gl, int textureUnit, int textureTarget) throws GLException {
        target = textureTarget;
        state[0] = textureUnit;
        final int texBindQName = getTexTargetQueryName(textureTarget);
        if( 0 == texBindQName ) {
            throw new GLException("Unsupported textureTarget "+toHexString(textureTarget));
        }
        gl.glGetIntegerv(texBindQName, state, 1);
        gl.glGetTexParameteriv(target, GL.GL_TEXTURE_MAG_FILTER, state, 2);
        gl.glGetTexParameteriv(target, GL.GL_TEXTURE_MIN_FILTER, state, 3);
        gl.glGetTexParameteriv(target, GL.GL_TEXTURE_WRAP_S, state, 4);
        gl.glGetTexParameteriv(target, GL.GL_TEXTURE_WRAP_T, state, 5);
    }

    /**
     * Restores the texture-unit's texture-target state.
     * <p>
     * First the texture-unit is activated, then all states are restored.
     * </p>
     * @param gl current GL context's GL object
     */
    public final void restore(GL gl) {
        gl.glActiveTexture(state[0]);
        gl.glBindTexture(target, state[1]);
        gl.glTexParameteri(target, GL.GL_TEXTURE_MAG_FILTER, state[2]);
        gl.glTexParameteri(target, GL.GL_TEXTURE_MIN_FILTER, state[3]);
        gl.glTexParameteri(target, GL.GL_TEXTURE_WRAP_S, state[4]);
        gl.glTexParameteri(target, GL.GL_TEXTURE_WRAP_T, state[5]);
    }

    /** Returns the texture-unit of this state, key value. Unit is of range [ {@link GL#GL_TEXTURE0}.. ]. */
    public final int getUnit() { return state[0]; }
    /** Returns the texture-target of this state, key value. */
    public final int getTarget() { return target; }

    /** Returns the state's texture-object. */
    public final int getObject() { return state[1]; }
    /** Returns the state's mag-filter param. */
    public final int getMagFilter() { return state[2]; }
    /** Returns the state's min-filter param. */
    public final int getMinFilter() { return state[3]; }
    /** Returns the state's wrap-s param. */
    public final int getWrapS() { return state[4]; }
    /** Returns the state's wrap-t param. */
    public final int getWrapT() { return state[5]; }


    @Override
    public final String toString() {
        return "TexState[unit "+(state[0] - GL.GL_TEXTURE0)+", target "+toHexString(target)+
                ": obj "+toHexString(state[1])+
                ", filter[mag "+toHexString(state[2])+", min "+toHexString(state[3])+"], "+
                ": wrap[s "+toHexString(state[4])+", t "+toHexString(state[5])+"]]";
    }
}
