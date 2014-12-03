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

import net.tridentsdk.api.entity.Entity;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.threads.TaskExecutor;
import net.tridentsdk.api.world.World;

import java.util.Collection;

/**
 * Creates threads and managers for those threads
 *
 * <p>These methods are primarily only supposed to be used by the server only. Because they are not volatile, they are
 * not marked with {@link net.tridentsdk.api.docs.InternalUseOnly}. However, you must be careful - data will not be
 * wiped unless you remove it yourself, which holds true until the server exits and shutsdown. Even calling the method
 * without using the returned {@link net.tridentsdk.api.threads.TaskExecutor} will place an additional assignment inside
 * the backing executor.</p>
 *
 * @author The TridentSDK Team
 */
public interface ThreadFactory {
    /**
     * Creates a new TaskExecutor using the entity thread pool
     *
     * @param entity the entity to assign
     * @return the task executor created
     */
    TaskExecutor entityThread(Entity entity);

    /**
     * Get all of the thread entity wrappers
     *
     * @return the values of the entity cache
     */
    Collection<Entity> entities();

    /**
     * Creates a new TaskExecutor using the player thread pool
     *
     * @param player the player to assign
     * @return the task executor created
     */
    TaskExecutor playerThread(Player player);

    /**
     * Gets all of the thread player wrappers
     *
     * @return the values of the concurrent cache
     */
    Collection<Player> players();

    /**
     * Creates a new TaskExecutor using the world thread pool
     *
     * @param world the world to assign
     * @return the created task executor
     */
    TaskExecutor worldThread(World world);

    /**
     * Get all of the wrapped world threads
     *
     * @return the worlds being managed by the task executors
     */
    Collection<World> worlds();

    /**
     * A new concurren task executor reimplemented in the server
     *
     * @param threads the threads available in the pool
     * @param <T> the assignment type for each thread
     * @return the execution factory
     */
    <T> ExecutorFactory<T> executor(int threads);
}
