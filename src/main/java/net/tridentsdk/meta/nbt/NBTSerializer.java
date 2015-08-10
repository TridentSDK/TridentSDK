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

package net.tridentsdk.meta.nbt;

import net.tridentsdk.reflect.FastClass;
import net.tridentsdk.reflect.FastField;
import net.tridentsdk.util.TridentLogger;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public final class NBTSerializer {
    public static <T> T deserialize(Class<T> clzz, CompoundTag tag) {
        if (!(NBTSerializable.class.isAssignableFrom(clzz))) {
            TridentLogger.get().error(new IllegalArgumentException("Provided object is not serializable!"));
        }

        FastClass cls = FastClass.get(clzz);
        T instance = cls.constructor().newInstance();

        return deserialize(instance, tag);
    }

    public static <T> T deserialize(T instance, CompoundTag tag) {
        if (!(NBTSerializable.class.isAssignableFrom(instance.getClass()))) {
            TridentLogger.get().error(new IllegalArgumentException("Provided object is not serializable!"));
        }

        FastClass cls = FastClass.get(instance.getClass());

        for (FastField field : cls.fields()) {
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

            if (value.type() != type) {
                continue;
            }

            field.set(instance, findJavaValue(value, field, f));
        }

        return instance;
    }

    private static Object findJavaValue(NBTTag value, FastField field, Field f) {
        switch (value.type()) {
            case BYTE:
                return value.asType(ByteTag.class).value();

            case BYTE_ARRAY:
                return value.asType(ByteArrayTag.class).value();

            case COMPOUND:
                if (NBTSerializable.class.isAssignableFrom(field.toField().getType())) {
                    return deserialize(field.toField().getType(), value.asType(CompoundTag.class));
                } else {
                    return value;
                }

            case DOUBLE:
                return value.asType(DoubleTag.class).value();

            case FLOAT:
                return value.asType(FloatTag.class).value();

            case INT:
                return value.asType(IntTag.class).value();

            case INT_ARRAY:
                return value.asType(IntArrayTag.class).value();

            case LONG:
                return value.asType(LongTag.class).value();

            case SHORT:
                return value.asType(ShortTag.class).value();

            case LIST:
                ListTag list = value.asType(ListTag.class);
                Class<?> listType = (Class<?>) ((ParameterizedType) f.getGenericType())
                        .getActualTypeArguments()[0];
                boolean isPrimitive = listType.isPrimitive() || listType.equals(String.class);

                if (!(NBTSerializable.class.isAssignableFrom(listType)) && !isPrimitive) {
                    break;
                }

                List<Object> l = new ArrayList<>();

                for (NBTTag t : list.listTags()) {
                    if (isPrimitive) {
                        l.add(findJavaValue(t, null, null));
                        continue;
                    }

                    CompoundTag compound = t.asType(CompoundTag.class);

                    l.add(deserialize(listType, compound));
                }

                return f.getType().cast(l);

            case STRING:
                return value.asType(StringTag.class).value();

            case NULL:
            default:
                return null;
        }

        return null;
    }

    public static CompoundTag serialize(NBTSerializable serializable, String name) {
        FastClass cls = FastClass.get(serializable.getClass());
        CompoundTagBuilder<NBTBuilder> builder = NBTBuilder.newBase(name);

        for (FastField field : cls.fields()) {
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
