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

import net.tridentsdk.api.entity.Entity;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.threads.TaskExecutor;
import net.tridentsdk.api.world.World;
import net.tridentsdk.plugin.TridentPlugin;

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
     * Creates a new TaskExecutor using the plugin thread pool
     *
     * @param plugin the plugin to assign
     * @return the created task executor
     */
    TaskExecutor pluginThread(TridentPlugin plugin);

    /**
     * Gets all of the thread plugin wrappers
     *
     * @return the values of the concurrent cache
     */
    Collection<TridentPlugin> plugins();

    /**
     * Creates a new TaskExecutor using the world thread pool
     *
     * @param worldd the world to assign
     * @return the created task executor
     */
    TaskExecutor worldThread(World worldd);

    /**
     * Get all of the wrapped world threads
     *
     * @return the worlds being managed by the task executors
     */
    Collection<World> worlds();

    <T> ExecutorFactory<T> executor(int threads);
}
