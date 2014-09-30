package net.tridentsdk.api.event.block;

import net.tridentsdk.api.Block;
import net.tridentsdk.api.Instrument;
import net.tridentsdk.api.Note;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.Cancellable;

import java.util.List;

/**
 * Called when a note is played, has a list of players that will hear this note
 */
public class NotePlayEvent extends BlockEvent implements Cancellable {
    private Note note;
    private Instrument instrument;
    private List<Player> players;

    private boolean cancelled;

    public NotePlayEvent(Block block, List<Player> players, Note note, Instrument instrument) {
        super(block);
        this.players = players;
        this.note = note;
        this.instrument = instrument;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    /**
     * Returns a mutable list of players that will hear this note being played
     * @return
     */
    public List<Player> getPlayers() {
        return players;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
