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
package net.tridentsdk.meta.entity.living;

import net.tridentsdk.meta.entity.EntityMeta;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
// TODO - documentation
public interface LivingEntityMeta extends EntityMeta {

    boolean isHandActive();

    void setHandActive(boolean active);

    boolean isMainHandActive();

    void setMainHandActive(boolean mainHand);

    float getHealth();

    void setHealth(float health);

    int getPotionEffectColor();

    void setPotionEffectColor(int potionEffectColor);

    boolean isPotionEffectAmbient();

    void setPotionEffectAmbient(boolean ambient);

    int getNumberOfArrowsInEntity();

    void setNumberOfArrowsInEntity(int arrows);

}
