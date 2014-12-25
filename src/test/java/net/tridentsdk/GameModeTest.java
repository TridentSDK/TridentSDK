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

package java.net.tridentsdk;

import net.tridentsdk.GameMode;
import org.junit.Assert;
import org.junit.Test;

public class GameModeTest {

    @Test
    public void testToByte() throws Exception {
        Assert.assertEquals(GameMode.SURVIVAL.toByte(), 0);
        Assert.assertEquals(GameMode.CREATIVE.toByte(), 1);
        Assert.assertEquals(GameMode.ADVENTURE.toByte(), 2);
        Assert.assertEquals(GameMode.SPECTATE.toByte(), 3);
        Assert.assertEquals(GameMode.HARDCORE.toByte(), 8);
    }

    @Test
    public void testToByte1() throws Exception {
        Assert.assertEquals(GameMode.toByte(GameMode.SURVIVAL), 0);
        Assert.assertEquals(GameMode.toByte(GameMode.CREATIVE), 1);
        Assert.assertEquals(GameMode.toByte(GameMode.ADVENTURE), 2);
        Assert.assertEquals(GameMode.toByte(GameMode.SPECTATE), 3);
        Assert.assertEquals(GameMode.toByte(GameMode.HARDCORE), 8);
    }

    @Test
    public void testGetGameMode() throws Exception {
        Assert.assertEquals(GameMode.getGameMode(0), GameMode.SURVIVAL);
        Assert.assertEquals(GameMode.getGameMode(1), GameMode.CREATIVE);
        Assert.assertEquals(GameMode.getGameMode(2), GameMode.ADVENTURE);
        Assert.assertEquals(GameMode.getGameMode(3), GameMode.SPECTATE);
        Assert.assertEquals(GameMode.getGameMode(8), GameMode.HARDCORE);
        Assert.assertEquals(GameMode.getGameMode(10), null);
    }
}