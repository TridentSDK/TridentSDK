package net.tridentsdk.base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class HorseMarkingsTest {

    @Test
    public void testMarkings() {
        for (HorseMarkings markings : HorseMarkings.values()) {
            assertEquals(markings, HorseMarkings.of(markings.getData()));
        }
    }

    @Test
    public void testIndex() {
        for (int i = 0; i <= 6; i++) {
            HorseMarkings.of(i);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testError() {
        HorseMarkings.of(7);
    }

    @Test
    public void testData() {
        assertEquals(HorseMarkings.WHITE, HorseMarkings.of(0));
        assertEquals(HorseMarkings.CREAMY, HorseMarkings.of(1));
        assertEquals(HorseMarkings.CHESTNUT, HorseMarkings.of(2));
        assertEquals(HorseMarkings.BROWN, HorseMarkings.of(3));
        assertEquals(HorseMarkings.BLACK, HorseMarkings.of(4));
        assertEquals(HorseMarkings.GRAY, HorseMarkings.of(5));
        assertEquals(HorseMarkings.DARK_BROWN, HorseMarkings.of(6));
    }

}
