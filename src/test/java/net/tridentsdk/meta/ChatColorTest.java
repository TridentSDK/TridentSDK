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

package net.tridentsdk.meta;

import org.junit.Assert;
import org.junit.Test;

public class ChatColorTest {
    @Test
    public void testToString() throws Exception {
        Assert.assertEquals(ChatColor.BLACK.name().toLowerCase(), "black");
        Assert.assertEquals(ChatColor.DARK_BLUE.name().toLowerCase(), "dark_blue");
        Assert.assertEquals(ChatColor.DARK_GREEN.name().toLowerCase(), "dark_green");
        Assert.assertEquals(ChatColor.DARK_AQUA.name().toLowerCase(), "dark_aqua");
        Assert.assertEquals(ChatColor.DARK_RED.name().toLowerCase(), "dark_red");
        Assert.assertEquals(ChatColor.DARK_PURPLE.name().toLowerCase(), "dark_purple");
        Assert.assertEquals(ChatColor.GOLD.name().toLowerCase(), "gold");
        Assert.assertEquals(ChatColor.GRAY.name().toLowerCase(), "gray");
        Assert.assertEquals(ChatColor.DARK_GRAY.name().toLowerCase(), "dark_gray");
        Assert.assertEquals(ChatColor.BLUE.name().toLowerCase(), "blue");
        Assert.assertEquals(ChatColor.GREEN.name().toLowerCase(), "green");
        Assert.assertEquals(ChatColor.AQUA.name().toLowerCase(), "aqua");
        Assert.assertEquals(ChatColor.RED.name().toLowerCase(), "red");
        Assert.assertEquals(ChatColor.LIGHT_PURPLE.name().toLowerCase(), "light_purple");
        Assert.assertEquals(ChatColor.WHITE.name().toLowerCase(), "white");
        Assert.assertEquals(ChatColor.OBFUSCATED.name().toLowerCase(), "obfuscated");
        Assert.assertEquals(ChatColor.BOLD.name().toLowerCase(), "bold");
        Assert.assertEquals(ChatColor.STRIKETHROUGH.name().toLowerCase(), "strikethrough");
        Assert.assertEquals(ChatColor.UNDERLINE.name().toLowerCase(), "underline");
        Assert.assertEquals(ChatColor.ITALIC.name().toLowerCase(), "italic");
        Assert.assertEquals(ChatColor.RESET.name().toLowerCase(), "reset");
    }
}