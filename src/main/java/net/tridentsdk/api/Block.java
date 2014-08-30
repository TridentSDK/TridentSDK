package net.tridentsdk.api;

import net.tridentsdk.api.util.Vector;

/**
 * TODO
 */
public class Block {

    private Material material;
    private Location location;

    public Block(Location location) {
        this.location = location;

        // Note: Avoid recursion by not creating a new instance from World#getBlockAt(Location)
        Block worldBlock = location.getWorld().getBlockAt(location);

        this.material = worldBlock.material;
    }

    public Material getType() {
        return material;
    }

    public Location getLocation() {
        return location;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Block getRelative(Vector vector) {
        return new Block(location.getRelative(vector));
    }
}
