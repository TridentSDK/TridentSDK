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
package net.tridentsdk.command;

import java.lang.annotation.*;

/**
 * Annotates methods which are command handlers.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cmd {
    /**
     * The name of the command that the method will handle.
     * No spaces allowed.
     *
     * @return the name
     */
    String name();

    /**
     * Command help, otherwise known as usage which displays
     * the arguments for the command.
     *
     * @return the help message
     */
    String help() default "No help for this command";

    /**
     * What the command does.
     *
     * @return the command description
     */
    String desc() default "What does this do?";
}