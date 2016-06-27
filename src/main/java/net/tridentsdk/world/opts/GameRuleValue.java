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

import com.google.common.base.Preconditions;

import javax.annotation.concurrent.Immutable;

/**
 * This class holds the value of a game rule whether it be a
 * boolean value or a number value.
 *
 * <p>This class is instantiated using {@code static}
 * factories, {@link #bool(boolean)} and
 * {@link #number(long)}.</p>
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Immutable
public final class GameRuleValue {
    /**
     * The number value of the game rule, {@code -1} if the
     * value is a boolean.
     */
    private final long numValue;

    /**
     * The boolean value of the game rule, {@code false} if
     * the value is a number.
     *
     * <p>This value may be false whether or not it is an
     * number.</p>
     */
    private final boolean boolValue;

    /**
     * Creates a new game rule value given the values.
     *
     * @param boolValue the boolean value
     * @param numValue the number value (determines the game
     *                 rule type, see {@link #numValue}.
     */
    private GameRuleValue(boolean boolValue, long numValue) {
        this.boolValue = boolValue;
        this.numValue = numValue;
    }

    /**
     * Creates a new number game rule value.
     *
     * @param l the number value to give to the game rule
     * @return the new game rule value
     */
    public static GameRuleValue number(long l) {
        return new GameRuleValue(false, l);
    }

    /**
     * Creates a new boolean game rule value.
     *
     * @param b the boolean value to give the game rule
     * @return the new game rule value
     */
    public static GameRuleValue bool(boolean b) {
        return new GameRuleValue(b, -1);
    }

    /**
     * Whether or not the game rule value is a number type.
     *
     * @return {@code true} if it is a number, {@code false}
     *         if it is not
     */
    public boolean isNumber() {
        return this.numValue != -1;
    }

    /**
     * Whether the game rule value is a boolean type.
     *
     * @return {@code true} if the game rule is a boolean,
     *         {@code false} if not
     */
    public boolean isBool() {
        return this.numValue == -1;
    }

    /**
     * Obtains the number value of this game rule.
     *
     * @return the number value, if this is a number
     * @throws IllegalArgumentException if the game rule is
     *         not a number
     */
    public long asNumber() {
        Preconditions.checkState(this.isNumber(), "Game rule is a boolean");
        return this.numValue;
    }

    /**
     * Obtains the boolean value of this game rule.
     *
     * @return the boolean value, if this is a boolean
     * @throws IllegalArgumentException if the game rule is
     *         not a boolean
     */
    public boolean asBool() {
        Preconditions.checkState(this.isBool(), "Game rule is a number");
        return this.boolValue;
    }
}