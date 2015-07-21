package net.tridentsdk.plugin;

import net.tridentsdk.concurrent.SelectableThread;
import net.tridentsdk.registry.Registry;

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
