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
package net.tridentsdk.api.factory;

public class Factories {
    private static TaskFactory taskFactory;
    private static ThreadFactory threadFactory;

    public static void init(TaskFactory factory) {
        taskFactory = factory;
    }

    public static void init(ThreadFactory factory) {
        threadFactory = factory;
    }

    public static TaskFactory taskFactory() {
        return taskFactory;
    }

    public static ThreadFactory threadFactory() {
        return threadFactory;
    }
}
