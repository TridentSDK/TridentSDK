package net.tridentsdk.entity.meta.living.animal;

import net.tridentsdk.base.DyeColor;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface WolfMeta extends TameableAnimalMeta {

    float getDamageTaken();

    void setDamageTaken(float damageTaken);

    boolean isBegging();

    void setBegging(boolean begging);

    DyeColor getCollarColor();

    void setCollarColor(DyeColor color);

}
