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

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import net.tridentsdk.Handler;
import net.tridentsdk.Trident;
import net.tridentsdk.concurrent.HeldValueLatch;
import net.tridentsdk.concurrent.TaskExecutor;
import net.tridentsdk.config.JsonConfig;
import net.tridentsdk.event.Listener;
import net.tridentsdk.plugin.annotation.PluginDescription;
import net.tridentsdk.plugin.cmd.Command;
import net.tridentsdk.util.TridentLogger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * Must be extended by a non-inner class to represent a plugin's <em>main class</em>
 *
 * @author The TridentSDK Team
 */
public class TridentPlugin {
    private static final HashFunction HASHER = Hashing.murmur3_32();
    private final File pluginFile;
    private final File configDirectory;
    private final PluginDescription description;
    private final JsonConfig defaultConfig;
    private final HeldValueLatch<TaskExecutor> executor = HeldValueLatch.create();
    public PluginClassLoader classLoader;

    /**
     * It's not a good idea to use this constructor
     */
    protected TridentPlugin() {
        // Prevent stack continuation
        throw new IllegalStateException("Cannot be directly instantiated");
    } // avoid any plugin initiation outside of this package

    TridentPlugin(File pluginFile, PluginDescription description, PluginClassLoader loader) {
        Handler.forPlugins().plugins().stream().filter(plugin -> plugin.getDescription().name().equalsIgnoreCase(description.name())).forEach(plugin -> TridentLogger.error(new IllegalStateException(
                "Plugin already initialized or plugin named " + description.name() + " exists already")));

        this.pluginFile = pluginFile;
        this.description = description;
        this.configDirectory = new File("plugins" + File.separator + description.name() + File.separator);
        this.defaultConfig = new JsonConfig(new File(this.configDirectory, "config.json"));
        this.classLoader = loader;
    }

    /**
     * Obtains the instance of the plugin which the caller class is in
     *
     * <p>Returns {@code null} if the plugin has not been loaded yet, or if the class is not a plugin loaded on the
     * server.</p>
     *
     * @return the instance of the plugin
     */
    @Nullable
    public static TridentPlugin instance() {
        Class<?> caller = Trident.findCaller(3);
        ClassLoader loader = caller.getClassLoader();
        for (TridentPlugin plugin : Handler.forPlugins().plugins())
            if (plugin.classLoader.equals(loader))
                return plugin;
        return null;
    }

    /**
     * Obtains the instance of the plugin which has the specified main class
     *
     * <p>Returns {@code null} if the plugin has not been loaded yet, or if the class is not a plugin loaded on the
     * server.</p>
     *
     * @param c the main class of the plugin to obtain the instance of
     * @return the instance of the plugin with the specified main class
     */
    @Nullable
    public static TridentPlugin instance(Class<? extends TridentPlugin> c) {
        ClassLoader loader = c.getClassLoader();
        for (TridentPlugin plugin : Handler.forPlugins().plugins())
            if (plugin.classLoader.equals(loader))
                return plugin;
        return null;
    }

    /**
     * Called by the handler to indicate the plugin has been constructed
     */
    public void onLoad() {
        // Method intentionally left blank
    }

    /**
     * Called by the handler to indicate the enabling of this plugin
     */
    public void onEnable() {
        // Method intentionally left blank
    }

    /**
     * Called by the handler to indicate the disabling of this plugin
     */
    public void onDisable() {
        // Method intentionally left blank
    }

    public final void startup() {
        // TODO
    }

    /**
     * Obtains the listener instance with the class specified
     *
     * @param c the class to find the listener instance by
     * @return the listener instance registered to the server
     */
    public <T extends Listener> T listenerBy(Class<T> c) {
        return (T) Handler.forEvents().listenersFor(this).get(c);
    }

    /**
     * Obtains the command instance with the class specified
     *
     * @param c the class to find the command instance by
     * @return the command instance registered to the server
     */
    public <T extends Command> T commandBy(Class<T> c) {
        return (T) Handler.forCommands().getCommands(this).get(c);
    }

    /**
     * Saves the configuration inside the jar to the plugin directory
     *
     * <p>If the configuration is already saved, this does not overwrite it.</p>
     */
    public void saveDefaultConfig() {
        this.saveResource("config.json", false);
    }

    /**
     * Saves a resource inside the jar to the plugin directory
     *
     * @param name    the filename of the directory
     * @param replace whether or not replace the old resource, if it exists
     */
    public void saveResource(String name, boolean replace) {
        try {
            InputStream is = this.getClass().getResourceAsStream('/' + name);
            File file = new File(this.configDirectory, name);

            if (is == null) {
                return;
            }

            if (replace && file.exists()) {
                file.delete();
            }

            Files.copy(is, file.getAbsoluteFile().toPath());
        } catch (IOException ex) {
            TridentLogger.error(ex);
        }
    }

    /**
     * Obtains the file which this plugin was loaded from
     *
     * @return the file which the plugin is loaded from
     */
    public final File getFile() {
        return this.pluginFile;
    }

    /**
     * The default configuration for this plugin, which may or may not exist physically
     *
     * @return the default configuration given to this plugin
     */
    public JsonConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    /**
     * The plugin directory
     *
     * <p>The returned file includes the trailing file separator</p>
     *
     * @return the plugin directory where resources like the default config are saved
     */
    public File getConfigDirectory() {
        return this.configDirectory;
    }

    /**
     * Obtains the annotation given by this plugin
     *
     * @return the plugin descriptor for this plugin
     */
    @Nonnull
    public final PluginDescription getDescription() {
        return this.description;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof TridentPlugin) {
            TridentPlugin otherPlugin = (TridentPlugin) other;
            if (otherPlugin.getDescription().name().equals(this.getDescription().name())) {
                if (otherPlugin.getDescription().author().equals(this.getDescription().author())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        // Find constants
        String name = this.getDescription().name();
        String author = this.getDescription().author();

        return HASHER.newHasher().putUnencodedChars(name).putUnencodedChars(author).hash().hashCode();
    }
}
