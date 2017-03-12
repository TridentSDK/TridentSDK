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
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@State(Scope.Benchmark)
public class VectorsTest {
    // close eyes, put hand on numpad to get these values
    // numbers selected from "fair dice roll"
    private static final double CHANGE_TO = -1.382;
    private static final int CHANGE_TO_I = 2919;
    private final Vector vec = new Vector();

    public static Vector rand() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        return new Vector(r.nextDouble(), r.nextDouble(), r.nextDouble());
    }

    @Test
    public void testSetsEqualsHash() {
        this.vec.setX(CHANGE_TO);
        this.vec.setY(CHANGE_TO);
        this.vec.setZ(CHANGE_TO);

        Vector v2 = new Vector(CHANGE_TO, CHANGE_TO, CHANGE_TO);
        assertEquals(v2, this.vec);
        assertEquals(v2.hashCode(), this.vec.hashCode());
        assertFalse(this.vec.equals(new Object()));
    }

    @Test
    public void testSGDouble() {
        this.vec.setX(CHANGE_TO);
        this.vec.setY(CHANGE_TO);
        this.vec.setZ(CHANGE_TO);

        assertEquals(CHANGE_TO, this.vec.getX(), 0);
        assertEquals(CHANGE_TO, this.vec.getY(), 0);
        assertEquals(CHANGE_TO, this.vec.getZ(), 0);
    }

    @Test
    public void testSGInt() {
        this.vec.setX(CHANGE_TO_I);
        this.vec.setY(CHANGE_TO_I);
        this.vec.setZ(CHANGE_TO_I);

        assertEquals(CHANGE_TO_I, this.vec.getIntX());
        assertEquals(CHANGE_TO_I, this.vec.getIntY());
        assertEquals(CHANGE_TO_I, this.vec.getIntZ());
    }

    @Test
    public void testOps() {
        Vector v2 = new Vector(CHANGE_TO, CHANGE_TO, CHANGE_TO);

        this.vec.add(v2);
        this.vec.add(CHANGE_TO, CHANGE_TO, CHANGE_TO);
        this.vec.add(CHANGE_TO_I, CHANGE_TO_I, CHANGE_TO_I);

        this.vec.subtract(v2);
        this.vec.subtract(CHANGE_TO, CHANGE_TO, CHANGE_TO);
        this.vec.subtract(CHANGE_TO_I, CHANGE_TO_I, CHANGE_TO_I);

        this.vec.multiply(v2);
        this.vec.multiply(CHANGE_TO, CHANGE_TO, CHANGE_TO);
        this.vec.multiply(CHANGE_TO_I, CHANGE_TO_I, CHANGE_TO_I);

        this.vec.divide(v2);
        this.vec.divide(CHANGE_TO, CHANGE_TO, CHANGE_TO);
        this.vec.divide(CHANGE_TO_I, CHANGE_TO_I, CHANGE_TO_I);

        assertEquals(new AbstractVector(), this.vec);
    }

    @Test
    public void testIntConstructor() {
        assertEquals(new AbstractVector(0, 0, 0), this.vec);
        assertEquals(new Vector(0, 0, 0), this.vec);
    }

    @Test
    public void testWrite() {
        Vector v2 = new Vector(CHANGE_TO, CHANGE_TO, CHANGE_TO);
        v2.vecWrite(this.vec);
        v2.write(this.vec);

        assertEquals(this.vec, v2);
    }

    @Test
    public void testNormalize() {
        Vector v2 = new Vector(CHANGE_TO_I, CHANGE_TO_I, CHANGE_TO_I);
        v2.normalize();

        assertEquals(1, v2.getMagnitude(), 0);
    }

    @Test
    public void testToString() {
        assertNotNull(this.vec.toString());
    }

    @Test
    public void testToPosition() {
        World world = Mockito.mock(World.class);
        Position position = this.vec.toPosition(world);
        assertEquals(world, position.world());
        assertEquals(position.getX(), this.vec.getX(), 0);
        assertEquals(position.getY(), this.vec.getY(), 0);
        assertEquals(position.getZ(), this.vec.getZ(), 0);
    }

    public static void main(String[] args) {
        m2();
    }

    // Runtime params
    // -server -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining
    public static void m1() {
        Vector vec = new Vector(0, 0, 0);
        int recursions = 600_000_000;
        long modulo = 100;

        p("Attempting to inline add(i, i, i)");

        int curMod = 0;
        for (int i = 0; i < recursions; i++) {
            if ((i & modulo) == 0) {
                curMod = i ^ (int) System.currentTimeMillis();
            }
            vec.add(curMod, curMod, curMod);
        }

        p("Finished attempt at " + (vec.getX() + vec.getY() + vec.getZ()));
    }

    private static void p(String s) {
        System.out.println(s);
    }

    ////////////////////////////////////////////////////////
    // BENCHMARKING ////////////////////////////////////////
    ////////////////////////////////////////////////////////

    public static void m2() {
        Options opt = new OptionsBuilder().include(".*" + VectorsTest.class.getSimpleName() + ".*")
                .timeUnit(TimeUnit.SECONDS)
                .mode(Mode.Throughput)
                .operationsPerInvocation(1)
                .warmupIterations(20)
                // .warmupTime(TimeValue.milliseconds(1))
                .measurementIterations(5)
                // .measurementTime(TimeValue.milliseconds(1))
                .forks(1)
                // .verbosity(VerboseMode.SILENT)
                .threads(4)
                .build();

        Collection<RunResult> results = null;
        try {
            results = new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }

        for (RunResult result : results) {
            System.out.println(result.getPrimaryResult().getLabel() + " - " + result.getPrimaryResult().getScore());
        }
    }

    private int modifier;
    private Vector adder;

    @Setup(Level.Iteration)
    public void setup() {
        this.modifier = ThreadLocalRandom.current().nextInt();
        this.adder = new Vector(this.modifier, this.modifier, this.modifier);
    }

    // @Benchmark
    public void add() {
        this.vec.add(this.modifier, this.modifier, this.modifier);
    }

    // Code duplication - addT - 2.5937485694194634E7

    // invokespecial opcode - addT - 2.4965829257029913E7
    // 27: invokespecial #8 // Method addImpl:(DDD)V

    // Notice the similar times between the invokespecial
    // and code duplication - this is inlining at work
    // what it does is it expands the method and pulls the
    // addImpl operations, injecting them in place of the
    // method call.
    // Ignoring the initial warmup iterations allow us to
    // look at the following jump between a time similar to
    // invokevirtual jump to that of inlined code:
    // # Warmup Iteration   8: 18829839.027 ops/s
    // # Warmup Iteration   9: 25005316.367 ops/s
    // # Warmup Iteration  10: 24855428.458 ops/s
    // # Warmup Iteration  11: 25181116.085 ops/s
    // This is an oversimplification of the JIT due to other
    // potential things that it could do (such as deopts)
    // that cause invokevirtual to be slightly slower than
    // using invokespecial, but I have chosen to ignore them
    // for brevity. Please use -verbose:jit for more info.
    //
    // Also note that these numbers are single-thread values
    // because the contended performance is irrelevant to
    // the raw times produced by any individual thread
    // that acquires the lock immediately. However, due to
    // the fact that this is highly unlikely, I will post
    // numbers running at 4 thread concurrent:
    // addImpl - addT - 9473313.90775613
    // add     - addT - 3940973.8172573545
    // As you can see the speedup provided by delegating
    // down to an ininable method is double and a half the
    // throughput of delegating to a lower implementation.

    // Note to self: javap -v -l -c -p [file] > ..txt
    // -v -l -c
    // Remember your media players when analysing bytecode

    // invokevirtual opcode - addT - 1.873220697301835E7
    @Benchmark
    public void addT() {
        this.vec.add(this.adder);
    }

    @TearDown(Level.Trial)
    public void teardown(Blackhole bh) {
        bh.consume(this.vec.getX() + this.vec.getY() + this.vec.getZ());
    }

    // JVM tuning test dummy
    public static void m3() {
        while (true) {}
    }
}
