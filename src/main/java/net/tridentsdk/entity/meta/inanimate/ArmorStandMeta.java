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
package net.tridentsdk.entity.meta.inanimate;

import net.tridentsdk.base.Vector;
import net.tridentsdk.entity.meta.living.LivingEntityMeta;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
// TODO - documentation
public interface ArmorStandMeta extends LivingEntityMeta {

    boolean isSmall();

    void setSmall(boolean small);

    boolean hasArms();

    void setHasArms(boolean hasArms);

    boolean hasBasePlate();

    void setHasBasePlate(boolean basePlate);

    boolean hasMarker();

    void setHasMarker(boolean hasMarker);

    Vector getHeadRotation();

    void setHeadRotation(Vector rotation);

    Vector getBodyRotation();

    void setBodyRotation(Vector rotation);

    Vector getLeftArmRotation();

    void setLeftArmRotation(Vector rotation);

    Vector getRightArmRotation();

    void setRightArmRotation(Vector rotation);

    Vector getLeftLegRotation();

    void setLeftLegRotation(Vector rotation);

    Vector getRightLegRotation();

    void setRightLegRotation(Vector rotation);

}
