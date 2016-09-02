package net.tridentsdk.base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class OcelotTypeTest {

    @Test
    public void testTypes() {
        for (OcelotType type : OcelotType.values()) {
            assertEquals(type, OcelotType.of(type.getData()));
        }
    }

    @Test
    public void testIndex() {
        for (int i = 0; i <= 3; i++) {
            OcelotType.of(i);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testError() {
        OcelotType.of(4);
    }

    @Test
    public void testData() {
        assertEquals(OcelotType.WILD, OcelotType.of(0));
        assertEquals(OcelotType.TUXEDO, OcelotType.of(1));
        assertEquals(OcelotType.TABBY, OcelotType.of(2));
        assertEquals(OcelotType.SIAMESE, OcelotType.of(3));
    }

}
