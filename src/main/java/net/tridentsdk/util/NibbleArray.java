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

import com.google.common.base.Preconditions;

import java.util.Arrays;

public final class NibbleArray {
    private final byte[] data;

    public NibbleArray(int size) {
        this(new byte[size / 2]);
    }

    public NibbleArray(byte... data) {
        Preconditions.checkArgument(((data.length % 2) == 0), "Size must be even! Size is " + data.length);
        this.data = data;
    }

    public byte[] array() {
        return data;
    }

    public int length() {
        return data.length * 2;
    }

    public int byteLength() {
        return data.length;
    }

    public byte get(int index) {
        return get(data, index);
    }

    public static void set(byte[] data, int index, byte value) {
        value &= 0xF;
        data[index / 2] &= (byte) (0xF << ((index + 1) % 2 * 4));
        data[index / 2] |= (byte) (value << (index % 2 * 4));
    }

    public void fill(byte value) {
        value &= 0xf;
        Arrays.fill(data, (byte) ((value << 4) | value));
    }

    public void setRaw(byte[] source) {
        Preconditions.checkArgument(data.length == source.length,
                "Byte array length must be the same as current size!");
        System.arraycopy(source, 0, data, 0, source.length);
    }


    public static byte get (byte[] source, int index) {
        return (byte) (source[index / 2] >> ((index) % 2 * 4) & 0xF);
    }
}
