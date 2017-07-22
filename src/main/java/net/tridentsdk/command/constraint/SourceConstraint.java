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
package net.tridentsdk.command.constraint;

import net.tridentsdk.command.Command;
import net.tridentsdk.command.CommandSource;
import net.tridentsdk.command.CommandSourceType;
import net.tridentsdk.ui.chat.ChatColor;
import net.tridentsdk.ui.chat.ChatComponent;

import javax.annotation.concurrent.Immutable;

/**
 * Command source constraint. Any source not given in the
 * constraint array will receive a warning message.
 *
 * <p>Must use a source constraint type.</p>
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Immutable
public class SourceConstraint implements Constraint {
    @Override
    public boolean handle(Command command, String label, CommandSource source, String[] args, Object constraint) {
        if (!(constraint instanceof CommandSourceType[])) {
            throw new IllegalArgumentException("SourceConstraint does not have the correct constraint arg");
        }

        CommandSourceType[] sources = (CommandSourceType[]) constraint;
        if (sources.length == 0) {
            return false;
        }

        for (CommandSourceType s : sources) {
            if (s.isTypeOf(source)) {
                return true;
            }
        }

        source.sendMessage(ChatComponent.create().setColor(ChatColor.RED).setText(source.getCmdType() + " cannot execute this command"));
        return false;
    }
}
