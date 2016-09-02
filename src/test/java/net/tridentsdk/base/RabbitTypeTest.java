package net.tridentsdk.base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class RabbitTypeTest {

    @Test
    public void testTypes() {
        for (RabbitType type : RabbitType.values()) {
            assertEquals(type, RabbitType.of(type.getData()));
        }
    }

    @Test
    public void testIndex() {
        for (int i = 0; i <= 5; i++) {
            RabbitType.of(i);
        }
        RabbitType.of(99);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testError() {
        RabbitType.of(6);
    }

    @Test
    public void testData() {
        assertEquals(RabbitType.BROWN, RabbitType.of(0));
        assertEquals(RabbitType.WHITE, RabbitType.of(1));
        assertEquals(RabbitType.BLACK, RabbitType.of(2));
        assertEquals(RabbitType.BLACK_AND_WHITE, RabbitType.of(3));
        assertEquals(RabbitType.GOLD, RabbitType.of(4));
        assertEquals(RabbitType.SALT_AND_PEPPER, RabbitType.of(5));

        assertEquals(RabbitType.KILLER_BUNNY, RabbitType.of(99));
    }

}
