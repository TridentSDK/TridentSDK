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

package java.net.tridentsdk.event.block;

import net.tridentsdk.event.block.BedExplodeEvent;
import org.junit.Assert;
import org.junit.Test;

public class BedExplodeEventTest {

    @Test
    public void testIsIgnored() throws Exception {
        BedExplodeEvent event = new BedExplodeEvent(null, 10);
        Assert.assertEquals(event.isIgnored(), false);

        event.cancel(true);
        Assert.assertEquals(event.isIgnored(), true);
    }

    @Test
    public void testGetStrength() throws Exception {
        BedExplodeEvent event = new BedExplodeEvent(null, 10);
        Assert.assertEquals(event.getStrength(), 10, 1);

        event.setStrength(20);
        Assert.assertEquals(event.getStrength(), 20, 1);
    }
}