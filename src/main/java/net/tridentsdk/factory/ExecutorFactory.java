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

package net.tridentsdk.factory;

import net.tridentsdk.concurrent.TaskExecutor;

import javax.annotation.concurrent.ThreadSafe;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/**
 * A tunable task executor which represents a scalable thread pool
 *
 * @author The TridentSDK Team
 */
@ThreadSafe
public interface ExecutorFactory extends Executor {
    /**
     * The maximum amount of expiring threads plus non-dying threads which are created when executing tasks or finding a scaled thread when
     * existing workers are occupied
     *
     * @return the maximum scale, by default {@code Integer.MAX_VALUE}
     */
    int maxScale();

    /**
     * Sets the maximum scale (explained in {@link #maxScale()}
     *
     * @param maxScale the maximum extra scaling threads
     */
    void setMaxScale(int maxScale);

    /**
     * The interval at which an expiring worker will die after inactivity
     *
     * @return the time, in milliseconds, which will need to elapse before an expiring worker dies
     */
    long expireIntervalMillis();

    /**
     * Sets the interval at which an expiring worker will die after inactivity
     *
     * @param expireIntervalMillis the interval, in milliseconds
     */
    void setExpireIntervalMillis(long expireIntervalMillis);

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
     * Obtains a worker which is available in the thread pool
     *
     * <p>Unlike using {@link #scaledThread()}, this does not create a new thread if all workers are occupied.</p>
     *
     * <p>Obtaining an executor and immediately adding a task is a broken idiom.
     * Store the executor, or use {@link #execute(Runnable)}</p>
     *
     * @return the next worker in the pool
     */
    TaskExecutor nextWorker();

    /**
     * Obtains an unoccupied thread, or if none exists, create a new thread
     *
     * <p>The new thread, if created, will expire. The default time is 60 seconds, if there are no tasks left.
     * The timer is renewed if there are still tasks. The timer is a lazy {@code long} stamp set when tasks are polled
     * if the worker wakes up spuriously or a task is added.</p>
     *
     * <p>Obtaining an executor and immediately adding a task is a broken idiom.
     * Store the executor, or use {@link #execute(Runnable)}</p>
     *
     * @return the thread which is unoccupied when observed
     */
    TaskExecutor scaledThread();

    /**
     * Lists all available task executors from the threads
     *
     * @return the thread list
     */
    List<TaskExecutor> threadList();

    /**
     * Adds support for running a runnable with callback
     *
     * @param callable the callback to run
     * @return the result of the task, before it has finished
     */
    <V> Future<V> submit(Callable<V> callable);

    /**
     * Shuts down the thread processes
     */
    void shutdown();
}
