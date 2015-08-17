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
package net.tridentsdk.effect.potion;

import net.tridentsdk.meta.nbt.NBTSerializable;

/**
 * Represents a potion effect with all the according values
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface PotionEffect extends NBTSerializable {
    /**
     * Returns the effect type of this potion
     * @return the effect type of this potion
     */
    PotionEffectType effectType();

    /**
     * Sets the effect type of this potion
     * @param type the effect type you wish to set it to
     */
    void setEffectType(PotionEffectType type);

    /**
     * Returns the amplifier of this potion
     * @return the amplifier of this potion
     */
    byte amplifier();

    /**
     * Sets the amplifier of the potion
     * @param b the amp you wish to set it to
     */
    void setAmplifier(byte b);

    /**
     * Returns the duration of the potion, in ticks
     * @return the duration of the potion in ticks
     */
    int duration();

    /**
     * Sets the duration of the potion, measured in ticks
     * @param duration duration you wish to set it to
     */
    void setDuration(int duration);

    /**
     * Returns whether or not if the potion is ambient
     * @return whether or not if the potion is ambient
     */
    boolean isAmbient();

    /**
     * Sets if the potion is ambient or not
     */
    void setAmbient(boolean ambient);

    /**
     * Returns whether the potion will show particles to the client
     * @return whether the potion will show particles to the client
     */
    boolean showParticles();

    /**
     * Sets if the particles should show to the client
     * @param showParticles if the particles should show to the client
     */
    void setShowParticles(boolean showParticles);
}
