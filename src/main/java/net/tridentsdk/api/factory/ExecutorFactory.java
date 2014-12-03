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
package net.tridentsdk.api.factory;

import net.tridentsdk.api.threads.TaskExecutor;

import java.util.Collection;
import java.util.List;

public interface ExecutorFactory<E> {
    /**
     * The thread with the least amount of tasks assigned to it
     *
     * @return the thread with the minimal task count
     */
    TaskExecutor scaledThread();

    /**
     * Assigns the scaled thread to the assignment
     *
     * <p>If already assigned, the executor is returned for the fast-path</p>
     *
     * @param assignment the assignment that uses the executor
     * @return the executor assigned
     */
    TaskExecutor assign(E assignment);

    /**
     * Adds a scaled thread to the assignment Map, useful if the assignment is created with the thread
     *
     * <p>Cannot replace existing entries. Acquired threads using {@link #assign(Object)} cannot be set.</p>
     *
     * @param executor the executor assigned
     * @param assignment the element that is associated with the executor
     */
    void set(TaskExecutor executor, E assignment);

    /**
     * Removes the assigned thread and reduces by one the scale factor for the thread
     *
     * @param assignment the assignment that uses the executor to be removed
     */
    void removeAssignment(E assignment);

    /**
     * Returns the assigned objects
     *
     * @return the assignments in the maps
     */
    Collection<E> values();

    /**
     * Lists all available task executors from the threads
     *
     * @return the thread list
     */
    List<TaskExecutor> threadList();

    /**
     * Shuts down the thread processes
     */
   void shutdown();
}
