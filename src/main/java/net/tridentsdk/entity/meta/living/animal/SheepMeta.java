package net.tridentsdk.entity.meta.living.animal;

import net.tridentsdk.base.DyeColor;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface SheepMeta extends AnimalMeta {

    DyeColor getSheepColor();

    void setSheepColor(DyeColor color);

    boolean isSheared();

    void setSheared(boolean sheared);

}
