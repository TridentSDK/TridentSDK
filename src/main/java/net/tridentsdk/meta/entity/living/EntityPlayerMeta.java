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
public interface EntityPlayerMeta extends EntityLivingMeta {

    float getAdditionalHearts();

    EntityPlayerMeta setAdditionalHearts(float hearts);

    int getScore();

    EntityPlayerMeta setScore(int score);

    byte getSkinFlags();

    EntityPlayerMeta setSkinFlags(byte skinFlags);

    boolean isCapeEnabled();

    EntityPlayerMeta setCapeEnabled(boolean enabled);

    boolean isJacketEnabled();

    EntityPlayerMeta setJacketEnabled(boolean enabled);

    boolean isLeftSleeveEnabled();

    EntityPlayerMeta setLeftSleeveEnabled(boolean enabled);

    boolean isRightSleeveEnabled();

    EntityPlayerMeta setRightSleeveEnabled(boolean enabled);

    boolean isLeftLegPantsEnabled();

    EntityPlayerMeta setLeftLegPantsEnabled(boolean enabled);

    boolean isRightLegPantsEnabled();

    EntityPlayerMeta setRightLegPantsEnabled(boolean enabled);

    boolean isHatEnabled();

    EntityPlayerMeta setHatEnabled(boolean enabled);

    boolean isLeftHandMain();

    EntityPlayerMeta setLeftHandMain(boolean main);

    Entity getLeftShoulderEntity();

    EntityPlayerMeta setLeftShoulderEntity(Entity parrot);

    Entity getRightShoulderEntity();

    EntityPlayerMeta setRightShoulderEntity(Entity parrot);

}
