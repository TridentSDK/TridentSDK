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

import net.tridentsdk.entity.types.OcelotType;
import org.junit.Assert;
import org.junit.Test;

public class OcelotTypeTest {

    @Test
    public void testAsInt() throws Exception {
        Assert.assertEquals(OcelotType.WILD.asInt(), 0);
        Assert.assertEquals(OcelotType.TUXEDO.asInt(), 1);
        Assert.assertEquals(OcelotType.TABBY.asInt(), 2);
        Assert.assertEquals(OcelotType.SIAMESE.asInt(), 3);
    }
}