package net.tridentsdk.api.threads;

import net.tridentsdk.api.entity.Entity;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.world.World;
import net.tridentsdk.plugin.TridentPlugin;

public interface ThreadProvider {
    TaskExecutor provideEntityThread(Entity entity);

    TaskExecutor providePlayerThread(Player player);

    TaskExecutor providePluginThread(TridentPlugin plugin);

    TaskExecutor provideWorldThread(World world);
}
