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

package java.net.tridentsdk.util;

import net.tridentsdk.util.Vector;
import org.junit.Assert;
import org.junit.Test;

public class VectorTest {

    @Test
    public void testAdd() throws Exception {
        Vector vector = new Vector();
        vector.add(new Vector(10, 10, 10));
        Assert.assertEquals(vector, new Vector(10, 10, 10));
    }

    @Test
    public void testAdd1() throws Exception {
        Vector vector = new Vector();
        vector.add(10d, 10d, 10d);
        Assert.assertEquals(vector, new Vector(10, 10, 10));
    }

    @Test
    public void testAdd2() throws Exception {
        Vector vector = new Vector();
        vector.add(10, 10, 10);
        Assert.assertEquals(vector, new Vector(10, 10, 10));
    }

    @Test
    public void testSubtract() throws Exception {
        Vector vector = new Vector();
        vector.subtract(new Vector(10, 10, 10));
        Assert.assertEquals(vector, new Vector(-10, -10, -10));
    }

    @Test
    public void testSubtract1() throws Exception {
        Vector vector = new Vector();
        vector.subtract(10d, 10d, 10d);
        Assert.assertEquals(vector, new Vector(-10, -10, -10));
    }

    @Test
    public void testSubtract2() throws Exception {
        Vector vector = new Vector();
        vector.subtract(10, 10, 10);
        Assert.assertEquals(vector, new Vector(-10, -10, -10));
    }

    @Test
    public void testMultiply() throws Exception {
        Vector vector = new Vector(5, 5, 5);
        vector.multiply(new Vector(10, 10, 10));
        Assert.assertEquals(vector, new Vector(50, 50, 50));
    }

    @Test
    public void testMultiply1() throws Exception {
        Vector vector = new Vector(5, 5, 5);
        vector.multiply(10d, 10d, 10d);
        Assert.assertEquals(vector, new Vector(50, 50, 50));
    }

    @Test
    public void testMultiply2() throws Exception {
        Vector vector = new Vector(5, 5, 5);
        vector.multiply(10, 10, 10);
        Assert.assertEquals(vector, new Vector(50, 50, 50));
    }

    @Test
    public void testMultiply3() throws Exception {
        Vector vector = new Vector(5, 5, 5);
        vector.multiply(10d);
        Assert.assertEquals(vector, new Vector(50, 50, 50));
    }

    @Test
    public void testDivide() throws Exception {
        Vector vector = new Vector(10, 10, 10);
        vector.divide(new Vector(5, 5, 5));
        Assert.assertEquals(vector, new Vector(2, 2, 2));
    }

    @Test
    public void testDivide1() throws Exception {
        Vector vector = new Vector(10, 10, 10);
        vector.divide(5d, 5d, 5d);
        Assert.assertEquals(vector, new Vector(2, 2, 2));
    }

    @Test
    public void testDivide2() throws Exception {
        Vector vector = new Vector(10, 10, 10);
        vector.divide(5, 5, 5);
        Assert.assertEquals(vector, new Vector(2, 2, 2));
    }

    @Test
    public void testDivide3() throws Exception {
        Vector vector = new Vector(10, 10, 10);
        vector.divide(5d);
        Assert.assertEquals(vector, new Vector(2, 2, 2));
    }

    @Test
    public void testCrossProduct() throws Exception {
        Vector vector = new Vector(5, 10, 15);
        vector.crossProduct(new Vector(20, 25, 30));
        Assert.assertEquals(vector, new Vector(-75, 265, -75));
    }

    @Test
    public void testMagnitudeSquared() throws Exception {
        Vector vector = new Vector(10, 10, 10);
        Assert.assertEquals(vector.magnitudeSquared(), 300, 1);
    }

    @Test
    public void testMagnitude() throws Exception {
        Vector vector = new Vector(24, 24, 24);
        Assert.assertEquals(vector.magnitude(), 41.569, 1);
    }

    @Test
    public void testNormalize() throws Exception {
        Vector vector = new Vector(0, 5, 10);
        vector = vector.normalize();

        Assert.assertEquals(vector.getX(), 0, 0.1);
        Assert.assertEquals(vector.getY(), 0.44, 0.1);
        Assert.assertEquals(vector.getZ(), 0.87, 0.1);
    }

    @Test
    public void testDotProduct() throws Exception {
        Vector vector = new Vector(5, 10, 15);
        Assert.assertEquals(vector.dotProduct(new Vector(20, 25, 30)), 800, 1);
    }

    @Test
    public void testEquals() throws Exception {
        Vector vector = new Vector(10, 10, 10);
        Assert.assertEquals(vector.equals(new Vector(10, 10, 10)), true);
        Assert.assertEquals(vector.equals(new Vector(0, 10, 10)), false);
        Assert.assertEquals(vector.equals(new Vector(10, 0, 10)), false);
        Assert.assertEquals(vector.equals(new Vector(10, 10, 0)), false);
    }
}