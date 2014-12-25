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

package net.tridentsdk;

// if these shouldn't exist, or should go somewhere else, just move them
// this is probably temporary

import net.tridentsdk.util.TridentLogger;

import java.util.concurrent.ThreadFactory;

/**
 * Contains the default values used in server.json
 *
 * @author The TridentSDK Team
 */
public final class Defaults {
    /**
     * Maximum allowed players on the server
     */
    public static final int MAX_PLAYERS = 10;
    /**
     * The text displayed below the server name in the multiplayer menu
     */
    public static final String MOTD = "Just another Trident server...";
    /**
     * The difficulty of the game
     */
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    /**
     * The icon on the left of the server
     */
    public static final String MOTD_IMAGE_LOCATION = "/server-icon.png";
    /**
     * Scheduler mode
     */
    public static final boolean IN_A_HURRY_MODE = true;
    /**
     * The threshold used for compression
     */
    public static final int COMPRESSION_THRESHHOLD = 256;
    /**
     * The server port
     */
    public static final int PORT = 25565;
    /**
     * The default address for the server
     */
    public static final String ADDRESS = "localhost";

    /**
     * The server's default exception handler
     */
    public static final Thread.UncaughtExceptionHandler EXCEPTION_HANDLER = new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread thread, Throwable throwable) {
            TridentLogger.error(throwable);
        }
    };

    /**
     * The thread factory which makes a thread that handles exceptions
     */
    public static final ThreadFactory ERROR_HANDLED = new ThreadFactory() {
        @Override
        public Thread newThread(final Runnable runnable) {
            return new Thread(new Runnable() {
                @Override
                public void run() {
                    Thread.setDefaultUncaughtExceptionHandler(EXCEPTION_HANDLER);
                    runnable.run();
                }
            });
        }
    };

    private Defaults() {
    }
}
