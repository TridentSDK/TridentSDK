package net.tridentsdk.api.event.entity;

import net.tridentsdk.api.entity.Entity;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when an Entity thinks it's just about time to explode
 */
public class EntityBeginExplosionCountdownEvent extends EntityEvent implements Cancellable {

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    private int length;

    public EntityBeginExplosionCountdownEvent(Entity entity, int length) {
        super(entity);
        this.length = length;
    }


}
