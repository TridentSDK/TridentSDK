package net.tridentsdk.base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class HorseTypeTest {

    @Test
    public void testTypes() {
        for (HorseType type : HorseType.values()) {
            assertEquals(type, HorseType.of(type.getData()));
        }
    }

    @Test
    public void testIndex() {
        for (int i = 0; i <= 4; i++) {
            HorseType.of(i);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testError() {
        HorseType.of(5);
    }

    @Test
    public void testData() {
        assertEquals(HorseType.HORSE, HorseType.of(0));
        assertEquals(HorseType.DONKEY, HorseType.of(1));
        assertEquals(HorseType.MULE, HorseType.of(2));
        assertEquals(HorseType.ZOMBIE, HorseType.of(3));
        assertEquals(HorseType.SKELETON, HorseType.of(4));
    }

}
