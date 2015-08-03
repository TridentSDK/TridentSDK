package net.tridentsdk.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Utility to wait until a task completes before continuing in the current thread
 *
 * @author The TridentSDK Team
 */
public class Joiner {
    private final CountDownLatch waiter = new CountDownLatch(1);

    /**
     * Joins the thread and signals the single waiter to continue
     */
    public void doJoin() {
        waiter.countDown();
    }

    /**
     * Awaits a subsequent join call
     */
    public void await() {
        try {
            waiter.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
