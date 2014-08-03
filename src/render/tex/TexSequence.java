package render.tex;

import com.jogamp.opengl.util.TimeFrameI;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

/**
 * Protocol for texture sequences, like animations, movies, etc.
 * <p>
 * Ensure to respect the texture coordinates provided by
 * {@link TexFrame}.{@link TexFrame#getTex() getTex()}.{@link Tex#getImageTexCoords() getImageTexCoords()}.
 * </p>
 * The user's shader shall be fitted for this implementation.
 * Assuming we use a base shader code w/o headers using </code>ShaderCode</code>.
 * (Code copied from unit test / demo <code>TexCubeES2</code>)
 * <pre>
 *
    static final String[] es2_prelude = { "#version 100\n", "precision mediump float;\n" };
    static final String gl2_prelude = "#version 110\n";
    static final String shaderBasename = "texsequence_xxx";  // the base shader code w/o headers
    static final String myTexLookupName = "myTex2D"; // the desired texture lookup function

    private void initShader(GL2ES2 gl, TexSequence texSeq) {
        // Create & Compile the shader objects
        ShaderCode rsVp = ShaderCode.create(gl, GL2ES2.GL_VERTEX_SHADER, TexCubeES2.class,
                                            "shader", "shader/bin", shaderBasename, true);
        ShaderCode rsFp = ShaderCode.create(gl, GL2ES2.GL_FRAGMENT_SHADER, TexCubeES2.class,
                                            "shader", "shader/bin", shaderBasename, true);

        // Prelude shader code w/ GLSL profile specifics [ 1. pre-proc, 2. other ]
        int rsFpPos;
        if(gl.isGLES2()) {
            // insert ES2 version string in beginning
            rsVp.insertShaderSource(0, 0, es2_prelude[0]);
            rsFpPos = rsFp.insertShaderSource(0, 0, es2_prelude[0]);
        } else {
            // insert GL2 version string in beginning
            rsVp.insertShaderSource(0, 0, gl2_prelude);
            rsFpPos = rsFp.insertShaderSource(0, 0, gl2_prelude);
        }
        // insert required extensions as determined by TexSequence implementation.
        rsFpPos = rsFp.insertShaderSource(0, rsFpPos, texSeq.getRequiredExtensionsShaderStub());
        if(gl.isGLES2()) {
            // insert ES2 default precision declaration
            rsFpPos = rsFp.insertShaderSource(0, rsFpPos, es2_prelude[1]);
        }
        // negotiate the texture lookup function name
        final String texLookupFuncName = texSeq.getTexLookupFunctionName(myTexLookupName);

        // in case a fixed lookup function is being chosen, replace the name in our code
        rsFp.replaceInShaderSource(myTexLookupName, texLookupFuncName);

        // Cache the TexSequence shader details in StringBuilder:
        final StringBuilder sFpIns = new StringBuilder();

        // .. declaration of the texture sampler using the implementation specific type
        sFpIns.append("uniform ").append(texSeq.getTexSampler2DType()).append(" mgl_ActiveTex;\n");

        // .. the actual texture lookup function, maybe null in case a built-in function is being used
        sFpIns.append(texSeq.getTexLookupFragmentShaderImpl());

        // Now insert the TexShader details in our shader after the given tag:
        rsFp.insertShaderSource(0, "TEXTURE-SEQUENCE-CODE-BEGIN", 0, sFpIns);

        // Create & Link the shader program
        ShaderProgram sp = new ShaderProgram();
        sp.add(rsVp);
        sp.add(rsFp);
        if(!sp.link(gl, System.err)) {
            throw new GLException("Couldn't link program: "+sp);
        }
        ...
 * </pre>
 * The above procedure might look complicated, however, it allows most flexibility and
 * workarounds to also deal with GLSL bugs.
 *
 */
public interface TexSequence {
    public static final String samplerExternalOES = "samplerExternalOES";
    public static final String sampler2D = "sampler2D";

    /**
     * Tex holder interface, maybe specialized by implementation
     * to associated related data.
     */
    public static class TexFrame extends TimeFrameI {
        public TexFrame(Tex t, int pts, int duration) {
            super(pts, duration);
            texture = t;
        }
        public TexFrame(Tex t) {
            texture = t;
        }

        public final Tex getTex() { return texture; }

        @Override
        public String toString() {
            return "TexFrame[pts " + pts + " ms, l " + duration + " ms, texID "+ (null != texture ? texture : 0) + "]";
        }
        protected final Tex texture;
    }

