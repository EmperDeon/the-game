package main.db.util;

import main.db.impl.InternalKey;
import main.db.impl.SeekingIterator;

/**
 * <p>A common interface for internal iterators.</p>
 *
 * @author <a href="http://hiramchirino.com">Hiram Chirino</a>
 */
public interface InternalIterator extends SeekingIterator<InternalKey, Slice> {
}
