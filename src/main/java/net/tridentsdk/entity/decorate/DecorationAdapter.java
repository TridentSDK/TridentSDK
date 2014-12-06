package net.tridentsdk.entity.decorate;

import net.tridentsdk.Location;
import net.tridentsdk.entity.Entity;
import net.tridentsdk.entity.EntityProperties;
import net.tridentsdk.entity.EntityType;
import net.tridentsdk.util.Vector;
import net.tridentsdk.world.World;

import java.util.List;
import java.util.UUID;

public class DecorationAdapter<T extends Entity> implements Entity {
    private final T entity;

    protected DecorationAdapter(T entity) {
        this.entity = entity;
    }

    protected T original() {
        return this.entity;
    }

    @Override
    public void teleport(double x, double y, double z) {
        entity.teleport(x, y, z);
    }

    @Override
    public void teleport(Entity entity) {
        entity.teleport(entity);
    }

    @Override
    public void teleport(Location location) {
        entity.teleport(location);
    }

    @Override
    public World getWorld() {
        return entity.getWorld();
    }

    @Override
    public Location getLocation() {
        return entity.getLocation();
    }

    @Override
    public Vector getVelocity() {
        return entity.getVelocity();
    }

    @Override
    public void setVelocity(Vector vector) {
        entity.setVelocity(vector);
    }

    @Override
    public void tick() {
        entity.tick();
    }

    @Override
    public boolean isOnGround() {
        return entity.isOnGround();
    }

    @Override
    public List<Entity> getNearbyEntities(double radius) {
        return entity.getNearbyEntities(radius);
    }

    @Override
    public String getDisplayName() {
        return entity.getDisplayName();
    }

    @Override
    public void setDisplayName(String name) {
        entity.setDisplayName(name);
    }

    @Override
    public boolean isNameVisible() {
        return entity.isNameVisible();
    }

    @Override
    public boolean isSilent() {
        return entity.isSilent();
    }

    @Override
    public int getId() {
        return entity.getId();
    }

    @Override
    public UUID getUniqueId() {
        return entity.getUniqueId();
    }

    @Override
    public void remove() {
        entity.remove();
    }

    @Override
    public Entity getPassenger() {
        return entity.getPassenger();
    }

    @Override
    public void setPassenger(Entity entity) {
        entity.setPassenger(entity);
    }

    @Override
    public void eject() {
        entity.eject();
    }

    @Override
    public EntityType getType() {
        return entity.getType();
    }

    @Override
    public void applyProperties(EntityProperties properties) {
        entity.applyProperties(properties);
    }
}
