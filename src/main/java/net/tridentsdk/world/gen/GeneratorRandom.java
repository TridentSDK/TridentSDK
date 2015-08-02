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

import javax.annotation.concurrent.ThreadSafe;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Used to generate random values for world generators
 *
 * @author The TridentSDK Team
 */
@ThreadSafe
public class GeneratorRandom {
    private static final ThreadLocalRandom SEED_SOURCE = ThreadLocalRandom.current();

    private final long seed;
    private final int randomInt;
    private final Random random;
    private final ThreadLocal<GeneratorRandom> randomThreadLocal = new ThreadLocal<GeneratorRandom>() {
        @Override
        protected GeneratorRandom initialValue() {
            return new GeneratorRandom(seed());
        }
    };

    /**
     * Creates a new generator random using the internal seed source
     */
    public GeneratorRandom() {
        this(SEED_SOURCE.nextLong());
    }

    /**
     * Creates a new seeded generator random
     *
     * @param seed the seed
     */
    public GeneratorRandom(long seed) {
        this.random = new Random(seed);
        this.seed = seed;
        this.randomInt = next();
    }

    /**
     * Obtains the internal random used to generate values
     *
     * @return the internal random
     */
    public Random internal() {
        return random;
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
     * Obtains the thread-specific random
     *
     * @return the random
     */
    public GeneratorRandom get() {
        return randomThreadLocal.get();
    }

    /**
     * Obtains the next int in the random sequence
     *
     * @return a random int
     */
    public int next() {
        return random.nextInt();
    }

    /**
     * Obtains the next int given the inclusive bounds
     *
     * @param start the lower bound
     * @param end the upper bound
     * @return a random int
     */
    public int between(int start, int end) {
        return random.nextInt((end - start) + 1) + start;
    }

    /**
     * Obtains the next int between 0 and the upper limit (inclusive)
     *
     * @param end the upper bound
     * @return a random int
     */
    public int under(int end) {
        return between(0, end);
    }

    /**
     * Scales the next random int between the given min and max values
     *
     * <p>Helps smooth the random distribution curve</p>
     *
     * @param min the minimum
     * @param max the maximum
     * @return the scaled random
     */
    public int scale(int min, int max) {
        return (int) (((double)(max - min)) * ((next() + 1) / 2)) + min;
    }

    /**
     * Scales the random int bound by the start and end between the given min and max values
     *
     * <p>Helps smooth the random distribution curve</p>
     *
     * @param start the lower random bound
     * @param end the upper random bound
     * @param min the minimum
     * @param max the maximum
     * @return the scaled random
     */
    public int scaleBetween(int start, int end, int min, int max) {
        return (int) (((double)(max - min)) * ((between(start, end) + 1) / 2)) + min;
    }

    /**
     * Scales the next random int between the given min and max values
     *
     * <p>Helps smooth the random distribution curve</p>
     *
     * @param end the upper random bound
     * @param min the minimum
     * @param max the maximum
     * @return the scaled random
     */
    public int scaleUnder(int end, int min, int max) {
        return scaleBetween(0, end, min, max);
    }

    /**
     * Obtains the initial random int. This is semantically equivalent to calling
     * {@link #next()} as soon as the random has been generated
     *
     * @return a random int (will always remain constant with this instance)
     */
    public int value() {
        return randomInt;
    }

    /**
     * Obtains the seed used to generate the random ints
     *
     * @return the seed
     */
    public long seed() {
        return this.seed;
    }
}