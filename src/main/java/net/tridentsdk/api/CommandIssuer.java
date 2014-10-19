package net.tridentsdk.api;

public interface CommandIssuer {
    /**
     * Issues a command from this sender, forcing them to run it
     */
    void invokeCommand(String message);

    /**
     * Gets the last command executed by this CommandIssuer, without the preceding "/"
     *
     * @return the last command executed by commandissuer
     */
    String getLastCommand();

}
