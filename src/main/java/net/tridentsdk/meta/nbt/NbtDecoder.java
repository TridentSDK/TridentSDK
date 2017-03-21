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
package net.tridentsdk.meta.nbt;

import io.netty.buffer.ByteBuf;

import javax.annotation.concurrent.Immutable;

/**
 * An NBT decoder receives raw bytes from a stream or file
 * and decodes it into an object wrapper that represents
 * the tag compound containing that NBT data.
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@Immutable
public final class NbtDecoder {
    // Prevent instantiation
    private NbtDecoder() {
    }

    public static TagCompound decode(ByteBuf source) {
        return new TagCompound(); // TODO
    }
}