package net.tridentsdk.api.threads;

/**
 * Execution abstraction
 *
 * @author The TridentSDK Team
 */
public interface TaskExecutor {
    /**
     * Adds the task to the queue
     *
     * @param task the task to add
     */
    void addTask(Runnable task);

    /**
     * Closes the thread and stops execution of new / remaining tasks
     */
    void interrupt();

    /**
     * Thread form
     *
     * @return the thread that is running
     */
    Thread asThread();
}