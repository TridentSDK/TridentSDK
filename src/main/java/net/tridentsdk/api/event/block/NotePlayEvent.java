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

import net.tridentsdk.api.*;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.Cancellable;

import java.util.List;

/**
 * Called when a note is played, has a list of players that will hear this note
 */
public class NotePlayEvent extends BlockEvent implements Cancellable {
    private final List<Player> players;
    private Note note;
    private Instrument instrument;
    private boolean cancel;

    /**
     * @param block      Block playing the Note
     * @param players    List of Players who can hear the Note
     * @param note       Note representing the sound being played
     * @param instrument Instrument of the Note
     */
    public NotePlayEvent(Block block, List<Player> players, Note note, Instrument instrument) {
        super(block);
        this.players = players;
        this.note = note;
        this.instrument = instrument;
    }

    /**
     * Returns the Note being played
     *
     * @return Note representing the sound that is being played
     */
    public Note getNote() {
        return this.note;
    }

    /**
     * Set the Note that is being played
     *
     * @param note Note that is being played
     */
    public void setNote(Note note) {
        this.note = note;
    }

    /**
     * Get the Instrument being used to play the Note
     *
     * @return Instrument being used to play the Note
     */
    public Instrument getInstrument() {
        return this.instrument;
    }

    /**
     * Set the Instrument to play the Note
     *
     * @param instrument Instrument to play the note
     */
    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    /**
     * Returns a list of players that will hear the Note being played
     *
     * @return List of Players who can hear the Note
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Return if the event is cancel
     *
     * @return true if cancel
     */
    @Override
    public boolean isCancelled() {
        return this.cancel;
    }

    /**
     * Set if the event is cancel
     *
     * @param cancel Boolean cancellation state of event
     */
    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
