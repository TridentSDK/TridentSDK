package net.tridentsdk.reflect.java;

import java.lang.reflect.Constructor;

import net.tridentsdk.reflect.FastConstructor;

public class JavaFastConstructor extends FastConstructor {
    private final Constructor<?> constr;

    public JavaFastConstructor(Class<?> owner, Class<?>... args) {
        try {
            this.constr = owner.getDeclaredConstructor(args);
        } catch (Exception ex) {
            throw new IllegalArgumentException(String.format("No constructor found in %s with args %s", owner.getName(), args));
        }
    }

    @Override
    public <T> T newInstance(Object... params) {
        try {
            constr.setAccessible(true);
            return (T) constr.newInstance(params);
        } catch (Exception ex) {
            return null;
        }
    }
}
