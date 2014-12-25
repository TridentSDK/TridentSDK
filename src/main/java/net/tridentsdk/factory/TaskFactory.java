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

import net.tridentsdk.concurrent.ScheduledTask;
import net.tridentsdk.concurrent.TridentRunnable;
import net.tridentsdk.plugin.TridentPlugin;

public interface TaskFactory {
    public ScheduledTask asyncRun(TridentPlugin plugin, TridentRunnable runnable);

    public ScheduledTask syncRun(TridentPlugin plugin, TridentRunnable runnable);

    public ScheduledTask asyncLater(TridentPlugin plugin, TridentRunnable runnable, long delay);

    public ScheduledTask syncLater(TridentPlugin plugin, TridentRunnable runnable, long delay);

    public ScheduledTask asyncRepeat(TridentPlugin plugin, TridentRunnable runnable, long delay, long initialInterval);

    public ScheduledTask syncRepeat(TridentPlugin plugin, TridentRunnable runnable, long delay, long initialInterval);
}
