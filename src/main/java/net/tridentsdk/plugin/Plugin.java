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

import lombok.Getter;
import lombok.NoArgsConstructor;
import net.tridentsdk.util.Misc;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * The superclass of a plugin's main class.
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@Getter
@NoArgsConstructor
@NotThreadSafe
public class Plugin {
    /**
     * The path to the plugin directory which holds jar
     * files and plugin config directories
     */
    public static final Path PLUGIN_DIR = Misc.HOME_PATH.resolve("plugins");

    /**
     * The path to the plugin's jar file
     */
    private Path path;
    /**
     * The path to the plugin's config directory
     *
     * <p>Plugin directories are not immediately created.
     * They must be checked and created before they are
     * used by the plugin. {@link #setup()} is a good time
     * to do this.</p>
     */
    private File directory;
    /**
     * The plugin's description annotation
     */
    private PluginDesc description;
    /**
     * The class loader used to resolve the plugin's classes
     */
    private PluginClassLoader classLoader;

    /**
     * Initializes the plugin with auxiliary resources that
     * were used when loading.
     *
     * @param path the path to the plugin jar file
     * @param desc the plugin description
     * @param classLoader the class loader used
     */
    void init(Path path,  PluginDesc desc, PluginClassLoader classLoader) {
        if (this.path == null) {
            this.path = path;
            this.directory = PLUGIN_DIR.resolve(desc.id()).toFile();
            this.description = desc;
            this.classLoader = classLoader;
        }
    }

    /**
     * Attempts to close the class loader and release all
     * resources associated with it, then nulls the instance
     * of the classloader in order to coerce the GC to
     * unload all associated plugin classes.
     */
    void release() {
        try {
            if (this.classLoader != null) {
                this.classLoader.close();
                this.classLoader = null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Special case function used to register needed classes
     * for use by server functions such as world generators.
     *
     * <p>Many developers fall victim to the fallacy that
     * plugin lifecycle methods are tied to the server
     * lifecycle. Unfortunately, this is simply not the case
     * as the server is free to be reloaded at any point in
     * its lifecycle, therefore it is only safe to assume
     * that plugin lifecycle methods are sub-cycles to the
     * server's.</p>
     */
    public void load() {
    }

    /**
     * Once the server has had time to properly initialize
     * the API, this function is safe to call extra
     * registry and config setup methods.
     *
     * <p>Many developers fall victim to the fallacy that
     * plugin lifecycle methods are tied to the server
     * lifecycle. Unfortunately, this is simply not the case
     * as the server is free to be reloaded at any point in
     * its lifecycle, therefore it is only safe to assume
     * that plugin lifecycle methods are sub-cycles to the
     * server's.</p>
     */
    public void setup() {
    }

    /**
     * This method is called before the plugin is unloaded
     * from the server and can be used to cleanup after
     * unused resources, such as saving config files.
     *
     * <p>Many developers fall victim to the fallacy that
     * plugin lifecycle methods are tied to the server
     * lifecycle. Unfortunately, this is simply not the case
     * as the server is free to be reloaded at any point in
     * its lifecycle, therefore it is only safe to assume
     * that plugin lifecycle methods are sub-cycles to the
     * server's.</p>
     */
    public void cleanup() {
    }
}