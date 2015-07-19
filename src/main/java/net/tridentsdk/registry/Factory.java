package net.tridentsdk.registry;

import com.google.gson.JsonObject;
import net.tridentsdk.concurrent.SelectableThreadPool;
import net.tridentsdk.config.Config;
import net.tridentsdk.config.ConfigSection;
import net.tridentsdk.docs.AccessNoDoc;
import net.tridentsdk.world.WorldLoader;
import net.tridentsdk.world.gen.AbstractGenerator;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Provides access points for creating instances of server objects
 *
 * @author The TridentSDK Team
 */
public final class Factory {
    private static volatile Implementation impl;

    private Factory() {
    }

    public static void setProvider(Implementation implementation) {
        impl = implementation;
    }

    /**
     * Creates a new {@link java.util.concurrent.ConcurrentHashMap} backed Set
     *
     * @param <E> the element type
     * @return the new concurrent set
     */
    public static <E> Set<E> newSet() {
        return Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    /**
     * Creates a new configuration from a path specified by a string
     *
     * @param path the path to use
     * @return the configuration that was created at the path
     */
    public static Config newConfig(String path) {
        return new Config(Paths.get(path));
    }

    /**
     * Allows more control over the pathing in which to create the configuration
     *
     * @param file the file to use a config
     * @return the configuration that was converted
     */
    public static Config newConfig(File file) {
        return new Config(file);
    }

    /**
     * Allows more control over the pathing in which to create the configuration
     *
     * @param path the NIO path format used to create the configuration
     * @return the configuration that was converted
     */
    public static Config newConfig(Path path) {
        return new Config(path);
    }

    /**
     * Creates a new section that can be set to a config
     *
     * @return the created section
     */
    public static ConfigSection newSection() {
        return new JsonSectionImpl();
    }

    /**
     * Creates a new section with a parent and value
     *
     * @param parent the parenting config section. Usually a {@link net.tridentsdk.config.Config}.
     * @param obj    the value to place under the section. Can be {@code null}.
     * @return the new section
     */
    public static ConfigSection newSection(ConfigSection parent, JsonObject obj) {
        return new JsonSectionImpl(parent, obj);
    }

    /**
     * Creates a new thread pool which allows extra customization and worker selection
     *
     * @param startingThreads the threads to keep alive at all times. Must be over 0.
     * @param name the name of the executor
     * @return the new thread pool
     */
    public static SelectableThreadPool newExecutor(int startingThreads, String name) {
        return impl.newPool(startingThreads, name);
    }

    /**
     * Creates a new world loader, which can use its own generator
     *
     * <p>The provided class must have a no-arg constructor.</p>
     *
     * @param generator the generator to use, a class to defensively protect the signature
     * @return the new world loader
     */
    public static WorldLoader newWorldLoader(Class<? extends AbstractGenerator> generator) {
        return impl.newLoader(generator);
    }

    /**
     * Creates a new world loader, which can use its own generator
     *
     * @return the new world loader
     */
    public static WorldLoader newWorldLoader() {
        return impl.newLoader(null);
    }

    @AccessNoDoc
    private static final class JsonSectionImpl extends ConfigSection {
        public JsonSectionImpl() {
            super();
        }

        public JsonSectionImpl(ConfigSection parent, JsonObject obj) {
            super(parent, obj);
        }
    }
}
