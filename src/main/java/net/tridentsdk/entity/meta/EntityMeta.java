/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2016 The TridentSDK Team
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
package net.tridentsdk.entity.meta;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
// TODO - documentation
public interface EntityMeta {

    boolean isOnFire();

    void setOnFire(boolean onFire);

    boolean isCrouched();

    void setCrouched(boolean crouched);

    boolean isSprinting();

    void setSprinting(boolean sprinting);

    boolean isEating();

    void setEating(boolean eating);

    boolean isInvisible();

    void setInvisible(boolean invisible);

    boolean isGlowing();

    void setGlowing(boolean glowing);

    boolean isUsingElytra();

    void setUsingElytra(boolean usingElytra);

    int getAir();

    void setAir(int air);

    String getCustomName();

    void setCustomName(String name);

    boolean isCustomNameVisible();

    void setCustomNameVisible(boolean visible);

    boolean isSilent();

    void setSilent(boolean silent);

    boolean isNoGravity();

    void setNoGravity(boolean noGravity);
}