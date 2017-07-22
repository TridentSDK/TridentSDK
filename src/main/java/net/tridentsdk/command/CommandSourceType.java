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
package net.tridentsdk.command;

import net.tridentsdk.Server;
import net.tridentsdk.base.Block;
import net.tridentsdk.base.Substance;
import net.tridentsdk.entity.living.Player;

import javax.annotation.concurrent.Immutable;

/**
 * The type of command source that is being dispatched.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Immutable
public enum CommandSourceType {
    PLAYER {
        @Override
        public boolean isTypeOf(CommandSource o) {
            return o instanceof Player;
        }
    }, CONSOLE {
        @Override
        public boolean isTypeOf(CommandSource o) {
            return o instanceof Server;
        }
    }, BLOCK {
        @Override
        public boolean isTypeOf(CommandSource o) {
            return o instanceof Block && ((Block) o).getSubstance() == Substance.COMMAND_BLOCK;
        }
    }, ALL {
        @Override
        public boolean isTypeOf(CommandSource o) {
            return true;
        }
    };

    /**
     * Checks to see whether the given source is a type of
     * the command source.
     *
     * @param o the source to check
     * @return {@code true} if it is a type of the command
     * source
     */
    public abstract boolean isTypeOf(CommandSource o);
}
