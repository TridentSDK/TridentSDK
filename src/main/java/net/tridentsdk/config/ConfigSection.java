/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2014 The TridentSDK Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.tridentsdk.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.tridentsdk.util.TridentLogger;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a section of the Config file
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public class ConfigSection {
    final Object handleLock = new Object();
    private final Object parentLock = new Object();
    @GuardedBy("parentLock")
    ConfigSection parent;
    @GuardedBy("handleLock")
    JsonObject jsonHandle;

    /**
     * Instantiated by subclasses only
     */
    protected ConfigSection() {
    }

    /**
     * Creates a config section for the parent section
     *
     * @param parent the section to be sub-sectioned under
     * @param obj    the section handler, used to store values
     */
    protected ConfigSection(ConfigSection parent, JsonObject obj) {
        this.parent = parent;
        this.jsonHandle = obj;
    }

    /**
     * Gets a config section with the elements defined in the specified collection
     *
     * @param list the collection of objects to serialize to a config section
     * @param <V>  the type in the collection
     * @return the config section with all the values in the list
     */
    public static <V> ConfigSection addToList(Collection<V> list) {
        if (!(list instanceof ConfigSectionList)) {
            TridentLogger.get().error(
                    new UnsupportedOperationException("Can only add new ConfigSection-s to ConfigSectionList"));
            return null;
        }
        ConfigSection section = new ConfigSection(((ConfigSectionList) list).parent(), new JsonObject());
        list.add((V) section);

        return section;
    }

    /**
     * Gets an integer from the config with the given tag, or defaults to the fallback if the tag is not found
     *
     * @param tag the tag to find the value from
     * @param def the default value if no tag is found
     * @return the integer at the tag
     */
    public int getInt(String tag, int def) {
        synchronized (handleLock) {
            return this.contains(tag) ? this.jsonHandle.get(tag).getAsInt() : def;
        }
    }

    /**
     * Gets an integer from the config with the given tag
     *
     * <p>Gives {@code 0} if the value could not be found</p>
     *
     * @param tag the tag to find the value from
     * @return the integer at the tag
     */
    public int getInt(String tag) {
        synchronized (handleLock) {
            return this.getInt(tag, 0);
        }
    }

    /**
     * Sets the value at the tag to a specified integer
     *
     * @param tag the tag to set the value
     * @param in  the integer value to set the tag
     */
    public void setInt(String tag, int in) {
        synchronized (handleLock) {
            this.jsonHandle.addProperty(tag, in);
        }
    }

    /**
     * Gets a double from the config with the given tag, or defaults to the fallback if the tag is not found
     *
     * @param tag the tag to find the value from
     * @param def the default value if no tag is found
     * @return the double at the tag
     */
    public double getDouble(String tag, double def) {
        synchronized (handleLock) {
            return this.contains(tag) ? this.jsonHandle.get(tag).getAsDouble() : def;
        }
    }

    /**
     * Gets a double from the config with the given tag
     *
     * <p>Gives {@code 0.0D} if the value could not be found</p>
     *
     * @param tag the tag to find the value from
     * @return the double at the tag
     */
    public double getDouble(String tag) {
        synchronized (handleLock) {
            return this.getDouble(tag, 0.0D);
        }
    }

    /**
     * Sets a double at the tag to a specified double
     *
     * @param tag the tag to set the value
     * @param d   the double to set to the tag
     */
    public void setDouble(String tag, double d) {
        synchronized (handleLock) {
            this.jsonHandle.addProperty(tag, d);
        }
    }

    /**
     * Gets a float from the config with the given tag, or defaults to the fallback if the tag is not found
     *
     * @param tag the tag to find the value from
     * @param def the default value if no tag is found
     * @return the float at the tag
     */
    public float getFloat(String tag, float def) {
        synchronized (handleLock) {
            return this.contains(tag) ? this.jsonHandle.get(tag).getAsFloat() : def;
        }
    }

    /**
     * Gets an float from the config with the given tag
     *
     * <p>Gives {@code 0.0F if the value could not be found}</p>
     *
     * @param tag the tag to find the value from
     * @return the float at the tag
     */
    public float getFloat(String tag) {
        synchronized (handleLock) {
            return this.getFloat(tag, 0.0F);
        }
    }

    /**
     * Sets the float at the tag to a specified float
     *
     * @param tag the tag to set the value
     * @param f   the float to set the tag to
     */
    public void setFloat(String tag, float f) {
        synchronized (handleLock) {
            this.jsonHandle.addProperty(tag, f);
        }
    }

    /**
     * Gets a character from the config with the given tag, or defaults to the fallback if the tag is not found
     *
     * @param tag the tag to find the value from
     * @param def the default value if no tag is found
     * @return the character at the tag
     */
    public char getChar(String tag, char def) {
        synchronized (handleLock) {
            return this.contains(tag) ? this.jsonHandle.get(tag).getAsCharacter() : def;
        }
    }

    /**
     * Gets a character from the config with the given tag
     *
     * <p>Gives {@code \u0000} if the value could not be found</p>
     *
     * @param tag the tag to find the value from
     * @return the character at the tag
     */
    public char getChar(String tag) {
        synchronized (handleLock) {
            return this.getChar(tag, '\u0000');
        }
    }

    /**
     * Sets a character at the tag to the specified character
     *
     * @param tag the tag to set the value
     * @param c   the character to set the tag to
     */
    public void setChar(String tag, char c) {
        synchronized (handleLock) {
            this.jsonHandle.addProperty(tag, c);
        }
    }

    /**
     * Gets a boolean from the config with the given tag, defaulting to the fallback if the tag is not found
     *
     * @param tag the tag to find the value from
     * @param def the default value if the tag is not found
     * @return the boolean at the tag
     */
    public boolean getBoolean(String tag, boolean def) {
        synchronized (handleLock) {
            return this.contains(tag) ? this.jsonHandle.get(tag).getAsBoolean() : def;
        }
    }

    /**
     * Gets a boolean from the config with the given tag
     *
     * <p>Gives {@code false} if the value could not be found</p>
     *
     * @param tag the tag to find the value from
     * @return the boolean at the tag
     */
    public boolean getBoolean(String tag) {
        synchronized (handleLock) {
            return this.getBoolean(tag, false);
        }
    }

    /**
     * Sets the boolean the specified tag
     *
     * @param tag the tag to set the value
     * @param b   the boolean to set to at the tag
     */
    public void setBoolean(String tag, boolean b) {
        synchronized (handleLock) {
            this.jsonHandle.addProperty(tag, b);
        }
    }

    /**
     * Gets a byte from the config with the given tag, defaulting to the fallback if the tag is not found
     *
     * @param tag the tag to find the value from
     * @param def the default if the tag is not found
     * @return the byte at the tag
     */
    public byte getByte(String tag, byte def) {
        synchronized (handleLock) {
            return this.contains(tag) ? this.jsonHandle.get(tag).getAsByte() : def;
        }
    }

    /**
     * Gets a character from the config with the given tag
     *
     * <p>Gives {@code (byte) 0} if the value could not be found</p>
     *
     * @param tag the tag to find the value from
     * @return the character at the tag
     */
    public byte getByte(String tag) {
        synchronized (handleLock) {
            return this.getByte(tag, (byte) 0);
        }
    }

    /**
     * Sets the byte the specified tag
     *
     * @param tag the tag to set the value
     * @param b   the byte to set to at the tag
     */
    public void setByte(String tag, byte b) {
        synchronized (handleLock) {
            this.jsonHandle.addProperty(tag, b);
        }
    }

    /**
     * Gets the string at the specified tag, defaulting to the specified default if not found
     *
     * @param tag the tag to set the value
     * @param def the default value to
     */
    public String getString(String tag, String def) {
        synchronized (handleLock) {
            return this.contains(tag) ? this.jsonHandle.get(tag).getAsString() : def;
        }
    }

    public String getString(String tag) {
        synchronized (handleLock) {
            return this.getString(tag, null);
        }
    }

    /**
     * Sets the string the specified tag
     *
     * @param tag the tag to set the value
     * @param s   the string to set at the tag
     */
    public void setString(String tag, String s) {
        synchronized (handleLock) {
            this.jsonHandle.addProperty(tag, s);
        }
    }

    /**
     * Gets the list at the tag
     *
     * @param tag  the tag to find the value from
     * @param type the types contained in the list
     * @param <V>  the list type
     * @return the list from the section
     */
    public <V> List<V> getList(String tag, Class<V> type) {
        JsonArray array;
        synchronized (handleLock) {
            array = this.jsonHandle.get(tag).getAsJsonArray();
        }

        //Handle ConfigSection seperately as it is special
        if (type.equals(ConfigSection.class)) {
            List<V> result = new ConfigSectionList<>(this, array);
            for (JsonElement element : array) {
                if (element == null)
                    continue;
                result.add((V) new ConfigSection(this, element.getAsJsonObject()));
            }
            return result;
        } else {
            List<V> result = new ConfigList<>(array);
            for (JsonElement element : array) {
                if (element == null)
                    continue;
                result.add(GsonFactory.gson().fromJson(element, type));
            }
            return result;
        }
    }

    /**
     * Adds an empty list into the tag
     *
     * @param tag  the tag to set the value
     * @param type the types in the list
     * @param <V>  the list type
     * @return the list added to the section
     */
    public <V> List<V> addList(String tag, Class<V> type) {
        synchronized (handleLock) {
            this.jsonHandle.add(tag, new JsonArray());
        }

        return this.getList(tag, type);
    }

    /**
     * Gets a BigInteger from the section, defaulting the the fallback if not found
     *
     * @param tag the tag to find the value from
     * @param def the default value
     * @return the BigInteger value at the tag
     */
    public BigInteger getBigInteger(String tag, BigInteger def) {
        synchronized (handleLock) {
            return this.contains(tag) ? this.jsonHandle.get(tag).getAsBigInteger() : def;
        }
    }

    /**
     * Gets the BigInteger at the tag
     *
     * <p>Defaults to {@code null} if not found</p>
     *
     * @param tag the tag to find the value from
     * @return the BigInteger at the tag
     */
    public BigInteger getBigInteger(String tag) {
        synchronized (handleLock) {
            return this.getBigInteger(tag, null);
        }
    }

    /**
     * Sets the {@link java.math.BigInteger} the specified tag
     *
     * @param s  the tag to set the integer to
     * @param bi the BigInteger ot set the tag to
     */
    public void setBigInteger(String s, BigInteger bi) {
        synchronized (handleLock) {
            this.setString(s, bi.toString());
        }
    }

    /**
     * Gets a BigDecimal at the specified tag, defaulting to the fallback if not found
     *
     * @param tag the tag to find the value from
     * @param def the default value
     * @return the value at the tag
     */
    public BigDecimal getBigDecimal(String tag, BigDecimal def) {
        synchronized (handleLock) {
            return this.contains(tag) ? this.jsonHandle.get(tag).getAsBigDecimal() : def;
        }
    }

    /**
     * Gets a BigDecimal at the specified tag
     *
     * <p>Defaults to {@code null} if the tag is not found</p>
     *
     * @param tag the tag to find the value from
     * @return the value of the tag
     */
    public BigDecimal getBigDecimal(String tag) {
        synchronized (handleLock) {
            return this.getBigDecimal(tag, null);
        }
    }

    /**
     * Sets the {@link java.math.BigDecimal} the specified tag
     *
     * @param tag the tag to set the value
     * @param bd  the BigDecimal to set the tag to
     */
    public void setBigDecimal(String tag, BigDecimal bd) {
        synchronized (handleLock) {
            this.setString(tag, bd.toPlainString());
        }
    }

    /**
     * Gets the Object at the specified tag
     *
     * @param tag   the tag to find the value from
     * @param clazz the type of the object
     * @param <V>   the object type
     * @return the value
     */
    public <V> V getObject(String tag, Class<V> clazz) {
        synchronized (handleLock) {
            return this.contains(tag) ? GsonFactory.gson().fromJson(this.jsonHandle.get(tag), clazz) : null;
        }
    }

    /**
     * Sets the object at the specified tag
     *
     * @param tag    the tag to set the value
     * @param object the value to set the tag to
     */
    public void setObject(String tag, Object object) {
        synchronized (handleLock) {
            this.jsonHandle.add(tag, GsonFactory.gson().toJsonTree(object));
        }
    }

    /**
     * Removes the tag from the configuration
     *
     * @param tag the tag to remove
     */
    public void remove(String tag) {
        synchronized (handleLock) {
            this.jsonHandle.remove(tag);
        }
    }

    /**
     * Checks to see if the section contains the tag
     *
     * @param tag the tag to see if contained
     * @return {@code true} if the tag is in the section, {@code false} if not
     */
    public boolean contains(String tag) {
        synchronized (handleLock) {
            return this.jsonHandle.has(tag);
        }
    }

    /**
     * As JSON form
     *
     * @return the JSON version of the section
     */
    public JsonObject asJsonObject() {
        synchronized (handleLock) {
            return this.jsonHandle;
        }
    }

    /**
     * The JSON root from the parent
     *
     * @return the parent root section
     */
    public Config rootSection() {
        synchronized (parentLock) {
            return this.parent.rootSection();
        }
    }

    /**
     * The section parent
     *
     * @return the parent of the section
     */
    public ConfigSection parentSection() {
        synchronized (parentLock) {
            return this.parent;
        }
    }

    /**
     * Gets a sub section which has the current section as a parent
     *
     * <p>A new section is created if it does not exist</p>
     *
     * @param tag the tag to get the section from
     * @return the section with the given tag under this section
     */
    public ConfigSection getConfigSection(String tag) {
        if (this.contains(tag)) {
            return new ConfigSection(this, this.jsonHandle.get(tag).getAsJsonObject());
        } else {
            this.jsonHandle.add(tag, new JsonObject());
            return new ConfigSection(this, this.jsonHandle.get(tag).getAsJsonObject());
        }
    }

    /**
     * Returns all of the topmost keys. Will not have inner section keys.
     *
     * @return the config keys
     */
    public Set<String> keys() {
        Set<Map.Entry<String, JsonElement>> entries = entries();

        Set<String> keys = Sets.newHashSet();
        keys.addAll(entries.stream().map(Map.Entry::getKey).collect(Collectors.toList()));

        return keys;
    }

    /**
     * Returns the topmost values
     *
     * @return the values
     */
    public Collection<JsonElement> values() {
        Set<Map.Entry<String, JsonElement>> entries = entries();

        List<JsonElement> values = Lists.newArrayList();
        values.addAll(entries.stream().map(Map.Entry::getValue).collect(Collectors.toList()));

        return values;
    }

    /**
     * Obtains the set of the topmost key-value entries
     *
     * @return the key-value entry set
     */
    public Set<Map.Entry<String, JsonElement>> entries() {
        synchronized (handleLock) {
            return jsonHandle.entrySet();
        }
    }

    /**
     * Saves the parent data
     */
    public void save() {
        synchronized (parentLock) {
            this.parent.save();
        }
    }
}