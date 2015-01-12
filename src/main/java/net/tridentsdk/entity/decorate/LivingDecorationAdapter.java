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
import net.tridentsdk.entity.Projectile;
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
    public double health() {
        return entity.health();
    }

    @Override
    public void setHealth(double health) {
        entity.setHealth(health);
    }

    @Override
    public double maxHealth() {
        return entity.maxHealth();
    }

    @Override
    public void setMaxHealth(double maxHealth) {
        entity.setMaxHealth(maxHealth);
    }

    @Override
    public long remainingAir() {
        return entity.remainingAir();
    }

    @Override
    public void setRemainingAir(long ticks) {
        entity.setRemainingAir(ticks);
    }

    @Override
    public Coordinates headLocation() {
        return entity.headLocation();
    }

    @Override
    public boolean canCollectItems() {
        return entity.canCollectItems();
    }

    @Override
    public EntityDamageEvent lastDamageEvent() {
        return entity.lastDamageEvent();
    }

    @Override
    public Player lastPlayerDamager() {
        return entity.lastPlayerDamager();
    }

    @Override
    public boolean isDead() {
        return entity.isDead();
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
