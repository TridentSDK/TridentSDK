package net.tridentsdk.entity.meta.living;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
// TODO - documentation
public interface InsentientEntityMeta extends LivingEntityMeta {

    boolean isNoAI();

    void setNoAI(boolean noAI);

    boolean isLeftHanded();

    void setLeftHanded(boolean leftHanded);

}
