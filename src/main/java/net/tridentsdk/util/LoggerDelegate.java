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
import net.tridentsdk.Trident;
import net.tridentsdk.meta.ChatColor;
import net.tridentsdk.plugin.Plugin;
import net.tridentsdk.registry.Registered;
import org.slf4j.Logger;
import org.slf4j.Marker;

import javax.annotation.Nullable;
import java.util.Arrays;

/**
 * Represents a delegated logger
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public class LoggerDelegate implements Logger {
    private static final String[] ERRORS = {
            "Aw, Mazen! Really?",
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
            "Hey look, we got another one!",
            "And this is the point where I give up",
            "Indeed, there seems to be an error, m'lord!",
            "I don't know how this happened, I thought black holes were sefe!",
            "I promise* that this won't happen again! * Please read license agreement",
            "I pronounce you owner and error, you may now report me.",
            "So class, what have we learned today?",
            "You saw nothing!",
            "Quick hide! Did anyone see us?",
    };
    private final Logger logger;
    
    public LoggerDelegate(Logger logger) {
        this.logger = logger;
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
                        builder.append(parse(s));

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
     * Formats the throwable
     *
     * @param throwable the throwable to format
     */
    public void error(Throwable throwable) {
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
        logger.error("======== Print Debug Information =========");
        StackTraceElement main = stackTrace[0];
        logger.error("Class:  " + main.getClassName());
        logger.error("Method: " + main.getMethodName());
        logger.error("Line:   " + (main.getLineNumber() > 0 ? main.getLineNumber() : "Native method"));
        logger.error("======== End Debug Information =========");
        logger.error("");
        logger.error("======== Print Stacktrace =========");
        for (StackTraceElement element : stackTrace) {
            String className = element.getClassName();
            if (className.contains("io.netty")) break;

            logger.error("    at " + className + "." +
                    element.getMethodName() + "(...) : " +
                    (!element.isNativeMethod() ? element.getLineNumber() : "Native method"));
        }
        logger.error("======== End Stacktrace =========");
        logger.error("");
        logger.error("======== Print Server info =========");
        logger.error("Trident version: " + Trident.version());
        logger.error("Plugins:         " + Arrays.toString(
                Collections2.transform(Registered.plugins(), new Function<Plugin, String>() {
                    @Nullable @Override
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
        logger.error("======== End Server info =========");
        logger.error("");
        logger.error("========  END ERROR  =========");
    }
    
    ///////////////////////////////////////// DELEGATE METHODS /////////////////////////////////////////

    @Override
    public String getName() {
        return logger.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public void trace(String s) {
        logger.trace(parse(s));
    }

    @Override
    public void trace(String s, Object o) {
        logger.trace(parse(s), o);
    }

    @Override
    public void trace(String s, Object o, Object o1) {
        logger.trace(parse(s), o, o1);
    }

    @Override
    public void trace(String s, Object... objects) {
        logger.trace(parse(s), objects);
    }

    @Override
    public void trace(String s, Throwable throwable) {
        logger.trace(parse(s), throwable);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return logger.isTraceEnabled(marker);
    }

    @Override
    public void trace(Marker marker, String s) {
        logger.trace(marker, parse(s));
    }

    @Override
    public void trace(Marker marker, String s, Object o) {
        logger.trace(marker, parse(s));
    }

    @Override
    public void trace(Marker marker, String s, Object o, Object o1) {
        logger.trace(marker, parse(s), o, o1);
    }

    @Override
    public void trace(Marker marker, String s, Object... objects) {
        logger.trace(marker, parse(s), objects);
    }

    @Override
    public void trace(Marker marker, String s, Throwable throwable) {
        logger.trace(marker, parse(s), throwable);
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public void debug(String s) {
        logger.debug(parse(s));
    }

    @Override
    public void debug(String s, Object o) {
        logger.debug(parse(s), o);
    }

    @Override
    public void debug(String s, Object o, Object o1) {
        logger.debug(parse(s), o);
    }

    @Override
    public void debug(String s, Object... objects) {
        logger.debug(parse(s), objects);
    }

    @Override
    public void debug(String s, Throwable throwable) {
        logger.debug(parse(s), throwable);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return logger.isDebugEnabled(marker);
    }

    @Override
    public void debug(Marker marker, String s) {
        logger.debug(marker, parse(s));
    }

    @Override
    public void debug(Marker marker, String s, Object o) {
        logger.debug(marker, parse(s), o);
    }

    @Override
    public void debug(Marker marker, String s, Object o, Object o1) {
        logger.debug(marker, parse(s), o, o1);
    }

    @Override
    public void debug(Marker marker, String s, Object... objects) {
        logger.debug(marker, parse(s), objects);
    }

    @Override
    public void debug(Marker marker, String s, Throwable throwable) {
        logger.debug(marker, parse(s), throwable);
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    public void info(String s) {
        logger.info(parse(s));
    }

    @Override
    public void info(String s, Object o) {
        logger.info(parse(s), o);
    }

    @Override
    public void info(String s, Object o, Object o1) {
        logger.info(parse(s), o, o1);
    }

    @Override
    public void info(String s, Object... objects) {
        logger.info(parse(s), objects);
    }

    @Override
    public void info(String s, Throwable throwable) {
        logger.info(parse(s), throwable);
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return logger.isInfoEnabled(marker);
    }

    @Override
    public void info(Marker marker, String s) {
        logger.info(marker, parse(s));
    }

    @Override
    public void info(Marker marker, String s, Object o) {
        logger.info(marker, parse(s), o);
    }

    @Override
    public void info(Marker marker, String s, Object o, Object o1) {
        logger.info(marker, parse(s), o, o1);
    }

    @Override
    public void info(Marker marker, String s, Object... objects) {
        logger.info(marker, parse(s), objects);
    }

    @Override
    public void info(Marker marker, String s, Throwable throwable) {
        logger.info(marker, parse(s), throwable);
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    @Override
    public void warn(String s) {
        logger.warn(parse(s));
    }

    @Override
    public void warn(String s, Object o) {
        logger.warn(parse(s), o);
    }

    @Override
    public void warn(String s, Object... objects) {
        logger.warn(parse(s), objects);
    }

    @Override
    public void warn(String s, Object o, Object o1) {
        logger.warn(parse(s), o, o1);
    }

    @Override
    public void warn(String s, Throwable throwable) {
        logger.warn(parse(s), throwable);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return logger.isWarnEnabled();
    }

    @Override
    public void warn(Marker marker, String s) {
        logger.warn(marker, parse(s));
    }

    @Override
    public void warn(Marker marker, String s, Object o) {
        logger.warn(marker, parse(s), o);
    }

    @Override
    public void warn(Marker marker, String s, Object o, Object o1) {
        logger.warn(marker, parse(s), o, o1);
    }

    @Override
    public void warn(Marker marker, String s, Object... objects) {
        logger.warn(marker, parse(s), objects);
    }

    @Override
    public void warn(Marker marker, String s, Throwable throwable) {
        logger.warn(marker, parse(s), throwable);
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    @Override
    public void error(String s) {
        logger.error(parse(s));
    }

    @Override
    public void error(String s, Object o) {
        logger.error(parse(s), o);
    }

    @Override
    public void error(String s, Object o, Object o1) {
        logger.error(parse(s), o, o1);
    }

    @Override
    public void error(String s, Object... objects) {
        logger.error(parse(s), objects);
    }

    @Override
    public void error(String s, Throwable throwable) {
        logger.error(parse(s), throwable);
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return logger.isErrorEnabled(marker);
    }

    @Override
    public void error(Marker marker, String s) {
        logger.error(marker, parse(s));
    }

    @Override
    public void error(Marker marker, String s, Object o) {
        logger.error(marker, parse(s), o);
    }

    @Override
    public void error(Marker marker, String s, Object o, Object o1) {
        logger.error(marker, parse(s), o, o1);
    }

    @Override
    public void error(Marker marker, String s, Object... objects) {
        logger.error(marker, parse(s), objects);
    }

    @Override
    public void error(Marker marker, String s, Throwable throwable) {
        logger.error(marker, parse(s), throwable);
    }
}
