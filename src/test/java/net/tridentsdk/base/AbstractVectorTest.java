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

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.openjdk.jmh.runner.options.VerboseMode;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class AbstractVectorTest {
    private static final double CHANGE_TO = -1.382;
    private final AbstractVector<?> vec = new AbstractVector<>();

    @Test
    public void testSetsEqualsHash() {
        this.vec.setX(CHANGE_TO);
        this.vec.setY(CHANGE_TO);
        this.vec.setZ(CHANGE_TO);

        AbstractVector<?> v2 = new AbstractVector<>(CHANGE_TO, CHANGE_TO, CHANGE_TO);
        Assert.assertEquals(v2, this.vec);
        Assert.assertEquals(v2.hashCode(), this.vec.hashCode());
    }

    @Test
    public void testGets() {
        Assert.assertEquals(CHANGE_TO, this.vec.x(), 0);
        Assert.assertEquals(CHANGE_TO, this.vec.y(), 0);
        Assert.assertEquals(CHANGE_TO, this.vec.z(), 0);
    }

    @Test
    public void testOps() {
        AbstractVector<?> v2 = new AbstractVector<>(CHANGE_TO, CHANGE_TO, CHANGE_TO);
        // TODO
    }

    public static void main(String[] args) {
        m2();
    }

    // Runtime params
    // -server -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining
    public static void m1() {
        AbstractVector<?> vec = new AbstractVector<>(0, 0, 0);
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

        p("Finished attempt at " + (vec.x() + vec.y() + vec.z()));
    }

    private static void p(String s) {
        System.out.println(s);
    }

    ////////////////////////////////////////////////////////
    // BENCHMARKING ////////////////////////////////////////
    ////////////////////////////////////////////////////////

    public static void m2() {
        Options opt = new OptionsBuilder().include(".*" + AbstractVectorTest.class.getSimpleName() + ".*")
                .timeUnit(TimeUnit.NANOSECONDS)
                .mode(Mode.AverageTime)
                .operationsPerInvocation(1)
                .warmupIterations(100_000)
                .warmupTime(TimeValue.nanoseconds(100_000))
                .measurementIterations(300_000_000)
                .measurementTime(TimeValue.nanoseconds(100_000))
                .forks(1)
                .verbosity(VerboseMode.SILENT)
                .threads(2)
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

    private int modifier = 0;

    @Setup(Level.Iteration)
    public void setup() {
        this.modifier = ThreadLocalRandom.current().nextInt();
    }

    @Benchmark
    public void test() {
        this.vec.add(this.modifier, this.modifier, this.modifier);
    }

    @TearDown(Level.Trial)
    public void teardown(Blackhole bh) {
        bh.consume(this.vec.x() + this.vec.y() + this.vec.z());
    }
}