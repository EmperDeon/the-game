package utils.render;

import javax.media.opengl.GL;

public interface GLBuffer {
        public void bind(GL gl);
        public void free(GL gl);
}