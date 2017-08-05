package net.tridentsdk.meta.entity.living.monster;

import lombok.Getter;

import javax.annotation.Nonnull;

public enum EvokerSpellType {

    /**
     * No spell
     */
    NONE(0),

    /**
     * Summon Vex spell
     */
    SUMMON_VEX(1),

    /**
     * Attack spell
     */
    ATTACK(2),

    /**
     * WOLOLO spell (yes, this is actually what its called internally)
     */
    WOLOLO(3);

    @Getter
    private final int data;

    EvokerSpellType(int data) {
        this.data = data;
    }

    /**
     * Gets the Evoker spell type corresponding to the given internal identification number.
     * <br>
     * If none are found, an {@link IllegalArgumentException} will be thrown.
     *
     * @param id The identification number.
     *
     * @return The skeleton type.
     */
    @Nonnull
    public static EvokerSpellType of(int id) {
        for (EvokerSpellType type : values()) {
            if (type.data == id) {
                return type;
            }
        }

        throw new IllegalArgumentException("no Evoker spell type with id = " + id);
    }

}
