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
package net.tridentsdk.api.factory;

import net.tridentsdk.api.threads.TaskExecutor;

import java.util.Collection;
import java.util.List;

public interface ExecutorFactory<Assignment> {
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
    TaskExecutor assign(Assignment assignment);

    /**
     * Removes the assigned thread and reduces by one the scale factor for the thread
     *
     * @param assignment the assignment that uses the executor to be removed
     */
    void removeAssignment(Assignment assignment);

    /**
     * Returns the assigned objects
     *
     * @return the assignments in the maps
     */
    Collection<Assignment> values();

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
