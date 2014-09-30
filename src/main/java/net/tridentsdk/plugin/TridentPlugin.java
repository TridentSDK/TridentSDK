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

import net.tridentsdk.api.config.JsonConfig;
import net.tridentsdk.plugin.annotation.PluginDescription;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class TridentPlugin {

    private final File pluginFile;
    private final File configDirectory;
    private final PluginDescription description;
    private final JsonConfig defaultConfig;

    final PluginClassLoader classLoader;

    protected TridentPlugin() {
        this.pluginFile = null;
        this.description = null;
        this.defaultConfig = null;
        this.configDirectory = null;
        this.classLoader = null;
    } // avoid any plugin initiation outside of this package

    TridentPlugin(File pluginFile, PluginDescription description, PluginClassLoader loader) {
        this.pluginFile = pluginFile;
        this.description = description;
        this.configDirectory = new File("plugins/" + description.name() + "/");
        this.defaultConfig = new JsonConfig(new File(configDirectory, "config.json"));
        this.classLoader = loader;
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    final void startup() {
        // TODO
    }

    public void saveDefaultConfig() {
        saveResource("config.json", false);
    }

    public void saveResource(String name, boolean replace) {
        try {
            InputStream is = getClass().getResourceAsStream("/" + name);
            File file = new File(configDirectory, name);

            if(is == null) {
                return;
            }

            if(replace && file.exists()) {
                file.delete();
            }

            Files.copy(is, file.getAbsoluteFile().toPath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public final File getFile() {
        return this.pluginFile;
    }

    public JsonConfig getDefaultConfig() {
        return defaultConfig;
    }

    public File getConfigDirectory() {
        return configDirectory;
    }

    public final PluginDescription getDescription() {
        return this.description;
    }
}
