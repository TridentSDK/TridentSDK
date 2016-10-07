package net.tridentsdk.reflect.asm;

import com.esotericsoftware.reflectasm.MethodAccess;

import net.tridentsdk.reflect.FastMethod;

public class AsmFastMethod extends FastMethod {
    private final MethodAccess access;

    public AsmFastMethod(Class<?> owner, String name, Class<?>[] args, MethodAccess access) {
        super(owner, name, args);
        this.access = access;
    }

    @Override
    public Object invoke(Object instance, Object... args) {
        return this.access.invoke(instance, name(), args);
    }
}
