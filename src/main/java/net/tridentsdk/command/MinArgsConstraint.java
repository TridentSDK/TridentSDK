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

import net.tridentsdk.ui.chat.ChatColor;
import net.tridentsdk.ui.chat.ChatComponent;

import javax.annotation.concurrent.Immutable;

/**
 * Minimum args constraint. Any number of args below the
 * constraint results in displaying the help message.
 *
 * <p>Must use an integer constraint.</p>
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Immutable
public class MinArgsConstraint implements Constraint {
    @Override
    public boolean handle(Cmd cmd, String label, CmdSource source, String[] args, Object constraint) {
        if (!(constraint instanceof Integer)) {
            throw new IllegalArgumentException("MinArgsConstraint does not have the correct constraint arg");
        }

        int i = (int) constraint;

        if (args.length < i) {
            source.sendMessage(ChatComponent.create().setColor(ChatColor.RED).setText("Usage: " + cmd.help()));
            return false;
        }

        return true;
    }
}