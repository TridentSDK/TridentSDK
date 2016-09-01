package net.tridentsdk.entity.meta.living.animal;

import java.util.UUID;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface TameableAnimalMeta extends AnimalMeta {

    boolean isSitting();

    void setSitting(boolean sitting);

    boolean isAngry();

    void setAngry(boolean angry);

    boolean isTamed();

    void setTamed(boolean tamed);

    UUID getOwner();

    void setOwner(UUID owner);

}
