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
package net.tridentsdk.chat;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class ChatColorTest {

    @Test
    public void testColorRegistration() {
        for (ChatColor c : ChatColor.values()) {
            assertEquals(c, ChatColor.of(c.getColorChar()));
        }
    }

    @Test
    public void testColorStrings() {
        String colorChar = "\u00A7";
        for (ChatColor c : ChatColor.values()) {
            assertEquals(colorChar + c.getColorChar(), c.toString());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testError() {
        ChatColor.of('g');
    }

}
