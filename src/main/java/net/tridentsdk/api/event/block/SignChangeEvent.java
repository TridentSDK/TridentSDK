/*
 * Copyright (c) 2014, The TridentSDK Team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *     3. Neither the name of the The TridentSDK Team nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL The TridentSDK Team BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.tridentsdk.api.event.block;

import net.tridentsdk.api.Block;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.Cancellable;
import org.apache.commons.lang.Validate;

/**
 * Called when a player edits a sign, or when the sign is first created
 */
public class SignChangeEvent extends BlockEvent implements Cancellable {

    private final Player editor;
    private String[] contents;
    private boolean cancel;

    public SignChangeEvent(Block block, Player editor, String... contents) {
        super(block);
        this.editor = editor;
        this.contents = contents;
    }

    /**
     * Return if the event is cancelled
     *
     * @return true if cancelled
     */
    @Override
    public boolean isCancelled() {
        return this.cancel;
    }

    /**
     * Set if the event is cancelled
     *
     * @param cancel Boolean cancellation state of event
     */
    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Returns the contents of the Sign
     *
     * @return String[] contents of the Sign
     */
    public String[] getContents() {
        return this.contents;
    }

    /**
     * Sets the contents of the Sign
     * @param contents String[] contents of the Sign
     */
    public void setContents(String... contents) {
        this.contents = contents;
    }

    /**
     * Returns the text of the specified line
     *
     * @param i line of the Sign
     * @return String text of the specified line
     */
    public String getLine(int i) {
        Validate.isTrue(i >= 0, "Sign line is below 0");
        Validate.isTrue(i <= 3, "Sign line is above 3");
        return this.contents[i];
    }

    /**
     * Sets the value of a line
     *
     * @param i line of the Sign
     * @param text String text to set the line as
     * @return String previous text on the specified line
     */
    public String setLine(int i, String text) {
        Validate.isTrue(!text.isEmpty(), "Sign line length is below 0 characters");
        Validate.isTrue(text.length() <= 16, "Sign line length is above 16 characters");

        String previous = this.contents[i];
        this.contents[i] = text;
        return previous;
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
