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
package net.tridentsdk.base;

import net.tridentsdk.world.World;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class PositionTest {
    private final World world = Mockito.mock(World.class);
    
    @Test
    public void testWorld() {
        assertNotNull(new Position(this.world).world());
    }

    @Test
    public void testYaw() {
        assertEquals(0, new Position(this.world, 0D, 0D, 0D, 0F, 0F).yaw(), 0);
    }

    @Test
    public void testPitch() {
        assertEquals(0, new Position(this.world, 0, 0, 0).pitch(), 0);
    }

    @Test
    public void testTheOtherConstructor() {
        assertEquals(new Position(this.world, 0D, 0D, 0D), new Position(this.world, 0D, 0D, 0D));
    }

    @Test
    public void testWrite() {
        Position p0 = new Position(this.world);
        Position p1 = new Position(this.world);

        Vector vector = VectorsTest.rand();
        vector.vecWrite(p0);

        p0.write(p1);
        assertEquals(p0, p1);
    }

    @Test
    public void testBlock() {
        Position position = new Position(this.world);
        Block block = position.block();
        assertEquals(this.world.blockAt(position.intX(), position.intY(), position.intZ()), block);
    }

    @Test
    public void distance() {
        Position p0 = new Position(this.world);
        Position p1 = new Position(this.world, 0, 1, 0);
        assertEquals(1, p0.distance(p1), 0);
    }

    @Test
    public void testEqualsHashString() {
        Position p0 = new Position(this.world);
        Position p1 = new Position(this.world);

        Vector vector = VectorsTest.rand();
        vector.vecWrite(p0);
        vector.vecWrite(p1);

        assertEquals(p0, p1);
        assertNotEquals(p0, VectorsTest.rand());
        assertEquals(p0.hashCode(), p1.hashCode());
        assertEquals(p0.toString(), p1.toString());
    }

    @Test
    public void testSettersAndGetters() {
        Position p0 = new Position(this.world);
        p0.set(1, 2, 3);
        p0.setYaw(4);
        p0.setPitch(5);
        assertEquals(1, p0.x(), 0);
        assertEquals(2, p0.y(), 0);
        assertEquals(3, p0.z(), 0);
        assertEquals(4, p0.yaw(), 0);
        assertEquals(5, p0.pitch(), 0);

        assertEquals(p0.intX() / 16, p0.getChunkX());
        assertEquals(p0.intZ() / 16, p0.getChunkZ());
    }

    @Test
    public void testClone() {
        Position p0 = new Position(this.world);
        assertEquals(p0, p0.clone());
    }

}
