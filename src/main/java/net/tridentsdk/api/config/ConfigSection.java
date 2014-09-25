/*
 * Copyright (c) 2014, The TridentSDK Team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 *     3. Neither the name of TridentSDK nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.tridentsdk.api.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Represents a section of the Config file
 * 
 * @author The TridentSDK Team
 */
public class ConfigSection {
    ConfigSection parent;
    JsonObject jsonHandle;
    
    protected ConfigSection() { };
    
    protected ConfigSection(ConfigSection parent, JsonObject obj) {
        this.parent = parent;
        this.jsonHandle = obj;
    }

    public int getInt(String tag, int def) {
        return (contains(tag)) ? jsonHandle.get(tag).getAsInt() : def;
    }

    public int getInt(String tag) {
        return getInt(tag, 0);
    }

    public void setInt(String tag, int in) {
        jsonHandle.addProperty(tag, in);
    }

    public double getDouble(String tag, double def) {
        return (contains(tag)) ? jsonHandle.get(tag).getAsDouble() : def;
    }

    public double getDouble(String tag) {
        return getDouble(tag, 0D);
    }

    public void setDouble(String tag, double d) {
        jsonHandle.addProperty(tag, d);
    }

    public float getFloat(String tag, float def) {
        return (contains(tag)) ? jsonHandle.get(tag).getAsFloat() : def;
    }

    public float getFloat(String tag) {
        return getFloat(tag, 0F);
    }

    public void setFloat(String tag, float f) {
        jsonHandle.addProperty(tag, f);
    }

    public char getChar(String tag, char def) {
        return (contains(tag)) ? jsonHandle.get(tag).getAsCharacter() : def;
    }

    public char getChar(String tag) {
        return getChar(tag, '\u0000');
    }

    public void setChar(String tag, char c) {
        jsonHandle.addProperty(tag, c);
    }

    public boolean getBoolean(String tag, boolean def) {
        return (contains(tag)) ? jsonHandle.get(tag).getAsBoolean() : def;
    }

    public boolean getBoolean(String tag) {
        return getBoolean(tag, false);
    }

    public void setBoolean(String tag, boolean b) {
        jsonHandle.addProperty(tag, b);
    }

    public byte getByte(String tag, byte def) {
        return (contains(tag)) ? jsonHandle.get(tag).getAsByte() : def;
    }

    public byte getByte(String tag) {
        return getByte(tag, (byte) 0);
    }

    public void setByte(String tag, byte b) {
        jsonHandle.addProperty(tag, b);
    }

    public String getString(String tag, String def) {
        return (contains(tag)) ? jsonHandle.get(tag).getAsString() : def;
    }

    public String getString(String tag) {
        return getString(tag, null);
    }

    public void setString(String tag, String s) {
        jsonHandle.addProperty(tag, s);
    }
    
    public <V> List<V> getList(String tag, Class<V> type) {
        JsonArray array = jsonHandle.get(tag).getAsJsonArray();
        
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
    
    public <V> List<V> addList(String tag, Class<V> type) {
        jsonHandle.add(tag, new JsonArray());
        return getList(tag, type);
    }

    public BigInteger getBigInteger(String tag, BigInteger def) {
        return (contains(tag)) ? jsonHandle.get(tag).getAsBigInteger() : def;
    }

    public BigInteger getBigInteger(String tag) {
        return getBigInteger(tag, null);
    }

    public void setBigInteger(String s, BigInteger bi) {
        setString(s, bi.toString());
    }

    public BigDecimal getBigDecimal(String tag, BigDecimal def) {
        return (contains(tag)) ? jsonHandle.get(tag).getAsBigDecimal() : def;
    }

    public BigDecimal getBigDecimal(String tag) {
        return getBigDecimal(tag, null);
    }

    public void setBigDecimal(String tag, BigDecimal bd) {
        setString(tag, bd.toPlainString());
    }
    
    public <V> V getObject(String tag, Class<V> clazz) {
        return contains(tag) ? GsonFactory.getGson().fromJson(jsonHandle.get(tag), clazz) : null;
    }
    
    public void setObject(String tag, Object object) {
        jsonHandle.add(tag, GsonFactory.getGson().toJsonTree(object));
    }

    public void remove(String tag) {
        jsonHandle.remove(tag);
    }

    public boolean contains(String tag) {
        return jsonHandle.has(tag);
    }
    
    public JsonObject asJsonObject() {
        return jsonHandle;
    }
    
    public JsonConfig getRootSection() {
        return parent.getRootSection();
    }
    
    public ConfigSection getParentSection() {
        return parent;
    }
    
    public ConfigSection getConfigSection(String tag) {
        if (contains(tag)) {
            return new ConfigSection(this, jsonHandle.get(tag).getAsJsonObject());
        } else {
            jsonHandle.add(tag, new JsonObject());
            return new ConfigSection(this, jsonHandle.get(tag).getAsJsonObject());
        }
    }
    
    public void save() {
        parent.save();
    }
    
    //TODO: Better way?
    public static <V> ConfigSection addToList(List<V> list) {
        if (!(list instanceof ConfigSectionList)) {
            throw new UnsupportedOperationException("Can only add new ConfigSection-s to ConfigSectionList");
        }
        ConfigSection section = new ConfigSection(((ConfigSectionList) list).getParent(), new JsonObject());
        list.add((V) section);
        
        return section;
    }
}
