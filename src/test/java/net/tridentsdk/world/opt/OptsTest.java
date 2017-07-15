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
package net.tridentsdk.world.opt;

import org.junit.Test;

import static net.tridentsdk.world.opt.GameRule.*;
import static org.junit.Assert.*;

public class OptsTest {
    @Test(expected = IllegalArgumentException.class)
    public void difficultyTest() {
        assertEquals(3, Difficulty.HARDCORE.asByte());
        assertEquals(0, Difficulty.PEACEFUL.asByte());
        assertEquals(1, Difficulty.EASY.asByte());
        assertEquals(2, Difficulty.NORMAL.asByte());
        assertEquals(3, Difficulty.HARD.asByte());
        assertEquals(Difficulty.HARD, Difficulty.from(3));
        Difficulty.from(30);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void gameModeTest() {
        assertEquals(0, GameMode.SURVIVAL.asInt());
        assertEquals(1, GameMode.CREATIVE.asByte());
        assertEquals(2, GameMode.ADVENTURE.asByte());
        assertEquals(3, GameMode.SPECTATOR.asByte());
        assertEquals(GameMode.SURVIVAL, GameMode.from(0));
        GameMode.from(30);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void dimensionTest() {
        assertEquals(-1, Dimension.NETHER.asInt());
        assertEquals(0, Dimension.OVERWORLD.asByte());
        assertEquals(1, Dimension.END.asByte());
        assertEquals(Dimension.OVERWORLD, Dimension.from(0));
        Dimension.from(30);
    }

    @Test(expected = IllegalArgumentException.class)
    public void gameRuleTest() {
        assertTrue(CMD_BLOCK_OUTPUT.getDefault());
        assertTrue(MOVE_CHECK.getDefault());
        assertTrue(DAYLIGHT_CYCLE.getDefault());
        assertTrue(FIRE_TICK.getDefault());
        assertTrue(MOB_LOOT.getDefault());
        assertTrue(MOB_SPAWN.getDefault());
        assertTrue(TILE_DROP.getDefault());
        assertFalse(KEEP_INVENTORY.getDefault());
        assertTrue(LOG_ADMIN_CMDS.getDefault());
        assertTrue(MOB_GRIEF.getDefault());
        assertTrue(NATURAL_REGEN.getDefault());
        assertEquals(3, RANDOM_TICK_SPEED.getDefault().intValue());
        assertTrue(SEND_CMD_FEEDBACK.getDefault());
        assertTrue(SHOW_DEATH_MSG.getDefault());
        assertEquals(CMD_BLOCK_OUTPUT, from(CMD_BLOCK_OUTPUT.toString()));
        from("tridentsdk");
    }

    @Test(expected = IllegalArgumentException.class)
    public void levelTypeTest() {
        assertEquals("default", LevelType.DEFAULT.toString());
        assertEquals("flat", LevelType.FLAT.toString());
        assertEquals("largeBiomes", LevelType.LARGE_BIOMES.toString());
        assertEquals("amplified", LevelType.AMPLIFIED.toString());
        assertEquals("default_1_1", LevelType.DEBUG.toString());
        assertEquals(LevelType.DEFAULT, LevelType.from("default"));
        LevelType.from("tridentsdk");
    }

    @Test
    public void gameRuleValueTest() {
        GameRuleMap map = new GameRuleMap();
        map.set(DAYLIGHT_CYCLE, false);
        map.set(SEND_CMD_FEEDBACK, false);
        assertTrue(map.isSet(DAYLIGHT_CYCLE));
        assertTrue(map.isSet(SEND_CMD_FEEDBACK));
        assertFalse(map.get(DAYLIGHT_CYCLE));
        map.reset(DAYLIGHT_CYCLE);
        assertTrue(map.get(DAYLIGHT_CYCLE));
        assertFalse(map.get(SEND_CMD_FEEDBACK));
        map.resetAll();
        assertTrue(map.get(SEND_CMD_FEEDBACK));

        GameRuleMap map2 = new GameRuleMap();
        map2.set(DAYLIGHT_CYCLE, false);
        map2.set(SEND_CMD_FEEDBACK, false);
        map.copyTo(map2);
        assertFalse(map2.get(DAYLIGHT_CYCLE));
        assertFalse(map2.get(SEND_CMD_FEEDBACK));
    }
}
