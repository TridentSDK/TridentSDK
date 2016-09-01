package net.tridentsdk.entity.meta.living.monster;

import net.tridentsdk.base.SkeletonType;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
// TODO - documentation
public interface SkeletonMeta extends MonsterMeta {

    SkeletonType getSkeletonType();

    void setSkeletonType(SkeletonType type);

    boolean isSwingingArms();

    void setSwingingArms(boolean swingingArms);

}
