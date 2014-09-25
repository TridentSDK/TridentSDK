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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonConfig {

    private static final Gson GSON = new Gson();

    private JsonObject obj;
    private final File file;

    public JsonConfig(File file) {
        this.file = file;
        reload();
    }

    public int getInt(String tag, int def) {
        return (contains(tag)) ? obj.get(tag).getAsInt() : def;
    }

    public int getInt(String tag) {
        return getInt(tag, 0);
    }

    public void setInt(String tag, int in) {
        obj.addProperty(tag, in);
    }

    public double getDouble(String tag, double def) {
        return (contains(tag)) ? obj.get(tag).getAsDouble() : def;
    }

    public double getDouble(String tag) {
        return getDouble(tag, 0D);
    }

    public void setDouble(String tag, double d) {
        obj.addProperty(tag, d);
    }

    public float getFloat(String tag, float def) {
        return (contains(tag)) ? obj.get(tag).getAsFloat() : def;
    }

    public float getFloat(String tag) {
        return getFloat(tag, 0F);
    }

    public void setFloat(String tag, float f) {
        obj.addProperty(tag, f);
    }

    public char getChar(String tag, char def) {
        return (contains(tag)) ? obj.get(tag).getAsCharacter() : def;
    }

    public char getChar(String tag) {
        return getChar(tag, '\u0000');
    }

    public void setChar(String tag, char c) {
        obj.addProperty(tag, c);
    }

    public boolean getBoolean(String tag, boolean def) {
        return (contains(tag)) ? obj.get(tag).getAsBoolean() : def;
    }

    public boolean getBoolean(String tag) {
        return getBoolean(tag, false);
    }

    public void setBoolean(String tag, boolean b) {
        obj.addProperty(tag, b);
    }

    public byte getByte(String tag, byte def) {
        return (contains(tag)) ? obj.get(tag).getAsByte() : def;
    }

    public byte getByte(String tag) {
        return getByte(tag, (byte) 0);
    }

    public void setByte(String tag, byte b) {
        obj.addProperty(tag, b);
    }

    public String getString(String tag, String def) {
        return (contains(tag)) ? obj.get(tag).getAsString() : def;
    }

    public String getString(String tag) {
        return getString(tag, null);
    }

    public void setString(String tag, String s) {
        obj.addProperty(tag, s);
    }

    public JsonObject getJsonObject(String tag, JsonObject def) {
        return (contains(tag)) ? obj.get(tag).getAsJsonObject() : def;
    }

    public JsonObject getJsonObject(String tag) {
        return getJsonObject(tag, null);
    }

    public void setJsonObject(String tag, JsonObject o) {
        obj.add(tag, o);
    }

    public JsonArray getJsonArray(String tag, JsonArray def) {
        return (contains(tag)) ? obj.get(tag).getAsJsonArray() : def;
    }

    public JsonArray getJsonArray(String tag) {
        return getJsonArray(tag, null);
    }

    public void setJsonArray(String tag, JsonArray array) {
        obj.add(tag, array);
    }

    public BigInteger getBigInteger(String tag, BigInteger def) {
        return (contains(tag)) ? obj.get(tag).getAsBigInteger() : def;
    }

    public BigInteger getBigInteger(String tag) {
        return getBigInteger(tag, null);
    }

    public void setBigInteger(String s, BigInteger bi) {
        setString(s, bi.toString());
    }

    public BigDecimal getBigDecimal(String tag, BigDecimal def) {
        return (contains(tag)) ? obj.get(tag).getAsBigDecimal() : def;
    }

    public BigDecimal getBigDecimal(String tag) {
        return getBigDecimal(tag, null);
    }

    public void setBigDecimal(String tag, BigDecimal bd) {
        setString(tag, bd.toPlainString());
    }

    public JsonElement remove(String tag) {
        return obj.remove(tag);
    }

    public String toJson() {
        return GSON.toJson(obj);
    }

    public void save() {
        try {
            file.delete();
            file.createNewFile();

            FileWriter writer = new FileWriter(file);

            writer.write(toJson());

            writer.flush();
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void reload() {
        try {
            this.obj = file.exists() ? GSON.fromJson(new FileReader(file), JsonObject.class) :
                    GSON.fromJson("{}", JsonObject.class);
        }catch(FileNotFoundException ignored) {
        }
    }

    public boolean contains(String tag) {
        return obj.has(tag);
    }
}
