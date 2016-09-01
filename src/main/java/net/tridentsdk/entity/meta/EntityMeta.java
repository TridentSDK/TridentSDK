package net.tridentsdk.entity.meta;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
// TODO - documentation
public interface EntityMeta {

    boolean isOnFire();

    void setOnFire(boolean onFire);

    boolean isCrouched();

    void setCrouched(boolean crouched);

    boolean isSprinting();

    void setSprinting(boolean sprinting);

    boolean isEating();

    void setEating(boolean eating);

    boolean isInvisible();

    void setInvisible(boolean invisible);

    boolean isGlowing();

    void setGlowing(boolean glowing);

    boolean isUsingElytra();

    void setUsingElytra(boolean usingElytra);

    int getAir();

    void setAir(int air);

    String getCustomName();

    void setCustomName(String name);

    boolean isCustomNameVisible();

    void setCustomNameVisible(boolean visible);

    boolean isSilent();

    void setSilent(boolean silent);

    boolean isNoGravity();

    void setNoGravity(boolean noGravity);

}
