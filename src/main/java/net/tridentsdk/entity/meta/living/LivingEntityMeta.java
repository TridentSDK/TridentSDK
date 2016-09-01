package net.tridentsdk.entity.meta.living;

import net.tridentsdk.entity.meta.EntityMeta;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface LivingEntityMeta extends EntityMeta {

    boolean isHandActive();

    void setHandActive(boolean active);

    boolean isMainHandActive();

    void setMainHandActive(boolean mainHand);

    float getHealth();

    void setHealth(float health);

    int getPotionEffectColor();

    void setPotionEffectColor(int potionEffectColor);

    boolean isPotionEffectAmbient();

    void setPotionEffectAmbient(boolean ambient);

    int getNumberOfArrowsInEntity();

    void setNumberOfArrowsInEntity(int arrows);

}
