package net.tridentsdk.api;

/**
 * Immutable value representing the pitch of a note played
 */
public class Note {

    private final short id;

    Note(int id) {
        if(id > 24) {
            throw new IllegalArgumentException("Note is too high!");
        }
        else if (id < 0) {
            throw new IllegalArgumentException("Note is too low!");
        }

        this.id = (short) id;
    }

    /**
     * Returns a note one step sharper than this
     * @return
     */
    public Note sharpen() {
        if(id + 1 > 24) {
            throw new IllegalArgumentException("Cannot sharpen this note, it is already the max");
        }
        return new Note(id + 1);
    }

    /**
     * Returns a note flatter than this
     * @return
     */
    public Note flatten() {
        if(id - 1 < 0) {
            throw new IllegalArgumentException("Cannot flatten this note, it is already the max");
        }
        return new Note(id - 1);
    }

    // TODO: make this more notable
}
