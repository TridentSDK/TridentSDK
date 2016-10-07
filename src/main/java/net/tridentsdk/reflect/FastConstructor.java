package net.tridentsdk.reflect;

public abstract class FastConstructor {
    public abstract <T> T newInstance(Object... params);
}
