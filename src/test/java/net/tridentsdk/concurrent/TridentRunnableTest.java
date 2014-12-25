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

package net.tridentsdk.concurrent;

import net.tridentsdk.concurrent.ScheduledTask;
import net.tridentsdk.concurrent.SchedulerType;
import net.tridentsdk.concurrent.TridentRunnable;
import net.tridentsdk.plugin.TridentPlugin;
import org.junit.Assert;
import org.junit.Test;

public class TridentRunnableTest {
    private final TridentRunnable runnable = new TridentRunnable() {
        @Override
        public void run() {
        }
    };

    @Test
    public void lifecycle() {
        runnable.prerunSync();
        runnable.run();
        runnable.runAfterAsync();
        runnable.runAfterSync();
        Assert.assertEquals(runnable, runnable);
    }

    @Test
    public void implAccess() {
        ScheduledTask task;
        runnable.markSchedule(task = new ScheduledTask() {
            long interval;

            @Override
            public long getInterval() {
                return interval;
            }

            @Override
            public void setInterval(long interval) {
                this.interval = interval;
            }

            @Override
            public SchedulerType getType() {
                return SchedulerType.ASYNC_LATER;
            }

            @Override
            public TridentRunnable getRunnable() {
                return runnable;
            }

            @Override
            public TridentPlugin getPlugin() {
                return null;
            }

            @Override
            public void cancel() {
            }

            @Override
            public void run() {
                runnable.run();
            }
        });
        runnable.setInterval(21);
        Assert.assertEquals(runnable.getInterval(), 21);
        Assert.assertEquals(runnable.getId(), 1);
        Assert.assertEquals(runnable.getTask(), task);
        runnable.cancel(); // Just for the heck of it
    }
}
