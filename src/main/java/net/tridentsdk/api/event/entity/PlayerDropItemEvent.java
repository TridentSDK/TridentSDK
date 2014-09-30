package net.tridentsdk.api.event.entity;

import net.tridentsdk.api.Location;
import net.tridentsdk.api.entity.Entity;
import net.tridentsdk.api.entity.Item;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.Cancellable;
import org.apache.commons.lang.Validate;

public class PlayerDropItemEvent extends EntitySpawnEvent implements Cancellable {
    private final Player player;

    public PlayerDropItemEvent(Entity item, Location location, Player player) {
        super(item, location);
        Validate.isTrue(item instanceof Item, "Must drop an item!");
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Item getItem() {
        return (Item) super.getEntity();
    }

    public void setItem(Item item) {
        super.setEntity(item);
    }


}
