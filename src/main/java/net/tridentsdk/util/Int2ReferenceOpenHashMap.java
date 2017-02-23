/*
 * Copyright (C) 2002-2016 Sebastiano Vigna
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.tridentsdk.util;

import net.tridentsdk.doc.Policy;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * Minimal class of the one included in fastutil, Vilsol got
 * salty about 40MB dependency and wouldn't shade it lmao.
 *
 * Summary of changes made:
 * - If its deprecated its removed
 * - If its never used its removed
 * - If its an error remove the whole thing
 * - Change all the default ret values
 * - If it comes from another class put it first
 * - If it extends another class remove whatever it extends
 * - Otherwise its copy pasted
 * - This class isn't ours! Check the license header
 */
@Policy("license change")
@NotThreadSafe
public class Int2ReferenceOpenHashMap<V> {
    /**
     * 2<sup>32</sup> &middot; &phi;, &phi; = (&#x221A;5
     * &minus; 1)/2.
     */
    private static final int INT_PHI = 0x9E3779B9;

    public final static int mix(final int x) {
        final int h = x * INT_PHI;
        return h ^ (h >>> 16);
    }

    /**
     * Return the least power of two greater than or equal
     * to the specified value.
     *
     * <p>Note that this function will return 1 when the
     * argument is 0.
     *
     * @param x a long integer smaller than or equal to
     * 2<sup>62</sup>.
     * @return the least power of two greater than or equal
     * to the specified value.
     */
    public static long nextPowerOfTwo(long x) {
        if (x == 0) return 1;
        x--;
        x |= x >> 1;
        x |= x >> 2;
        x |= x >> 4;
        x |= x >> 8;
        x |= x >> 16;
        return (x | x >> 32) + 1;
    }


    /**
     * Returns the maximum number of entries that can be
     * filled before rehashing.
     *
     * @param n the size of the backing array.
     * @param f the load factor.
     * @return the maximum number of entries before
     * rehashing.
     */
    public static int maxFill(final int n, final float f) {
        /* We must guarantee that there is always at least
		 * one free entry (even with pathological load factors). */
        return Math.min((int) Math.ceil(n * f), n - 1);
    }

    /**
     * Returns the least power of two smaller than or equal
     * to 2<sup>30</sup> and larger than or equal to
     * <code>Math.ceil( expected / f )</code>.
     *
     * @param expected the expected number of elements in a
     * hash table.
     * @param f the load factor.
     * @return the minimum possible size for a backing
     * array.
     * @throws IllegalArgumentException if the necessary
     * size is larger than 2<sup>30</sup>.
     */
    public static int arraySize(final int expected, final float f) {
        final long s = Math.max(2, nextPowerOfTwo((long) Math.ceil(expected / f)));
        if (s > (1 << 30))
            throw new IllegalArgumentException("Too large (" + expected + " expected elements with load factor " + f + ")");
        return (int) s;
    }

    /**
     * The initial default size of a hash table.
     */
    private static int DEFAULT_INITIAL_SIZE = 16;
    /**
     * The default load factor of a hash table.
     */
    private static float DEFAULT_LOAD_FACTOR = .75f;

    /**
     * The array of keys.
     */
    protected transient int[] key;
    /**
     * The array of values.
     */
    protected transient V[] value;
    /**
     * The mask for wrapping a position counter.
     */
    protected transient int mask;
    /**
     * Whether this set contains the key zero.
     */
    protected transient boolean containsNullKey;
    /**
     * The current table size.
     */
    protected transient int n;
    /**
     * Threshold after which we rehash. It must be the table
     * size times
     * {@link #f}.
     */
    protected transient int maxFill;
    /**
     * Number of entries in the set (including the key zero,
     * if present).
     */
    protected int size;
    /**
     * The acceptable load factor.
     */
    protected final float f;

    /**
     * Creates a new hash map.
     *
     * <p>
     * The actual table size will be the least power of two
     * greater than
     * <code>expected</code>/<code>f</code>.
     *
     * @param expected the expected number of elements in
     * the hash set.
     * @param f the load factor.
     */
    @SuppressWarnings("unchecked")
    public Int2ReferenceOpenHashMap(final int expected, final float f) {
        if (f <= 0 || f > 1)
            throw new IllegalArgumentException(
                    "Load factor must be greater than 0 and smaller than or equal to 1");
        if (expected < 0)
            throw new IllegalArgumentException(
                    "The expected number of elements must be nonnegative");
        this.f = f;
        this.n = arraySize(expected, f);
        this.mask = this.n - 1;
        this.maxFill = maxFill(this.n, f);
        this.key = new int[this.n + 1];
        this.value = (V[]) new Object[this.n + 1];
    }

    public Int2ReferenceOpenHashMap() {
        this(DEFAULT_INITIAL_SIZE, DEFAULT_LOAD_FACTOR);
    }

    private int realSize() {
        return this.containsNullKey ? this.size - 1 : this.size;
    }

