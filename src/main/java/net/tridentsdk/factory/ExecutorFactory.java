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
 * A task executor that has multiple specified threads
 *
 * @author The TridentSDK Team
 */
@ThreadSafe
public interface ExecutorFactory extends Executor {
    /**
     * The thread with the least amount of tasks assigned to it
     *
     * @return the thread with the minimal task count
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
