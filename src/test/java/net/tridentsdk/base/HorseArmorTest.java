package net.tridentsdk.base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class HorseArmorTest {

    @Test
    public void testArmors() {
        assertEquals(3, HorseArmor.LEATHER.getArmor());
        assertEquals(5, HorseArmor.IRON.getArmor());
        assertEquals(7, HorseArmor.GOLD.getArmor());
        assertEquals(11, HorseArmor.DIAMOND.getArmor());
    }

}
