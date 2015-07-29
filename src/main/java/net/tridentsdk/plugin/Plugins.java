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
package net.tridentsdk.plugin;

import net.tridentsdk.concurrent.SelectableThread;
import net.tridentsdk.registry.Registry;

import javax.annotation.concurrent.ThreadSafe;
import java.io.File;

/**
 * Handles server plugins, loading and unloading, class management, and lifecycle management for plugins
 *
 * <p>To access this handler, use this code:
 * <pre><code>
 *     PluginHandler handler = Registered.plugins();
 * </code></pre></p>
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public interface Plugins extends Registry<Plugin> {
    /**
     * Loads the plugin file
     *
     * @param pluginFile the plugin file to load
     */
    Plugin load(File pluginFile);

    /**
     * Enables the given plugin
     *
     * @param plugin the plugin to enable
     */
    void enable(Plugin plugin);

    /**
     * Disables the plugin unloading the classes and removing the subprocess
     *
     * @param plugin the plugin to disable
     */
    void disable(Plugin plugin);

    /**
     * Obtains the worker used to load plugins. Use this executor to send plugin tasks from the server.
     *
     * @return the worker
     */
    SelectableThread executor();
}
