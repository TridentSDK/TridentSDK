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

package java.net.tridentsdk.base;

import com.google.common.collect.Sets;
import net.tridentsdk.Coordinates;
import net.tridentsdk.base.Substance;
import net.tridentsdk.base.Tile;
import net.tridentsdk.base.TileSnapshot;
import net.tridentsdk.entity.projectile.Projectile;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.tridentsdk.base.impl.WorldImpl;
import java.util.Set;

public class TileSnapshotTest {
    private final Tile tile = Mockito.mock(Tile.class);
    private final Set<Projectile> projectiles = Sets.newHashSet(Mockito.mock(Projectile.class));
    private Substance substance = Substance.ACACIA_STAIRS;

    {
        Mockito.when(tile.getLocation()).thenReturn(Coordinates.create(new WorldImpl(), 0, 0, 0));
        Mockito.when(tile.getSubstance()).thenReturn(substance);
        Mockito.when(tile.projectiles()).thenReturn(projectiles);
    }

    @Test
    public void create() {
        Assert.assertNotNull(TileSnapshot.of(tile));
    }

    @Test
    public void load() {
        tile.setSubstance(Substance.BLAZE_ROD);
        tile.setMeta((byte) 0x5F);
        for (int i = 0; i < 10; i++) {
            tile.put(Mockito.mock(Projectile.class));
        }
        TileSnapshot.of(tile).load();
        Assert.assertEquals(tile.getSubstance(), Substance.ACACIA_STAIRS);
        Assert.assertEquals(tile.getMeta(), 0);
        Assert.assertEquals(tile.projectiles().size(), 1);
    }
}
