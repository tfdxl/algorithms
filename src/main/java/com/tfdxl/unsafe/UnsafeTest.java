package com.tfdxl.unsafe;

/**
 * @author Tian Feng
 */
public class UnsafeTest {

    private UnsafeTest next;

    /**
     * Unsafe mechanics
     */
    private static final sun.misc.Unsafe UNSAFE;
    private static final long nextOffSet;

    static {
        try {
            UNSAFE = sun.misc.Unsafe.getUnsafe();
            Class<?> k = UnsafeTest.class;
            nextOffSet = UNSAFE.objectFieldOffset
                    (k.getDeclaredField("next"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public static void main(String[] args) {
        System.err.println("UnsafeTest.nextOffSet ---> " + UnsafeTest.nextOffSet);
    }
}
