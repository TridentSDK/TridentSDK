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
package net.tridentsdk.plugin;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import lombok.Getter;
import net.tridentsdk.Server;
import net.tridentsdk.command.CmdListener;
import net.tridentsdk.logger.Logger;
import net.tridentsdk.doc.Policy;
import net.tridentsdk.event.Listener;
import net.tridentsdk.util.Misc;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * A plugin loader is the manager of plugins to be loaded
 * and already loaded plugins. It provides access to the
 * server by allowing server-side modifications to make
 * changes to how the server works.
 *
 * <p>Because using this class entails interacting with the
 * plugin's dedicated thread, methods called from the server
 * must be wrapped with the plugin's executor in order to
 * prevent developers from </p>
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@NotThreadSafe
public class PluginLoader {
    /**
     * A thread-safe init field which protects the
     * constructor from being called a second time
     */
    private static final AtomicInteger init = new AtomicInteger();
    /**
     * The plugin folder's class pool for use in loading
     * plugins
     */
    private static final ClassPool PLUGIN_CP = new ClassPool(true);

    /**
     * A hashtable of name-plugins that have been loaded
     */
    @Getter
    private final Map<String, Plugin> loaded = new HashMap<>();

    /**
     * Creates a new plugin loader. Cannot be used by
     * plu
     */
    public PluginLoader() {
        if (!init.compareAndSet(0, 1)) {
            throw new IllegalStateException("Use Server#getPluginLoader()");
        }
    }

    /**
     * Attempts to load all plugins in the server's plugin
     * directory by walking the file tree and calling
     * {@link #load(Path)}.
     *
     * <p>This method must be explicitly handle on the plugin
     * thread because plugins calling this method will cause
     * the executor to livelock.</p>
     */
    @Policy("handle on plugin thread")
    public void loadAll() {
        this.loadAll(new HashSet<>());
    }

