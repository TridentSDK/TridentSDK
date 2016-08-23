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

import javax.annotation.concurrent.ThreadSafe;
import java.io.OutputStream;

/**
 * Represents the server logger, whether or not there is
 * a physical logger on the working machine.
 *
 * <p>Chat colors may be used in conjunction with the
 * following string in order to add color to the message on
 * the logger (dependant on the OS and the implementation).
 * </p>
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public interface Logger {
    /**
     * Obtains a new logger by the given name.
     *
     * @param name the name to call the new logger
     * @return the logger
     */
    static Logger get(String name) {
        return Impl.get().newLogger(name);
    }

    /**
     * Obtains a new logger for the given {@code class}.
     *
     * @param cls the {@code class} to get the new logger
     * @return the logger
     */
    static Logger get(Class<?> cls) {
        return Impl.get().newLogger(cls.getSimpleName());
    }

    /**
     * Obtains the name of this logger as it was registered
     * by the {@link #get(String)} or {@link #get(Class)}.
     *
     * @return the name
     */
    String name();

    /**
     * Logs the given string to the logger without any
     * color formatting.
     *
     * @param s the string to log
     */
    void log(String s);

    /**
     * Logs the given string to the logger indicative of a
     * successful operation (usually a green color, however
     * it is up to the implementation to decide).
     *
     * @param s the string to log
     */
    void success(String s);

    /**
     * Logs the given string to the logger indicative of a
     * warning (usually a yellow color, however it is up to
     * the implementation to decide).
     *
     * @param s the string to log
     */
    void warn(String s);

    /**
     * Logs the given string to the logger indicative of an
     * error (usually a red color, however it is up to the
     * implementation to decide).
     *
     * @param s the string to log
     */
    void error(String s);

    /**
     * Logs the given string as a debug message to the
     * logger.
     *
     * <p>The implementation may decide to hide debug
     * messages to be logged only in the logfile, or to
     * allow server owners to view it, or even disable this
     * function entirely.</p>
     *
     * @param s the string to log
     */
    void debug(String s);

    // No debugp (really?) no good reason to make pretty
    // debug messages

    /**
     * Obtains the raw output that the underlying logger
     * logs messages.
     *
     * <p>This is useful for wrapping with the
     * {@link java.io} API classes.</p>
     *
     * @return the outputstream
     */
    OutputStream out();
}