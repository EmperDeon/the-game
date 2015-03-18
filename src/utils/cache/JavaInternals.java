package utils.cache;

import java.lang.reflect.*;
import java.nio.*;
import sun.misc.*;

public class JavaInternals {
    private static final Unsafe unsafe;

    static {
        try {
            unsafe = (Unsafe) getField(Unsafe.class, "theUnsafe").get(null);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Field getField(Class cls, String name) {
        try {
            Field f = cls.getDeclaredField(name);
            f.setAccessible(true);
            return f;
        } catch (NoSuchFieldException | SecurityException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Method getMethod(Class cls, String name, Class... params) {
        try {
            Method m = cls.getDeclaredMethod(name, params);
            m.setAccessible(true);
            return m;
        } catch (NoSuchMethodException | SecurityException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Unsafe getUnsafe() {
        return unsafe;
    }

    public static long allocateMemory(long size, Object holder) {
        final long address = unsafe.allocateMemory(size);
        Cleaner.create(holder, () -> {
         unsafe.freeMemory(address);
        });
        return address;
    }

    public static long getByteBufferAddress(ByteBuffer buffer) {
        try {
            return getField(Buffer.class, "address").getLong(buffer);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}
