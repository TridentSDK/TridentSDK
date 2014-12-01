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

import net.tridentsdk.api.DisplayInfo;
import net.tridentsdk.api.docs.InternalUseOnly;
import net.tridentsdk.api.docs.PossiblyThreadSafe;
import net.tridentsdk.api.threads.HeldValueLatch;

/**
 * Available creational factories for basic objects
 *
 * <p>If the factories are accessed before initialization, the caller thread will block</p>
 *
 * <p>This is managed by using a {@link net.tridentsdk.api.threads.HeldValueLatch}, instead of a single latch. If a
 * class needs to be initialized that uses another factory, it will never occur because the thread is setting a value
 * after the previous initialization is blocked because it was not fully initialized.</p>
 *
 * @author The TridentSDK Team
 */
@PossiblyThreadSafe
public class Factories {
    private static final HeldValueLatch<TaskFactory> taskFactory = new HeldValueLatch<>();
    private static final HeldValueLatch<ThreadFactory> threadFactory = new HeldValueLatch<>();
    private static final HeldValueLatch<ConfigFactory> configFactory = new HeldValueLatch<>();
    private static final HeldValueLatch<CollectFactory> collectFactory = new HeldValueLatch<>();

    private static final ReflectFactory reflectionFactory = new ReflectFactory();
    private static final DisplayInfo INFO = new DisplayInfo();

    @InternalUseOnly
    public static void init(TaskFactory factory) {
        taskFactory.countDown(factory);
    }

    @InternalUseOnly
    public static void init(ThreadFactory factory) {
        threadFactory.countDown(factory);
    }

    @InternalUseOnly
    public static void init(ConfigFactory factory) {
        configFactory.countDown(factory);
    }

    @InternalUseOnly
    public static void init(CollectFactory factory) {
        collectFactory.countDown(factory);
    }

    /**
     * Schedulers and task executors
     *
     * @return the task factory
     */
    public static TaskFactory tasks() {
        try {
            return taskFactory.await();
        } catch (InterruptedException e) {
            // Release up the stack
            Thread.currentThread().interrupt();
            return null;
        }
    }

    /**
     * Thread management, thread creators/assignment
     *
     * @return the threads factory
     */
    public static ThreadFactory threads() {
        try {
            return threadFactory.await();
        } catch (InterruptedException e) {
            // Release up the stack
            Thread.currentThread().interrupt();
            return null;
        }
    }

    /**
     * Reflection shortcuts and fast wrappers
     *
     * @return the reflection factory
     */
    public static ReflectFactory reflect() {
        return reflectionFactory;
    }

    /**
     * Reimplemented or new backed collections
     *
     * @return the collection factory
     */
    public static CollectFactory collect() {
        try {
            return collectFactory.await();
        } catch (InterruptedException e) {
            // Release up the stack
            Thread.currentThread().interrupt();
            return null;
        }
    }

    /**
     * Public server information displayed to the client
     *
     * @return the server information modifiers and getters
     */
    public static DisplayInfo serverInfo() {
        return INFO;
    }

    /**
     * Deals with configurations and files
     *
     * @return the configuration factory
     */
    public static ConfigFactory configs() {
        try {
            return configFactory.await();
        } catch (InterruptedException e) {
            // Release up the stack
            Thread.currentThread().interrupt();
            return null;
        }
    }
}
