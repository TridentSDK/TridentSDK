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
package net.tridentsdk.meta.item;

/**
 * Represents the additional metadata for a skull
 */
public interface SkullMeta extends ItemMeta {
    /**
     * Returns the name of the owner of this skull
     * @return the name of the owner of this skull
     */
    String owner();

    /**
     * Sets the name of the owner of this skull
     * @param name name of the owner you wish to set it to
     */
    void setOwner(String name);
}
