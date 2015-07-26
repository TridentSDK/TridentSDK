package net.tridentsdk.util;

/**
 * Holds a value to cheat inner class finalization requirements
 *
 * @author The TridentSDK Team
 */
public final class Value<T> {
    private T value;

    private Value(T t) {
        this.value = t;
    }

    /**
     * Creates a new value which has the initial state set to the provided object
     *
     * @param t   the value to hold
     * @param <T> the value type
     * @return the new value holder
     */
    public static <T> Value<T> of(T t) {
        return new Value<>(t);
    }

    /**
     * Sets the value
     *
     * @param t the value
     */
    public void set(T t) {
        this.value = t;
    }

    /**
     * Gets the value
     *
     * @return the value
     */
    public T get() {
        return this.value;
    }
}
