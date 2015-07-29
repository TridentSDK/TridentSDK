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

import net.tridentsdk.plugin.Plugin;
import net.tridentsdk.registry.Registered;
import net.tridentsdk.registry.Registry;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Generates tasks that are queued for execution in the specified method
 *
 * <p>In this context, synchronously defines a task which is run on the same thread which the plugin that submitted it
 * is also running on. An asynchronous task runs on a separate thread pool and requires the accessed state to be
 * correct synchronized to prevent inconsistencies.</p>
 *
 * <p>Ticks are a measurement of time which take place every 1/20th of a second. This is measured by the server's
 * ticking thread comparing the timestamps since the epoch.</p>
 *
 * <p>This class can be accessed using {@link Registered#tasks()}</p>
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public interface Scheduler extends Registry<ScheduledTask> {
    /**
     * Asynchronously run a task after the next tick
     *
     * @param plugin   the plugin which the task is registered to
     * @param runnable the runnable to perform the task
     * @return the task which was wrapped by the scheduler
     */
    ScheduledTask asyncRun(Plugin plugin, ScheduledRunnable runnable);

    /**
     * Synchronously run a task after the next tick
     *
     * @param plugin   the plugin which the task is registered to
     * @param runnable the runnable to perform the task
     * @return the task which was wrapped by the scheduler
     */
    ScheduledTask syncRun(Plugin plugin, ScheduledRunnable runnable);

    /**
     * Asynchronously run a task after a specified time has passed
     *
     * @param plugin   the plugin which the task is registered to
     * @param runnable the runnable to perform the task
     * @param delay    the amount of ticks to wait until the task is executed
     * @return the task which was wrapped by the scheduler
     */
    ScheduledTask asyncLater(Plugin plugin, ScheduledRunnable runnable, long delay);

    /**
     * Asynchronously run a task after a specified time has passed
     *
     * @param plugin   the plugin which the task is registered to
     * @param runnable the runnable to perform the task
     * @param delay    the amount of ticks to wait until the task is executed
     * @return the task which was wrapped by the scheduler
     */
    ScheduledTask syncLater(Plugin plugin, ScheduledRunnable runnable, long delay);

    /**
     * Asynchronously run a task repeatedly
     *
     * @param plugin          the plugin which the task is registered to
     * @param runnable        the runnable to perform the task
     * @param delay           the amount of ticks to wait until the task is executed
     * @param initialInterval the amount of time between each execution which occurs repeatedly until cancelled
     * @return the task which was wrapped by the scheduler
     */
    ScheduledTask asyncRepeat(Plugin plugin, ScheduledRunnable runnable, long delay, long initialInterval);

    /**
     * Synchronously run a task repeatedly
     *
     * @param plugin          the plugin which the task is registered to
     * @param runnable        the runnable to perform the task
     * @param delay           the amount of ticks to wait until the task is executed
     * @param initialInterval the amount of time between each execution which occurs repeatedly until cancelled
     * @return the task which was wrapped by the scheduler
     */
    ScheduledTask syncRepeat(Plugin plugin, ScheduledRunnable runnable, long delay, long initialInterval);
}
