package net.tridentsdk.base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class DyeColorTest {

    @Test
    public void testColors() {
        for (DyeColor color : DyeColor.values()) {
            assertEquals(color, DyeColor.of(color.getData()));
        }
    }

    @Test
    public void testIndex() {
        for (int i = 0; i <= 0xF; i++) {
            DyeColor.of(i);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testError() {
        DyeColor.of(16);
    }

    @Test
    public void testData() {
        assertEquals(DyeColor.BLACK, DyeColor.of(0x0));
        assertEquals(DyeColor.RED, DyeColor.of(0x1));
        assertEquals(DyeColor.DARK_GREEN, DyeColor.of(0x2));
        assertEquals(DyeColor.BROWN, DyeColor.of(0x3));
        assertEquals(DyeColor.DARK_BLUE, DyeColor.of(0x4));
        assertEquals(DyeColor.DARK_PURPLE, DyeColor.of(0x5));
        assertEquals(DyeColor.CYAN, DyeColor.of(0x6));
        assertEquals(DyeColor.LIGHT_GRAY, DyeColor.of(0x7));
        assertEquals(DyeColor.DARK_GRAY, DyeColor.of(0x8));
        assertEquals(DyeColor.PINK, DyeColor.of(0x9));
        assertEquals(DyeColor.LIGHT_GREEN, DyeColor.of(0xA));
        assertEquals(DyeColor.YELLOW, DyeColor.of(0xB));
        assertEquals(DyeColor.LIGHT_BLUE, DyeColor.of(0xC));
        assertEquals(DyeColor.MAGENTA, DyeColor.of(0xD));
        assertEquals(DyeColor.ORANGE, DyeColor.of(0xE));
        assertEquals(DyeColor.WHITE, DyeColor.of(0xF));
    }

}
