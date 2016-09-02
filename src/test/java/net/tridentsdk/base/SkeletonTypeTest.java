package net.tridentsdk.base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class SkeletonTypeTest {

    @Test
    public void testTypes() {
        for (SkeletonType type : SkeletonType.values()) {
            assertEquals(type, SkeletonType.of(type.getData()));
        }
    }

    @Test
    public void testIndex() {
        for (int i = 0; i <= 2; i++) {
            SkeletonType.of(i);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testError() {
        SkeletonType.of(3);
    }

    @Test
    public void testData() {
        assertEquals(SkeletonType.NORMAL, SkeletonType.of(0));
        assertEquals(SkeletonType.WITHER, SkeletonType.of(1));
        assertEquals(SkeletonType.STRAY, SkeletonType.of(2));
    }

}
