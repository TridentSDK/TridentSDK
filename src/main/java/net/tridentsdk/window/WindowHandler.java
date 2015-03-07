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
package net.tridentsdk.window;

import java.util.Collection;

/**
 * Handles windows opened and closed by the server
 *
 * @author The TridentSDK Team
 */
public interface WindowHandler {
    /**
     * Gets a window by its ID
     *
     * @param id the ID of a window
     * @return the window with the ID, or {@code null} if it doesn't exist
     */
    public Window windowBy(int id);

    /**
     * Registers the window with the manager
     *
     * @param window the window to be registered
     */
    public void registerWindow(Window window);

    /**
     * Gets all registered windows with the manager
     *
     * @return the windows registered
     */
    public Collection<Window> windows();
}
