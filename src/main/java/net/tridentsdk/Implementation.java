package net.tridentsdk;

import net.tridentsdk.concurrent.Scheduler;
import net.tridentsdk.concurrent.SelectableThreadPool;
import net.tridentsdk.docs.InternalUseOnly;
import net.tridentsdk.plugin.channel.ChannelHandler;
import net.tridentsdk.window.WindowHandler;
import net.tridentsdk.world.WorldLoader;
import net.tridentsdk.world.gen.AbstractGenerator;

@InternalUseOnly
public interface Implementation {
    SelectableThreadPool newPool(int i, String s);
    WorldLoader newLoader(Class<? extends AbstractGenerator> g);
    Scheduler scheduler();
    ChannelHandler chanHandler();
    WindowHandler winHandler();
}
