package net.tridentsdk.plugin;

import net.tridentsdk.plugin.annotation.PluginDescription;

import java.io.File;

public class TridentPlugin {

    private File pluginFile;
    private PluginDescription description;

    private TridentPlugin() {} // avoid any plugin initiation outside of this package

    TridentPlugin(File pluginFile, PluginDescription description) {
        this.pluginFile = pluginFile;
        this.description = description;
    }

    void startup() {
        // TODO
    }

    public File getFile() {
        return pluginFile;
    }

    public PluginDescription getDescription() {
        return description;
    }
}
