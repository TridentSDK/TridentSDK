package net.tridentsdk.entity.meta.living.monster;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
// TODO - documentation
public interface WitherMeta extends MonsterMeta {

    int getFirstHeadTarget();

    void setFirstHeadTarget(int target);

    int getSecondHeadTarget();

    void setSecondHeadTarget(int target);

    int getThirdHeadTarget();

    void setThirdHeadTarget(int target);

    int getInvulnerableTime();

    void setInvulnerableTime(int invulnerableTime);

}
