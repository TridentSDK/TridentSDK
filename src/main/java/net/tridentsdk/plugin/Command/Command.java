package net.tridentsdk.plugin.Command;

import net.tridentsdk.api.CommandIssuer;
import net.tridentsdk.api.ConsoleSender;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.plugin.TridentPlugin;

public abstract class Command {

    protected TridentPlugin owner;

    public TridentPlugin getOwner() {
        return this.owner;
    }

    /**
     * Called when this command is invoked by a player
     * @param player
     * @param arguments may be null
     * @param alias
     */
    public void handlePlayer(Player player, String arguments, String alias) {
        // Method intentionally left blank
    }

    /**
     * Called when this command is invoked by the console
     * @param sender
     * @param arguments may be null
     * @param alias
     */
    public void handleConsole(ConsoleSender sender, String arguments, String alias) {
        // Method intentionally left blank
    }

    /**
     * Called when this command is invoked by a player, console, or other sender
     * @param sender
     * @param arguments may be null
     * @param alias
     */
    public void handle (CommandIssuer sender, String arguments, String alias) {
        // Method intentionally left blank
    }

    /**
     * Called if this command is overriden by another
     */
    public void notifyOverriden() {
        // Method intentionally left blank

    }

}
