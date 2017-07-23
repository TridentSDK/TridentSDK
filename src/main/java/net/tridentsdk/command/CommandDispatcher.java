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

/**
 * A command dispatcher functions as the method wrapper for
 * command handling classes.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
public abstract class CommandDispatcher {

    /**
     * Gets the associated fallback.
     *
     * @return The fallback.
     */
    public abstract String getPlugin();

    /**
     * Gets the associated {@link Command}.
     *
     * @return The command.
     */
    public abstract Command getCommand();

    /**
     * Runs the command handled by the method wrapped by
     * this command source.
     *
     * @param cmd the command to run
     * @param source the command sender
     * @param args the command arguments
     */
    public abstract void run(String cmd, CommandSource source, String[] args);

    /**
     * Obtains whether or not the given class is what this
     * command is contained by.
     *
     * @param cls the class to check
     * @return {@code true} if the class is the class which
     * contains the command
     */
    public abstract boolean isContainedBy(Class<? extends CommandListener> cls);

}
