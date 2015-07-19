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
package net.tridentsdk.service;

import net.tridentsdk.entity.living.Player;
import net.tridentsdk.plugin.Plugin;

/**
 * Provides chat properties to the {@link net.tridentsdk.service.ChatHandler}
 *
 * <p>The formatting provides only the left most portion (the player name and prompt), not the actual chat message</p>
 *
 * @author The TridentSDK Team
 */
public interface ChatIdentityFormatter {
    /**
     * Obtains a formatted string of the chat identifier
     *
     * <p>
     *     <ul>
     *         <li>{@code %p} - the default prefix</li>
     *         <li>{@code %n} - the player name</li>
     *         <li>{@code %s} - the default suffix</li>
     *         <li>{@code %d} - the default prompt</li>
     *     </ul>
     * </p>
     *
     * <p>The default is usually {@code %n>}. The default is filled in, but cannot replace the default string.</p>
     *
     * <p>Examples:
     * <pre><code>
     *     // Default is: John>
     *
     *     // [Prefix]John[Suffix]>>
     *     return "[Prefix]%n[Suffix]>>";
     *
     *     // [Prefix]JOHN>
     *     return "[Prefix]" + sender.displayName().toUpperCase() + "%d";
     * </code></pre></p>
     *
     * <p>It is generally a good idea to use the provided variables than to hardcode them, as the implementation is
     * allowed to change the chat characters.</p>
     *
     * @param message the default identifier
     * @param sender the player sending the identifier
     * @return the foratted string
     */
    String format(String message, Player sender);

    /**
     * A callback indicating the overriding of this provider
     *
     * @param other the chat provider that is being set
     * @param overrider the plugin that is setting the overrider
     */
    void overriden(ChatIdentityFormatter other, Plugin overrider);
}
