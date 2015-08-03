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
package net.tridentsdk.world.gen;

import net.tridentsdk.util.FastRandom;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Used to generate random values for world generators
 *
 * @author The TridentSDK Team
 */
@ThreadSafe
public class GeneratorRandom {
    private final long seed;
    private volatile long next;

    /**
     * Creates a new generator random using a random seed
     */
    public GeneratorRandom() {
        this(FastRandom.random());
    }

    /**
     * Creates a new seeded generator random
     *
     * @param seed the seed
     */
    public GeneratorRandom(long seed) {
        this.seed = seed;
        this.next = seed;
    }

    // non-atomic bit ops, nice.
    private long next() {
        next ^= (next << 21);
        next ^= (next >>> 35);
        next ^= (next << 4);
        return next;
    }

    /**
     * Forks the random using a new random seed generated from this random
     *
     * @return the new generator random
     */
    public GeneratorRandom fork() {
        return new GeneratorRandom(next());
    }

    /**
     * Forks the random using the provided sed
     *
     * @param seed the seed to use
     * @return the new generator random
     */
    public GeneratorRandom fork(long seed) {
        return new GeneratorRandom(seed);
    }

    /**
     * Obtains the next long between 0 and the upper limit (inclusive)
     *
     * @param end the upper bound
     * @return a random long
     */
    public long under(long end) {
        long random = next();
        return Math.abs(random % end);
    }

    /**
     * Scales the next random long between the given min and max values
     *
     * <p>Helps smooth the random distribution curve</p>
     *
     * @param end the upper random bound
     * @param min the minimum
     * @param max the maximum
     * @return the scaled random
     */
    public long scaleUnder(long end, long min, long max) {
        return (long) (((double) (max - min)) * ((under(end) + 1) / 2)) + min;
    }

    /**
     * Obtains the seed used to generate the random longs
     *
     * @return the seed
     */
    public long seed() {
        return this.seed;
    }
}