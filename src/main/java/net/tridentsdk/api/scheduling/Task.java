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
package net.tridentsdk.api.scheduling;

import net.tridentsdk.plugin.TridentPlugin;

import java.util.concurrent.atomic.AtomicBoolean;

public interface Task extends Runnable {
    /**
     * Interval is the ticks left of a specific action for repeating and delayed tasks
     *
     * <p>For delayed tasks, the interval is the amount of ticks delay, and for repeating, interval is the amount
     * of ticks between each repeat of the task.</p>
     *
     * <p>Setting the interval will reset the ticks already accumulated by the task</p>
     *
     * @param interval the interval to set the task to
     */
    void setInterval(long interval);

    /**
     * Gets the interval set or created by the task
     *
     * @return the interval, as defined in {@link net.tridentsdk.api.scheduling.Task#setInterval(long)}
     */
    long getInterval();

    /**
     * The task scheduling type
     *
     * @return the type the task is scheduled according to
     */
    SchedulerType getType();

    /**
     * The execution runnable, invoked when the task is scheduled to occur
     *
     * @return the runnable that is run at scheduled time
     */
    TridentRunnable getRunnable();

    /**
     * Whether or not the task has been run by the execution iterators in the scheduler
     *
     * @return {@code true} if the task has run already during the tick, {@code false} if it needs to begin
     */
    AtomicBoolean getRan();

    /**
     * The plugin that scheduled the task, or passed in when scheduled
     *
     * @return the scheduling plugin
     */
    TridentPlugin getPlugin();

    /**
     * Cancels the task, only needed for repeating tasks. Scheduled later and run tasks are auto cancelled.
     */
    void cancel();
}
