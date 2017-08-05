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
package net.tridentsdk.meta.entity;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
// TODO - documentation
public interface EntityMeta {

    boolean isOnFire();

    EntityMeta setOnFire(boolean onFire);

    boolean isCrouched();

    EntityMeta setCrouched(boolean crouched);

    boolean isSprinting();

    EntityMeta setSprinting(boolean sprinting);

    boolean isEating();

    EntityMeta setEating(boolean eating);

    boolean isInvisible();

    EntityMeta setInvisible(boolean invisible);

    boolean isGlowing();

    EntityMeta setGlowing(boolean glowing);

    boolean isUsingElytra();

    EntityMeta setUsingElytra(boolean usingElytra);

    int getAir();

    EntityMeta setAir(int air);

    String getCustomName();

    EntityMeta setCustomName(String name);

    boolean isCustomNameVisible();

    EntityMeta setCustomNameVisible(boolean visible);

    boolean isSilent();

    EntityMeta setSilent(boolean silent);

    boolean isNoGravity();

    EntityMeta setNoGravity(boolean noGravity);
}