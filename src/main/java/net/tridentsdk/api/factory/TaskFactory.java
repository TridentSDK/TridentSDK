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
