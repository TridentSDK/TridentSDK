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
package net.tridentsdk.meta.block;

/**
 * Utility class for manipulating byte arrays
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public final class ByteArray {
    private ByteArray() {
    }

    /**
     * Writes the second end of the short to a byte array
     *
     * @param s the short to write
     * @return the encoded short
     */
    public static byte writeSecond(short s) {
        return (byte) s;
    }

    /**
     * Writes the first end of the short of the byte array
     *
     * @param s the short to write
     * @return the encoded short
     */
    public static byte writeFirst(short s) {
        return (byte) ((s >> 8) & 0xFF);
    }

    /**
     * Reads a short from two bytes in big endian order
     *
     * @param first  the first byte
     * @param second the second byte
     * @return the decoded short
     */
    public static short readShort(byte first, byte second) {
        return (short) ((first << 8) + (second & 0xFF));
    }
}