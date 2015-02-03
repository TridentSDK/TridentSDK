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

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import net.tridentsdk.Trident;
import net.tridentsdk.docs.InternalUseOnly;
import net.tridentsdk.docs.Volatile;
import net.tridentsdk.meta.ChatColor;
import net.tridentsdk.plugin.TridentPlugin;
import net.tridentsdk.plugin.cmd.ServerConsole;
import org.apache.log4j.*;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * Logger for Trident, automatically obtains the correct logger for the class
 *
 * @author The TridentSDK Team
 */
@Volatile(policy = "Init FIRST", reason = "Requires SLF4J to be configured", fix = "first static block in main class")
public final class TridentLogger {
    private static final String[] ERRORS = { "Aw, Mazen! Really?", "I feel funny", "9 + 10 does not equal 21", "Dang", "Tony Abbot, the fax didn't go through", "This wasn't supposed to happen. It did anyways.", "Huston, we have a problem", "Oh great, a stacktrace. Can't we write good software for once?", "Trust me " + "this isn't a bug, it's a feature!" };

    private TridentLogger() {
    }

    @InternalUseOnly
    public static void init() {
        String PATTERN = "%d{dd MMM HH:mm} [%p] %m%n";

        ConsoleAppender console = new ConsoleAppender(); //create appender
        console.setLayout(new PatternLayout(PATTERN));
        console.setThreshold(Level.INFO);
        console.activateOptions();

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

                File[] list = logs.toFile().listFiles();
                copyLog(path, logs, list.length);

                Files.delete(path);
            }
        } catch (IOException e) {
            // Well we can't really do anything about it :/
            e.printStackTrace();
        }

        FileAppender fa = new FileAppender();

        fa.setName("FileLogger");
        fa.setFile("trident.log");
        fa.setLayout(new PatternLayout(PATTERN));
        fa.setThreshold(Level.DEBUG);
        fa.setAppend(true);
        fa.activateOptions();

        Logger.getRootLogger().addAppender(fa);
    }

    private static void copyLog(Path original, Path directory, int index) throws IOException {
        Path newPath = directory.resolve("old." + index + ".log");
        Files.copy(original, newPath);
    }

    /**
     * Obtains the logger for the class that calls this method
     *
     * @return the logger for that class
     */
    public static org.slf4j.Logger logger() {
        return LoggerFactory.getLogger(Trident.findCaller(4));
    }

    /**
     * Logs a message to the class logger
     *
     * @param item the item to log
     */
    public static void log(String item) {
        logger().info(parse(item) + ServerConsole.RESET);
    }

    /**
     * Logs an error message to the class logger with a red escape
     *
     * @param message the message to log
     */
    public static void error(String message) {
        logger().error(ServerConsole.RED + parse(message) + ServerConsole.RESET);
    }

    /**
     * Warns the console with a yellow escape
     *
     * @param item the item to log
     */
    public static void warn(String item) {
        logger().warn(ServerConsole.YELLOW + parse(item) + ServerConsole.RESET);
    }

    /**
     * Logs to the logger with a green escape
     *
     * @param item the item to log
     */
    public static void success(String item) {
        log(ServerConsole.GREEN + item);
    }

    private static String parse(String item) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < item.length(); i++) {
            char c = item.charAt(i);
            if (c == '§') {
                // Find the character after that
                char esc = item.charAt(i + 1);
                ChatColor color = ChatColor.forColor(esc);

                // Not a real color, continue on
                if (color == null) {
                    builder.append(c);
                } else {
                    String s = ChatColor.consoleFormat(color);

                    // Not a console color, append it anyways
                    if ("".equals(s)) {
                        builder.append(c);
                    } else {
                        // Append the console format instead of the chat color
                        builder.append(s);

                        // Skip the next character, don't even append it
                        i++;
                    }
                }
            } else {
                // No color escape, append normally
                builder.append(c);
            }
        }

        return builder.toString();
    }

    /**
     * Formats a throwable to be logged to the console
     *
     * <p>By default this is not needed as the threads which runs almost all operations by default have a set
     * uncaught exception handler</p>
     *
     * @param throwable the error to log
     */
    public static void error(Throwable throwable) {
        org.slf4j.Logger logger = logger();
        StackTraceElement[] stackTrace = throwable.getStackTrace();

        logger.error("========  BEGIN ERROR =========");

        logger.error("");
        String errorMessage = throwable.getMessage();

        if (errorMessage == null || errorMessage.equals("null")) {
            errorMessage = throwable.getClass().getSimpleName();
        }

        logger.error(ERRORS[(int) FastRandom.random(ERRORS.length - 1)]);
        logger.error("");
        logger.error("Error occurred in thread \"" + Thread.currentThread().getName() + "\": " + errorMessage);
        logger.error("");
        logger.error("======== Generating Debug Information =========");
        StackTraceElement main = stackTrace[0];
        logger.error("Class:  " + main.getClassName());
        logger.error("Method: " + main.getMethodName());
        logger.error("Line:   " + (main.getLineNumber() > 0 ? main.getLineNumber() : "Native method"));
        logger.error("========   Ending Debug Information   =========");

        logger.error("");

        logger.error("======== Printing Stacktrace =========");
        for (StackTraceElement element : stackTrace) {
            logger.error("    at " + element.getClassName() + "." +
                    element.getMethodName() + "(...) : " +
                    (!element.isNativeMethod() ? element.getLineNumber() : "Native method"));
        }
        logger.error("========  Ending Stacktrace  =========");

        logger.error("");

        logger.error("========     Server info    =========");
        logger.error("Trident version: " + Trident.version());
        logger.error("Plugins:         " + Arrays.toString(
                Lists.transform(Trident.pluginHandler().plugins(), new Function<TridentPlugin, String>() {
                    @Nullable
                    @Override
                    public String apply(TridentPlugin plugin) {
                        return plugin.description().name();
                    }
                }).toArray()));
        logger.error("Java:            version " + System.getProperty("java.version") + " distributed by " +
                System.getProperty("java.vendor"));
        logger.error("OS:              running " +
                System.getProperty("os.name") + " version " +
                System.getProperty("os.version") + " " +
                System.getProperty("os.arch"));
        logger.error("======== Ending Server info =========");

        logger.error("");

        logger.error("========  END ERROR  =========");
    }
}
