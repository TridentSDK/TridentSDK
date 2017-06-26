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

import net.tridentsdk.base.Block;

/**
 * Represents metadata held by a sign
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface SignMeta extends BlockMeta<Block> {
    /**
     * Obtains the text at the given index [0-3]
     *
     * @param index the index
     * @return the text
     */
    String textAt(int index);

    /**
     * Sets the text at the index [0-3] to the provided text
     *
     * @param index the index
     * @param text  the text
     */
    void setTextAt(int index, String text);
}
