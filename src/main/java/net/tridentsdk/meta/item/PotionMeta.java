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

import java.util.List;

import net.tridentsdk.effect.potion.PotionEffect;

/**
 * Represents a Potion's additional metadata
 */
public interface PotionMeta extends ItemMeta {
    /**
     * Gets this Potion's applied effects.
     *
     * @return The effects.
     */
    List<PotionEffect> effects();

    /**
     * Sets this Potion's applied effects.
     * 
     * @param effects
     *            The effects to use in place of the current ones.
     */
    void setEffects(List<PotionEffect> effects);

    /**
     * Adds a provided potion effect to this item.
     * 
     * @param effect
     *            The effect.
     */
    default void addEffect(PotionEffect effect) {
        List<PotionEffect> effects = this.effects();

        effects.add(effect);
        this.setEffects(effects);
    }
}
