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

import net.tridentsdk.Trident;
import net.tridentsdk.event.Listener;
import net.tridentsdk.factory.Factories;
import net.tridentsdk.perf.FastClass;
import net.tridentsdk.plugin.cmd.Command;
import net.tridentsdk.plugin.cmd.CommandHandler;
import net.tridentsdk.util.TridentLogger;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;

public class PluginClassLoader extends URLClassLoader {
    final Map<String, Class<?>> classes = Factories.collect().createMap();
    private Class<? extends TridentPlugin> pluginClass;

    public PluginClassLoader(File pluginFile) throws MalformedURLException {
        super(new URL[] { pluginFile.toURI().toURL() });
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return this.loadClass(name, true);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (name.startsWith("net.tridentsdk")) {
            TridentLogger.error(new ClassNotFoundException(name));
        }
        Class<?> result = this.classes.get(name);

        if (result == null) {
            result = super.loadClass(name, resolve);

            if (result == null) {
                if (resolve) {
                    try {
                        result = Class.forName(name);
                    } catch (ClassNotFoundException ignored) {
                    }
                }
            }
        }

        if (result != null) {
            if (TridentPlugin.class.isAssignableFrom(result)) {
                if (this.pluginClass != null) {
                    TridentLogger.error(new PluginLoadException("JAR has 2 plugin classes!"));
                }

                this.pluginClass = result.asSubclass(TridentPlugin.class);
            }

            this.classes.put(result.getName(), result);
            FastClass.get(result);

            return result;
        }

        TridentLogger.error(new ClassNotFoundException(name));
        return null;
    }

    public void unloadClasses() {
        pluginClass = null;
        for (Class<?> cls : this.classes.values()) {
            if (Listener.class.isAssignableFrom(cls)) {
                Trident.getEventHandler().unregister(cls.asSubclass(Listener.class));
            }

            if (Command.class.isAssignableFrom(cls)) {
                CommandHandler.removeCommand(cls.asSubclass(Command.class));
            }

            for (Field field : cls.getDeclaredFields()) {
                if (field.getType().getClassLoader().equals(this) && Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);
                    try {
                        field.set(null, null);
                    } catch (IllegalAccessException e) {
                        TridentLogger.error(e);
                    }
                } // TODO instance held fields
            }
        }
        classes.clear();
    }

    public Class<? extends TridentPlugin> getPluginClass() {
        return this.pluginClass;
    }
}
