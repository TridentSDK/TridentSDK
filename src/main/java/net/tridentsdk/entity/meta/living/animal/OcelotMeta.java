package net.tridentsdk.entity.meta.living.animal;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface OcelotMeta extends TameableAnimalMeta {

    OcelotType getOcelotType();

    void setOcelotType(OcelotType type);

    enum OcelotType {
        // TODO
    }

}
