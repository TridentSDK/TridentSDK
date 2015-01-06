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
package net.tridentsdk.api.util;

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

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
        }

        return true;
    }
}
