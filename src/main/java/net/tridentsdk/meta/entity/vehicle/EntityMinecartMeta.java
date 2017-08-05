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
package net.tridentsdk.meta.entity.vehicle;

import net.tridentsdk.meta.entity.EntityMeta;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
// TODO - documentation
public interface EntityMinecartMeta extends EntityMeta {

    int getShakingPower();

    EntityMinecartMeta setShakingPower(int shakingPower);

    int getShakingDirection();

    EntityMinecartMeta setShakingDirection(int shakingDirection);

    float getShakingMultiplier();

    EntityMinecartMeta setShakingMultiplier(boolean shakingMultiplier);

    int getMinecartBlockID();

    EntityMinecartMeta setMinecartBlockID(int blockID);

    int getMinecartBlockData();

    EntityMinecartMeta setMinecartBlockData(int blockData);

    int getMinecartBlockY();

    EntityMinecartMeta setMinecartBlockY(int blockY);

    boolean isShowBlock();

    EntityMinecartMeta setShowBlock(boolean showBlock);

}
