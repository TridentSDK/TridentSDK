package net.tridentsdk.base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class VillagerProfessionTest {

    @Test
    public void testTypes() {
        for (VillagerProfession type : VillagerProfession.values()) {
            assertEquals(type, VillagerProfession.of(type.getData()));
        }
    }

    @Test
    public void testIndex() {
        for (int i = 0; i <= 4; i++) {
            VillagerProfession.of(i);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testError() {
        VillagerProfession.of(5);
    }

    @Test
    public void testData() {
        assertEquals(VillagerProfession.FARMER, VillagerProfession.of(0));
        assertEquals(VillagerProfession.LIBRARIAN, VillagerProfession.of(1));
        assertEquals(VillagerProfession.PRIEST, VillagerProfession.of(2));
        assertEquals(VillagerProfession.BLACKSMITH, VillagerProfession.of(3));
        assertEquals(VillagerProfession.BUTCHER, VillagerProfession.of(4));
    }

    @Test
    public void testZombies() {
        for (VillagerProfession type : VillagerProfession.values()) {
            assertEquals(ZombieType.of(type.getData() + 1), ZombieType.valueOf("VILLAGER_" + type.name()));
        }
    }

}
