package net.tridentsdk.api.event.entity;

import net.tridentsdk.api.entity.Entity;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when an explosion is about to happen, caused by an entity (the only other explosion in the game is from a
 * bed, of all things)
 */
public class EntityExplodeEvent extends EntityEvent implements Cancellable {
    private float strength;

    public float getStrength() {
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    public EntityExplodeEvent(Entity entity, float strength) {

        super(entity);
        this.strength = strength;
    }
}
