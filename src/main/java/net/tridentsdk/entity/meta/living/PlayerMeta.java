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
package net.tridentsdk.entity.meta.living;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
// TODO - documentation
public interface PlayerMeta extends LivingEntityMeta {

    float getAdditionalHearts();

    void setAdditionalHearts(float hearts);

    int getScore();

    void setScore(int score);

    byte getSkinFlags();

    void setSkinFlags(byte skinFlags);

    boolean isCapeEnabled();

    void setCapeEnabled(boolean enabled);

    boolean isJacketEnabled();

    void setJacketEnabled(boolean enabled);

    boolean isLeftSleeveEnabled();

    void setLeftSleeveEnabled(boolean enabled);

    boolean isRightSleeveEnabled();

    void setRightSleeveEnabled(boolean enabled);

    boolean isLeftLegPantsEnabled();

    void setLeftLegPantsEnabled(boolean enabled);

    boolean isRightLegPantsEnabled();

    void setRightLegPantsEnabled(boolean enabled);

    boolean isHatEnabled();

    void setHatEnabled(boolean enabled);

    boolean isLeftHandMain();

    void setLeftHandMain(boolean main);

}
