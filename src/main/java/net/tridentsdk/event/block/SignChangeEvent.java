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

package net.tridentsdk.event.block;

import com.google.common.base.Preconditions;
import net.tridentsdk.base.Block;
import net.tridentsdk.docs.Volatile;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.event.Cancellable;

import javax.annotation.concurrent.GuardedBy;

/**
 * Called when a player edits a sign, or when the sign is first created
 *
 * @author The TridentSDK Team
 */
public class SignChangeEvent extends BlockEvent implements Cancellable {
	
    @Volatile(policy = "No individual element modify",
            reason = "Not thread safe",
            fix = "Write/Read the entire field ONLY")
    @GuardedBy("this")
    private String[] contents;
    private boolean cancelled;
    private final Player editor;

    public SignChangeEvent(Block block, Player editor, String... contents) {
        super(block);
        this.editor = editor;
        this.contents = contents;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Returns the lines of the Sign.
     *
     * @return The Sign's text in lines.
     */
    public String[] getLines() {
        synchronized (this) {
            return contents;
        }
    }

    /**
     * Sets the contents of the Sign.
     *
     * @param contents String[] contents of the Sign
     */
    public void setLines(String... contents) {
        synchronized (this) {
            this.contents = contents;
        }
    }

    /**
     * Returns the text of the specified line
     *
     * @param i line of the Sign
     * @return String text of the specified line
     */
    public String getLine(int i) {
        Preconditions.checkArgument(i >= 0, "Sign line is below 0");
        Preconditions.checkArgument(i <= 3, "Sign line is above 3");
        synchronized (this) {
            return this.contents[i];
        }
    }

    /**
     * Sets the value of a line
     *
     * @param i    line of the Sign
     * @param text String text to set the line as
     * @return String previous text on the specified line
     */
    public String setLine(int i, String text) {
        Preconditions.checkNotNull(!text.isEmpty(), "Sign line length is below 0 characters");
        Preconditions.checkNotNull(text.length() <= 16, "Sign line length is above 16 characters");

        synchronized (this) {
            String previous = this.contents[i];
            this.contents[i] = text;
            return previous;
        }
    }

    /**
     * Returns the Player who edited the Sign
     *
     * @return Player editor of the sign, null if no player
     */
    public Player getEditor() {
        return this.editor;
    }
}
