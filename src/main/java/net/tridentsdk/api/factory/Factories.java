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

/**
 * Available creational factories for basic objects
 *
 * @author The TridentSDK Team
 */
public class Factories {
    private static TaskFactory taskFactory;
    private static ThreadFactory threadFactory;
    private static ConfigFactory configFactory;
    private static final ReflectFactory reflectionFactory = new ReflectFactory();
    private static final DisplayInfo INFO = new DisplayInfo();

    @InternalUseOnly
    public static void init(TaskFactory factory) {
        taskFactory = factory;
    }

    @InternalUseOnly
    public static void init(ThreadFactory factory) {
        threadFactory = factory;
    }

    @InternalUseOnly
    public static void init(ConfigFactory factory) {
        configFactory = factory;
    }

    /**
     * Schedulers and task executors
     *
     * @return the task factory
     */
    public static TaskFactory tasks() {
        return taskFactory;
    }

    /**
     * Thread management, thread creators/assignment
     *
     * @return the threads factory
     */
    public static ThreadFactory threads() {
        return threadFactory;
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
        return configFactory;
    }
}
