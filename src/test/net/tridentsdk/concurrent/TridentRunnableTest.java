package net.tridentsdk.concurrent;

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
            public void setInterval(long interval) {
                this.interval = interval;
            }

            @Override
            public long getInterval() {
                return interval;
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
