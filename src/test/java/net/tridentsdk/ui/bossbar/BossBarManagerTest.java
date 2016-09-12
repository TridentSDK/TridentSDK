package net.tridentsdk.ui.bossbar;

import org.junit.Test;

/**
 * @author Nick Robson
 */
public class BossBarManagerTest {

    @Test(expected = NullPointerException.class)
    public void testNonExistence() {
        BossBarManager.getInstance();
    }

}
