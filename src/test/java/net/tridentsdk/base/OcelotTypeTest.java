/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
