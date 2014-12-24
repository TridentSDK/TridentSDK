package net.tridentsdk.base.impl;

import com.google.common.collect.Sets;
import net.tridentsdk.Coordinates;
import net.tridentsdk.base.Substance;
import net.tridentsdk.base.Tile;
import net.tridentsdk.entity.Entity;
import net.tridentsdk.entity.projectile.Projectile;
import net.tridentsdk.util.Vector;

import java.util.Collection;

public class TileImpl implements Tile {
    private Substance substance = Substance.ACACIA_STAIRS;
    private byte meta;

    @Override
    public void setSubstance(Substance material) {
        this.substance = material;
    }

    @Override
    public Substance getSubstance() {
        return substance;
    }

    @Override
    public Coordinates getLocation() {
        return Coordinates.create(new WorldImpl(), 0, 0, 0);
    }

    @Override
    public byte getMeta() {
        return meta;
    }

    @Override
    public void setMeta(byte data) {
        this.meta = data;
    }

    @Override
    public Tile relativeTile(Vector vector) {
        return new TileImpl();
    }

    @Override
    public boolean isImpaledEntity() {
        return false;
    }

    @Override
    public boolean isImpaledTile() {
        return false;
    }

    @Override
    public Entity impaledEntity() {
        return null;
    }

    @Override
    public Tile impaledTile() {
        return null;
    }

    @Override
    public void put(Projectile projectile) {
    }

    @Override
    public boolean remove(Projectile projectile) {
        return false;
    }

    @Override
    public void clear() {
    }

    @Override
    public Collection<Projectile> projectiles() {
        return Sets.newHashSet();
    }
}
