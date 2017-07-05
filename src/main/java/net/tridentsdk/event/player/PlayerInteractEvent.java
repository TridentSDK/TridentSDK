package net.tridentsdk.event.player;

import lombok.Getter;
import lombok.Setter;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.event.Cancellable;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * This event is dispatched whenever a player right-clicks
 * the air.
 *
 * @author TridentSDK
 * @since 0.4-alpha
 */
@NotThreadSafe
public class PlayerInteractEvent extends PlayerEvent implements Cancellable {
    @Getter
    @Setter
    private boolean cancelled;

    public PlayerInteractEvent(Player player) {
        super(player);
    }
}