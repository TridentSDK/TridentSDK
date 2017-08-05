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

import net.tridentsdk.entity.Entity;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
// TODO - documentation
public interface PlayerMeta extends LivingMeta {

    float getAdditionalHearts();

    PlayerMeta setAdditionalHearts(float hearts);

    int getScore();

    PlayerMeta setScore(int score);

    byte getSkinFlags();

    PlayerMeta setSkinFlags(byte skinFlags);

    boolean isCapeEnabled();

    PlayerMeta setCapeEnabled(boolean enabled);

    boolean isJacketEnabled();

    PlayerMeta setJacketEnabled(boolean enabled);

    boolean isLeftSleeveEnabled();

    PlayerMeta setLeftSleeveEnabled(boolean enabled);

    boolean isRightSleeveEnabled();

    PlayerMeta setRightSleeveEnabled(boolean enabled);

    boolean isLeftLegPantsEnabled();

    PlayerMeta setLeftLegPantsEnabled(boolean enabled);

    boolean isRightLegPantsEnabled();

    PlayerMeta setRightLegPantsEnabled(boolean enabled);

    boolean isHatEnabled();

    PlayerMeta setHatEnabled(boolean enabled);

    boolean isLeftHandMain();

    PlayerMeta setLeftHandMain(boolean main);

    Entity getLeftShoulderEntity();

    PlayerMeta setLeftShoulderEntity(Entity parrot);

    Entity getRightShoulderEntity();

    PlayerMeta setRightShoulderEntity(Entity parrot);

}
