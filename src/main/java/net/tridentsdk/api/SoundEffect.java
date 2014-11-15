/*
 *     TridentSDK - A Minecraft Server API
 *     Copyright (C) 2014, The TridentSDK Team
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.tridentsdk.api;

/**
 * TODO more darude sandstorm's
 */
public enum SoundEffect {
    AMBIENT_CAVE("ambient.cove.cave"),
    AMBIENT_WEATHER_RAIN("ambient.weather.rain"),
    AMBIENT_WEATHER_THUNDER("ambient.weather.thunder"),

    DAMAGE_FALLBIG("damage.fallbig"),
    DAMAGE_FALLSMALL("damage.fallsmall"),

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

    private final String data;

    SoundEffect(String s) {
        this.data = s;
    }

    @Override
    public String toString() {
        return this.data;
    }
}
