package net.tridentsdk.entity.meta.living.monster;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface GuardianMeta extends MonsterMeta {

    boolean isRetractingSpikes();

    void setRetractingSpikes(boolean retractingSpikes);

    boolean isElderly();

    void setElderly(boolean elderly);

    int getTargetEntityID();

    void setTargetEntityID();

}
