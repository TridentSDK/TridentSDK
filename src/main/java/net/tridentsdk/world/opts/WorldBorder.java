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
package net.tridentsdk.world.opts;

import net.tridentsdk.world.IntPair;

import javax.annotation.concurrent.ThreadSafe;

/**
 * This class represents the world border
 *
 */
@ThreadSafe
public interface WorldBorder {
    IntPair center();

    void setCenter();

    int size();

    int safeZoneSize();

    int warningSize();

    int warningTime();

    int lerpSize();

    int lerpTime();

    double damage();
}