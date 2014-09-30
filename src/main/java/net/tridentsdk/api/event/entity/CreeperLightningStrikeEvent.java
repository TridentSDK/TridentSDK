package net.tridentsdk.api.event.entity;

import net.tridentsdk.api.entity.Entity;
import net.tridentsdk.api.event.Cancellable;
import net.tridentsdk.api.event.weather.LightningStrikeEvent;

public class CreeperLightningStrikeEvent extends EntityEvent implements Cancellable {
    private final LightningStrikeEvent cause;

    public CreeperLightningStrikeEvent(Entity entity, LightningStrikeEvent cause) {
        super(entity);
        this.cause = cause;
    }
}
