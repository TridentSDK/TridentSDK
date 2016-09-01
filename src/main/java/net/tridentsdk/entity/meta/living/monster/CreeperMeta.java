package net.tridentsdk.entity.meta.living.monster;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
// TODO - documentation
public interface CreeperMeta extends MonsterMeta {

    int getCreeperState();

    void setCreeperState(int state);

    boolean isCharged();

    void setCharged(boolean charged);

    boolean isIgnited();

    void setIgnited(boolean ignited);

}
