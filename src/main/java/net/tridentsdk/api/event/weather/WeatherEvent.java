package net.tridentsdk.api.event.weather;

import net.tridentsdk.api.event.Cancellable;
import net.tridentsdk.api.event.Event;
import net.tridentsdk.api.world.World;

public class WeatherEvent extends Event implements Cancellable {
    public final World world;
    private boolean cancelled;

    public WeatherEvent(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
