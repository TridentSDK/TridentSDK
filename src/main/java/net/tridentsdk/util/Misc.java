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
package net.tridentsdk.util;

import javax.annotation.concurrent.Immutable;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Miscellaneous functions that only serve to shorten code.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Immutable
public final class Misc {
    /**
     * String constant for NBT values in world option enums
     * where the value to be found does not exist
     */
    public static final String NBT_BOUND_FAIL = "NBT value out of range for class %s";
    /**
     * The working directory as a string
     */
    public static final String HOME = System.getProperty("user.dir");
    /**
     * The Path directory to the working dir
     */
    public static final Path HOME_PATH = Paths.get(HOME);

    // Prevent instantiation
    private Misc() {
    }
}