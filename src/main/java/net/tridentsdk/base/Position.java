/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2014 The TridentSDK Team
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

package net.tridentsdk.base;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.LongAdder;

/**
 * Position is a container class that represents a three-
 * dimensional coordinate within a volume grid.
 *
 * <p>This container also may hold a yaw and pitch values
 * for use with rotatable objects such as entities.</p>
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public final class Position extends Vector {
    // Unpadded 3 coordinate values
    private volatile double x;
    private volatile double y;
    private volatile double z;

    // private volatile long

    private volatile double pitch;
    private volatile double yaw;

    private final LongAdder stamp = new LongAdder();

    public double x() {
        return x;
    }


    // TODO
}
