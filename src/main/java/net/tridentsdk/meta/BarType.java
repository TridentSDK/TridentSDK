package net.tridentsdk.meta;

/**
 * Enum of all bar types.
 *
 * @author The TridentSDK Team
 */
public enum BarType {
    /**
     * The action bar.
     */
    ACTION_BAR(0),

    /**
     * The boss bar.
     */
    BOSS_BAR(1);

    private int id;

    BarType(int id) {
        this.id = id;
    }

    /**
     * Get the id value of the enum
     *
     * @return the id value
     */
    public int id() {
        return id;
    }
}
