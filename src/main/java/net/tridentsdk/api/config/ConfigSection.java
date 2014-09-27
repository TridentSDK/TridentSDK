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

/**
 * Represents a section of the Config file
 *
 * @author The TridentSDK Team
 */
public class ConfigSection {
    ConfigSection parent;
    JsonObject jsonHandle;

    protected ConfigSection() { }

    protected ConfigSection(ConfigSection parent, JsonObject obj) {
        this.parent = parent;
        this.jsonHandle = obj;
    }

    //TODO: Better way?
    public static <V> ConfigSection addToList(Collection<V> list) {
        if (!(list instanceof ConfigSectionList)) {
            throw new UnsupportedOperationException("Can only add new ConfigSection-s to ConfigSectionList");
        }
        ConfigSection section = new ConfigSection(((ConfigSectionList) list).getParent(), new JsonObject());
        list.add((V) section);

        return section;
    }

    public int getInt(String tag, int def) {
        return this.contains(tag) ? this.jsonHandle.get(tag).getAsInt() : def;
    }

    public int getInt(String tag) {
        return this.getInt(tag, 0);
    }

    public void setInt(String tag, int in) {
        this.jsonHandle.addProperty(tag, in);
    }

    public double getDouble(String tag, double def) {
        return this.contains(tag) ? this.jsonHandle.get(tag).getAsDouble() : def;
    }

    public double getDouble(String tag) {
        return this.getDouble(tag, 0.0D);
    }

    public void setDouble(String tag, double d) {
        this.jsonHandle.addProperty(tag, d);
    }

    public float getFloat(String tag, float def) {
        return this.contains(tag) ? this.jsonHandle.get(tag).getAsFloat() : def;
    }

    public float getFloat(String tag) {
        return this.getFloat(tag, 0.0F);
    }

    public void setFloat(String tag, float f) {
        this.jsonHandle.addProperty(tag, f);
    }

    public char getChar(String tag, char def) {
        return this.contains(tag) ? this.jsonHandle.get(tag).getAsCharacter() : def;
    }

    public char getChar(String tag) {
        return this.getChar(tag, '\u0000');
    }

    public void setChar(String tag, char c) {
        this.jsonHandle.addProperty(tag, c);
    }

    public boolean getBoolean(String tag, boolean def) {
        return this.contains(tag) ? this.jsonHandle.get(tag).getAsBoolean() : def;
    }

    public boolean getBoolean(String tag) {
        return this.getBoolean(tag, false);
    }

    public void setBoolean(String tag, boolean b) {
        this.jsonHandle.addProperty(tag, b);
    }

    public byte getByte(String tag, byte def) {
        return this.contains(tag) ? this.jsonHandle.get(tag).getAsByte() : def;
    }

    public byte getByte(String tag) {
        return this.getByte(tag, (byte) 0);
    }

    public void setByte(String tag, byte b) {
        this.jsonHandle.addProperty(tag, b);
    }

    public String getString(String tag, String def) {
        return this.contains(tag) ? this.jsonHandle.get(tag).getAsString() : def;
    }

    public String getString(String tag) {
        return this.getString(tag, null);
    }

    public void setString(String tag, String s) {
        this.jsonHandle.addProperty(tag, s);
    }

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

    public <V> List<V> addList(String tag, Class<V> type) {
        this.jsonHandle.add(tag, new JsonArray());
        return this.getList(tag, type);
    }

    public BigInteger getBigInteger(String tag, BigInteger def) {
        return this.contains(tag) ? this.jsonHandle.get(tag).getAsBigInteger() : def;
    }

    public BigInteger getBigInteger(String tag) {
        return this.getBigInteger(tag, null);
    }

    public void setBigInteger(String s, BigInteger bi) {
        this.setString(s, bi.toString());
    }

    public BigDecimal getBigDecimal(String tag, BigDecimal def) {
        return this.contains(tag) ? this.jsonHandle.get(tag).getAsBigDecimal() : def;
    }

    public BigDecimal getBigDecimal(String tag) {
        return this.getBigDecimal(tag, null);
    }

    public void setBigDecimal(String tag, BigDecimal bd) {
        this.setString(tag, bd.toPlainString());
    }

    public <V> V getObject(String tag, Class<V> clazz) {
        return this.contains(tag) ? GsonFactory.getGson().fromJson(this.jsonHandle.get(tag), clazz) : null;
    }

    public void setObject(String tag, Object object) {
        this.jsonHandle.add(tag, GsonFactory.getGson().toJsonTree(object));
    }

    public void remove(String tag) {
        this.jsonHandle.remove(tag);
    }

    public boolean contains(String tag) {
        return this.jsonHandle.has(tag);
    }

    public JsonObject asJsonObject() {
        return this.jsonHandle;
    }

    public JsonConfig getRootSection() {
        return this.parent.getRootSection();
    }

    public ConfigSection getParentSection() {
        return this.parent;
    }

    public ConfigSection getConfigSection(String tag) {
        if (this.contains(tag)) {
            return new ConfigSection(this, this.jsonHandle.get(tag).getAsJsonObject());
        } else {
            this.jsonHandle.add(tag, new JsonObject());
            return new ConfigSection(this, this.jsonHandle.get(tag).getAsJsonObject());
        }
    }

    public void save() {
        this.parent.save();
    }
}
