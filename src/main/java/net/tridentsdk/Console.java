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

import net.tridentsdk.plugin.cmd.CommandIssuer;
import net.tridentsdk.plugin.cmd.PlatformColor;

/**
 * Represents a console which runs the server, can be sent messages and execute commands
 *
 * <p>Includes hardcoded ANSI escapes for console colors</p>
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public interface Console extends Messagable, CommandIssuer {
    String RESET = PlatformColor.forColor("reset");
    String BLACK = PlatformColor.forColor("black");
    String RED = PlatformColor.forColor("red");
    String GREEN = PlatformColor.forColor("green");
    String YELLOW = PlatformColor.forColor("yellow");
    String BLUE = PlatformColor.forColor("blue");
    String PURPLE = PlatformColor.forColor("purple");
    String CYAN = PlatformColor.forColor("cyan");
    String WHITE = PlatformColor.forColor("white");
}