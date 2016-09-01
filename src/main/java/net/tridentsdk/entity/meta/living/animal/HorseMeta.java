package net.tridentsdk.entity.meta.living.animal;

import java.util.UUID;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface HorseMeta extends AnimalMeta {

    boolean isTame();

    void setTame(boolean tame);

    boolean isSaddled();

    void setSaddled(boolean saddled);

    boolean hasChest();

    void setHasChest(boolean hasChest);

    boolean isBred();

    void setBred(boolean isBred);

    boolean isHorseEating();

    void setHorseEating(boolean eating);

    boolean isRearing();

    void setRearing(boolean rearing);

    boolean isMouthOpen();

    void setMouthOpen(boolean mouthOpen);

    HorseType getHorseType();

    void setHorseType(HorseType type);

    HorseVariant getHorseVariant();

    void setHorseVariant(HorseVariant variant);

    UUID getOwner();

    void setOwner(UUID uuid);

    HorseArmor getHorseArmor();

    void setHorseArmor(HorseArmor armor);

    enum HorseType {
        // TODO
    }

    enum HorseVariant {
        // TODO
    }

    enum HorseArmor {
        // TODO
    }

}
