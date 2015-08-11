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
package net.tridentsdk.meta.item;

public interface MapDecoration {
    /**
     * Gets this decoration's unique ID.
     *
     * @return The ID.
     */
    String id();

    /**
     * Gets this decoration's type position in the client-side decorations file.
     * 
     * @return The type position.
     */
    byte type();

    /**
     * Gets the X-coordinate of the decoration's location.
     * 
     * @return The location's X-coordinate.
     */
    double x();

    /**
     * Gets the Z-coordinate of the decoration's location.
     * 
     * @return The location's Z-coordinate.
     */
    double z();

    /**
     * Gets the decoration's rotation.
     * 
     * @return The rotation.
     */
    double rot();
}
