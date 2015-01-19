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

package net.tridentsdk.plugin.cmd;

import net.tridentsdk.Messagable;

/**
 * Represents a console which runs the server, can be sent messages and execute commands
 *
 * <p>Includes hardcoded ANSI escapes for console colors</p>
 *
 * @author The TridentSDK Team
 */
public interface ServerConsole extends Messagable, CommandIssuer {
    public static final String RESET = PlatformColor.forColor("reset");
    public static final String BLACK = PlatformColor.forColor("black");
    public static final String RED = PlatformColor.forColor("red");
    public static final String GREEN = PlatformColor.forColor("green");
    public static final String YELLOW = PlatformColor.forColor("yellow");
    public static final String BLUE = PlatformColor.forColor("blue");
    public static final String PURPLE = PlatformColor.forColor("purple");
    public static final String CYAN = PlatformColor.forColor("cyan");
    public static final String WHITE = PlatformColor.forColor("white");
}
