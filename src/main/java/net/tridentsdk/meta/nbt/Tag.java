/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
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
package net.tridentsdk.meta.nbt;

import lombok.*;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * Represents a {@code TAG_Compound} in the NBT format.
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@ToString
@NotThreadSafe
@AllArgsConstructor
public class Tag {
    /**
     * An entry that terminates a compound tag early
     */
    private static final Map.Entry<String, Tag> NULL_ENTRY = new AbstractMap.SimpleEntry<>(
            "", new Tag(Type.END, null));

    /**
     * The tag type
     */
    @Getter
    private final Type type;
    /**
     * The object that is contained by this tag
     */
    @Getter
    @Setter
    private Object object;

    /**
     * Reads a tag compound from the given stream.
     *
     * @param stream the stream to read
     * @return the root compound tag
     */
    public static Tag.Compound decode(DataInputStream stream) {
        Map.Entry<String, Tag> root = readFully(stream);
        return (Tag.Compound) root.getValue().getObject();
    }

    /**
     * Reads the full object from the given data stream.
     *
     * @param stream the stream to read from
     * @return the tag that was read from stream
     */
    private static Map.Entry<String, Tag> readFully(DataInputStream stream) {
        try {
            int i = stream.readByte();
            Type type = Type.getMapping().get(i);
            if (type == Type.END) {
                return NULL_ENTRY;
            }

            int len = stream.readUnsignedShort();
            if (len > 0) {
                byte[] arr = new byte[len];
                stream.readFully(arr);
                String name = new String(arr, Type.UTF_8);
                Object o = type.read(name, stream);
                return new AbstractMap.SimpleEntry<>(name, new Tag(type, o));
            } else {
                Object o = type.read("", stream);
                return new AbstractMap.SimpleEntry<>("", new Tag(type, o));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * An NBT type, which supports reading and writing of
     * those types.
     *
     * @author TridentSDK
     * @since 0.5-alpha
     */
    @Immutable
    public enum Type {
        END {
            @Override
            public Object read(String name, DataInputStream stream) {
                return null;
            }

            @Override
            public void write(Object o, DataOutputStream stream) {
            }

            @Override
            public List<?> newListOfType() {
                return new List<Void>(this);
            }
        },
        BYTE {
            @Override
            public Object read(String name, DataInputStream stream) throws IOException {
                return stream.readByte();
            }

            @Override
            public void write(Object o, DataOutputStream stream) throws IOException {
                stream.writeByte((byte) o);
            }

            @Override
            public List<?> newListOfType() {
                return new List<Byte>(this);
            }
        },
        SHORT {
            @Override
            public Object read(String name, DataInputStream stream) throws IOException {
                return stream.readShort();
            }

            @Override
            public void write(Object o, DataOutputStream stream) throws IOException {
                stream.writeShort((short) o);
            }

            @Override
            public List<?> newListOfType() {
                return new List<Short>(this);
            }
        },
        INT {
            @Override
            public Object read(String name, DataInputStream stream) throws IOException {
                return stream.readInt();
            }

            @Override
            public void write(Object o, DataOutputStream stream) throws IOException {
                stream.writeInt((int) o);
            }

            @Override
            public List<?> newListOfType() {
                return new List<Integer>(this);
            }
        },
        LONG {
            @Override
            public Object read(String name, DataInputStream stream) throws IOException {
                return stream.readLong();
            }

            @Override
            public void write(Object o, DataOutputStream stream) throws IOException {
                stream.writeLong((long) o);
            }

            @Override
            public List<?> newListOfType() {
                return new List<Long>(this);
            }
        },
        FLOAT {
            @Override
            public Object read(String name, DataInputStream stream) throws IOException {
                return stream.readFloat();
            }

            @Override
            public void write(Object o, DataOutputStream stream) throws IOException {
                stream.writeFloat((float) o);
            }

            @Override
            public List<?> newListOfType() {
                return new List<Float>(this);
            }
        },
        DOUBLE {
            @Override
            public Object read(String name, DataInputStream stream) throws IOException {
                return stream.readDouble();
            }

            @Override
            public void write(Object o, DataOutputStream stream) throws IOException {
                stream.writeDouble((double) o);
            }

            @Override
            public List<?> newListOfType() {
                return new List<Double>(this);
            }
        },
        BYTE_ARRAY {
            @Override
            public Object read(String name, DataInputStream stream) throws IOException {
                int len = stream.readInt();
                byte[] arr = new byte[len];
                stream.readFully(arr);
                return arr;
            }

            @Override
            public void write(Object o, DataOutputStream stream) throws IOException {
                byte[] arr = (byte[]) o;
                stream.writeInt(arr.length);
                for (byte b : arr) {
                    stream.writeByte(b);
                }
            }

            @Override
            public List<?> newListOfType() {
                return new List<byte[]>(this);
            }
        },
        STRING {
            @Override
            public Object read(String name, DataInputStream stream) throws IOException {
                int len = stream.readUnsignedShort();
                byte[] arr = new byte[len];
                stream.readFully(arr);
                return new String(arr, UTF_8);
            }

            @Override
            public void write(Object o, DataOutputStream stream) throws IOException {
                byte[] arr = ((String) o).getBytes(UTF_8);
                stream.writeShort(arr.length);
                for (byte b : arr) {
                    stream.writeByte(b);
                }
            }

            @Override
            public List<?> newListOfType() {
                return new List<String>(this);
            }
        },
        LIST {
            @Override
            public Object read(String name, DataInputStream stream) throws IOException {
                int type = stream.readByte();
                Type t = mapping.get(type);
                if (t != null) {
                    int len = stream.readInt();
                    List list = t.newListOfType();
                    for (int i = 0; i < len; i++) {
                        list.add(t.read("", stream));
                    }

                    return list;
                } else {
                    return Collections.emptyList();
                }
            }

            @Override
            public void write(Object o, DataOutputStream stream) throws IOException {
                List list = (List) o;
                Type type = list.getType();
                stream.writeByte(type.ordinal());
                stream.writeInt(list.size());
                for (Object obj : list) {
                    type.write(obj, stream);
                }
            }

            @Override
            public List<?> newListOfType() {
                return new List<List<?>>(this);
            }
        },
        COMPOUND {
            @Override
            public Object read(String name, DataInputStream stream) {
                Compound compound = new Compound(name);
                while (true) {
                    Map.Entry<String, Tag> tag = readFully(stream);
                    if (tag.getValue().getType() == Type.END) {
                        break;
                    } else {
                        compound.add(tag);
                    }
                }

                return compound;
            }

            @Override
            public void write(Object o, DataOutputStream stream) {
                Compound compound = (Compound) o;
                for (Map.Entry<String, Tag> entry : compound.getEntries().entrySet()) {
                    entry.getValue().getType().writeFully(entry.getKey(), entry.getValue().getObject(), stream);
                }
                END.writeFully("", null, stream);
            }

            @Override
            public List<?> newListOfType() {
                return new List<Compound>(this);
            }
        },
        INT_ARRAY {
            @Override
            public Object read(String name, DataInputStream stream) throws IOException {
                int len = stream.readInt();
                int[] arr = new int[len];
                for (int i = 0; i < len; i++) {
                    arr[i] = stream.readInt();
                }

                return arr;
            }

            @Override
            public void write(Object o, DataOutputStream stream) throws IOException {
                int[] arr = (int[]) o;
                stream.writeInt(arr.length);
                for (int i : arr) {
                    stream.writeInt(i);
                }
            }

            @Override
            public List<?> newListOfType() {
                return new List<int[]>(this);
            }
        };

        /**
         * The default UTF8 charset used to encode and
         * decode string tags
         */
        @Getter
        public static final Charset UTF_8 = Charset.forName("UTF-8");
        /**
         * The mapping of type indices with their respective
         * types
         */
        @Getter
        private static final Map<Integer, Type> mapping = new HashMap<Integer, Type>() {
            @Override
            public Type get(Object key) {
                Type type = super.get(key);
                if (type == null) {
                    return END;
                }

                return type;
            }
        };

        static {
            for (Type t : Type.values()) {
                mapping.put(t.ordinal(), t);
            }
        }

        /**
         * Writes the header plus the payload for the given
         * tag object and name.
         *
         * @param name the name of the tag
         * @param o the object to write
         * @param stream the stream to write to
         */
        public void writeFully(String name, Object o, DataOutputStream stream) {
            try {
                stream.writeByte(this.ordinal());
                if (this == END) {
                    return;
                }

                byte[] arr = name.getBytes(UTF_8);
                stream.writeShort(arr.length);
                for (byte b : arr) {
                    stream.writeByte(b);
                }
                this.write(o, stream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * Reads the given non-header portion from the given
         * stream.
         *
         * @param name the name of the tag
         * @param stream the stream to read
         * @return the parsed object
         * @throws IOException this shouldn't happen
         */
        public abstract Object read(String name, DataInputStream stream) throws IOException;

        /**
         * Writes the non-header portion for the given
         * stream.
         *
         * @param o the object to write
         * @param stream the stream to write to
         * @throws IOException this shouldn't happen
         */
        public abstract void write(Object o, DataOutputStream stream) throws IOException;

        /**
         * Create a new list with the elements of this
         * specified type.
         *
         * @return the new tag list
         */
        public abstract List<?> newListOfType();
    }

    /**
     * A compound tag represents an unordered list of tag
     * items that is typically the root of all the data
     * contained by a particular object.
     *
     * @author TridentSDK
     * @since 0.5-alpha
     */
    @Getter
    @ThreadSafe
    @AllArgsConstructor
    public static class Compound {
        /**
         * The list of tags contained by this compound
         */
        private final Map<String, Tag> entries = new ConcurrentHashMap<>();
        /**
         * The name of the compound tag
         */
        private final String name;

        /**
         * Obtains the value of the object at the given key.
         *
         * @param key the key to find the value
         * @param <T> the type of object to find
         * @return the tag value, or {@code null} if not
         * found
         */
        @Nullable
        public <T> T get(String key) {
            Tag tag = this.entries.get(key);
            if (tag == null) {
                return null;
            }

            return (T) tag.getObject();
        }

        /**
         * Obtains the key of the specified type at the
         * given key.
         *
         * <p>Use {@link #get(String)} to check for
         * availability to {@code null} instead of this
         * method.</p>
         *
         * @param key the key to obtain the tag at
         * @return the tag, or the tag's default value
         * if it cannot be {@code null}
         */
        public byte getByte(String key) {
            Tag tag = this.entries.get(key);
            if (tag == null) {
                throw new IllegalArgumentException("No key found for: " + key);
            }

            return (byte) tag.getObject();
        }

        /**
         * Obtains the key of the specified type at the
         * given key.
         *
         * <p>Use {@link #get(String)} to check for
         * availability to {@code null} instead of this
         * method.</p>
         *
         * @param key the key to obtain the tag at
         * @return the tag, or the tag's default value
         * if it cannot be {@code null}
         */
        public short getShort(String key) {
            Tag tag = this.entries.get(key);
            if (tag == null) {
                throw new IllegalArgumentException("No key found for: " + key);
            }

            return (short) tag.getObject();
        }

        /**
         * Obtains the key of the specified type at the
         * given key.
         *
         * <p>Use {@link #get(String)} to check for
         * availability to {@code null} instead of this
         * method.</p>
         *
         * @param key the key to obtain the tag at
         * @return the tag, or the tag's default value
         * if it cannot be {@code null}
         */
        public int getInt(String key) {
            Tag tag = this.entries.get(key);
            if (tag == null) {
                throw new IllegalArgumentException("No key found for: " + key);
            }

            return (int) tag.getObject();
        }

        /**
         * Obtains the key of the specified type at the
         * given key.
         *
         * <p>Use {@link #get(String)} to check for
         * availability to {@code null} instead of this
         * method.</p>
         *
         * @param key the key to obtain the tag at
         * @return the tag, or the tag's default value
         * if it cannot be {@code null}
         */
        public long getLong(String key) {
            Tag tag = this.entries.get(key);
            if (tag == null) {
                throw new IllegalArgumentException("No key found for: " + key);
            }

            return (long) tag.getObject();
        }

        /**
         * Obtains the key of the specified type at the
         * given key.
         *
         * <p>Use {@link #get(String)} to check for
         * availability to {@code null} instead of this
         * method.</p>
         *
         * @param key the key to obtain the tag at
         * @return the tag, or the tag's default value
         * if it cannot be {@code null}
         */
        public float getFloat(String key) {
            Tag tag = this.entries.get(key);
            if (tag == null) {
                throw new IllegalArgumentException("No key found for: " + key);
            }

            return (float) tag.getObject();
        }

        /**
         * Obtains the key of the specified type at the
         * given key.
         *
         * <p>Use {@link #get(String)} to check for
         * availability to {@code null} instead of this
         * method.</p>
         *
         * @param key the key to obtain the tag at
         * @return the tag, or the tag's default value
         * if it cannot be {@code null}
         */
        public byte[] getByteArray(String key) {
            Tag tag = this.entries.get(key);
            if (tag == null) {
                throw new IllegalArgumentException("No key found for: " + key);
            }

            return (byte[]) tag.getObject();
        }

        /**
         * Obtains the key of the specified type at the
         * given key.
         *
         * <p>Use {@link #get(String)} to check for
         * availability to {@code null} instead of this
         * method.</p>
         *
         * @param key the key to obtain the tag at
         * @return the tag, or the tag's default value
         * if it cannot be {@code null}
         */
        public String getString(String key) {
            Tag tag = this.entries.get(key);
            if (tag == null) {
                throw new IllegalArgumentException("No key found for: " + key);
            }

            return (String) tag.getObject();
        }

        /**
         * Obtains the key of the specified type at the
         * given key.
         *
         * <p>Use {@link #get(String)} to check for
         * availability to {@code null} instead of this
         * method.</p>
         *
         * @param key the key to obtain the tag at
         * @return the tag, or the tag's default value
         * if it cannot be {@code null}
         */
        public <T> List<T> getList(String key) {
            Tag tag = this.entries.get(key);
            if (tag == null) {
                throw new IllegalArgumentException("No key found for: " + key);
            }

            return (List<T>) tag.getObject();
        }

        /**
         * Obtains the key of the specified type at the
         * given key.
         *
         * <p>Use {@link #get(String)} to check for
         * availability to {@code null} instead of this
         * method.</p>
         *
         * @param key the key to obtain the tag at
         * @return the tag, or the tag's default value
         * if it cannot be {@code null}
         */
        public Compound getCompound(String key) {
            Tag tag = this.entries.get(key);
            if (tag == null) {
                throw new IllegalArgumentException("No key found for: " + key);
            }

            return (Compound) tag.getObject();
        }

        /**
         * Obtains the key of the specified type at the
         * given key.
         *
         * <p>Use {@link #get(String)} to check for
         * availability to {@code null} instead of this
         * method.</p>
         *
         * @param key the key to obtain the tag at
         * @return the tag, or the tag's default value
         * if it cannot be {@code null}
         */
        public int[] getIntArray(String key) {
            Tag tag = this.entries.get(key);
            if (tag == null) {
                throw new IllegalArgumentException("No key found for: " + key);
            }

            return (int[]) tag.getObject();
        }

        /**
         * Sets the value of the tag at the given key,
         * ignoring all previous values. Do not use if the
         * value passed in is not constant (i.e. relies on
         * an old value of the tag), use the compute
         * functions instead.
         *
         * @param key the key to set the tag value
         * @param i the new tag value
         */
        public void putByte(String key, byte i) {
            this.entries.compute(key, (k, v) -> {
                if (v == null) {
                    return new Tag(Type.BYTE, i);
                } else {
                    v.setObject(i);
                    return v;
                }
            });
        }

        /**
         * Sets the value of the tag at the given key,
         * ignoring all previous values. Do not use if the
         * value passed in is not constant (i.e. relies on
         * an old value of the tag), use the compute
         * functions instead.
         *
         * @param key the key to set the tag value
         * @param i the new tag value
         */
        public void putShort(String key, short i) {
            this.entries.compute(key, (k, v) -> {
                if (v == null) {
                    return new Tag(Type.SHORT, i);
                } else {
                    v.setObject(i);
                    return v;
                }
            });
        }

        /**
         * Sets the value of the tag at the given key,
         * ignoring all previous values. Do not use if the
         * value passed in is not constant (i.e. relies on
         * an old value of the tag), use the compute
         * functions instead.
         *
         * @param key the key to set the tag value
         * @param i the new tag value
         */
        public void putInt(String key, int i) {
            this.entries.compute(key, (k, v) -> {
                if (v == null) {
                    return new Tag(Type.INT, i);
                } else {
                    v.setObject(i);
                    return v;
                }
            });
        }

        /**
         * Sets the value of the tag at the given key,
         * ignoring all previous values. Do not use if the
         * value passed in is not constant (i.e. relies on
         * an old value of the tag), use the compute
         * functions instead.
         *
         * @param key the key to set the tag value
         * @param i the new tag value
         */
        public void putLong(String key, long i) {
            this.entries.compute(key, (k, v) -> {
                if (v == null) {
                    return new Tag(Type.LONG, i);
                } else {
                    v.setObject(i);
                    return v;
                }
            });
        }

        /**
         * Sets the value of the tag at the given key,
         * ignoring all previous values. Do not use if the
         * value passed in is not constant (i.e. relies on
         * an old value of the tag), use the compute
         * functions instead.
         *
         * @param key the key to set the tag value
         * @param i the new tag value
         */
        public void putFloat(String key, float i) {
            this.entries.compute(key, (k, v) -> {
                if (v == null) {
                    return new Tag(Type.FLOAT, i);
                } else {
                    v.setObject(i);
                    return v;
                }
            });
        }

        /**
         * Sets the value of the tag at the given key,
         * ignoring all previous values. Do not use if the
         * value passed in is not constant (i.e. relies on
         * an old value of the tag), use the compute
         * functions instead.
         *
         * @param key the key to set the tag value
         * @param i the new tag value
         */
        public void putDouble(String key, double i) {
            this.entries.compute(key, (k, v) -> {
                if (v == null) {
                    return new Tag(Type.DOUBLE, i);
                } else {
                    v.setObject(i);
                    return v;
                }
            });
        }

        /**
         * Sets the value of the tag at the given key,
         * ignoring all previous values. Do not use if the
         * value passed in is not constant (i.e. relies on
         * an old value of the tag), use the compute
         * functions instead.
         *
         * @param key the key to set the tag value
         * @param i the new tag value
         */
        public void putByteArray(String key, byte[] i) {
            this.entries.compute(key, (k, v) -> {
                if (v == null) {
                    return new Tag(Type.BYTE_ARRAY, i);
                } else {
                    v.setObject(i);
                    return v;
                }
            });
        }

        /**
         * Sets the value of the tag at the given key,
         * ignoring all previous values. Do not use if the
         * value passed in is not constant (i.e. relies on
         * an old value of the tag), use the compute
         * functions instead.
         *
         * @param key the key to set the tag value
         * @param i the new tag value
         */
        public void putString(String key, String i) {
            this.entries.compute(key, (k, v) -> {
                if (v == null) {
                    return new Tag(Type.STRING, i);
                } else {
                    v.setObject(i);
                    return v;
                }
            });
        }

        /**
         * Sets the value of the tag at the given key,
         * ignoring all previous values. Do not use if the
         * value passed in is not constant (i.e. relies on
         * an old value of the tag), use the compute
         * functions instead.
         *
         * @param key the key to set the tag value
         * @param i the new tag value
         */
        public void putList(String key, List i) {
            this.entries.compute(key, (k, v) -> {
                if (v == null) {
                    return new Tag(Type.LIST, i);
                } else {
                    v.setObject(i);
                    return v;
                }
            });
        }

        /**
         * Sets the value of the tag at the given key,
         * ignoring all previous values. Do not use if the
         * value passed in is not constant (i.e. relies on
         * an old value of the tag), use the compute
         * functions instead.
         *
         * @param i the new tag value
         */
        public void putCompound(Compound i) {
            this.entries.compute(i.getName(), (k, v) -> {
                if (v == null) {
                    return new Tag(Type.COMPOUND, i);
                } else {
                    v.setObject(i);
                    return v;
                }
            });
        }

        /**
         * Sets the value of the tag at the given key,
         * ignoring all previous values. Do not use if the
         * value passed in is not constant (i.e. relies on
         * an old value of the tag), use the compute
         * functions instead.
         *
         * @param key the key to set the tag value
         * @param i the new tag value
         */
        public void putIntArray(String key, int[] i) {
            this.entries.compute(key, (k, v) -> {
                if (v == null) {
                    return new Tag(Type.INT_ARRAY, i);
                } else {
                    v.setObject(i);
                    return v;
                }
            });
        }

        /**
         * Performs a computation using the previous value
         * of the key. This operation is atomic and
         * therefore safe to make assumptions about previous
         * state values in the given function.
         *
         * <p>It is preferable to use this method if the
         * value must be calculated, i.e. incrementing or
         * adding a value to the tag.</p>
         *
         * <p>The value passed into the function is
         * nullable, indicating that it might not exist.</p>
         *
         * @param key the to key to compute the value
         * @param func the computation to run, returning the
         * new tag value
         */
        public void computeByte(String key, BiFunction<String, Byte, Byte> func) {
            this.entries.compute(key, (k, v) -> {
                if (v == null) {
                    return new Tag(Type.BYTE, func.apply(k, null));
                } else {
                    v.setObject(func.apply(k, (Byte) v.getObject()));
                    return v;
                }
            });
        }

        /**
         * Performs a computation using the previous value
         * of the key. This operation is atomic and
         * therefore safe to make assumptions about previous
         * state values in the given function.
         *
         * <p>It is preferable to use this method if the
         * value must be calculated, i.e. incrementing or
         * adding a value to the tag.</p>
         *
         * <p>The value passed into the function is
         * nullable, indicating that it might not exist.</p>
         *
         * @param key the to key to compute the value
         * @param func the computation to run, returning the
         * new tag value
         */
        public void computeShort(String key, BiFunction<String, Short, Short> func) {
            this.entries.compute(key, (k, v) -> {
                if (v == null) {
                    return new Tag(Type.SHORT, func.apply(k, null));
                } else {
                    v.setObject(func.apply(k, (Short) v.getObject()));
                    return v;
                }
            });
        }

        /**
         * Performs a computation using the previous value
         * of the key. This operation is atomic and
         * therefore safe to make assumptions about previous
         * state values in the given function.
         *
         * <p>It is preferable to use this method if the
         * value must be calculated, i.e. incrementing or
         * adding a value to the tag.</p>
         *
         * <p>The value passed into the function is
         * nullable, indicating that it might not exist.</p>
         *
         * @param key the to key to compute the value
         * @param func the computation to run, returning the
         * new tag value
         */
        public void computerInt(String key, BiFunction<String, Integer, Integer> func) {
            this.entries.compute(key, (k, v) -> {
                if (v == null) {
                    return new Tag(Type.INT, func.apply(k, null));
                } else {
                    v.setObject(func.apply(k, (Integer) v.getObject()));
                    return v;
                }
            });
        }

        /**
         * Performs a computation using the previous value
         * of the key. This operation is atomic and
         * therefore safe to make assumptions about previous
         * state values in the given function.
         *
         * <p>It is preferable to use this method if the
         * value must be calculated, i.e. incrementing or
         * adding a value to the tag.</p>
         *
         * <p>The value passed into the function is
         * nullable, indicating that it might not exist.</p>
         *
         * @param key the to key to compute the value
         * @param func the computation to run, returning the
         * new tag value
         */
        public void computeLong(String key, BiFunction<String, Long, Long> func) {
            this.entries.compute(key, (k, v) -> {
                if (v == null) {
                    return new Tag(Type.LONG, func.apply(k, null));
                } else {
                    v.setObject(func.apply(k, (Long) v.getObject()));
                    return v;
                }
            });
        }

        /**
         * Performs a computation using the previous value
         * of the key. This operation is atomic and
         * therefore safe to make assumptions about previous
         * state values in the given function.
         *
         * <p>It is preferable to use this method if the
         * value must be calculated, i.e. incrementing or
         * adding a value to the tag.</p>
         *
         * <p>The value passed into the function is
         * nullable, indicating that it might not exist.</p>
         *
         * @param key the to key to compute the value
         * @param func the computation to run, returning the
         * new tag value
         */
        public void computeFloat(String key, BiFunction<String, Float, Float> func) {
            this.entries.compute(key, (k, v) -> {
                if (v == null) {
                    return new Tag(Type.FLOAT, func.apply(k, null));
                } else {
                    v.setObject(func.apply(k, (Float) v.getObject()));
                    return v;
                }
            });
        }

        /**
         * Performs a computation using the previous value
         * of the key. This operation is atomic and
         * therefore safe to make assumptions about previous
         * state values in the given function.
         *
         * <p>It is preferable to use this method if the
         * value must be calculated, i.e. incrementing or
         * adding a value to the tag.</p>
         *
         * <p>The value passed into the function is
         * nullable, indicating that it might not exist.</p>
         *
         * @param key the to key to compute the value
         * @param func the computation to run, returning the
         * new tag value
         */
        public void computeDouble(String key, BiFunction<String, Double, Double> func) {
            this.entries.compute(key, (k, v) -> {
                if (v == null) {
                    return new Tag(Type.DOUBLE, func.apply(k, null));
                } else {
                    v.setObject(func.apply(k, (Double) v.getObject()));
                    return v;
                }
            });
        }

        /**
         * Performs a computation using the previous value
         * of the key. This operation is atomic and
         * therefore safe to make assumptions about previous
         * state values in the given function.
         *
         * <p>It is preferable to use this method if the
         * value must be calculated, i.e. incrementing or
         * adding a value to the tag.</p>
         *
         * <p>The value passed into the function is
         * nullable, indicating that it might not exist.</p>
         *
         * @param key the to key to compute the value
         * @param func the computation to run, returning the
         * new tag value
         */
        public void computeByteArray(String key, BiFunction<String, byte[], byte[]> func) {
            this.entries.compute(key, (k, v) -> {
                if (v == null) {
                    return new Tag(Type.BYTE_ARRAY, func.apply(k, null));
                } else {
                    v.setObject(func.apply(k, (byte[]) v.getObject()));
                    return v;
                }
            });
        }

        /**
         * Performs a computation using the previous value
         * of the key. This operation is atomic and
         * therefore safe to make assumptions about previous
         * state values in the given function.
         *
         * <p>It is preferable to use this method if the
         * value must be calculated, i.e. incrementing or
         * adding a value to the tag.</p>
         *
         * <p>The value passed into the function is
         * nullable, indicating that it might not exist.</p>
         *
         * @param key the to key to compute the value
         * @param func the computation to run, returning the
         * new tag value
         */
        public void computeString(String key, BiFunction<String, String, String> func) {
            this.entries.compute(key, (k, v) -> {
                if (v == null) {
                    return new Tag(Type.STRING, func.apply(k, null));
                } else {
                    v.setObject(func.apply(k, (String) v.getObject()));
                    return v;
                }
            });
        }

        /**
         * Performs a computation using the previous value
         * of the key. This operation is atomic and
         * therefore safe to make assumptions about previous
         * state values in the given function.
         *
         * <p>It is preferable to use this method if the
         * value must be calculated, i.e. incrementing or
         * adding a value to the tag.</p>
         *
         * <p>The value passed into the function is
         * nullable, indicating that it might not exist.</p>
         *
         * @param key the to key to compute the value
         * @param func the computation to run, returning the
         * new tag value
         */
        public void computeList(String key, BiFunction<String, List, List> func) {
            this.entries.compute(key, (k, v) -> {
                if (v == null) {
                    return new Tag(Type.LIST, func.apply(k, null));
                } else {
                    v.setObject(func.apply(k, (List) v.getObject()));
                    return v;
                }
            });
        }

        /**
         * Performs a computation using the previous value
         * of the key. This operation is atomic and
         * therefore safe to make assumptions about previous
         * state values in the given function.
         *
         * <p>It is preferable to use this method if the
         * value must be calculated, i.e. incrementing or
         * adding a value to the tag.</p>
         *
         * <p>The value passed into the function is
         * nullable, indicating that it might not exist.</p>
         *
         * @param key the to key to compute the value
         * @param func the computation to run, returning the
         * new tag value
         */
        public void computeCompound(String key, BiFunction<String, Compound, Compound> func) {
            this.entries.compute(key, (k, v) -> {
                if (v == null) {
                    return new Tag(Type.INT, func.apply(k, null));
                } else {
                    v.setObject(func.apply(k, (Compound) v.getObject()));
                    return v;
                }
            });
        }

        /**
         * Performs a computation using the previous value
         * of the key. This operation is atomic and
         * therefore safe to make assumptions about previous
         * state values in the given function.
         *
         * <p>It is preferable to use this method if the
         * value must be calculated, i.e. incrementing or
         * adding a value to the tag.</p>
         *
         * <p>The value passed into the function is
         * nullable, indicating that it might not exist.</p>
         *
         * @param key the to key to compute the value
         * @param func the computation to run, returning the
         * new tag value
         */
        public void computeIntArray(String key, BiFunction<String, int[], int[]> func) {
            this.entries.compute(key, (k, v) -> {
                if (v == null) {
                    return new Tag(Type.INT_ARRAY, func.apply(k, null));
                } else {
                    v.setObject(func.apply(k, (int[]) v.getObject()));
                    return v;
                }
            });
        }

        /**
         * Removes the NBT tag at the given key.
         *
         * @param key the key to remove
         * @return {@code true} if the key exists and was
         * removed, {@code false} if this operation had no
         * effect
         */
        public boolean remove(String key) {
            return this.entries.remove(key) != null;
        }

        /**
         * Writes the data in this compound tag to the given
         * stream.
         *
         * @param stream the stream to write to
         */
        public void write(DataOutputStream stream) {
            Type.COMPOUND.writeFully(this.name, this, stream);
        }

        /**
         * Adds the given parsed entry to the compound tag.
         *
         * @param entry the entry to add
         */
        protected void add(Map.Entry<String, Tag> entry) {
            this.entries.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * A custom list supporting the storage of its element
     * type.
     *
     * @author TridentSDK
     * @since 0.5-alpha
     * @param <E> the actual object element type
     */
    @NotThreadSafe
    @RequiredArgsConstructor
    public static class List<E> extends ArrayList<E> {
        @Getter
        private final Type type;
    }
}