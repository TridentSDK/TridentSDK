package net.tridentsdk.plugin;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

public class PluginClassLoader extends URLClassLoader {

    private Class<? extends TridentPlugin> pluginClass;
    private Map<String, Class<?>> classes = new HashMap<>();

    PluginClassLoader(File pluginFile) throws MalformedURLException {
        super(new URL[] { pluginFile.toURI().toURL() });
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return loadClass(name, true);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if(name.startsWith("net.tridentsdk")) {
            throw new ClassNotFoundException(name);
        }
        Class<?> result = classes.get(name);

        if(result == null) {
            result = super.loadClass(name, resolve);

            if(result == null) {
                if(resolve) {
                    try{
                        result = Class.forName(name);
                    }catch(ClassNotFoundException ignored) {}
                }
            }
        }

        if(result != null) {
            if(TridentPlugin.class.isAssignableFrom(result)) {
                if(pluginClass != null) {
                    throw new PluginLoadException("JAR has 2 plugin classes!");
                }

                pluginClass = result.asSubclass(TridentPlugin.class);
            }

            classes.put(result.getName(), result);

            return result;
        }

        throw new ClassNotFoundException(name);
    }

    public Class<? extends TridentPlugin> getPluginClass() {
        return pluginClass;
    }
}
