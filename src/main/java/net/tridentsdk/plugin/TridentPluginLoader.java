package net.tridentsdk.plugin;

import net.tridentsdk.plugin.annotation.PluginDescription;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class TridentPluginLoader {

    private List<TridentPlugin> plugins = new ArrayList<>();

    @Deprecated
    public TridentPluginLoader() {}

    public void load(File pluginFile) {
        try{
            // load all classes
            PluginClassLoader loader = new PluginClassLoader(pluginFile);
            JarFile jarFile = new JarFile(pluginFile);
            Enumeration<JarEntry> entries = jarFile.entries();

            while(entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();

                if(!(entry.getName().endsWith(".class"))) {
                    continue;
                }

                loader.loadClass(entry.getName().replace('/', '.'));
            }

            // start initiating the plugin class and registering commands and listeners
            Class<? extends TridentPlugin> pluginClass = loader.getPluginClass();
            PluginDescription description = pluginClass.getAnnotation(PluginDescription.class);

            if(description == null) {
                throw new PluginLoadException("Description annotation does not exist!");
            }

            Constructor defaultConstructor = pluginClass.getConstructor(File.class, PluginDescription.class);
            TridentPlugin plugin = (TridentPlugin) defaultConstructor.newInstance(pluginFile, description);

            this.plugins.add(plugin);
            plugin.startup();

            // TODO: Register commands and listeners
        }catch(IOException | ClassNotFoundException | NoSuchMethodException
                | IllegalAccessException | InvocationTargetException | InstantiationException ex) {
            throw new PluginLoadException(ex);
        }
    }

    public List<TridentPlugin> getPlugins() {
        return Collections.unmodifiableList(plugins);
    }
}
