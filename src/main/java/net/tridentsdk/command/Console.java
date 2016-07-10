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
package net.tridentsdk.command;

/**
 * Represents the server console, whether or not there is
 * a physical console on the working machine.
 *
 * <p>Chat colors may be used in conjunction with the
 * following string in order to add color to the message on
 * the console (dependant on the OS and the implementation).
 * </p>
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
public interface Console {
    /**
     * Logs the given string to the console without any
     * color formatting.
     *
     * @param s the string to log
     */
    void log(String s);

    /**
     * Logs the given string to the console indicative of a
     * successful operation (usually a green color, however
     * it is up to the implementation to decide).
     *
     * @param s the string to log
     */
    void success(String s);

    /**
     * Logs the given string to the console indicative of a
     * warning (usually a yellow color, however it is up to
     * the implementation to decide).
     *
     * @param s the string to log
     */
    void warn(String s);

    /**
     * Logs the given string to the console indicative of an
     * error (usually a red color, however it is up to the
     * implementation to decide).
     *
     * @param s the string to log
     */
    void error(String s);

    /**
     * Logs the given string as a debug message to the
     * console.
     *
     * <p>The implementation may decide to hide debug
     * messages to be logged only in the logfile, or to
     * allow server owners to view it, or even disable this
     * function entirely.</p>
     *
     * @param s the string to log
     */
    void debug(String s);
}