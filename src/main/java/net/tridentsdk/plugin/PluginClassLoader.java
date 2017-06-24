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
import net.tridentsdk.Server;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A custom class loader used to store classes loaded during
 * the plugin loading cycle.
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@NotThreadSafe
public class PluginClassLoader extends URLClassLoader {
    /**
     * Mapping of class names to classes that have already
     * been loaded by other class loaders
     */
    private static final Map<String, Class<?>> CLASS_MAP = new HashMap<>();

    /**
     * A collection of classes loaded by this class loader
     */
    @Getter
    private final Set<Class<?>> classes = new HashSet<>();

    /**
     * Creates a new plugin class loader that uses the given
     * plugin jar file as the lookup path.
     *
     * @param plugin the plugin jar file
     * @throws MalformedURLException if something dumb
     * happened
     */
    public PluginClassLoader(File plugin) throws MalformedURLException {
        super(new URL[] { plugin.toURI().toURL() }, Server.class.getClassLoader());
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> cls = CLASS_MAP.get(name);
        if (cls != null) {
            return cls;
        }

        cls = super.findClass(name);
        this.classes.add(cls);
        CLASS_MAP.put(name, cls);

        return cls;
    }

    @Override
    public void close() throws IOException {
        super.close();

        for (Class<?> c : this.classes) {
            if (CLASS_MAP.remove(c.getName()) == null) {
                throw new RuntimeException("Failed to cleanup after class, memory leak may occur");
            }
        }

        this.classes.clear();
    }
}