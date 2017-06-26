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

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/**
 * A tunable task executor which represents a scalable thread pool
 *
 * <p>This class contains 3 selection methods:
 * <ul>
 *   <li>Core: Selects a non-dying thread</li>
 *   <li>Next: Selects a thread, might be non-dying</li>
 *   <li>Scaled: Creates a new thread if all workers are occupied and the count is below maxThreads. Otherwise returns nextThread.</li>
 * </ul></p>
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public interface SelectableThreadPool extends Executor {
    /**
     * The maximum amount of expiring concurrent plus non-dying concurrent which are created when executing tasks or finding a scaled thread when
     * existing workers are occupied
     *
     * @return the maximum scale, by default {@code 50}
     */
    int maxThreads();

    /**
     * Sets the maximum scale (explained in {@link #maxThreads()}
     *
     * @param maxScale the maximum extra scaling concurrent
     */
    void setMaxThreads(int maxScale);

    /**
     * The interval at which an <em>expiring</em> worker will die after inactivity
     *
     * @return the time, in milliseconds, which will need to elapse before an expiring worker dies
     */
    long threadExpiryTime();

    /**
     * Sets the interval at which an expiring worker will die after inactivity
     *
     * @param expireIntervalMillis the interval, in milliseconds
     */
    void setThreadExpiryTime(long expireIntervalMillis);

    /**
     * Obtains whether an expiring worker will check the task list size before dieing
     *
     * @return {@code true} (default) to indiciate the worker does check the task list size
     */
    boolean mustEmptyBeforeExpire();

    /**
     * Ensures there are no tasks in an expiring worker before it dies
     *
     * @param mustEmptyBeforeExpire {@code true} (default) if the worker needs to ensure the task list is empty
     */
    void setMustEmptyBeforeExpire(boolean mustEmptyBeforeExpire);

    /**
     * Selects a thread in much the same way as {@link #selectNext()}, however, it does not include concurrent that have been
     * added to the scaling pool.
     *
     * <p>This is useful for caching SelectableThreads for thread-confinement</p>
     *
     * @return the next selected thread
     */
    SelectableThread selectCore();

    /**
     * Obtains a worker which is available in the thread pool
     *
     * <p>Unlike using {@link #selectScaled()}, this does not create a new thread if all workers are occupied.</p>
     *
     * <p>Obtaining an executor and immediately adding a task is a broken idiom.
     * Store the executor, or use {@link #execute(Runnable)}</p>
     *
     * @return the next worker in the pool
     */
    SelectableThread selectNext();

    /**
     * Obtains an unoccupied thread, or if none exists, create a new thread
     *
     * <p>The new thread, if created, will expire. The default time is 60 seconds, if there are no tasks left.
     * The timer is not renewed if there are still tasks. The timer is a lazy {@code long} stamp set when tasks are polled
     * if the worker wakes up spuriously or a task is added.</p>
     *
     * <p>Obtaining an executor and immediately adding a task is a broken idiom.
     * Store the executor, or use {@link #execute(Runnable)}</p>
     *
     * @return the thread which is unoccupied when observed
     */
    SelectableThread selectScaled();

    /**
     * Lists all available task executors from the concurrent
     *
     * @return the thread list
     */
    List<SelectableThread> workers();

    /**
     * Adds support for running a runnable with callback
     *
     * @param callable the callback to run
     * @return the result of the task, before it has finished
     */
    @Nonnull
    <V> Future<V> submit(Callable<V> callable);

    /**
     * Shuts down the thread processes
     */
    void shutdown();
}
