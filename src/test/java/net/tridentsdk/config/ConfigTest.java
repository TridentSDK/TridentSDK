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
import net.tridentsdk.util.Misc;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class ConfigTest {
    private static final String TEST_PATH = Misc.HOME + "/kek/cfg.json";

    static {
        Impl.setImpl(null);
    }

    @Test(expected = NullPointerException.class)
    public void testPathString() {
        Config cfg = Config.load(TEST_PATH);
        assertEquals(TEST_PATH, cfg.path().toString());
    }

    @Test(expected = NullPointerException.class)
    public void testPath() {
        // This is stupid
        Path path = Paths.get(TEST_PATH);
        Config cfg = Config.load(path);
        assertEquals(path, cfg.path());
    }

    @Test(expected = NullPointerException.class)
    public void testFile() {
        File file = new File(TEST_PATH);
        Config cfg = Config.load(file);
        assertEquals(file, cfg.file());
    }
}