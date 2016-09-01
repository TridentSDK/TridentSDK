package net.tridentsdk.entity.meta.living.animal;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface RabbitMeta extends AnimalMeta {

    RabbitType getRabbitType();

    void setRabbitType(RabbitType type);

    enum RabbitType {
        // TODO
    }

}
