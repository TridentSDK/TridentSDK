package net.tridentsdk.entity.meta.living.monster;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
// TODO - documentation
public interface EndermanMeta extends MonsterMeta {

    int getCarriedBlockID();

    void setCarriedBlockID(int blockID);

    int getCarriedBlockData();

    void setCarriedBlockData(int blockData);

    boolean isScreaming();

    void setScreaming(boolean screaming);

}
