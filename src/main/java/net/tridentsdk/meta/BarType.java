package net.tridentsdk.meta;

/**
 * Enum of all bar types.
 *
 * @author The TridentSDK Team
 */
public enum BarType {
    ACTION_BAR(0),
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
