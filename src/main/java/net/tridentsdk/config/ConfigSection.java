package net.tridentsdk.config;

import java.util.Collection;
import java.util.Set;

/**
 * This class represents a section within a configuration
 * file.
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
public interface ConfigSection {
    // TODO doc

    Set<String> keys();

    Collection<Object> values();

    Object get(String key);

    <T> T get(String key, Class<T> type);

    void set(String key, Object value);

    int getInt(String key);

    void setInt(String key, int value);

    short getShort(String key);

    void setShort(String key, short value);

    long getLong(String key);

    void setLong(String key, long value);

    byte getByte(String key);

    void setByte(String key, byte value);

    float getFloat(String key);

    void setFloat(String key, float value);

    double getDouble(String key);

    void setDouble(String key, double value);

    char getChar(String key);

    void setChar(String key, char value);

    boolean getBoolean(String key);

    void setBoolean(String key, boolean value);

    String getString(String key);

    void setString(String key, String value);

    Collection<?> getCollection(String key);

    <T> Collection<T> getCollection(String key, Class<T> type);

    void createSection(String name);

    ConfigSection getSection(String name);

    Collection<ConfigSection> children();

    ConfigSection rootSection();

    ConfigSection parent();
}