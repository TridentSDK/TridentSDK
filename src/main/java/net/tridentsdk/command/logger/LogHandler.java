/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2016 The TridentSDK Team
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
package net.tridentsdk.command.logger;

import net.tridentsdk.Impl;

/**
 * Handlers may be added to loggers in order to intercept
 * output from them in the form of {@link LogMessage}s.
 *
 * <p>Use {@link #intercept(Logger, LogHandler)}} method to
 * add the handlers to a logger.</p>
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface LogHandler {
    /**
     * Attaches the given logger handler to the given
     * logger.
     *
     * @param logger the logger from which to intercept
     * messages, or {@code null} to intercept
     * all messages.
     * @param handler the handler
     */
    static void intercept(Logger logger, LogHandler handler) {
        Impl.get().attachHandler(logger, handler);
    }

    /**
     * Removes the given logger handler from the given
     * logger.
     *
     * @param logger the logger from which to remove the
     * handler, or {@code null} to remove from all loggers
     * all messages.
     * @param handler the handler
     * @return {@code true} to indicate success
     */
    static boolean removeHandler(Logger logger, LogHandler handler) {
        return Impl.get().removeHandler(logger, handler);
    }

    /**
     * Handles the message that was passed to the logger.
     *
     * @param message the message to be printed
     * @return {@code true} if the message should continue,
     * or {@code false} to cancel the message from being
     * logged
     */
    boolean handle(LogMessage message);
}