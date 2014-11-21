/*
 *     TridentSDK - A Minecraft Server API
 *     Copyright (C) 2014, The TridentSDK Team
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
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