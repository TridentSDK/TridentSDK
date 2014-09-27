package net.tridentsdk.api.event.weather;

import net.tridentsdk.api.event.Cancellable;
import net.tridentsdk.api.world.World;

/**
 * Called when the weather on the world changes to sun
 */
public class SunEvent extends WeatherEvent implements Cancellable {
    public SunEvent(World world) {
        super(world);
    }
}
