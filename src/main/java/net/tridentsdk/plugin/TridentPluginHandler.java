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

import com.google.common.collect.Lists;
import net.tridentsdk.Trident;
import net.tridentsdk.concurrent.TaskExecutor;
import net.tridentsdk.docs.InternalUseOnly;
import net.tridentsdk.event.Listener;
import net.tridentsdk.factory.ExecutorFactory;
import net.tridentsdk.factory.Factories;
import net.tridentsdk.plugin.annotation.IgnoreRegistration;
import net.tridentsdk.plugin.annotation.PluginDescription;
import net.tridentsdk.plugin.cmd.Command;
import net.tridentsdk.plugin.cmd.CommandHandler;
import net.tridentsdk.util.TridentLogger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class TridentPluginHandler {
    private static final ExecutorFactory<TridentPlugin> PLUGIN_EXECUTOR_FACTORY = Factories.threads().executor(2);
    private final List<TridentPlugin> plugins = Lists.newArrayList();

    @InternalUseOnly
    public void load(final File pluginFile) {
        final TaskExecutor executor = PLUGIN_EXECUTOR_FACTORY.scaledThread();
        executor.addTask(new Runnable() {
            @Override
            public void run() {
                JarFile jarFile = null;
                try {
                    // load all classes
                    PluginClassLoader loader = new PluginClassLoader(pluginFile);
                    jarFile = new JarFile(pluginFile);
                    Enumeration<JarEntry> entries = jarFile.entries();

                    while (entries.hasMoreElements()) {
                        JarEntry entry = entries.nextElement();

                        if (!entry.getName().endsWith(".class")) continue;
                        loader.loadClass(entry.getName().replace('/', '.'));
                    }

                    // start initiating the plugin class and registering commands and listeners
                    Class<? extends TridentPlugin> pluginClass = loader.getPluginClass();
                    PluginDescription description = pluginClass.getAnnotation(PluginDescription.class);

                    if (description == null) {
                        TridentLogger.error(new PluginLoadException("Description annotation does not exist!"));
                    }

                    Constructor<? extends TridentPlugin> defaultConstructor = pluginClass.
                            getConstructor(File.class, PluginDescription.class, PluginClassLoader.class);
                    final TridentPlugin plugin = defaultConstructor.newInstance(pluginFile, description, loader);

                    plugins.add(plugin);
                    PLUGIN_EXECUTOR_FACTORY.set(executor, plugin);

                    plugin.startup(executor);
                    plugin.onLoad();

                    for (Class<?> cls : plugin.classLoader.classes.values()) {
                        register(plugin, cls, executor);
                    }

                    plugin.onEnable();
                } catch (IOException | ClassNotFoundException | NoSuchMethodException
                        | IllegalAccessException | InvocationTargetException | InstantiationException ex) {
                    TridentLogger.error(new PluginLoadException(ex));
                } finally {
                    if (jarFile != null) try {
                        jarFile.close();
                    } catch (IOException e) {
                        TridentLogger.error(e);
                    }
                }
            }
        });
    }

    private void register(TridentPlugin plugin, Class<?> cls, TaskExecutor executor)
            throws InstantiationException, IllegalAccessException {
        Object instance = null;
        if (Listener.class.isAssignableFrom(cls) && !cls.isAnnotationPresent(
                IgnoreRegistration.class)) {
            Trident.getServer().getEventHandler().registerListener(plugin, executor,
                                                                   (Listener) (instance = cls.newInstance()));
        }

        if (Command.class.isAssignableFrom(cls)) {
            CommandHandler.addCommand((Command) (instance == null ? cls.newInstance() : instance));
        }
    }

    public void disable(TridentPlugin plugin) {
        plugin.onDisable();

        this.plugins.remove(plugin);
        plugin.classLoader.unloadClasses();
        plugin.classLoader = null;
    }

    public Iterable<TridentPlugin> getPlugins() {
        return Collections.unmodifiableList(this.plugins);
    }
}
