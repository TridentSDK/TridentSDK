/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2014 The TridentSDK Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.tridentsdk.entity.decorate;

import net.tridentsdk.Coordinates;
import net.tridentsdk.entity.Entity;
import net.tridentsdk.entity.EntityProperties;
import net.tridentsdk.entity.LivingEntity;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.entity.projectile.Projectile;
import net.tridentsdk.event.entity.EntityDamageEvent;

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
    public Coordinates getEyeLocation() {
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
    public Impalable asImpalable() {
        return entity.asImpalable();
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
