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
