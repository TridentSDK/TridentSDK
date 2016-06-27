/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2016 The TridentSDK Team
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

import net.tridentsdk.world.opts.Difficulty;
import net.tridentsdk.world.opts.GameMode;
import net.tridentsdk.world.opts.GameRuleValue;
import org.junit.Test;

import static net.tridentsdk.world.opts.GameRule.*;
import static org.junit.Assert.*;

public class OptsTest {
    @Test(expected = IllegalArgumentException.class)
    public void difficultyTest() {
        assertEquals(-1, Difficulty.HARDCORE.asByte());
        assertEquals(0, Difficulty.PEACEFUL.asByte());
        assertEquals(1, Difficulty.EASY.asByte());
        assertEquals(2, Difficulty.NORMAL.asByte());
        assertEquals(3, Difficulty.HARD.asByte());
        assertEquals(Difficulty.PEACEFUL, Difficulty.from(0));
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

    @Test(expected = IllegalArgumentException.class)
    public void gameRuleTest() {
        assertTrue(CMD_BLOCK_OUTPUT.defValue().asBool());
        assertTrue(MOVE_CHECK.defValue().asBool());
        assertTrue(DAYLIGHT_CYCLE.defValue().asBool());
        assertTrue(FIRE_TICK.defValue().asBool());
        assertTrue(MOB_LOOT.defValue().asBool());
        assertTrue(MOB_SPAWN.defValue().asBool());
        assertTrue(TILE_DROP.defValue().asBool());
        assertFalse(KEEP_INVENTORY.defValue().asBool());
        assertTrue(LOG_ADMIN_CMDS.defValue().asBool());
        assertTrue(MOB_GRIEF.defValue().asBool());
        assertTrue(NATURAL_REGEN.defValue().asBool());
        assertEquals(3, RANDOM_TICK_SPEED.defValue().asNumber());
        assertTrue(SEND_CMD_FEEDBACK.defValue().asBool());
        assertTrue(SHOW_DEATH_MSG.defValue().asBool());
        assertEquals(CMD_BLOCK_OUTPUT, from(CMD_BLOCK_OUTPUT.toString()));
        from("tridentsdk");
    }

    @Test
    public void gameRuleValueTest() {
        GameRuleValue bool = GameRuleValue.bool(true);
        assertTrue(bool.isBool());
        assertTrue(bool.asBool());

        GameRuleValue num = GameRuleValue.number(3);
        assertTrue(num.isNumber());
        assertEquals(3, num.asNumber());
    }
}