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

/**
 * Represents sound effects played to the player
 *
 * @author The TridentSDK Team
 * TODO more darude sandstorm's
 */
public enum Audio {
    AMBIENT_CAVE("ambient.cove.cave"),
    AMBIENT_RAIN("ambient.weather.rain"),
    AMBIENT_THUNDER("ambient.weather.thunder"),

    FALL_BIG("damage.fallbig"),
    FALL_SMALL("damage.fallsmall"),

    FIRE_ACTIVE("fire.active"),
    FIRE_IGNITE("fire.ignite"),

    LAVA("liquid.lava"),
    LAVAPOP("liquid.lavapop"),
    SPLASH("liquid.splash"),
    WATER("liquid.water"),

    BLAZE_BREATH("mob.blaze.breathe"),
    BLAZE_DEATH("mob.blaze.death"),
    BLAZE_HIT("mob.blaze.hit"),

    CAT_HISS("mob.cat.hiss"),
    CAT_HITT("mob.cat.hitt"),
    CAT_MEOW("mob.cat.meow"),
    CAT_PURREOW("mob.cat.purreow");

    private final String s;

    Audio(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return this.s;
    }
}
