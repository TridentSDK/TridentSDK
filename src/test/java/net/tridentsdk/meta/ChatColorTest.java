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
        Assert.assertEquals(ChatColor.BLACK.name(), "black");
        Assert.assertEquals(ChatColor.DARK_BLUE.name(), "dark_blue");
        Assert.assertEquals(ChatColor.DARK_GREEN.name(), "dark_green");
        Assert.assertEquals(ChatColor.DARK_AQUA.name(), "dark_aqua");
        Assert.assertEquals(ChatColor.DARK_RED.name(), "dark_red");
        Assert.assertEquals(ChatColor.DARK_PURPLE.name(), "dark_purple");
        Assert.assertEquals(ChatColor.GOLD.name(), "gold");
        Assert.assertEquals(ChatColor.GRAY.name(), "gray");
        Assert.assertEquals(ChatColor.DARK_GRAY.name(), "dark_gray");
        Assert.assertEquals(ChatColor.BLUE.name(), "blue");
        Assert.assertEquals(ChatColor.GREEN.name(), "green");
        Assert.assertEquals(ChatColor.AQUA.name(), "aqua");
        Assert.assertEquals(ChatColor.RED.name(), "red");
        Assert.assertEquals(ChatColor.LIGHT_PURPLE.name(), "light_purple");
        Assert.assertEquals(ChatColor.WHITE.name(), "white");
        Assert.assertEquals(ChatColor.OBFUSCATED.name(), "obfuscated");
        Assert.assertEquals(ChatColor.BOLD.name(), "bold");
        Assert.assertEquals(ChatColor.STRIKE_THROUGH.name(), "strikethrough");
        Assert.assertEquals(ChatColor.UNDERLINE.name(), "underline");
        Assert.assertEquals(ChatColor.ITALIC.name(), "italic");
        Assert.assertEquals(ChatColor.RESET.name(), "reset");
    }
}