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
/**
 * The abstract scheduling facilities and tools used by TridentSDK.
 *
 * Among these, include:
 * <ul>
 *     <li>{@link net.tridentsdk.api.scheduling.SchedulerType} - The task type that determines the scheduled behavior of
 *     the task</li>
 *     <li>{@link net.tridentsdk.api.scheduling.Task} - The wrapper that encases the functionality of the the task
 *     after scheduling</li>
 *     <li>{@link net.tridentsdk.api.scheduling.TridentRunnable} - The overriden task to be run at the scheduled time</li>
 * </ul>
 *
 * These should not be used on their own unless from the implementing runnable that is provided by the
 * {@link net.tridentsdk.api.factory.TaskFactory}.
 */
package net.tridentsdk.api.scheduling;