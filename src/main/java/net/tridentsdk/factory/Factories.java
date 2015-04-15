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

import net.tridentsdk.AccessBridge;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Available creational factories for basic objects
 *
 * <p>If the factories are accessed before initialization, the caller thread will block</p>
 *
 * @author The TridentSDK Team
 */
@ThreadSafe
public final class Factories {
    private static final ConfigFactory configFactory = new ConfigFactory();
    private static final ReflectFactory reflectionFactory = new ReflectFactory();

    private Factories() {
    }

    /**
     * Schedulers and task executors
     *
     * @return the task factory
     */
    public static TaskFactory tasks() {
        return AccessBridge.open().demand(TaskFactory.class);
    }

    /**
     * Thread management, thread creators/assignment
     *
     * @return the threads factory
     */
    public static ThreadFactory threads() {
        return AccessBridge.open().demand(ThreadFactory.class);
    }

    /**
     * Reimplemented or new backed collections
     *
     * @return the collection factory
     */
    public static CollectFactory collect() {
        return new CollectFactory();
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
     * Deals with configurations and files
     *
     * @return the configuration factory
     */
    public static ConfigFactory configs() {
        return configFactory;
    }
}
