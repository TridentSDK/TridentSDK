package net.tridentsdk.ui.bossbar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class BossBarColorTest {

    @Test
    public void testColors() {
        for (BossBarColor color : BossBarColor.values()) {
            assertEquals(color, BossBarColor.of(color.getId()));
        }
    }

    @Test
    public void testIndex() {
        assertEquals(7, BossBarColor.values().length);
        for (int i = 0; i <= 6; i++) {
            BossBarColor.of(i);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testError() {
        BossBarColor.of(7);
    }

    @Test
    public void testIDs() {
        assertEquals(BossBarColor.PINK, BossBarColor.of(0));
        assertEquals(BossBarColor.BLUE, BossBarColor.of(1));
        assertEquals(BossBarColor.RED, BossBarColor.of(2));
        assertEquals(BossBarColor.GREEN, BossBarColor.of(3));
        assertEquals(BossBarColor.YELLOW, BossBarColor.of(4));
        assertEquals(BossBarColor.PURPLE, BossBarColor.of(5));
        assertEquals(BossBarColor.WHITE, BossBarColor.of(6));
    }

}
