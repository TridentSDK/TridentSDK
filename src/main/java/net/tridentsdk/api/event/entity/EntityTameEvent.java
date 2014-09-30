package net.tridentsdk.api.event.entity;

import net.tridentsdk.api.entity.Tameable;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when an entity is tamed, i.e. a horse or ocelot
 */
public class EntityTameEvent extends EntityEvent implements Cancellable {
    private final Player tamer;
    private boolean cancelled;

    public EntityTameEvent(Tameable entity, Player tamer) {
        super(entity);
        this.tamer = tamer;
    }

    public Player getTamer() {
        return tamer;
    }

    public boolean isCancelled() {

        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
