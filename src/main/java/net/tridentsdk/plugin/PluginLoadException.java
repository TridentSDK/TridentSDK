package net.tridentsdk.plugin;

public class PluginLoadException extends RuntimeException {

    public PluginLoadException(Exception ex) {
        super(ex);
    }

    public PluginLoadException() {
        super();
    }

    public PluginLoadException(String message) {
        super(message);
    }
}
