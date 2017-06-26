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

package net.tridentsdk.util;

import com.google.common.collect.ForwardingCollection;
import com.google.common.collect.ImmutableList;
import net.tridentsdk.Console;
import net.tridentsdk.Trident;
import net.tridentsdk.docs.InternalUseOnly;
import net.tridentsdk.docs.Policy;
import net.tridentsdk.registry.Registry;
import org.apache.log4j.*;
import org.slf4j.LoggerFactory;

import javax.annotation.concurrent.ThreadSafe;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Logger for Trident, automatically obtains the correct logger for the class
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
@ThreadSafe
@Policy("Ensure initialization ASAP in main class")
public final class TridentLogger extends ForwardingCollection<TridentLogger> implements Registry<TridentLogger> {
    private static final Map<String, TridentLogger> LOGGERS = new ConcurrentHashMap<>();

    @InternalUseOnly
    public static void init(Level level) {
        ConsoleAppender console = new ConsoleAppender(); //create appender
        console.setLayout(new PatternLayout("%d{dd MMM HH:mm} [%p] %m%n"));
        console.setThreshold(level);
        console.activateOptions();
        console.setWriter(new Writer() {
            BufferedWriter writer = new BufferedWriter(new PrintWriter(System.out));

            @Override
            public void write(char[] cbuf, int off, int len) throws IOException {
                writer.write(("\r" + new String(cbuf)).toCharArray(), off, len + 1);
                writer.write("$ ".toCharArray());
            }

            @Override
            public void flush() throws IOException {
                writer.flush();
            }

            @Override
            public void close() throws IOException {
                writer.close();
            }
        });

        Logger.getRootLogger().addAppender(console);

        // We will copy the current log to the logs directory to prevent the file
        // from destroying the text editor if it needs to be opened after a week
        // of continuous errors
        Path logs = Trident.fileContainer().resolve("logs");
        Path path = Trident.fileContainer().resolve("trident.log");
        try {
            if (Files.exists(path)) {
                if (!Files.exists(logs))
                    Files.createDirectory(logs);

                // Half a gigabyte
                if (Files.size(path) >= 2_560_000) {
                    File[] list = logs.toFile().listFiles();
                    copyLog(path, logs, list.length);

                    Files.delete(path);
                }
            }
        } catch (IOException e) {
            // Well we can't really do anything about it :/
            e.printStackTrace();
        }

        FileAppender fa = new FileAppender();

        fa.setName("FileLogger");
        fa.setFile("trident.log");
        fa.setLayout(new PatternLayout("%d{dd MMM HH:mm} [%p][%c{1}][%t] %m%n"));
        fa.setThreshold(Level.DEBUG);
        fa.setAppend(true);
        fa.activateOptions();
        try {
            fa.setWriter(new Writer() {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fa.getFile()));

                @Override
                public void write(char[] cbuf, int off, int len) throws IOException {
                    String s = new String(cbuf, off, len);
                    String[] ss = s.split("\n");                   // Prevents losing newlines
                    for (int i = 0; i < ss.length; i++) {
                        String sss = ss[i];
                        ss[i] = sss.replaceAll("\\P{Print}", "")   // Replaces Unicode escapes
                                .replaceAll("\\[[\\d]{1,2}m", ""); // Replaces colors
                    }
                    StringBuilder finalS = new StringBuilder();
                    for (String sss : ss) {
                        finalS.append(sss).append("\n");
                    }
                    s = finalS.toString();

                    writer.write(s.toCharArray());
                }

                @Override
                public void flush() throws IOException {
                    writer.flush();
                }

                @Override
                public void close() throws IOException {
                    writer.close();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        Logger.getRootLogger().addAppender(fa);
    }

    private static void copyLog(Path original, Path directory, int index) throws IOException {
        Path newPath = directory.resolve("old." + index + ".log");
        Files.copy(original, newPath);
    }

    private final LoggerDelegate logger;

    private TridentLogger(String name) {
        this.logger = new LoggerDelegate(LoggerFactory.getLogger(name));
    }

    /**
     * Obtains the internal underlying representation of the logger
     *
     * @return the SLF4J raw logger
     */
    public LoggerDelegate internal() {
        return this.logger;
    }

    @Override
    protected Collection<TridentLogger> delegate() {
        return ImmutableList.copyOf(LOGGERS.values());
    }

    /**
     * Obtains the logger for the class that calls this method
     *
     * @return the logger for that class
     */
    public static TridentLogger get() {
        return get(Trident.findCaller(3));
    }

    /**
     * Obtains the logger for the class that is specified
     *
     * @param cls the specified class
     * @return the logger for that class
     */
    public static TridentLogger get(Class<?> cls) {
        return get(cls.getName());
    }

    /**
     * Wraps the logger with a TridentLogger instance
     *
     * @param logger the logger to wrap
     * @return the cached, or a new instance of a logger wrapper
     */
    public static TridentLogger get(org.slf4j.Logger logger) {
        return get(logger.getName());
    }

    /**
     * Obtains a logger, creating a new one if it does not exist in the logger registry
     *
     * @param name the logger name
     * @return the new logger
     */
    public static TridentLogger get(String name) {
        return LOGGERS.computeIfAbsent(name, k -> new TridentLogger(name));
    }

    /**
     * Logs a message to the class logger
     *
     * @param item the item to log
     */
    public void log(String item) {
        internal().info(item + Console.RESET);
    }

    /**
     * Logs debug to the logger output
     *
     * @param message the message to log
     */
    public void debug(String message) {
        internal().debug(message);
    }

    /**
     * Logs an error message to the class logger with a red escape
     *
     * @param message the message to log
     */
    public void error(String message) {
        internal().error(Console.RED + message + Console.RESET);
    }

    /**
     * Warns the console with a yellow escape
     *
     * @param item the item to log
     */
    public void warn(String item) {
        internal().warn(Console.YELLOW + item + Console.RESET);
    }

    /**
     * Logs to the logger with a green escape
     *
     * @param item the item to log
     */
    public void success(String item) {
        log(Console.GREEN + item);
    }

    /**
     * Formats a throwable to be logged to the console
     *
     * <p>By default this is not needed as the concurrent which runs almost all operations by default have a set
     * uncaught exception handler</p>
     *
     * @param throwable the error to log
     */
    public void error(Throwable throwable) {
        internal().error(throwable);
    }
}