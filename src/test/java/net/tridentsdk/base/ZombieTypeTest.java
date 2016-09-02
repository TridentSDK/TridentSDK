package net.tridentsdk.base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class ZombieTypeTest {

    @Test
    public void testTypes() {
        for (ZombieType type : ZombieType.values()) {
            assertEquals(type, ZombieType.of(type.getData()));
        }
    }

    @Test
    public void testIndex() {
        for (int i = 0; i <= 6; i++) {
            ZombieType.of(i);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testError() {
        ZombieType.of(7);
    }

    @Test
    public void testData() {
        assertEquals(ZombieType.NORMAL, ZombieType.of(0));
        assertEquals(ZombieType.VILLAGER_FARMER, ZombieType.of(1));
        assertEquals(ZombieType.VILLAGER_LIBRARIAN, ZombieType.of(2));
        assertEquals(ZombieType.VILLAGER_PRIEST, ZombieType.of(3));
        assertEquals(ZombieType.VILLAGER_BLACKSMITH, ZombieType.of(4));
        assertEquals(ZombieType.VILLAGER_BUTCHER, ZombieType.of(5));
        assertEquals(ZombieType.HUSK, ZombieType.of(6));
    }

    @Test
    public void testVillagers() {
        for (ZombieType type : ZombieType.values()) {
            if (type.isVillager()) {
                assertEquals(VillagerProfession.of(type.getData() - 1), VillagerProfession.valueOf(type.name().substring(9)));
            }
        }
    }

}
