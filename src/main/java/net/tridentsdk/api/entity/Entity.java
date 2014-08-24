package net.tridentsdk.api.entity;

import net.tridentsdk.Location;
import net.tridentsdk.api.util.Vector;

import java.util.List;
import java.util.UUID;

public interface Entity {

    public void teleport(double x, double y, double z);

    public void teleport(Entity entity);

    public void teleport(Location location);

    public String getWorld(); // TODO: Change to world object

    public Location getLocation();

    public void setVelocity(Vector vector);

    public Vector getVelocity();

    public void tick();

    public void hide(Entity entity);

    public void show(Entity entity);

    public boolean isOnGround();

    public List<Entity> getNearbyEntities();

    public UUID getUniqueId();

    public void remove();

    public boolean isDead();

    public abstract void setPassenger(Entity entity);

    public abstract Entity getPassenger();

    public void eject();

    public EntityType getType();
}
