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
import com.google.common.collect.Collections2;
import com.google.common.collect.ForwardingCollection;
import com.google.common.collect.ImmutableList;
import net.tridentsdk.ServerConsole;
import net.tridentsdk.Trident;
import net.tridentsdk.docs.InternalUseOnly;
import net.tridentsdk.docs.Volatile;
import net.tridentsdk.meta.ChatColor;
import net.tridentsdk.plugin.Plugin;
import net.tridentsdk.registry.Registered;
import net.tridentsdk.registry.Registry;
import org.apache.log4j.*;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
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
@Volatile(policy = "Init FIRST", reason = "Requires SLF4J to be configured", fix = "first static block in main class")
public final class TridentLogger extends ForwardingCollection<TridentLogger> implements Registry<TridentLogger> {
    private static final String[] ERRORS = { "Aw, Mazen! Really?",
            "I feel funny",
            "9 + 10 does not equal 21",
            "Dang",
            "Tony Abbot, the fax didn't go through",
            "This wasn't supposed to happen. It did anyways.",
            "Houston, we have a problem",
            "Oh great, a stacktrace. Can't we write good software for once?",
            "Trust me this isn't a bug, it's a feature!",
            "Pierre pls",
            "Myth is extra salty today",
            "Budgie, your NBT... It's leaking...",
            "Vilsol is a Java developer? What?",
            "gg Tigur",
            "Orange? No, no, Trident is blue. Oh, Max?",
            "If anyone asks, you never saw me",
            "Hey look, we got another one!",
            "I feel... so... empty...",
            "I just can't even anymore",
            "And this is the point where I give up",
            "Indeed, there seems to be an error, m'lord!",
            "I don't know how this happened, I thought black holes were sefe!",
            "I promise* that this won't happen again! * Please read license agreement",
            "I pronounce you owner and error, you may now report me.",
            "This kinda feels funny...",
            "DONT LOOK AT ME, IM NAKED!",
            "So class, what have we learned today?",
            "I... I think I just... nevermind! You saw nothing!",
            "Quick hide! Did anyone see us?",
            "It wasn't me! It was him! *Points at Dinnerbone*"};
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

    private final org.slf4j.Logger logger;

    private TridentLogger(String name) {
        this.logger = LoggerFactory.getLogger(name);
    }

    /**
     * Obtains the internal underlying representation of the logger
     *
     * @return the SLF4J raw logger
     */
    public org.slf4j.Logger internal() {
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
        return get(Trident.findCaller(4));
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
    public static void log(String item) {
        get().internal().info(parse(item) + ServerConsole.RESET);
    }

    public void info(String item) {
        internal().info(parse(item) + ServerConsole.RESET);
    }

    /**
     * Logs debug to the logger output
     *
     * @param message the message to log
     */
    public static void debug(String message) {
        get().internal().debug(message);
    }

    /**
     * Logs an error message to the class logger with a red escape
     *
     * @param message the message to log
     */
    public static void error(String message) {
        get().internal().error(ServerConsole.RED + parse(message) + ServerConsole.RESET);
    }

    /**
     * Warns the console with a yellow escape
     *
     * @param item the item to log
     */
    public static void warn(String item) {
        get().internal().warn(ServerConsole.YELLOW + parse(item) + ServerConsole.RESET);
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
                ChatColor color = ChatColor.of(esc);

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
     * <p>By default this is not needed as the concurrent which runs almost all operations by default have a set
     * uncaught exception handler</p>
     *
     * @param throwable the error to log
     */
    public static void error(Throwable throwable) {
        org.slf4j.Logger logger = get().internal();
        StackTraceElement[] stackTrace = throwable.getStackTrace();

        logger.error("========  BEGIN ERROR =========");

        logger.error("");
        String errorMessage = throwable.getMessage();

        if (errorMessage == null || errorMessage.equals("null")) {
            errorMessage = throwable.getClass().getSimpleName();
        }

        logger.error(ERRORS[(int) FastRandom.random(ERRORS.length - 1)]);
        logger.error("");
        logger.error("Error occurred in thread \"" + Thread.currentThread().getName() + "\": ");
        logger.error(errorMessage);
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
            String className = element.getClassName();
            if (className.contains("io.netty")) break;

            logger.error("    at " + className + "." +
                    element.getMethodName() + "(...) : " +
                    (!element.isNativeMethod() ? element.getLineNumber() : "Native method"));
        }
        logger.error("========  Ending Stacktrace  =========");

        logger.error("");

        logger.error("========     Server info    =========");
        logger.error("Trident version: " + Trident.version());
        logger.error("Plugins:         " + Arrays.toString(
                Collections2.transform(Registered.plugins(), new Function<Plugin, String>() {
                    @Nullable
                    @Override
                    public String apply(Plugin plugin) {
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
