package net.tridentsdk.entity.meta.living;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface AgeableMeta extends CreatureMeta {

    boolean isBaby();

    void setBaby(boolean baby);

}
