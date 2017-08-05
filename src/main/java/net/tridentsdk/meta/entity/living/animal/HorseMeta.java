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
package net.tridentsdk.meta.entity.living.animal;

import java.util.UUID;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
// TODO - documentation
public interface HorseMeta extends TameableMeta {

    boolean isSaddled();

    HorseMeta setSaddled(boolean saddled);

    boolean hasChest();

    HorseMeta setHasChest(boolean hasChest);

    boolean isBred();

    HorseMeta setBred(boolean isBred);

    boolean isHorseEating();

    HorseMeta setHorseEating(boolean eating);

    boolean isRearing();

    HorseMeta setRearing(boolean rearing);

    boolean isMouthOpen();

    HorseMeta setMouthOpen(boolean mouthOpen);

    HorseType getHorseType();

    HorseMeta setHorseType(HorseType type);

    HorseColor getHorseColor();

    HorseMeta setHorseColor(HorseColor color);

    HorseMarkings getHorseMarkings();

    HorseMeta setHorseMarkings(HorseMarkings markings);

    UUID getOwner();

    HorseMeta setOwner(UUID uuid);

    HorseArmor getHorseArmor();

    HorseMeta setHorseArmor(HorseArmor armor);

}