    /**
     * Attempts to load all plugins in the server's plugin
     * directory by walking the file tree and calling
     * {@link #load(Path)}.
     *
     * <p>This method must be explicitly handle on the plugin
     * thread because plugins calling this method will cause
     * the executor to livelock.</p>
     *
     * @param skip the plugins to skip as they have already
     * been loaded or are being loaded to mask out depends
     */
    @Policy("handle on plugin thread")
    private void loadAll(Set<Path> skip) {
        try {
            Files.walkFileTree(Misc.HOME_PATH.resolve("plugins"), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    if (skip.contains(file)) {
                        return FileVisitResult.CONTINUE;
                    }

                    PluginLoader.this.load(file, skip);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads a particular given path jar file.
     *
     * <p>This method must be explicitly handle on the path
     * thread because plugins calling this method will cause
     * the executor to livelock.</p>
     *
     * <p>This method does not call {@link Plugin#setup()}.
     * </p>
     *
     * @param path the file from which to load the path
     * @return the path which was loaded by calling this
     * method, or {@code null} if it failed for some reason
     */
    @Policy("handle on path thread")
    public Plugin load(Path path) {
        return this.load(path, new HashSet<>());
    }

    /**
     * Loads a particular given path jar file.
     *
     * <p>This method must be explicitly handle on the path
     * thread because plugins calling this method will cause
     * the executor to livelock.</p>
     *
     * <p>This method does not call {@link Plugin#setup()}.
     * </p>
     *
     * @param path the file from which to load the path
     * @param skip the collection of plugins to be passed
     * to the recursive load method in order to prevent
     * recursing over dependencies
     * @return the path which was loaded by calling this
     * method, or {@code null} if it failed for some reason
     */
    @Policy("handle on path thread")
    private Plugin load(Path path, Set<Path> skip) {
        skip.add(path);

        File pluginFile = path.toFile();

        Set<String> names = new HashSet<>();
        try (JarFile jarFile = new JarFile(pluginFile)) {
            PLUGIN_CP.appendClassPath(pluginFile.getPath());

            PluginDesc description = null;
            String main = null;

            for (Enumeration<JarEntry> entries = jarFile.entries(); entries.hasMoreElements(); ) {
                JarEntry entry = entries.nextElement();

                if (entry.isDirectory() || !entry.getName().endsWith(".class")) {
                    continue;
                }

                String name = entry.getName().replace(".class", "").replace('/', '.');
                names.add(name);

                CtClass cls = PLUGIN_CP.get(name);
                PluginDesc desc = (PluginDesc) cls.getAnnotation(PluginDesc.class);
                if (desc != null && cls.getSuperclass().getName().equals(Plugin.class.getName())) {
                    if (description != null) {
                        throw new RuntimeException("Plugin cannot have more than two plugin main classes");
                    }

                    main = name;
                    description = desc;
                }
            }

            if (main == null) {
                throw new RuntimeException("Plugin does not have a main class");
            }

            if (this.loaded.containsKey(description.id())) {
                throw new RuntimeException("Plugin with ID \"" + description.name() + "\" has already been loaded");
            }

            Logger.get(PluginLoader.class).log("Loading " + description.id() + ':' + description.version() + "... ");

            if (description.depends().length > 0) {
                this.loadAll(skip);

                for (String dep : description.depends()) {
                    String[] split = dep.split(":");

                    Plugin dependency = this.loaded.get(split[0]);
                    if (dependency == null || !dependency.getDescription().version().equals(split[1])) {
                        throw new RuntimeException("Dependency " + dep + " not satisfied");
                    }
                }
            }

            PluginClassLoader loader = new PluginClassLoader(pluginFile);
            Class<? extends Plugin> pluginClass = null;
            for (String name : names) {
                Class<?> cls = loader.loadClass(name);
                if (name.equals(main)) {
                    pluginClass = cls.asSubclass(Plugin.class);
                }

                if (cls.isAnnotationPresent(SkipRegistry.class)) {
                    continue;
                }

                if (cls.isAssignableFrom(Listener.class)) {
                    Server.getInstance().getEventController().register(
                            cls.asSubclass(Listener.class).getConstructor().newInstance());
                }

                if (cls.isAssignableFrom(CmdListener.class)) {
                    Server.getInstance().getCmdHandler().register(description,
                            cls.asSubclass(CmdListener.class).getConstructor().newInstance());
                }
            }

            Plugin plugin = pluginClass.getConstructor().newInstance();
            plugin.init(path, description, loader);

            this.loaded.put(description.id(), plugin);
            plugin.load();

            Logger.get(PluginLoader.class).success("Successfully loaded " + description.id() + ':' + description.version() + '!');

            return plugin;
        } catch (IOException |
                IllegalAccessException |
                InstantiationException |
                NoSuchMethodException |
                InvocationTargetException |
                ClassNotFoundException |
                NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Attempts to unload the plugin given the ID string.
     *
     * @param plugin the ID string of the plugin which to
     * unload from the server
     * @return {@code true} if this unload operation
     * succeeds, {@code false} if it fails or there is no
     * such ID
     */
    @Policy("handle on plugin thread")
    public boolean unload(String plugin) {
        Plugin p = this.loaded.remove(plugin);
        if (p == null) {
            return false;
        }

        PluginDesc description = p.getDescription();
        Logger.get(PluginLoader.class).log("Unloading " + description.id() + ':' + description.version() + "... ");

        Set<Class<?>> classes = p.getClassLoader().getClasses();
        for (Class<?> cls : classes) {
            if (cls.isAssignableFrom(Listener.class)) {
                Server.getInstance().getEventController().unregister(cls.asSubclass(Listener.class));
            }

            if (cls.isAssignableFrom(CmdListener.class)) {
                Server.getInstance().getCmdHandler().unregister(cls.asSubclass(CmdListener.class));
            }
        }

        p.cleanup();
        p.release();
        return true;
    }

    /**
     * Attempts to unload all plugins currently loaded on
     * the server.
     *
     * @return {@code true} if all plugins succeed in
     * unloading, {@code false} if one or more fail
     */
    @Policy("handle on plugin thread")
    public boolean unloadAll() {
        for (Plugin plugin : this.loaded.values()) {
            Set<Class<?>> classes = plugin.getClassLoader().getClasses();
            for (Class<?> cls : classes) {
                if (cls.isAssignableFrom(Listener.class)) {
                    Server.getInstance().getEventController().unregister(cls.asSubclass(Listener.class));
                }

                if (cls.isAssignableFrom(CmdListener.class)) {
                    Server.getInstance().getCmdHandler().unregister(cls.asSubclass(CmdListener.class));
                }
            }

            plugin.cleanup();
            plugin.release();
        }

        this.loaded.clear();
        return true;
    }

    /**
     * Attempts to unload all plugins, running their
     * cleanup hooks, and then reloading and setting up the
     * plugins again without restarting the server.
     */
    @Policy("handle on plugin thread")
    public void reload() {
        if (this.unloadAll()) {
            this.loadAll();
            for (Plugin plugin : this.loaded.values()) {
                plugin.setup();
            }
        } else {
            Logger.get(PluginLoader.class).error("Unloading plugins failed...");
        }
    }
}