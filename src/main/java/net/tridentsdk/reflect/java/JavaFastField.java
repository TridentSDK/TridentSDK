package net.tridentsdk.reflect.java;

import java.lang.reflect.Field;

import net.tridentsdk.reflect.FastField;

public class JavaFastField extends FastField {
    private Field field;

    public JavaFastField(Class<?> owner, String name) {
        super(owner, name);
        try {
            this.field = owner.getField(name);
        } catch (Exception ex) {
            throw new IllegalArgumentException(String.format("No field found in %s with name %s", owner.getName(), name));
        }
    }

    @Override
    public void set(Object instance, Object value) {
        try {
            field.set(instance, value);
        } catch (Exception ex) {}
    }

    @Override
    public <T> T get(Object instance) {
        try {
            field.setAccessible(true);
            return (T) field.get(instance);
        } catch (Exception ex) {
            return null;
        }
    }
}
