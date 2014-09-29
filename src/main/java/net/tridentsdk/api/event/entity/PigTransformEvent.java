package net.tridentsdk.api.event.entity;

import net.tridentsdk.api.entity.Entity;
import net.tridentsdk.api.event.Cancellable;
import net.tridentsdk.api.event.weather.LightningStrikeEvent;

/**
 * Called when a lightning bolt strikes and causes a pig to turn into a zombie pigman
 */
public class PigTransformEvent extends EntityEvent implements Cancellable {
    private final LightningStrikeEvent cause;

    public PigTransformEvent(Entity entity, LightningStrikeEvent cause) {
        super(entity);
        this.cause = cause;

    }

    public LightningStrikeEvent getCause() {
        return cause;
    }
}
