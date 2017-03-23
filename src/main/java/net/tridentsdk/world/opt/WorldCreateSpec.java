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
package net.tridentsdk.world.opt;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import net.tridentsdk.base.Vector;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * The options for creating a new world.
 *
 * <p>These options will be transferred over to their
 * appropriate opt classes when the world has finished
 * generating.</p>
 *
 * <p>Although this class is not thread safe, it should be
 * safely published by passing it to the world loader.</p>
 *
 * @author TridentSDK
 * @since 0.4-alpha
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "WorldSpecBuilder")
@NotThreadSafe
public class WorldCreateSpec {
    /**
     * The default instance of the world creator
     * specification, which sets all of the settings to
     * their default in a vanilla world.
     */
    private static final WorldCreateSpec DEFAULT = new DefaultSpec();

    // BUILDER FIELDS
    @Getter
    private Difficulty difficulty = Difficulty.NORMAL;
    @Getter
    private Dimension dimension = Dimension.OVERWORLD;
    @Getter
    private GameMode gameMode = GameMode.SURVIVAL;
    @Getter
    private GameRuleMap gameRules = new GameRuleMap();
    @Getter
    private boolean allowFlight = false;
    @Getter
    private boolean allowPvp = true;
    @Getter
    private boolean allowPortals = true;
    @Getter
    private boolean forceGameMode = false;
    @Getter
    private boolean difficultyLocked = false;
    @Getter
    private int spawnProtectionRadius = 5;
    @Getter
    private Vector spawn = null;

    /**
     * Custom class used to deny changes to the world spec
     */
    private static class DefaultSpec extends WorldCreateSpec {
        private DefaultSpec() {
            super(true);
        }
    }

    /**
     * Whether this spec uses the default world options
     */
    private final boolean def;

    // Use static factories
    private WorldCreateSpec(boolean def) {
        this.def = def;
    }

    /**
     * Uses the default world options to build the world.
     *
     * @return the default world specification
     */
    public static WorldCreateSpec getDefaultOptions() {
        return DEFAULT;
    }

    /**
     * Create a new custom world options specification that
     * can be passed to the server world loader to create
     * a custom world.
     *
     * @return a new custom world specification
     */
    public static WorldCreateSpec custom() {
        return new WorldCreateSpec(false);
    }

    /**
     * Determines whether this option specification is uses
     * all default values or not.
     *
     * @return {@code true} for default values
     */
    public boolean isDefault() {
        return this.def;
    }
}