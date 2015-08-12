package net.tridentsdk.reflect.java;

import java.lang.reflect.Method;

import net.tridentsdk.reflect.FastMethod;

public class JavaFastMethod extends FastMethod {
    private final Method method;

    public JavaFastMethod(Class<?> owner, String name, Class<?>[] args) {
        super(owner, name, args);
        try {
            this.method = owner.getMethod(name, args);
        } catch (Exception ex) {
            throw new IllegalArgumentException(String.format("No method found in %s with name %s and args %s", owner.getName(), name, args));
        }
    }

    @Override
    public Object invoke(Object instance, Object... args) {
        try {
            method.setAccessible(true);
            return method.getReturnType() == void.class ? null : method.invoke(instance, args);
        } catch (Exception ex) {
            return null;
        }
    }
}
