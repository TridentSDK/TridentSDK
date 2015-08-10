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

import net.tridentsdk.effect.potion.PotionEffect;

import java.util.List;

/**
 * Represents a Potion's additional metadata
 */
public interface PotionMeta extends ItemMeta {
    List<PotionEffect> effects();

    void setEffects(List<PotionEffect> effects);

    default void addEffect(PotionEffect effect) {
        List<PotionEffect> effects = effects();

        effects.add(effect);
        setEffects(effects);
    }
}
