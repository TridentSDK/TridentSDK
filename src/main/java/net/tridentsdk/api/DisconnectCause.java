package net.tridentsdk.api;

public enum DisconnectCause {
    /**
     * The player was disconnected by the server (e.g. Kick)
     */
    SERVER,
    /**
     * The player was disconnected because of the player's actions (e.g. Quit)
     */
    PLAYER,
    /**
     * The player list connection to the server
     */
    LOST_CONNECTION
}
    