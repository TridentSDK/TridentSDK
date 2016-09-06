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
package net.tridentsdk.base;

import lombok.Getter;

/**
 * Represents a direction.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
public enum Direction {

    /**
     * Downwards
     */
    DOWN(0),

    /**
     * Upwards
     */
    UP(1),

    /**
     * North
     */
    NORTH(2),

    /**
     * South
     */
    SOUTH(3),

    /**
     * West
     */
    WEST(4),

    /**
     * East
     */
    EAST(5);

    @Getter
    private final int data;

    private Direction(int data) {
        this.data = data;
    }

}
