package net.tridentsdk.registry;

import net.tridentsdk.concurrent.Scheduler;
import net.tridentsdk.concurrent.SelectableThreadPool;
import net.tridentsdk.docs.InternalUseOnly;
import net.tridentsdk.plugin.channel.PluginChannels;
import net.tridentsdk.window.Inventories;
import net.tridentsdk.world.WorldLoader;
import net.tridentsdk.world.gen.AbstractGenerator;

@InternalUseOnly
public interface Implementation {
    SelectableThreadPool newPool(int i, String s);
    WorldLoader newLoader(Class<? extends AbstractGenerator> g);

    PluginChannels channels();
    Scheduler scheduler();

    Inventories inventories();
}
