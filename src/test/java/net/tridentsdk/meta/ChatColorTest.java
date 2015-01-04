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
        Assert.assertEquals(ChatColor.BLACK.toString(), "black");
        Assert.assertEquals(ChatColor.DARK_BLUE.toString(), "dark_blue");
        Assert.assertEquals(ChatColor.DARK_GREEN.toString(), "dark_green");
        Assert.assertEquals(ChatColor.DARK_AQUA.toString(), "dark_aqua");
        Assert.assertEquals(ChatColor.DARK_RED.toString(), "dark_red");
        Assert.assertEquals(ChatColor.DARK_PURPLE.toString(), "dark_purple");
        Assert.assertEquals(ChatColor.GOLD.toString(), "gold");
        Assert.assertEquals(ChatColor.GRAY.toString(), "gray");
        Assert.assertEquals(ChatColor.DARK_GRAY.toString(), "dark_gray");
        Assert.assertEquals(ChatColor.BLUE.toString(), "blue");
        Assert.assertEquals(ChatColor.GREEN.toString(), "green");
        Assert.assertEquals(ChatColor.AQUA.toString(), "aqua");
        Assert.assertEquals(ChatColor.RED.toString(), "red");
        Assert.assertEquals(ChatColor.LIGHT_PURPLE.toString(), "light_purple");
        Assert.assertEquals(ChatColor.WHITE.toString(), "white");
        Assert.assertEquals(ChatColor.OBFUSCATED.toString(), "obfuscated");
        Assert.assertEquals(ChatColor.BOLD.toString(), "bold");
        Assert.assertEquals(ChatColor.STRIKETHROUGH.toString(), "strikethrough");
        Assert.assertEquals(ChatColor.UNDERLINE.toString(), "underline");
        Assert.assertEquals(ChatColor.ITALIC.toString(), "italic");
        Assert.assertEquals(ChatColor.RESET.toString(), "reset");
    }
}