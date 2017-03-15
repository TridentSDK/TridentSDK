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
package net.tridentsdk.base;

import net.tridentsdk.world.World;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class PositionTest {
    private final World world = Mockito.mock(World.class);
    
    @Test
    public void testWorld() {
        assertNotNull(new Position(this.world).world());
    }

    @Test
    public void testYaw() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        float yaw = r.nextFloat();
        assertEquals(yaw, new Position(this.world,
                r.nextDouble(),
                r.nextDouble(),
                r.nextDouble(),
                yaw, r.nextFloat()).getYaw(), 0);
    }

    @Test
    public void testPitch() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        float pitch = r.nextFloat();
        assertEquals(pitch, new Position(this.world,
                r.nextDouble(),
                r.nextDouble(),
                r.nextDouble(),
                r.nextFloat(), pitch).getPitch(), 0);
    }

    @Test
    public void testTheOtherConstructor() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        double x = r.nextDouble();
        double y = r.nextDouble();
        double z = r.nextDouble();
        assertEquals(new Position(this.world, x, y, z), new Position(this.world, x, y, z, 0F, 0F));
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
        Block block = position.getBlock();
        assertEquals(this.world.getBlockAt(position.getIntX(), position.getIntY(), position.getIntZ()), block);
    }

    @Test
    public void distance() {
        Position p0 = new Position(this.world);
        Position p1 = new Position(this.world, 0, 10, 0);
        assertEquals(10, p0.distance(p1), 0);
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
        assertEquals(1, p0.getX(), 0);
        assertEquals(2, p0.getY(), 0);
        assertEquals(3, p0.getZ(), 0);
        assertEquals(4, p0.getYaw(), 0);
        assertEquals(5, p0.getPitch(), 0);

        assertEquals(p0.getIntX() / 16, p0.getChunkX());
        assertEquals(p0.getIntZ() / 16, p0.getChunkZ());

        p0.set(1D, 2D, 3D);
        assertEquals(1, p0.getX(), 0);
        assertEquals(2, p0.getY(), 0);
        assertEquals(3, p0.getZ(), 0);
        assertEquals(4, p0.getYaw(), 0);
        assertEquals(5, p0.getPitch(), 0);

        assertEquals(p0.getIntX() / 16, p0.getChunkX());
        assertEquals(p0.getIntZ() / 16, p0.getChunkZ());
    }

    @Test
    public void testClone() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        double x = r.nextDouble();
        double y = r.nextDouble();
        double z = r.nextDouble();
        float yaw = r.nextFloat();
        float pitch = r.nextFloat();
        Position p0 = new Position(this.world, x, y, z, yaw, pitch);

        assertEquals(p0, p0.clone());
    }

    @Test
    public void testToWorldVec() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        double x = r.nextDouble();
        double y = r.nextDouble();
        double z = r.nextDouble();

        Position p = new Position(this.world, x, y, z);
        ImmutableWorldVector vec = p.toWorldVector();

        assertEquals(vec.getWorld(), p.world());
        assertEquals(vec.getX(), p.getIntX());
        assertEquals(vec.getY(), p.getIntY());
        assertEquals(vec.getZ(), p.getIntZ());
    }
}