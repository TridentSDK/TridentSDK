package net.tridentsdk.reflect.asm;

import com.esotericsoftware.reflectasm.FieldAccess;

import net.tridentsdk.reflect.FastField;

public class AsmFastField extends FastField {
    private final FieldAccess access;

    public AsmFastField(Class<?> owner, String field, FieldAccess access) {
        super(owner, field);
        this.access = access;
    }

    @Override
    public void set(Object instance, Object value) {
        this.access.set(instance, field(), value);
    }

    @Override
    public <T> T get(Object instance) {
        return (T) this.access.get(instance, field());
    }
}
