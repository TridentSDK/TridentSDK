package net.tridentsdk.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MiscTest {
    @Test
    public void testConstants() {
        assertEquals("NBT value out of range for class %s", Misc.NBT_BOUND_FAIL);
        assertEquals(System.getProperty("user.dir"), Misc.HOME);
    }
}