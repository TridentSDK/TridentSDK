/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
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

import javax.annotation.concurrent.Immutable;
import java.time.ZonedDateTime;

/**
 * This class represents a message sent to the logger that
 * passes through the logging framework.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Immutable
public interface LogMessage {
    /**
     * Obtains the source logger, or the logger which was
     * used to issue the log message to the framework.
     *
     * @return the source logger
     */
    Logger getLogger();

    /**
     * Additional info pertaining to the log message that
     * will be appended when it is printed.
     *
     * @return the additional info
     */
    String[] getComponents();

    /**
     * The actual message to be logged.
     *
     * @return the message
     */
    String getMessage();

    /**
     * Obtains the time at which this message was created.
     *
     * @return the time
     */
    ZonedDateTime getTime();
}
