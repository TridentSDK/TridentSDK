/*
 * Copyright (c) 2014, The TridentSDK Team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *     3. Neither the name of the The TridentSDK Team nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL The TridentSDK Team BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.tridentsdk.api.config;

import com.google.gson.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

// TODO: Javadoc
/**
 * Represents a section of the Config file
 *
 * @author The TridentSDK Team
 */
public class ConfigSection {
    ConfigSection parent;
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
     * @param obj the section handler, used to store values
     */
    protected ConfigSection(ConfigSection parent, JsonObject obj) {
        this.parent = parent;
        this.jsonHandle = obj;
    }

    //TODO: Better way?

    /**
     * Gets a config section with the elements defined in the specified collection
     *
     * @param list the collection of objects to serialize to a config section
     * @param <V> the type in the collection
     * @return the config section with all the values in the list
     */
    public static <V> ConfigSection addToList(Collection<V> list) {
        if (!(list instanceof ConfigSectionList)) {
            throw new UnsupportedOperationException("Can only add new ConfigSection-s to ConfigSectionList");
        }
        ConfigSection section = new ConfigSection(((ConfigSectionList) list).getParent(), new JsonObject());
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
        return this.contains(tag) ? this.jsonHandle.get(tag).getAsInt() : def;
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
        return this.getInt(tag, 0);
    }

    /**
     * Sets the value at the tag to a specified integer
     *
     * @param tag the tag to set the value
     * @param in the integer value to set the tag
     */
    public void setInt(String tag, int in) {
        this.jsonHandle.addProperty(tag, in);
    }

    /**
     * Gets a double from the config with the given tag, or defaults to the fallback if the tag is not found
     *
     * @param tag the tag to find the value from
     * @param def the default value if no tag is found
     * @return the double at the tag
     */
    public double getDouble(String tag, double def) {
        return this.contains(tag) ? this.jsonHandle.get(tag).getAsDouble() : def;
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
        return this.getDouble(tag, 0.0D);
    }

    /**
     * Sets a double at the tag to a specified double
     *
     * @param tag the tag to set the value
     * @param d the double to set to the tag
     */
    public void setDouble(String tag, double d) {
        this.jsonHandle.addProperty(tag, d);
    }

    /**
     * Gets a float from the config with the given tag, or defaults to the fallback if the tag is not found
     *
     * @param tag the tag to find the value from
     * @param def the default value if no tag is found
     * @return the float at the tag
     */
    public float getFloat(String tag, float def) {
        return this.contains(tag) ? this.jsonHandle.get(tag).getAsFloat() : def;
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
        return this.getFloat(tag, 0.0F);
    }

    /**
     * Sets the float at the tag to a specified float
     *
     * @param tag the tag to set the value
     * @param f the float to set the tag to
     */
    public void setFloat(String tag, float f) {
        this.jsonHandle.addProperty(tag, f);
    }

    /**
     * Gets a character from the config with the given tag, or defaults to the fallback if the tag is not found
     *
     * @param tag the tag to find the value from
     * @param def the default value if no tag is found
     * @return the character at the tag
     */
    public char getChar(String tag, char def) {
        return this.contains(tag) ? this.jsonHandle.get(tag).getAsCharacter() : def;
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
        return this.getChar(tag, '\u0000');
    }

    /**
     * Sets a character at the tag to the specified character
     *
     * @param tag the tag to set the value
     * @param c the character to set the tag to
     */
    public void setChar(String tag, char c) {
        this.jsonHandle.addProperty(tag, c);
    }

    /**
     * Gets a boolean from the config with the given tag, defaulting to the fallback if the tag is not found
     *
     * @param tag the tag to find the value from
     * @param def the default value if the tag is not found
     * @return the boolean at the tag
     */
    public boolean getBoolean(String tag, boolean def) {
        return this.contains(tag) ? this.jsonHandle.get(tag).getAsBoolean() : def;
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
        return this.getBoolean(tag, false);
    }

    /**
     * Sets the boolean the specified tag
     *
     * @param tag the tag to set the value
     * @param b the boolean to set to at the tag
     */
    public void setBoolean(String tag, boolean b) {
        this.jsonHandle.addProperty(tag, b);
    }

    /**
     * Gets a byte from the config with the given tag, defaulting to the fallback if the tag is not found
     *
     * @param tag the tag to find the value from
     * @param def the default if the tag is not found
     * @return the byte at the tag
     */
    public byte getByte(String tag, byte def) {
        return this.contains(tag) ? this.jsonHandle.get(tag).getAsByte() : def;
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
        return this.getByte(tag, (byte) 0);
    }

    /**
     * Sets the byte the specified tag
     *
     * @param tag the tag to set the value
     * @param b the byte to set to at the tag
     */
    public void setByte(String tag, byte b) {
        this.jsonHandle.addProperty(tag, b);
    }

