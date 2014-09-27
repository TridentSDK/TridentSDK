package net.tridentsdk.api.event.weather;

import net.tridentsdk.api.event.Cancellable;
import net.tridentsdk.api.world.World;

/**
 * Called when the world's weather changes to rain
 */
public class RainEvent extends WeatherEvent implements Cancellable {
    public RainEvent(World world) {
        super(world);
    }
}
