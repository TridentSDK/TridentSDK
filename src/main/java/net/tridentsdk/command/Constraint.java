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

/**
 * A constraint is a property of a command such as min. args
 * length, max. args length, source, etc...
 * 
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface Constraint {
    /**
     * Handles the command that has been dispatched.
     * 
     * @param cmd the command info
     * @param label the command that was sent
     * @param source the command source
     * @param args the arguments to the command
     * @param constraint the constraint to use
     * @return {@code true} if it passes the constraints,
     * {@code false} otherwise
     */
    boolean handle(Cmd cmd, String label, CmdSource source, String[] args, Object constraint);
}