package net.tridentsdk.entity.meta.living.monster;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface GhastMeta extends FlyingEntityMeta {

    boolean isAttacking();

    void setAttacking(boolean attacking);

}
