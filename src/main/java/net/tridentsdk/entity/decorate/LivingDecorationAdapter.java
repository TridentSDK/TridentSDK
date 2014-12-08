package net.tridentsdk.entity.decorate;

import net.tridentsdk.Location;
import net.tridentsdk.base.Block;
import net.tridentsdk.entity.Entity;
import net.tridentsdk.entity.EntityProperties;
import net.tridentsdk.entity.LivingEntity;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.entity.projectile.Projectile;
import net.tridentsdk.event.entity.EntityDamageEvent;

import java.util.Collection;

public class LivingDecorationAdapter extends DecorationAdapter<LivingEntity> implements LivingEntity {
    private final LivingEntity entity;

    protected LivingDecorationAdapter(LivingEntity entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public void hide(Entity entity) {
        this.entity.hide(entity);
    }

    @Override
    public void show(Entity entity) {
        this.entity.show(entity);
    }

    @Override
    public double getHealth() {
        return entity.getHealth();
    }

    @Override
    public void setHealth(double health) {
        entity.setHealth(health);
    }

    @Override
    public double getMaxHealth() {
        return entity.getMaxHealth();
    }

    @Override
    public void setMaxHealth(double maxHealth) {
        entity.setMaxHealth(maxHealth);
    }

    @Override
    public long getRemainingAir() {
        return entity.getRemainingAir();
    }

    @Override
    public void setRemainingAir(long ticks) {
        entity.setRemainingAir(ticks);
    }

    @Override
    public Location getEyeLocation() {
        return entity.getEyeLocation();
    }

    @Override
    public boolean canPickupItems() {
        return entity.canPickupItems();
    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        return entity.getLastDamageCause();
    }

    @Override
    public Player hurtByPlayer() {
        return entity.hurtByPlayer();
    }

    @Override
    public boolean isDead() {
        return entity.isDead();
    }

    @Override
    public boolean isImpaledEntity() {
        return entity.isImpaledEntity();
    }

    @Override
    public boolean isImpaledTile() {
        return entity.isImpaledTile();
    }

    @Override
    public Entity impaledEntity() {
        return entity.impaledEntity();
    }

    @Override
    public Block impaledTile() {
        return entity.impaledTile();
    }

    @Override
    public void put(Projectile projectile) {
        entity.put(projectile);
    }

    @Override
    public boolean remove(Projectile projectile) {
        return entity.remove(projectile);
    }

    @Override
    public void clear() {
        entity.clear();
    }

    @Override
    public Collection<Projectile> projectiles() {
        return entity.projectiles();
    }

    @Override
    public <T extends Projectile> T launchProjectile(EntityProperties properties) {
        return entity.launchProjectile(properties);
    }

    @Override
    protected LivingEntity original() {
        return entity;
    }
}
