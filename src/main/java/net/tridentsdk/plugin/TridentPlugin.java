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
import net.tridentsdk.Trident;
import net.tridentsdk.concurrent.HeldValueLatch;
import net.tridentsdk.concurrent.TaskExecutor;
import net.tridentsdk.config.JsonConfig;
import net.tridentsdk.event.Listener;
import net.tridentsdk.plugin.annotation.PluginDescription;
import net.tridentsdk.util.TridentLogger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class TridentPlugin {
    private static final HashFunction HASHER = Hashing.murmur3_32();

    PluginClassLoader classLoader;
    private final File pluginFile;
    private final File configDirectory;
    private final PluginDescription description;
    private final JsonConfig defaultConfig;
    private final HeldValueLatch<TaskExecutor> executor = HeldValueLatch.create();

    protected TridentPlugin() {
        this.pluginFile = null;
        this.description = null;
        this.defaultConfig = null;
        this.configDirectory = null;
        this.classLoader = null;
    } // avoid any plugin initiation outside of this package

    TridentPlugin(File pluginFile, PluginDescription description, PluginClassLoader loader) {
        for (TridentPlugin plugin : Trident.getServer().pluginHandler().getPlugins()) {
            if (plugin.getDescription().name().equalsIgnoreCase(description.name())) {
                TridentLogger.error(new IllegalStateException(
                        "Plugin already initialized or plugin with this name already exists! " +
                                "Name: " + description.name()));
            }
        }

        this.pluginFile = pluginFile;
        this.description = description;
        this.configDirectory = new File("plugins/" + description.name() + '/');
        this.defaultConfig = new JsonConfig(new File(this.configDirectory, "config.json"));
        this.classLoader = loader;
    }

    public static TridentPlugin getInstance() {
        Class<?> caller = Trident.getCaller(3);
        ClassLoader loader = caller.getClassLoader();
        for (TridentPlugin plugin : Trident.pluginHandler().getPlugins())
            if (plugin.getClass().getClassLoader().equals(loader))
                return plugin;
        return null;
    }

    public static TridentPlugin getInstance(Class<? extends TridentPlugin> c) {
        ClassLoader loader = c.getClassLoader();
        for (TridentPlugin plugin : Trident.pluginHandler().getPlugins())
            if (plugin.getClass().getClassLoader().equals(loader))
                return plugin;
        return null;
    }

    public void onEnable() {
        // Method intentionally left blank
    }

    public void onLoad() {
        // Method intentionally left blank
    }

    public void onDisable() {
        // Method intentionally left blank
    }

    final void startup(TaskExecutor executor) {
        // TODO
        this.executor.countDown(executor);
    }

    public Listener instanceOf(Class<? extends Listener> c) {
        return Trident.eventHandler().listenersFor(this).get(c);
    }

    public void saveDefaultConfig() {
        this.saveResource("config.json", false);
    }

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

    public final File getFile() {
        return this.pluginFile;
    }

    public JsonConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    public File getConfigDirectory() {
        return this.configDirectory;
    }

    public final PluginDescription getDescription() {
        return this.description;
    }

    public TaskExecutor getExecutor() {
        try {
            return executor.await();
        } catch (InterruptedException e) {
            TridentLogger.error(e);
        }

        // Should NEVER happen
        TridentLogger.error(new PluginLoadException(
                "Plugin not loaded correctly, the executor is null for " + getDescription().name()));
        return null;
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
