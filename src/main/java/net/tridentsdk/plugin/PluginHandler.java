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
import net.tridentsdk.Handler;
import net.tridentsdk.Trident;
import net.tridentsdk.concurrent.SelectableThread;
import net.tridentsdk.docs.InternalUseOnly;
import net.tridentsdk.event.Listener;
import net.tridentsdk.plugin.annotation.IgnoreRegistration;
import net.tridentsdk.plugin.annotation.PluginDescription;
import net.tridentsdk.plugin.cmd.Command;
import net.tridentsdk.registry.Factory;
import net.tridentsdk.util.TridentLogger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Handles server plugins, loading and unloading, class management, and lifecycle management for plugins
 *
 * <p>To access this handler, use this code:
 * <pre><code>
 *     TridentPluginHandler handler = Handler.forPlugins();
 * </code></pre></p>
 *
 * @author The TridentSDK Team
 */
public class PluginHandler {
    private static final SelectableThread EXECUTOR = Factory.newExecutor(1, "Plugins").selectNext();
    private final List<Plugin> plugins = Lists.newArrayList();

    /**
     * Do not instantiate this without being Trident
     *
     * <p>To access this handler, use this code:
     * <pre><code>
     *     TridentPluginHandler handler = Handler.forPlugins();
     * </code></pre></p>
     */
    public PluginHandler() {
        if (!Trident.isTrident())
            TridentLogger.error(new IllegalAccessException("Can only be instantiated by Trident"));
    }

    @InternalUseOnly
    public void load(final File pluginFile) {
        EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                Plugin plugin = null;
                JarFile jarFile = null;
                try {
                    // load all classes
                    jarFile = new JarFile(pluginFile);
                    PluginClassLoader loader = new PluginClassLoader(pluginFile, getClass().getClassLoader());
                    Class<? extends Plugin> pluginClass = null;

                    Enumeration<JarEntry> entries = jarFile.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry entry = entries.nextElement();

                        if (entry.isDirectory() || !entry.getName().endsWith(".class")) {
                            continue;
                        }

                        String name = entry.getName().replace(".class", "").replace('/', '.');
                        Class<?> loadedClass = loader.loadClass(name);

                        loader.putClass(loadedClass);

                        if (Plugin.class.isAssignableFrom(loadedClass)) {
                            if (pluginClass != null)
                                TridentLogger.error(new PluginLoadException("Plugin has more than one main class!"));

                            pluginClass = loadedClass.asSubclass(Plugin.class);
                        }
                    }

                    // start initiating the plugin class and registering commands and listeners
                    if (pluginClass == null) {
                        TridentLogger.error(new PluginLoadException("Plugin does not have a main class"));
                        loader.unloadClasses();
                        loader = null; // help gc
                        return;
                    }

                    PluginDescription description = pluginClass.getAnnotation(PluginDescription.class);

                    if (description == null) {
                        TridentLogger.error(new PluginLoadException("PluginDescription annotation does not exist!"));
                        loader.unloadClasses();
                        loader = null; // help gc
                        return;
                    }

                    TridentLogger.log("Loading " + description.name() + " version " + description.version());

                    Constructor<? extends Plugin> defaultConstructor = pluginClass.getSuperclass()
                            .asSubclass(Plugin.class)
                            .getDeclaredConstructor(File.class, PluginDescription.class, PluginClassLoader.class);
                    defaultConstructor.setAccessible(true);
                    plugin = defaultConstructor.newInstance(pluginFile, description, loader);

                    plugins.add(plugin);

                    plugin.load();

                    for (Class<?> cls : loader.locallyLoaded.values()) {
                        register(plugin, cls, EXECUTOR);
                    }

                    plugin.enable();
                    TridentLogger.success("Loaded " + description.name() + " version " + description.version());
                } catch (IOException | NoSuchMethodException | IllegalAccessException | InvocationTargetException
                        | InstantiationException | ClassNotFoundException ex) { // UNLOAD PLYGIN
                    TridentLogger.error(new PluginLoadException(ex));
                    if (plugin != null)
                        disable(plugin);
                } finally {
                    if (jarFile != null)
                        try {
                            jarFile.close();
                        } catch (IOException e) {
                            TridentLogger.error(e);
                        }
                }
            }
        });
    }

    private void register(Plugin plugin, Class<?> cls, SelectableThread executor) throws InstantiationException {
        if (Modifier.isAbstract(cls.getModifiers()))
            return;

        Object instance = null;
        Constructor<?> c = null;

        try {
            if (!cls.isAnnotationPresent(IgnoreRegistration.class)) {
                if (Listener.class.isAssignableFrom(cls)) {
                    c = cls.getConstructor();
                    Handler.forEvents().registerListener(plugin, (Listener) (instance = c.newInstance()));
                }

                if (Command.class.isAssignableFrom(cls)) {
                    if (c == null)
                        c = cls.getConstructor();
                    Handler.forCommands().addCommand(plugin, (Command) (instance == null ? c.newInstance() : instance));
                }
            }
        } catch (NoSuchMethodException e) {
            TridentLogger.error(
                    new PluginLoadException("A no-arg constructor for class " + cls.getName() + " does not exist"));
        } catch (IllegalAccessException e) {
            TridentLogger.error(
                    new PluginLoadException("A no-arg constructor for class " + cls.getName() + " is not accessible"));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * Disables the plugin unloading the classes and removing the subprocess
     *
     * @param plugin the plugin to disable
     */
    public void disable(final Plugin plugin) {
        EXECUTOR.execute(() -> {
            // Perform disabling first, we don't want to unload everything
            // then disable it
            // State checking could be performed which breaks the class loader
            plugin.disable();

            plugins.remove(plugin);

            plugin.classLoader.unloadClasses();
            plugin.classLoader = null;
        });
    }

    /**
     * Obtains an immutable list of plugins that are currently <strong>loaded</strong>
     * (not the plugins that are inside the plugin directory)
     *
     * @return the collection of plugins that are loaded
     */
    public List<Plugin> plugins() {
        return Collections.unmodifiableList(this.plugins);
    }

    /**
     * Obtains the worker used to load plugins. Use this executor to send plugin tasks from the server.
     *
     * @return the worker
     */
    public SelectableThread executor() {
        return EXECUTOR;
    }
}