    /**
     * Gets the string at the specified tag, defaulting to the specified default if not found
     *
     * @param tag the tag to set the value
     * @param def the default value to
     */
    public String getString(String tag, String def) {
        return this.contains(tag) ? this.jsonHandle.get(tag).getAsString() : def;
    }

    public String getString(String tag) {
        return this.getString(tag, null);
    }

    /**
     * Sets the string the specified tag
     *
     * @param tag the tag to set the value
     * @param s the string to set at the tag
     */
    public void setString(String tag, String s) {
        this.jsonHandle.addProperty(tag, s);
    }

    /**
     * Gets the list at the tag
     *
     * @param tag the tag to find the value from
     * @param type the types contained in the list
     * @param <V> the list type
     * @return the list from the section
     */
    public <V> List<V> getList(String tag, Class<V> type) {
        JsonArray array = this.jsonHandle.get(tag).getAsJsonArray();

        //Handle ConfigSection seperately as it is special
        if (type.equals(ConfigSection.class)) {
            List<V> result = new ConfigSectionList<>(this, array);
            for (JsonElement element : array) {
                result.add((V) new ConfigSection(this, element.getAsJsonObject()));
            }
            return result;
        } else {
            List<V> result = new ConfigList<>(array);
            for (JsonElement element : array) {
                result.add(GsonFactory.getGson().fromJson(element, type));
            }
            return result;
        }
    }

    /**
     * Adds an empty list into the tag
     *
     * @param tag the tag to set the value
     * @param type the types in the list
     * @param <V> the list type
     * @return the list added to the section
     */
    public <V> List<V> addList(String tag, Class<V> type) {
        this.jsonHandle.add(tag, new JsonArray());
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
        return this.contains(tag) ? this.jsonHandle.get(tag).getAsBigInteger() : def;
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
        return this.getBigInteger(tag, null);
    }

    /**
     * Sets the {@link java.math.BigInteger} the specified tag
     *
     * @param s the tag to set the integer to
     * @param bi the BigInteger ot set the tag to
     */
    public void setBigInteger(String s, BigInteger bi) {
        this.setString(s, bi.toString());
    }

    /**
     * Gets a BigDecimal at the specified tag, defaulting to the fallback if not found
     *
     * @param tag the tag to find the value from
     * @param def the default value
     * @return the value at the tag
     */
    public BigDecimal getBigDecimal(String tag, BigDecimal def) {
        return this.contains(tag) ? this.jsonHandle.get(tag).getAsBigDecimal() : def;
    }

    /**
     * Gets a BigDecimal at the specified tag
     *
     * <p>Defaults to {@code null} if the tag is not found</p>
     *
     * @param tag
     * @return
     */
    public BigDecimal getBigDecimal(String tag) {
        return this.getBigDecimal(tag, null);
    }

    /**
     * Sets the {@link java.math.BigDecimal} the specified tag
     *
     * @param tag the tag to set the value
     * @param bd the BigDecimal to set the tag to
     */
    public void setBigDecimal(String tag, BigDecimal bd) {
        this.setString(tag, bd.toPlainString());
    }

    /**
     * Gets the Object at the specified tag
     *
     * @param tag the tag to find the value from
     * @param clazz the type of the object
     * @param <V> the object type
     * @return the value
     */
    public <V> V getObject(String tag, Class<V> clazz) {
        return this.contains(tag) ? GsonFactory.getGson().fromJson(this.jsonHandle.get(tag), clazz) : null;
    }

    /**
     * Sets the object at the specified tag
     *
     * @param tag the tag to set the value
     * @param object the value to set the tag to
     */
    public void setObject(String tag, Object object) {
        this.jsonHandle.add(tag, GsonFactory.getGson().toJsonTree(object));
    }

    /**
     * Removes the tag from the configuration
     *
     * @param tag the tag to remove
     */
    public void remove(String tag) {
        this.jsonHandle.remove(tag);
    }

    /**
     * Checks to see if the section contains the tag
     *
     * @param tag the tag to see if contained
     * @return {@code true} if the tag is in the section, {@code false} if not
     */
    public boolean contains(String tag) {
        return this.jsonHandle.has(tag);
    }

    /**
     * As JSON form
     *
     * @return the JSON version of the section
     */
    public JsonObject asJsonObject() {
        return this.jsonHandle;
    }

    /**
     * The JSON root from the parent
     *
     * @return the parent root section
     */
    public JsonConfig getRootSection() {
        return this.parent.getRootSection();
    }

    /**
     * The section parent
     *
     * @return the parent of the section
     */
    public ConfigSection getParentSection() {
        return this.parent;
    }

    /**
     * Gets a sub section which has the current section as a parent
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
     * Saves the parent data
     */
    public void save() {
        this.parent.save();
    }
}