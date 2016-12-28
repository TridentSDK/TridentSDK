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

package net.tridentsdk.util;

import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * String utilities
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
@Immutable
public final class StringUtil {
    private StringUtil() {
    }

    /**
     * A for-loop efficient method for concating strings (or in some cases objects)
     *
     * @param objects Objects you wish to concat into a String
     * @return Built string
     */
    public static String concat(Object... objects) {
        StringBuilder builder = new StringBuilder();

        for (Object o : objects) {
            builder.append(o);
        }

        return builder.toString();
    }

    /**
     * A for-loop efficient method for concating strings (or in some cases objects)
     *
     * @param objects Objects you wish to concat into a String
     * @param extenstion A object you would like after each object
     * @return Built string
     */
    public static String concat(Object... objects, Object extenstion) {
        StringBuilder builder = new StringBuilder();

        for (Object o : objects) {
            builder.append(o).append(extenstion);
        }

        return builder.toString();
    }

    /**
     * Checks if a string is a number
     *
     * @param str the string to check
     * @return {@code true} is number parsable
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            return false;
        }

        return true;
    }
}
