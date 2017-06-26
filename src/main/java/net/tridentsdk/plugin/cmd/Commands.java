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
package net.tridentsdk.plugin.cmd;

import net.tridentsdk.plugin.Plugin;
import net.tridentsdk.registry.Registry;

import javax.annotation.concurrent.ThreadSafe;

/**
 * /**
 * Handles commands passed from the server
 *
 * <p>To access this handler, use this code:
 * <pre><code>
 *     Commands handler = Registered.commands();
 * </code></pre></p>
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public interface Commands extends Registry<Command> {
    /**
     * Handles the message sent, without the preceding "/"
     *
     * @param message the command executed
     */
    void handle(String message, CommandIssuer issuer);

    /**
     * Registers the command for the plugin
     *
     * <p>You do not need to call this method unless the command is marked with
     * {@link net.tridentsdk.plugin.annotation.IgnoreRegistration}</p>
     *
     * @param plugin the plugin to register for
     * @param command the command to register
     * @return unspecified
     */
    int register(Plugin plugin, Command command);

    /**
     * Unregisters the given command handler
     *
     * @param cls the command to unregister
     */
    void unregister(Class<? extends Command> cls);
}
