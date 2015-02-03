/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2014 The TridentSDK Team
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

package net.tridentsdk.entity;

import org.junit.Assert;
import org.junit.Test;

public class RabbitTypeTest {

    @Test
    public void testToInt() throws Exception {
        Assert.assertEquals(RabbitType.BROWN.asInt(), 0);
        Assert.assertEquals(RabbitType.WHITE.asInt(), 1);
        Assert.assertEquals(RabbitType.BLACK.asInt(), 2);
        Assert.assertEquals(RabbitType.WHITE_AND_BLACK.asInt(), 3);
        Assert.assertEquals(RabbitType.GOLD.asInt(), 4);
        Assert.assertEquals(RabbitType.SALT_AND_PEPPER.asInt(), 5);
        Assert.assertEquals(RabbitType.KILLER_RABBIT.asInt(), 99);
    }

    @Test
    public void testAsInt() throws Exception {
        Assert.assertEquals(RabbitType.asInt(RabbitType.BROWN), 0);
        Assert.assertEquals(RabbitType.asInt(RabbitType.WHITE), 1);
        Assert.assertEquals(RabbitType.asInt(RabbitType.BLACK), 2);
        Assert.assertEquals(RabbitType.asInt(RabbitType.WHITE_AND_BLACK), 3);
        Assert.assertEquals(RabbitType.asInt(RabbitType.GOLD), 4);
        Assert.assertEquals(RabbitType.asInt(RabbitType.SALT_AND_PEPPER), 5);
        Assert.assertEquals(RabbitType.asInt(RabbitType.KILLER_RABBIT), 99);
    }
}