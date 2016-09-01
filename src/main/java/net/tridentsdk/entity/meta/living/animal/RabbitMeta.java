package net.tridentsdk.entity.meta.living.animal;

import net.tridentsdk.base.RabbitType;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface RabbitMeta extends AnimalMeta {

    RabbitType getRabbitType();

    void setRabbitType(RabbitType type);

}
