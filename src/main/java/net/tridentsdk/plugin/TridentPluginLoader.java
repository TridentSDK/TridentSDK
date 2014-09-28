/*
 * Copyright (c) 2014, The TridentSDK Team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *     3. Neither the name of the The TridentSDK Team nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL The TridentSDK Team BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.tridentsdk.plugin;

import net.tridentsdk.plugin.annotation.PluginDescription;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class TridentPluginLoader {

    private final List<TridentPlugin> plugins = new ArrayList<>();

    public void load(File pluginFile) {
        JarFile jarFile = null;
        try {
            // load all classes
            PluginClassLoader loader = new PluginClassLoader(pluginFile);
            jarFile = new JarFile(pluginFile);
            Enumeration<JarEntry> entries = jarFile.entries();

            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();

                if (!entry.getName().endsWith(".class")) {
                    continue;
                }

                loader.loadClass(entry.getName().replace('/', '.'));
            }

            // start initiating the plugin class and registering commands and listeners
            Class<? extends TridentPlugin> pluginClass = loader.getPluginClass();
            PluginDescription description = pluginClass.getAnnotation(PluginDescription.class);

            if (description == null) {
                throw new PluginLoadException("Description annotation does not exist!");
            }

            if (description.name() == null) {
                throw new PluginLoadException("Plugin has no name!");
            }

            if (description.name().equalsIgnoreCase("Trident")
                    || description.name().equalsIgnoreCase("TridentSDK")
                    || description.name().equalsIgnoreCase("Mojang")
                    || description.name().equalsIgnoreCase("Microsoft")) {
                throw new PluginLoadException("Plugin is using invalid name!");
            }

            Constructor<? extends TridentPlugin> defaultConstructor =
                    pluginClass.getConstructor(File.class, PluginDescription.class);
            TridentPlugin plugin = defaultConstructor.newInstance(pluginFile, description);

            this.plugins.add(plugin);
            plugin.startup();

            // TODO: Register commands and listeners
        } catch (IOException | ClassNotFoundException | NoSuchMethodException
                | IllegalAccessException | InvocationTargetException | InstantiationException ex) {
            throw new PluginLoadException(ex);
        } finally {
            if (jarFile != null)
                try {
                    jarFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public List<TridentPlugin> getPlugins() {
        return Collections.unmodifiableList(this.plugins);
    }
}
