package net.tridentsdk.api.scheduling;

import net.tridentsdk.plugin.TridentPlugin;

public interface Scheduler {

    /**
     * Schedules a runnable to be run next tick
     * @param plugin The plugin that is creating this runnable
     * @param runnable the runnable to run
     * @return an integer representing the id of this runnable
     */
    public TridentRunnable runTaskAsynchronously(TridentPlugin plugin, TridentRunnable runnable);

    public TridentRunnable runTaskSynchronously(TridentPlugin plugin, TridentRunnable runnable);

    public TridentRunnable runTaskAsyncLater(TridentPlugin plugin, TridentRunnable runnable, long delay);

    public TridentRunnable runTaskSyncLater(TridentPlugin plugin, TridentRunnable runnable, long delay);

    public TridentRunnable runTaskAsyncRepeatingLater(TridentPlugin plugin, TridentRunnable runnable,
                                                     long delay, long initialInterval);

    public TridentRunnable runTaskSyncRepeatingLater(TridentPlugin plugin, TridentRunnable runnable,
                                                     long delay, long intialInterval);
}
