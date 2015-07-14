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

import net.tridentsdk.Handler;
import net.tridentsdk.event.Listener;
import net.tridentsdk.factory.Factories;
import net.tridentsdk.plugin.cmd.Command;
import net.tridentsdk.util.TridentLogger;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;

/**
 * The classloader for plugins
 *
 * @author The TridentSDK Team
 */
public class PluginClassLoader extends URLClassLoader {
    final Map<String, Class<?>> locallyLoaded = Factories.collect().createMap();

    PluginClassLoader(File pluginFile, ClassLoader loader) throws MalformedURLException {
        super(new URL[]{pluginFile.toURI().toURL()}, loader);
    }

    void link(Class<?> c) {
        super.resolveClass(c);
    }

    Class<?> defineClass(String name, byte[] source) {
        return super.defineClass(name, source, 0, source.length);
    }

    void putClass(Class<?> cls) {
        locallyLoaded.put(cls.getName(), cls);
    }

    void unloadClasses() {
        for (Class<?> cls : locallyLoaded.values()) {
            if (Listener.class.isAssignableFrom(cls)) {
                Handler.forEvents().unregister(cls.asSubclass(Listener.class));
            }

            if (Command.class.isAssignableFrom(cls)) {
                Handler.forCommands().removeCommand(cls.asSubclass(Command.class));
            }

            for (Field field : cls.getDeclaredFields()) {
                // Simply remove all the object references
                // primitive types are OK
                if (field.getType().isAssignableFrom(Object.class) && Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);
                    try {
                        field.set(null, null);
                    } catch (IllegalAccessException e) {
                        TridentLogger.error(e);
                    }
                } // TODO instance held fields
            }
        }
        locallyLoaded.clear();
    }
}
