package net.tridentsdk.entity.meta.living.animal;

import net.tridentsdk.base.OcelotType;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
// TODO - documentation
public interface OcelotMeta extends TameableAnimalMeta {

    OcelotType getOcelotType();

    void setOcelotType(OcelotType type);

}
