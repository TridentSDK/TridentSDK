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

package net.tridentsdk.event.player;

import net.tridentsdk.event.Cancellable;
import net.tridentsdk.event.Event;
import net.tridentsdk.window.Window;

/**
 * Called when a player closes an inventory window
 *
 * @author The TridentSDK Team
 */
public class PlayerCloseWindowEvent extends Event implements Cancellable {
	
    private final Window window;
    private boolean cancelled;

    public PlayerCloseWindowEvent(Window window) {
        this.window = window;
        this.cancelled = false;
    }

    public Window getWindow() {
        return this.window;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
    
}
