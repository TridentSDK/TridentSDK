/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2016 The TridentSDK Team
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
package net.tridentsdk.config;

import net.tridentsdk.Impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class represents the configuration file loaded into
 * memory.
 *
 * <p>TridentSDK strongly assumes that the implementation
 * uses RFC 7159 JSON files as the configuration format.</p>
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
public interface Config extends ConfigSection {
    // TODO throw exceptions?

    /**
     * Loads the given config into memory, given the path
     * as a String.
     *
     * @param path the path from which to laod the config
     * @return the loaded config memory representation
     */
    static Config load(String path) {
        return Impl.get().newCfg(Paths.get(path));
    }

    /**
     * Loads the given config into memory, given the path
     * as a Path object.
     *
     * @param path the path from which to laod the config
     * @return the loaded config memory representation
     */
    static Config load(Path path) {
        return Impl.get().newCfg(path);
    }

    /**
     * Loads the given config into memory, given the path
     * as a File object.
     *
     * @param file the file from which to laod the config
     * @return the loaded config memory representation
     */
    static Config load(File file) {
        return Impl.get().newCfg(file.toPath());
    }

    /**
     * Obtains the configuration file which stores the data
     * held in the config.
     *
     * @return the config as a file
     */
    File asFile();

    /**
     * Obtains the path object which stores the data held in
     * the config.
     *
     * @return the config as a path
     */
    Path asPath();

    /**
     * Obtains the folder which contains this configuration
     * file.
     *
     * @return the container folder
     */
    File directory();

    /**
     * Loads (or reloads) data from the file into memory.
     *
     * @return the response to loading the data
     * @throws RuntimeException if any response other than
     *                          {@link IoResponse#SUCCESS} is
     *                          returned
     */
    IoResponse load();

    /**
     * Saves the data in memory to file.
     *
     * @return RuntimeException if any response other than
     * {@link IoResponse#SUCCESS} is
     * returned
     */
    IoResponse save() throws IOException;
}