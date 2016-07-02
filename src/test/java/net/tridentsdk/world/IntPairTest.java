package net.tridentsdk.world;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntPairTest {
    private final IntPair pair = IntPair.make(100, 200);

    @Test
    public void testGets() {
        assertEquals(100, this.pair.x());
        assertEquals(200, this.pair.z());
    }
}