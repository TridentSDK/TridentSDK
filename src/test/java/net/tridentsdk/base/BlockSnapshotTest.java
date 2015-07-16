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

package net.tridentsdk.base;

import net.tridentsdk.Position;
import net.tridentsdk.world.World;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class BlockSnapshotTest {
    private final Block block = Mockito.mock(Block.class);
    private Substance substance = Substance.ACACIA_STAIRS;

    {
        World world = Mockito.mock(World.class);

        Mockito.when(world.getBlockAt(Position.create(world, 0, 0, 0))).thenReturn(Mockito.mock(Block.class));
        Mockito.when(block.getPosition()).thenReturn(Position.create(world, 0, 0, 0));
        Mockito.when(block.getSubstance()).thenReturn(substance);
    }

    @Test
    public void create() {
        Assert.assertNotNull(BlockSnapshot.of(block));
    }

    @Test
    public void load() {
        block.setSubstance(Substance.BLAZE_ROD);
        block.setMeta((byte) 0x5F);
        BlockSnapshot.of(block).load();

        Assert.assertEquals(block.getSubstance(), Substance.ACACIA_STAIRS);
        Assert.assertEquals(block.getMeta(), 0);
    }
}
