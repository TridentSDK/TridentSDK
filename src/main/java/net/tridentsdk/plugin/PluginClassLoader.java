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

import java.io.File;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class PluginClassLoader extends URLClassLoader {
    private final Map<String, Class<?>> classes = new HashMap<>();
    private Class<? extends TridentPlugin> pluginClass;

    PluginClassLoader(File pluginFile) throws MalformedURLException {
        super(new URL[] { pluginFile.toURI().toURL() });
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return this.loadClass(name, true);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (name.startsWith("net.tridentsdk")) {
            throw new ClassNotFoundException(name);
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
                    throw new PluginLoadException("JAR has 2 plugin classes!");
                }

                this.pluginClass = result.asSubclass(TridentPlugin.class);
            }

            this.classes.put(result.getName(), result);

            return result;
        }

        throw new ClassNotFoundException(name);
    }

    public void unloadClasses() {
        for (Class<?> cls : this.classes.values()) {
            // TODO: unload class
        }
    }

    public Class<? extends TridentPlugin> getPluginClass() {
        return this.pluginClass;
    }
}
