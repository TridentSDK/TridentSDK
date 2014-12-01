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
package net.tridentsdk.api.factory;

import com.google.gson.JsonObject;
import net.tridentsdk.api.config.ConfigSection;
import net.tridentsdk.api.config.JsonConfig;
import net.tridentsdk.api.docs.AccessNoDoc;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Handles the server configuration and provides configuration facilities
 *
 * @author The TridentSDK Team
 */
public abstract class ConfigFactory {
    public abstract JsonConfig serverConfig();

    /**
     * Creates a new configuration from a path specified by a string
     *
     * @param path the path to use
     * @return the configuration that was created at the path
     */
    public JsonConfig createConfig(String path) {
        return new JsonConfig(Paths.get(path));
    }

    /**
     * Allows more control over the pathing in which to create the configuration
     *
     * @param file the file to use a config
     * @return the configuration that was converted
     */
    public JsonConfig createConfig(File file) {
        return new JsonConfig(file);
    }

    /**
     * Allows more control over the pathing in which to create the configuration
     *
     * @param path the NIO path format used to create the configuration
     * @return the configuration that was converted
     */
    public JsonConfig createConfig(Path path) {
       return new JsonConfig(path);
    }

    /**
     * Creates a new section that can be set to a config
     *
     * @return the created section
     */
    public ConfigSection newSection() {
        return new JsonSectionImpl();
    }

    /**
     * Creates a new section with a parent and value
     *
     * @param parent the parenting config section. Usually a {@link net.tridentsdk.api.config.JsonConfig}.
     * @param obj the value to place under the section. Can be {@code null}.
     * @return the new section
     */
    public ConfigSection newSection(ConfigSection parent, JsonObject obj) {
        return new JsonSectionImpl(parent, obj);
    }

    @AccessNoDoc
    private final class JsonSectionImpl extends ConfigSection {
        public JsonSectionImpl() {
            super();
        }

        public JsonSectionImpl(ConfigSection parent, JsonObject obj) {
            super(parent, obj);
        }
    }
}
