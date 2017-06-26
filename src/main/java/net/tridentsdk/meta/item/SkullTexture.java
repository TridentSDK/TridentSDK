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

public interface SkullTexture {
    /**
     * Gets the timestamp of this texture.
     *
     * @return The timestamp.
     */
    public long timestamp();

    /**
     * Gets the profile ID associated with this texture.
     *
     * @return The profile ID.
     */
    public String profileId();

    /**
     * Gets the profile name associated with this texture.
     *
     * @return The profile name.
     */
    public String profileName();

    /**
     * Gets whether or not the texture is classified as public or not.
     *
     * @return True if public, false otherwise.
     */
    public boolean isPublic();

    /**
     * Gets the skin URL of the texture.
     *
     * @return The skin URL.
     */
    public String skinUrl();

    /**
     * Gets the cape URL of the texture.
     * 
     * @return The cape URL.
     */
    public String capeUrl();
}
