package net.tridentsdk.entity.meta.living.monster;

import net.tridentsdk.base.ZombieType;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface ZombieMeta extends MonsterMeta {

    boolean isBaby();

    void setBaby(boolean baby);

    ZombieType getZombieType();

    void setZombieType(ZombieType type);

    boolean isConverting();

    void setConverting(boolean converting);

    boolean areHandsHeldUp();

    void setHandsHeldUp(boolean handsUp);

}
