package render.tex;

import java.io.Serializable;

/** Specifies texture coordinates for a rectangular area of a
    texture. Note that some textures are inherently flipped vertically
    from OpenGL's standard coordinate system. This class takes care of
    this vertical flip so that the "bottom" and "top" coordinates may
    sometimes be reversed. From the point of view of code rendering
    textured polygons, it can always map the bottom and left texture
    coordinates from the TexCoords to the lower left point of the
    textured polygon and achieve correct results. */

public class TexCoords implements Serializable{
    // These represent the lower-left point
    private final float left;
    private final float bottom;
    // These represent the upper-right point
    private final float right;
    private final float top;

    public TexCoords(float left, float bottom,
                         float right, float top) {
        this.left = left;
        this.bottom = bottom;
        this.right = right;
        this.top = top;
    }

    /** Transfers <code>{s * ss, t * ts}</code> from this object into the given <code>float[8+d_off]</code> in the following order:
     * <pre>
     *   left,  bottom
     *   right, bottom
     *   left,  top
     *   right  top
     * </pre>
     */
    public float[] getST_LB_RB_LT_RT(float[] d, int d_off, float ss, float ts) {
        d[0+d_off] = left  *ss;  d[1+d_off] = bottom*ts;
        d[2+d_off] = right *ss;  d[3+d_off] = bottom*ts;
        d[4+d_off] = left  *ss;  d[5+d_off] = top   *ts;
        d[6+d_off] = right *ss;  d[7+d_off] = top   *ts;
        return d;
    }

    /** Returns the leftmost (x) texture coordinate of this
        rectangle. */
    public float left() { return left; }

    /** Returns the rightmost (x) texture coordinate of this
        rectangle. */
    public float right() { return right; }

    /** Returns the bottommost (y) texture coordinate of this
        rectangle. */
    public float bottom() { return bottom; }

    /** Returns the topmost (y) texture coordinate of this
        rectangle. */
    public float top() { return top; }

    @Override
    public String toString() { return "TexCoord[h: "+left+" - "+right+", v: "+bottom+" - "+top+"]"; }
}
