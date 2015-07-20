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

package net.tridentsdk.util;

/**
 * A fast random class which generates medium-quality pseudo-random values
 *
 * <p>Normally such a class should have been implemented upon using the time system with currentTimeMills(), however
 * this was avoided for two reasons:
 * <ul>
 * <li>Security - If the timer did not update in time, a malicious user may use that same random. While this is not
 * designed as a cryptographically secure random, malice is a strong enough reason to use nanoTime()</li>
 * <li>Randomness - Using successive calls to this random may produce the same random number. The occurrance of this
 * depends on the overhead of the method call, in which case a full millisecond has not passed yet, rather than the
 * possibility that the timer has not updated (which again, is another plausible reason for this)</li>
 * </ul></p>
 *
 * <p>Note that the methods which produce a random using the hash is not a seed. The next number is never ever
 * consistent unless nanoTime returns the same value and the hashed object is the same. The methods which do not
 * provide
 * a hash do not create a new object, rather it does not hash the nanoTime at all.</p>
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public class FastRandom {
    private FastRandom() {
    }

    /**
     * Finds a random number
     *
     * @return the random number
     */
    public static long random() {
        long x = System.nanoTime();
        x ^= (x << 21);
        x ^= (x >>> 35);
        x ^= (x << 4);
        return (x > 0) ? x : -x;
    }

    /**
     * Finds a random number with an upper limit
     *
     * @param upper the returned value is < upper
     * @return the random number
     */
    public static long random(int upper) {
        long x = System.nanoTime();
        x ^= (x << 21);
        x ^= (x >>> 35);
        x ^= (x << 4);

        long rand = x % upper;
        return (rand > 0) ? rand : -rand;
    }

    /**
     * Finds a random number with a hash
     *
     * @param hash the object to randomize the hash with hashCode
     * @return the random number
     */
    public static long random(Object hash) {
        long x = System.nanoTime() ^ hash.hashCode();
        x ^= (x << 21);
        x ^= (x >>> 35);
        x ^= (x << 4);
        return (x > 0) ? x : -x;
    }

    /**
     * Finds a random number with a hash
     *
     * @param upper the returned value is < upper
     * @param hash  the object to randomize the seed with hashCode
     * @return the random number
     */
    public static long random(int upper, Object hash) {
        long x = System.nanoTime() ^ hash.hashCode();
        x ^= (x << 21);
        x ^= (x >>> 35);
        x ^= (x << 4);

        long rand = x % upper;
        return (rand > 0) ? rand : -rand;
    }
}
