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
package net.tridentsdk.meta.entity.inanimate;

import net.tridentsdk.base.Vector;
import net.tridentsdk.meta.entity.living.LivingMeta;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
// TODO - documentation
public interface ArmorStandMeta extends LivingMeta {

    boolean isSmall();

    ArmorStandMeta setSmall(boolean small);

    boolean hasArms();

    ArmorStandMeta setHasArms(boolean hasArms);

    boolean hasBasePlate();

    ArmorStandMeta setHasBasePlate(boolean basePlate);

    boolean hasMarker();

    ArmorStandMeta setHasMarker(boolean hasMarker);

    Vector getHeadRotation();

    ArmorStandMeta setHeadRotation(Vector rotation);

    Vector getBodyRotation();

    ArmorStandMeta setBodyRotation(Vector rotation);

    Vector getLeftArmRotation();

    ArmorStandMeta setLeftArmRotation(Vector rotation);

    Vector getRightArmRotation();

    ArmorStandMeta setRightArmRotation(Vector rotation);

    Vector getLeftLegRotation();

    ArmorStandMeta setLeftLegRotation(Vector rotation);

    Vector getRightLegRotation();

    ArmorStandMeta setRightLegRotation(Vector rotation);

}
