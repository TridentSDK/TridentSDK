package net.tridentsdk.api.scheduling;

import net.tridentsdk.plugin.TridentPlugin;

public abstract class TridentRunnable implements Runnable {

    /**
     * Id of this runnable, unique to this runtime
     */
    private int id;

    /**
     * Guaranteed to be run before this Runnable on the main thread, even if this runnable
     * is going to be run asynchronously, useful for collecting resources to work on.
     */
    public void prerunSync() {};

    /**
     * Runs after this runnable has finished, asynchronously
     */
    public void runAfterAsync() {};

    /**
     * Runs after this runnable has finished, synchronously
     */
    public void runAfterSync() {};

    public TridentRunnable runTaskSynchronously(TridentPlugin plugin) {
        // TODO
        return null;
    }

    public TridentRunnable runTaskAsynchronously(TridentPlugin plugin) {
        // TODO
        return null;
    }

    public TridentRunnable runTaskSyncLater(TridentPlugin plugin, long delay) {
        // TODO
        return null;
    }

    public TridentRunnable runTaskAsyncLater(TridentPlugin plugin, long delay) {
        // TODO
        return null;
    }

    public TridentRunnable runRepeatingTask(TridentPlugin plugin, long delay, long interval) {
        // TODO
        return null;
    }


}
