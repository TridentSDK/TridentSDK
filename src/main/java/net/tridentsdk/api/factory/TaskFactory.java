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

import net.tridentsdk.api.scheduling.Task;
import net.tridentsdk.api.scheduling.TridentRunnable;
import net.tridentsdk.plugin.TridentPlugin;

public interface TaskFactory {
    public Task asyncRun(TridentPlugin plugin, TridentRunnable runnable);

    public Task syncRun(TridentPlugin plugin, TridentRunnable runnable);

    public Task asyncLater(TridentPlugin plugin, TridentRunnable runnable, long delay);

    public Task syncLater(TridentPlugin plugin, TridentRunnable runnable, long delay);

    public Task asyncRepeat(TridentPlugin plugin, TridentRunnable runnable, long delay, long initialInterval);

    public Task syncRepeat(TridentPlugin plugin, TridentRunnable runnable, long delay, long initialInterval);
}
