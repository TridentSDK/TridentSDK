package net.tridentsdk.api.nbt;

import net.tridentsdk.api.TridentFactory;
import net.tridentsdk.api.nbt.builder.CompoundTagBuilder;
import net.tridentsdk.api.nbt.builder.NBTBuilder;
import net.tridentsdk.api.reflect.FastClass;
import net.tridentsdk.api.reflect.FastField;
import net.tridentsdk.api.util.StringUtil;

import java.lang.reflect.Field;

public final class NBTSerializer {

    private NBTSerializer() {
    }

    public static <T> T deserialize(Class<T> clzz, CompoundTag tag) {
        if((NBTSerializable.class.isAssignableFrom(clzz))) {
            throw new IllegalArgumentException("Provided object is not serializable!");
        }

        FastClass cls = FastClass.get(clzz);
        T instance = cls.getConstructor().newInstance();

        for(FastField field : cls.getFields(instance)) {
            Field f = field.toField();

            if(!(f.isAnnotationPresent(NBTField.class))) {
                continue;
            }

            String tagName = f.getAnnotation(NBTField.class).name();
            TagType type = f.getAnnotation(NBTField.class).type();

            if(!(tag.containsTag(tagName)))
                new IllegalArgumentException(StringUtil.concat(tagName, " was unable to be found in provided compound tag!"))
                        .printStackTrace();

            NBTTag value = tag.getTag(tagName);

            if(value.getType() != type) {
                new IllegalArgumentException(StringUtil.concat(tagName, "'s tag type ", type,
                        " is not applicable to ", value.getType(), "! Ignoring...")).printStackTrace();
                continue;
            }

            switch(value.getType()) {
                case BYTE:
                    field.set(((ByteTag) value).getValue());
                    break;

                case BYTE_ARRAY:
                    field.set(((ByteArrayTag) value).getValue());
                    break;

                case COMPOUND:
                    field.set((CompoundTag) value);
                    break;

                case DOUBLE:
                    field.set(((DoubleTag) value).getValue());
                    break;

                case FLOAT:
                    field.set(((FloatTag) value).getValue());
                    break;

                case INT:
                    field.set(((IntTag) value).getValue());
                    break;

                case INT_ARRAY:
                    field.set(((IntArrayTag) value).getValue());
                    break;

                case LONG:
                    field.set(((LongTag) value).getValue());
                    break;

                case SHORT:
                    field.set(((ShortTag) value).getValue());
                    break;

                case LIST:
                    field.set((ListTag) value);
                    break;

                case STRING:
                    field.set(((StringTag) value).getValue());
                    break;

                case NULL:
                    field.set(null);
                    break;

                default:
                    break;
            }
        }

        return instance;
    }

    public static CompoundTag serialize(NBTSerializable serializable) {
        FastClass cls = FastClass.get(serializable.getClass());
        CompoundTagBuilder<NBTBuilder> builder =
                TridentFactory.createNbtBuilder(cls.toClass().getSimpleName());

        for(FastField field : cls.getFields(serializable)) {
            Field f = field.toField();

            if(!(f.isAnnotationPresent(NBTField.class))) {
                continue;
            }

            String tagName = f.getAnnotation(NBTField.class).name();
            TagType tagType = f.getAnnotation(NBTField.class).type();
            Object value = field.get();

            switch(tagType) {
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
}
