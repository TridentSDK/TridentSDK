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

package net.tridentsdk.event.block;

import org.junit.Assert;
import org.junit.Test;

public class SignChangeEventTest {

    @Test
    public void testIsIgnored() throws Exception {
        SignChangeEvent event = new SignChangeEvent(null, null, null);
        Assert.assertEquals(event.isCancelled(), false);

        event.setCancelled(true);
        Assert.assertEquals(event.isCancelled(), true);
    }

    @Test
    public void testSetContents() throws Exception {
        SignChangeEvent event = new SignChangeEvent(null, null, "Line 1", "Line 2", "Line 3", "Line 4");

        Assert.assertEquals(event.getLines()[0], "Line 1");
        Assert.assertEquals(event.getLines()[1], "Line 2");
        Assert.assertEquals(event.getLines()[2], "Line 3");
        Assert.assertEquals(event.getLines()[3], "Line 4");

        event.setLines("New Line 1", "New Line 2", "New Line 3", "New Line 4");

        Assert.assertEquals(event.getLines()[0], "New Line 1");
        Assert.assertEquals(event.getLines()[1], "New Line 2");
        Assert.assertEquals(event.getLines()[2], "New Line 3");
        Assert.assertEquals(event.getLines()[3], "New Line 4");
    }

    @Test
    public void testSetLine() throws Exception {
        SignChangeEvent event = new SignChangeEvent(null, null, "Line 1", "Line 2", "Line 3", "Line 4");

        Assert.assertEquals(event.getLines()[0], "Line 1");
        Assert.assertEquals(event.getLines()[1], "Line 2");
        Assert.assertEquals(event.getLines()[2], "Line 3");
        Assert.assertEquals(event.getLines()[3], "Line 4");

        Assert.assertEquals(event.setLine(0, "New Line 1"), "Line 1");
        Assert.assertEquals(event.setLine(1, "New Line 2"), "Line 2");
        Assert.assertEquals(event.setLine(2, "New Line 3"), "Line 3");
        Assert.assertEquals(event.setLine(3, "New Line 4"), "Line 4");

        Assert.assertEquals(event.getLines()[0], "New Line 1");
        Assert.assertEquals(event.getLines()[1], "New Line 2");
        Assert.assertEquals(event.getLines()[2], "New Line 3");
        Assert.assertEquals(event.getLines()[3], "New Line 4");
    }
}