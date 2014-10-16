package net.tridentsdk.plugin.Command;

import net.tridentsdk.api.ConsoleSender;
import net.tridentsdk.api.entity.living.Player;

public abstract class Command {

    public void handlePlayer(Player player, String arguments, String alias) {
        // Method intentionally left blank
    }

    public void handleConsole(ConsoleSender sender, String arguments, String alias) {
        // Method intentionally left blank
    }

}
