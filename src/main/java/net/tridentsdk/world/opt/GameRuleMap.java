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

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A game rule map is needed because game rules behave
 * specially in that if they are not changed, they have a
 * default value which the map can return, which increases
 * both performance and memory efficiency.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@ThreadSafe
public final class GameRuleMap {
    /**
     * The internal map representing the changes made to the
     * default value of the game rule.
     */
    private final Map<GameRule<?>, Object> changes = new ConcurrentHashMap<>();

    /**
     * Gets the value of the given game rule that is set in
     * this map.
     *
     * <p>If the current key was never set, then the default
     * is returned.</p>
     *
     * @param key the game rule to get
     * @param <T> the type of value
     * @return the value
     */
    @Nonnull
    public <T> T get(GameRule<T> key) {
        T t = (T) this.changes.get(key);
        if (t == null) {
            return key.getDefault();
        }

        return t;
    }

    /**
     * Sets the value of the game rule to the given value.
     *
     * @param key the game rule to set
     * @param value the value to set
     * @param <T> the type of value
     */
    public <T> void set(@Nonnull GameRule<T> key, T value) {
        if (!key.getDefault().equals(value)) {
            this.changes.put(key, value);
        }
    }

    /**
     * Obtains whether the given game rule has been set in
     * this game rule map or not.
     *
     * @param key the game rule to check
     * @param <T> the type of value
     * @return {@code true} if the game rule has been set,
     *         {@code false} if it is still default
     */
    public <T> boolean isSet(GameRule<T> key) {
        return this.changes.containsKey(key);
    }

    /**
     * Resets the given game rule to its default.
     *
     * @param key the game rule to reset
     * @param <T> the type of value held by the game rule
     */
    public <T> void reset(GameRule<T> key) {
        this.changes.remove(key);
    }

    /**
     * Resets all of the game rules that were changed to
     * their respective defaults.
     */
    public void resetAll() {
        this.changes.clear();
    }

    /**
     * Copies the changes to the game rule values held in
     * the given map to this map.
     *
     * @param map the map to copy
     */
    public void copyTo(GameRuleMap map) {
        map.changes.putAll(this.changes);
    }
}