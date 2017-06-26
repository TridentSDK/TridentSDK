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

import net.tridentsdk.entity.living.Player;
import net.tridentsdk.ui.chat.ChatColor;
import net.tridentsdk.ui.chat.ChatComponent;

import javax.annotation.concurrent.Immutable;

/**
 * A constraint limiting players that can execute a given
 * command. Command blocks and consoles ALWAYS have
 * permission.
 *
 * <p>Uses a string constraint.</p>
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Immutable
public class PermsConstraint implements Constraint {
    @Override
    public boolean handle(Cmd cmd, String label, CmdSource source, String[] args, Object constraint) {
        if (!(constraint instanceof String)) {
            throw new IllegalArgumentException("PermsConstraint does not have the correct constraint arg");
        }

        if (source.getCmdType() == CmdSourceType.CONSOLE || source.getCmdType() == CmdSourceType.BLOCK) {
            return true;
        } else {
            Player player = (Player) source;
            boolean b = player.hasPerm((String) constraint);
            if (!b) {
                player.sendMessage(ChatComponent.create().setColor(ChatColor.RED).
                        setText("You do not have the appropriate permissions to execute this command. " +
                                "Contact the server administrators if you believe that this is in error."));
            }

            return b;
        }
    }
}