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

package net.tridentsdk.base.impl;

import net.tridentsdk.Coordinates;
import net.tridentsdk.base.Substance;
import net.tridentsdk.base.Tile;
import net.tridentsdk.entity.decorate.Impalable;
import net.tridentsdk.util.Vector;
import org.mockito.Mockito;

public class TileImpl implements Tile {
    private Substance substance = Substance.ACACIA_STAIRS;
    private Impalable impalable = Mockito.mock(Impalable.class);
    private byte meta;

    @Override
    public Substance substance() {
        return substance;
    }

    @Override
    public void setSubstance(Substance material) {
        this.substance = material;
    }

    @Override
    public Coordinates location() {
        return Coordinates.create(new WorldImpl(), 0, 0, 0);
    }

    @Override
    public byte meta() {
        return meta;
    }

    @Override
    public void setMeta(byte data) {
        this.meta = data;
    }

    @Override
    public Tile relativeTile(Vector vector) {
        return new TileImpl();
    }

    @Override
    public Impalable asImpalable() {
        return impalable;
    }
}
