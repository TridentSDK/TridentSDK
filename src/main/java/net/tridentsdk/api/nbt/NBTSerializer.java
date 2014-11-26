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
package net.tridentsdk.api.nbt;

import net.tridentsdk.api.factory.TridentFactory;
import net.tridentsdk.api.nbt.builder.CompoundTagBuilder;
import net.tridentsdk.api.nbt.builder.NBTBuilder;
import net.tridentsdk.api.reflect.FastClass;
import net.tridentsdk.api.reflect.FastField;

import java.lang.reflect.Field;

public final class NBTSerializer {

    private NBTSerializer() {
    }

    public static <T> T deserialize(Class<T> clzz, CompoundTag tag) {
        if (!(NBTSerializable.class.isAssignableFrom(clzz))) {
            throw new IllegalArgumentException("Provided object is not serializable!");
        }

        FastClass cls = FastClass.get(clzz);
        T instance = cls.getConstructor().newInstance();

        return deserialize(instance, tag);
    }

    public static <T> T deserialize(T instance, CompoundTag tag) {
        if (!(NBTSerializable.class.isAssignableFrom(instance.getClass()))) {
            throw new IllegalArgumentException("Provided object is not serializable!");
        }

        FastClass cls = FastClass.get(instance.getClass());

        for (FastField field : cls.getFields()) {
            Field f = field.toField();

            if (!f.isAnnotationPresent(NBTField.class)) {
                continue;
            }

            String tagName = f.getAnnotation(NBTField.class).name();
            TagType type = f.getAnnotation(NBTField.class).type();
            NBTTag value;

            if (!tag.containsTag(tagName)) {
                value = new NullTag(tagName);
            } else {
                value = tag.getTag(tagName);
            }

            if (value.getType() != type) {
                continue;
            }

            switch (value.getType()) {
                case BYTE:
                    field.set(instance, value.asType(ByteTag.class).getValue());
                    break;

                case BYTE_ARRAY:
                    field.set(instance, value.asType(ByteArrayTag.class).getValue());
                    break;

                case COMPOUND:
                    field.set(instance, value);
                    break;

                case DOUBLE:
                    field.set(instance, value.asType(DoubleTag.class).getValue());
                    break;

                case FLOAT:
                    field.set(instance, value.asType(FloatTag.class).getValue());
                    break;

                case INT:
                    field.set(instance, value.asType(IntTag.class).getValue());
                    break;

                case INT_ARRAY:
                    field.set(instance, value.asType(IntArrayTag.class).getValue());
                    break;

                case LONG:
                    field.set(instance, value.asType(LongTag.class).getValue());
                    break;

                case SHORT:
                    field.set(instance, value.asType(ShortTag.class).getValue());
                    break;

                case LIST:
                    field.set(instance, value.asType(ListTag.class));
                    break;

                case STRING:
                    field.set(instance, value.asType(StringTag.class).getValue());
                    break;

                case NULL:
                    field.set(instance, null);
                    break;

                default:
                    break;
            }
        }

        return instance;
    }

    public static CompoundTag serialize(NBTSerializable serializable, String name) {
        FastClass cls = FastClass.get(serializable.getClass());
        CompoundTagBuilder<NBTBuilder> builder =
                TridentFactory.createNbtBuilder(name);

        for (FastField field : cls.getFields()) {
            Field f = field.toField();

            if (!f.isAnnotationPresent(NBTField.class)) {
                continue;
            }

            String tagName = f.getAnnotation(NBTField.class).name();
            TagType tagType = f.getAnnotation(NBTField.class).type();
            Object value = field.get(serializable);

            switch (tagType) {
                case BYTE:
                    builder.byteTag(tagName, (byte) value);
                    break;

                case BYTE_ARRAY:
                    builder.byteArrayTag(tagName, (byte[]) value);
                    break;

                case COMPOUND:
                    builder.compoundTag((CompoundTag) value);
                    break;

                case DOUBLE:
                    builder.doubleTag(tagName, (double) value);
                    break;

                case FLOAT:
                    builder.floatTag(tagName, (float) value);
                    break;

                case INT:
                    builder.intTag(tagName, (int) value);
                    break;

                case INT_ARRAY:
                    builder.intArrayTag(tagName, (int[]) value);
                    break;

                case LONG:
                    builder.longTag(tagName, (long) value);
                    break;

                case SHORT:
                    builder.shortTag(tagName, (short) value);
                    break;

                case LIST:
                    builder.listTag((ListTag) value);
                    break;

                case STRING:
                    builder.stringTag(tagName, (String) value);
                    break;

                case NULL:
                    builder.nullTag(tagName);
                    break;

                default:
                    break;
            }
        }

        return builder.endCompoundTag().build();
    }

    public static CompoundTag serialize(NBTSerializable serializable) {
        return serialize(serializable, serializable.getClass().getSimpleName());
    }
}
