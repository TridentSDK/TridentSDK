package net.tridentsdk.meta.entity.living.animal;

import lombok.Getter;

import javax.annotation.Nonnull;

public enum ParrotType {

    /**
     * A red and blue parrot.
     */
    RED_BLUE(0),

    /**
     * A blue parrot.
     */
    BLUE(1),

    /**
     * A green parrot.
     */
    GREEN(2),

    /**
     * A yellow and blue parrot.
     */
    YELLOW_BLUE(3),

    /**
     * A silver parrot.
     */
    SILVER(4);

    @Getter
    private final int data;

    ParrotType(int data) {
        this.data = data;
    }

    /**
     * Gets the parrot type corresponding to the given internal identification number.
     * <br>
     * If none are found, an {@link IllegalArgumentException} will be thrown.
     *
     * @param id The identification number.
     *
     * @return The rabbit type.
     */
    @Nonnull
    public static ParrotType of(int id) {
        for (ParrotType type : values()) {
            if (type.data == id) {
                return type;
            }
        }

        throw new IllegalArgumentException("no parrot type with id = " + id);
    }

}
