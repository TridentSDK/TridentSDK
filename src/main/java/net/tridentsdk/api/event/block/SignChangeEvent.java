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
    private boolean cancelled;

    public SignChangeEvent(Block block, Player editor, String[] contents) {
        super(block);
        this.editor = editor;
        this.contents = contents;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public String[] getContents() {
        return contents;
    }

    public String getLine(int i) {
        return contents[i];
    }

    public String setLine(int i, String contents) {
        Validate.isTrue(contents.length() <= 16, "Sign line length too long");
        String temp = this.contents[i];
        this.contents[i] = contents;
        return temp;
    }

    public void setContents(String[] contents) {
        this.contents = contents;
    }

    public Player getEditor() {
        return editor;
    }
}
