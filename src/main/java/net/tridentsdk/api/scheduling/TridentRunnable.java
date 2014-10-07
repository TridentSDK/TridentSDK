/*
 * Copyright (c) 2014, The TridentSDK Team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *     3. Neither the name of the The TridentSDK Team nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL The TridentSDK Team BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.tridentsdk.api.scheduling;

import net.tridentsdk.api.Trident;
import net.tridentsdk.plugin.TridentPlugin;

import java.util.concurrent.atomic.AtomicLong;

public abstract class TridentRunnable implements Runnable {

    /**
     * Id of this runnable, unique to this runtime
     */
    private final Integer id = -1;

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

    public final synchronized TridentRunnable runTaskSynchronously(TridentPlugin plugin) {
        this.checkState();
        return Trident.getServer().getScheduler().runTaskSynchronously(plugin, this);
    }

    public final synchronized TridentRunnable runTaskAsynchronously(TridentPlugin plugin) {
        this.checkState();

        return Trident.getServer().getScheduler().runTaskAsynchronously(plugin, this);
    }

    public final synchronized TridentRunnable runTaskSyncLater(TridentPlugin plugin, long delay) {
        this.checkState();
        return Trident.getServer().getScheduler().runTaskSyncLater(plugin, this, delay);
    }

    public final synchronized TridentRunnable runTaskAsyncLater(TridentPlugin plugin, long delay) {
        this.checkState();
        return Trident.getServer().getScheduler().runTaskAsyncLater(plugin, this, delay);
    }

    public final synchronized TridentRunnable runTaskSyncRepeating(TridentPlugin plugin, long delay, long interval) {
        this.checkState();
        Trident.getServer().getScheduler().runTaskSyncRepeating(plugin, this, delay, interval);
        this.interval.set(interval);
        return this;
    }

    public final synchronized TridentRunnable runTaskAsyncRepeating(TridentPlugin plugin, long delay, long interval) {
        this.checkState();
        Trident.getServer().getScheduler().runTaskAsyncRepeating(plugin, this, delay, interval);
        this.interval.set(interval);
        return this;
    }

    private void checkState() {
        if (this.id != -1) {
            throw new IllegalStateException("This runnable has already been scheduled!");
        }
    }

    public final void cancel() {
        Trident.getServer().getScheduler().cancel(this.id);
    }

    /**
     * Gets how long between runs this is supposed to wait if it is a repeating task
     */
    public final synchronized long getInterval() {
        return this.interval.get();
    }

    /**
     * Sets how long this runnable should wait between executions if this is a repeating task <p>If this task is
     * synchronous to the main thread, the change will be immediate, if it is not, the change may take an iteration to
     * take effect, however {@link TridentRunnable#getInterval()} will reflect the changes immediately</p>
     */
    public final synchronized void setInterval(long interval) {
        this.interval.set(interval);
    }

    /**
     * Used internally to refer to this runnable, probably shouldn't be used by plugins
     */
    public final int getId() {
        return this.id;
    }

    /**
     * Should be reimplemented by a runnable if it wants to use in a hurry <p>Reimplement to return true</p>
     */
    public boolean usesInAHurry() {
        return false;
    }
}
