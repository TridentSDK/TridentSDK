/*
 *     TridentSDK - A Minecraft Server API
 *     Copyright (C) 2014, The TridentSDK Team
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.tridentsdk.api.scheduling;

import net.tridentsdk.api.Trident;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public abstract class TridentRunnable implements Runnable {
    /**
     * Id of this runnable, unique to this runtime
     */
    private final AtomicInteger id = new AtomicInteger(-1);

    private final AtomicLong interval = new AtomicLong();
    private final boolean inAHurry = false;

    /**
     * Returns if this runnable is in a hurry, usually indicating that the server is shutting down
     */
    public boolean isInAHurry() {
        return this.inAHurry;
    }

    /**
     * Guaranteed to be run before this Runnable on the main thread, even if this runnable is going to be run
     * asynchronously, useful for collecting resources to work on.
     */
    public void prerunSync() {
    }

    /**
     * Runs after this runnable has finished, asynchronously
     */
    public void runAfterAsync() {
    }

    /**
     * Runs after this runnable has finished, synchronously
     */
    public void runAfterSync() {
    }

    /*  um, what?

    public final TridentRunnable runTaskSynchronously(TridentPlugin plugin) {
        this.checkState();
        return Trident.getServer().getScheduler().runTaskSynchronously(plugin, this);
    }

    public final TridentRunnable runTaskAsynchronously(TridentPlugin plugin) {
        this.checkState();

        return Trident.getServer().getScheduler().runTaskAsynchronously(plugin, this);
    }

    public final TridentRunnable runTaskSyncLater(TridentPlugin plugin, long delay) {
        this.checkState();
        return Trident.getServer().getScheduler().runTaskSyncLater(plugin, this, delay);
    }

    public final TridentRunnable runTaskAsyncLater(TridentPlugin plugin, long delay) {
        this.checkState();
        return Trident.getServer().getScheduler().runTaskAsyncLater(plugin, this, delay);
    }

    public final TridentRunnable runTaskSyncRepeating(TridentPlugin plugin, long delay, long interval) {
        this.checkState();
        Trident.getServer().getScheduler().runTaskSyncRepeating(plugin, this, delay, interval);
        this.interval.set(interval);
        return this;
    }

    public final TridentRunnable runTaskAsyncRepeating(TridentPlugin plugin, long delay, long interval) {
        this.checkState();
        Trident.getServer().getScheduler().runTaskAsyncRepeating(plugin, this, delay, interval);
        this.interval.set(interval);
        return this;
    }

    private void checkState() {
        if (this.id.get() != -1) throw new IllegalStateException("This runnable has already been scheduled!");
    } */

    public final void cancel() {
        Trident.getServer().getScheduler().cancel(this);
    }

    /**
     * Gets how long between runs this is supposed to wait if it is a repeating task
     */
    public final long getInterval() {
        return this.interval.get();
    }

    /**
     * Sets how long this runnable should wait between executions if this is a repeating task <p>If this task is
     * synchronous to the main thread, the change will be immediate, if it is not, the change may take an iteration to
     * take effect, however {@link TridentRunnable#getInterval()} will reflect the changes immediately</p>
     */
    public final void setInterval(long interval) {
        this.interval.set(interval);
    }

    /**
     * Used internally to refer to this runnable, probably shouldn't be used by plugins
     */
    public final int getId() {
        return this.id.get();
    }

    /**
     * Should be reimplemented by a runnable if it wants to use in a hurry <p>Reimplement to return true</p>
     */
    public boolean usesInAHurry() {
        return false;
    }
}
