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
package net.tridentsdk.entity.meta.minecart;

import net.tridentsdk.entity.meta.EntityMeta;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
// TODO - documentation
public interface MinecartMeta extends EntityMeta {

    int getShakingPower();

    void setShakingPower(int shakingPower);

    int getShakingDirection();

    void setShakingDirection(int shakingDirection);

    float getShakingMultiplier();

    void setShakingMultiplier(boolean shakingMultiplier);

    int getMinecartBlockID();

    void setMinecartBlockID(int blockID);

    int getMinecartBlockData();

    void setMinecartBlockData(int blockData);

    int getMinecartBlockY();

    void setMinecartBlockY(int blockY);

    boolean isShowBlock();

    void setShowBlock(boolean showBlock);

}
