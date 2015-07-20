/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2014 The TridentSDK Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.tridentsdk.concurrent;

import net.tridentsdk.docs.InternalUseOnly;

import javax.annotation.concurrent.ThreadSafe;

/**
 * A wrapper over {@link java.lang.Runnable} that provides access to the scheduling facilities after scheduled
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public abstract class ScheduledRunnable implements Runnable {
    private static volatile int currentId = 0;

    private final int id;
    private volatile ScheduledTask task;

    public ScheduledRunnable() {
        id = currentId += 1;
    }

    /**
     * Guaranteed to be run before this Runnable on the main thread, even if this runnable is going to be run
     * asynchronously, useful for collecting resources to work on.
     *
     * <p>This method is invoked synchronously relative to the {@link #run()} method</p>
     */
    public void beforeRun() {
    }

    /**
     * Runs after this runnable has finished asynchronously
     *
     * <p>This method does not require the code to be thread-safe</p>
     */
    public void afterAsyncRun() {
    }

    /**
     * Runs after this runnable has been executed synchronously
     */
    public void afterSyncRun() {
    }

    /**
     * Cancels the task and removes from execution. See {@link ScheduledTask#cancel()}
     *
     * <p>WARNING: This is a delegated function. DO NOT call this method before it is scheduled. A NullPointerException
     * will be thrown. This can be called when {@code task() != null}.</p>
     */
    public final void cancel() {
        task.cancel();
    }

    /**
     * Gets how long between runs this is supposed to wait if it is a repeating task
     *
     * <p>WARNING: This is a delegated function. DO NOT call this method before it is scheduled. A NullPointerException
     * will be thrown. This can be called when {@code task() != null}.</p>
     */
    public final long interval() {
        return task.interval();
    }

    /**
     * Sets how long this runnable should wait between executions if this is a repeating task
     *
     * <p>If this task is  synchronous to the main thread, the change will be immediate, if it is not, the change may
     * take an iteration to take effect, however {@link ScheduledRunnable#interval()} will reflect the changes
     * immediately</p>
     *
     * <p>WARNING: This is a delegated function. DO NOT call this method before it is scheduled. A NullPointerException
     * will be thrown. This can be called when {@code task() != null}.</p>
     */
    public final void setInterval(long interval) {
        task.setInterval(interval);
    }

    /**
     * Used internally to refer to this runnable, probably shouldn't be used by plugins
     */
    public final int id() {
        return this.id;
    }

    /**
     * The scheduled representation of the runnable
     *
     * @return the {@link ScheduledTask} object held within the scheduling implementation
     */
    public ScheduledTask asScheduledTask() {
        return this.task;
    }

    @InternalUseOnly
    public void markSchedule(ScheduledTask task) {
        this.task = task;
    }
}
