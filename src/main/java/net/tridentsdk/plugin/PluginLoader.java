package net.tridentsdk.plugin;

import java.util.Map;

/**
 * Loads plugin classes
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface PluginLoader {
    /**
     * Links all of the class dependencies
     *
     * @param c the class to link
     */
    void link(Class<?> c);

    /**
     * Creates a new Java class object for the class and the class source given
     *
     * @param name   the class name
     * @param source the class bytecode
     * @return the class representing the bytes given
     */
    Class<?> defineClass(String name, byte[] source);

    /**
     * Lists the class as loaded
     *
     * @param cls the class to list
     */
    void putClass(Class<?> cls);

    /**
     * Obtains the classes loaded by this plugin loader
     *
     * @return the class mapped to the class name, class
     */
    Map<String, Class<?>> loadedClasses();

    /**
     * Unloads the classes that were loaded from this class loader
     */
    void unloadClasses();
}
