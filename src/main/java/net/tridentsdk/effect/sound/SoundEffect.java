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
package net.tridentsdk.effect.sound;

import net.tridentsdk.effect.RemoteEffect;

/**
 * Represents a sound effect
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface SoundEffect extends RemoteEffect<SoundEffectType> {

    /**
     * Set the volume of the sound
     *
     * @param volume The volume of the sound (1 is 100%, can be higher)
     */
    void setVolume(float volume);

    /**
     * Set the pitch of the sound
     *
     * @param pitch The pitch of the sound (63 is 100%, can be higher)
     */
    void setPitch(byte pitch);

    /**
     * Returns the volume of the sound
     *
     * @return The volume of the sound
     */
    float volume();

    /**
     * Returns the pitch of the sound
     *
     * @return The pitch of the sound
     */
    byte pitch();

}