    private V removeEntry(final int pos) {
        final V oldValue = this.value[pos];
        this.value[pos] = null;
        this.size--;
        this.shiftKeys(pos);
        if (this.size < this.maxFill / 4 && this.n > DEFAULT_INITIAL_SIZE)
            this.rehash(this.n / 2);
        return oldValue;
    }

    private V removeNullEntry() {
        this.containsNullKey = false;
        final V oldValue = this.value[this.n];
        this.value[this.n] = null;
        this.size--;
        if (this.size < this.maxFill / 4 && this.n > DEFAULT_INITIAL_SIZE)
            this.rehash(this.n / 2);
        return oldValue;
    }

    private int insert(final int k, final V v) {
        int pos;
        if (((k) == (0))) {
            if (this.containsNullKey)
                return this.n;
            this.containsNullKey = true;
            pos = this.n;
        } else {
            int curr;
            final int[] key = this.key;
            // The starting point.
            if (!((curr = key[pos = (mix((k)))
                    & this.mask]) == (0))) {
                if (((curr) == (k)))
                    return pos;
                while (!((curr = key[pos = (pos + 1) & this.mask]) == (0)))
                    if (((curr) == (k)))
                        return pos;
            }
        }
        this.key[pos] = k;
        this.value[pos] = v;
        if (this.size++ >= this.maxFill)
            this.rehash(arraySize(this.size + 1, this.f));
        return -1;
    }

    public V put(final int k, final V v) {
        final int pos = this.insert(k, v);
        if (pos < 0)
            return null;
        final V oldValue = this.value[pos];
        this.value[pos] = v;
        return oldValue;
    }

    /**
     * Shifts left entries with the specified hash code,
     * starting at the
     * specified position, and empties the resulting free
     * entry.
     *
     * @param pos a starting position.
     */
    protected final void shiftKeys(int pos) {
        // Shift entries with the same hash.
        int last, slot;
        int curr;
        final int[] key = this.key;
        for (; ; ) {
            pos = ((last = pos) + 1) & this.mask;
            for (; ; ) {
                if (((curr = key[pos]) == (0))) {
                    key[last] = (0);
                    this.value[last] = null;
                    return;
                }
                slot = (mix((curr))) & this.mask;
                if (last <= pos ? last >= slot || slot > pos : last >= slot
                        && slot > pos)
                    break;
                pos = (pos + 1) & this.mask;
            }
            key[last] = curr;
            this.value[last] = this.value[pos];
        }
    }

    public V remove(final int k) {
        if (((k) == (0))) {
            if (this.containsNullKey)
                return this.removeNullEntry();
            return null;
        }
        int curr;
        final int[] key = this.key;
        int pos;
        // The starting point.
        if (((curr = key[pos = (mix((k)))
                & this.mask]) == (0)))
            return null;
        if (((k) == (curr)))
            return this.removeEntry(pos);
        while (true) {
            if (((curr = key[pos = (pos + 1) & this.mask]) == (0)))
                return null;
            if (((k) == (curr)))
                return this.removeEntry(pos);
        }
    }

    public V get(final int k) {
        if (((k) == (0)))
            return this.containsNullKey ? this.value[this.n] : null;
        int curr;
        final int[] key = this.key;
        int pos;
        // The starting point.
        if (((curr = key[pos = (mix((k)))
                & this.mask]) == (0)))
            return null;
        if (((k) == (curr)))
            return this.value[pos];
        // There's always an unused entry.
        while (true) {
            if (((curr = key[pos = (pos + 1) & this.mask]) == (0)))
                return null;
            if (((k) == (curr)))
                return this.value[pos];
        }
    }

    public boolean trim() {
        final int l = arraySize(this.size, this.f);
        if (l >= this.n || this.size > maxFill(l, this.f))
            return true;
        try {
            this.rehash(l);
        } catch (OutOfMemoryError cantDoIt) {
            return false;
        }
        return true;
    }

    /**
     * Rehashes the map.
     *
     * <P>
     * This method implements the basic rehashing strategy,
     * and may be overriden
     * by subclasses implementing different rehashing
     * strategies (e.g.,
     * disk-based rehashing). However, you should not
     * override this method
     * unless you understand the internal workings of this
     * class.
     *
     * @param newN the new size
     */
    @SuppressWarnings("unchecked")
    protected void rehash(final int newN) {
        final int key[] = this.key;
        final V value[] = this.value;
        final int mask = newN - 1; // Note that this is used by the hashing
        // macro
        final int newKey[] = new int[newN + 1];
        final V newValue[] = (V[]) new Object[newN + 1];
        int i = this.n, pos;
        for (int j = this.realSize(); j-- != 0; ) {
            while (((key[--i]) == (0))) ;
            if (!((newKey[pos = (mix((key[i])))
                    & mask]) == (0)))
                while (!((newKey[pos = (pos + 1) & mask]) == (0)))
                    ;
            newKey[pos] = key[i];
            newValue[pos] = value[i];
        }
        newValue[newN] = value[this.n];
        this.n = newN;
        this.mask = mask;
        this.maxFill = maxFill(this.n, this.f);
        this.key = newKey;
        this.value = newValue;
    }
}