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

import net.tridentsdk.base.Block;
import net.tridentsdk.base.Instrument;
import net.tridentsdk.base.Note;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.event.Cancellable;

import java.util.List;

/**
 * Called when a note is played, has a list of players that will hear this note
 *
 * @author The TridentSDK Team
 */
public class NotePlayEvent extends BlockEvent implements Cancellable {
    private final List<Player> players;
    private Note note;
    private Instrument instrument;
    private boolean cancelled;

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

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