    /**
     * Event listener to notify users of updates regarding the {@link TexSequence}.
     * <p>
     * The implementation sending the events, and hence calling down to all listeners,
     * does not necessarily make the user's OpenGL context current.
     * </p>
     * <p>
     * Further more, the call may happen off-thread, possibly holding another, possibly shared, OpenGL context current.
     * </p>
     * Hence a user shall not issue <i>any</i> OpenGL, time consuming
     * or {@link TexSequence} lifecycle operations directly.<br>
     * Instead, the user shall:
     * <ul>
     *   <li>issue commands off-thread via spawning off another thread, or</li>
     *   <li>injecting {@link GLRunnable} objects via {@link GLAutoDrawable#invoke(boolean, GLRunnable)}, or</li>
     *   <li>simply changing a volatile state of their {@link GLEventListener} implementation.</li>
     * </ul>
     * </p>
     * */
    public interface TexSeqEventListener<T extends TexSequence> {
        /**
         * Signaling listeners that a new {@link TexFrame} is available.
         * <p>
         * User shall utilize {@link TexSequence#getNextTex(GL)} to dequeue it to maintain
         * a consistent queue.
         * </p>
         * @param ts the event source
         * @param newFrame the newly enqueued frame
         * @param when system time in msec.
         **/
        public void newFrameAvailable(T ts, TexFrame newFrame, long when);
    }

    /** Returns the texture target used by implementation. */
    public int getTexTarget();

    /** Return the texture unit used to render the current frame. */
    public int getTexUnit();

    public int[] getTexMinMagFilter();

    public int[] getTexWrapST();

    /**
     * Returns the last updated texture.
     * <p>
     * In case the instance is just initialized, it shall return a <code>TexFrame</code>
     * object with valid attributes. The texture content may be undefined
     * until the first call of {@link #getNextTex(GL)}.<br>
     * </p>
     * Not blocking.
     *
     * @throws IllegalStateException if instance is not initialized
     */
    public TexFrame getLastTex() throws IllegalStateException ;

    /**
     * Returns the next texture to be rendered.
     * <p>
     * Implementation shall return the next frame if available, may block if a next frame may arrive <i>soon</i>.
     * Otherwise implementation shall return the last frame.
     * </p>
     * <p>
     * Shall return <code>null</code> in case <i>no</i> next or last frame is available.
     * </p>
     *
     * @throws IllegalStateException if instance is not initialized
     */
    public TexFrame getNextTex(GL gl) throws IllegalStateException ;

    /**
     * In case a shader extension is required, based on the implementation
     * and the runtime GL profile, this method returns the preprocessor macros, e.g.:
     * <pre>
     * #extension GL_OES_EGL_image_external : enable
     * </pre>
     *
     * @throws IllegalStateException if instance is not initialized
     */
    public String getRequiredExtensionsShaderStub() throws IllegalStateException ;

    /**
     * Returns either <code>sampler2D</code> or <code>samplerExternalOES</code>
     * depending on {@link #getLastTex()}.{@link TexFrame#getTex() getTex()}.{@link Tex#getTarget() getTarget()}.
     *
     * @throws IllegalStateException if instance is not initialized
     **/
    public String getTexSampler2DType() throws IllegalStateException ;

    /**
     * @param desiredFuncName desired lookup function name. If <code>null</code> or ignored by the implementation,
     *                        a build-in name is returned.
     * @return the final lookup function name
     *
     * @see {@link #getTexLookupFragmentShaderImpl()}
     *
     * @throws IllegalStateException if instance is not initialized
     */
    public String getTexLookupFunctionName(String desiredFuncName) throws IllegalStateException ;

    /**
     * Returns the complete texture2D lookup function code of type
     * <pre>
     *   vec4 <i>funcName</i>(in <i>getTexSampler2DType()</i> image, in vec2 texCoord) {
     *      vec4 texColor = do_something_with(image, texCoord);
     *      return texColor;
     *   }
     * </pre>
     * <p>
     * <i>funcName</i> can be negotiated and queried via {@link #getTexLookupFunctionName(String)}.
     * </p>
     * Note: This function may return an empty string in case a build-in lookup
     * function is being chosen. If the implementation desires so,
     * {@link #getTexLookupFunctionName(String)} will ignore the desired function name
     * and returns the build-in lookup function name.
     * </p>
     * @see #getTexLookupFunctionName(String)
     * @see #getTexSampler2DType()
     *
     * @throws IllegalStateException if instance is not initialized
     */
    public String getTexLookupFragmentShaderImpl() throws IllegalStateException ;
}
