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

package net.tridentsdk.permission;

/**
 * An interface describing an object that can be a server operator.
 * Typical examples include Players and the Console.
 * 
 * @author Nick Robson
 */
public interface ServerOperator {

    /**
     * Gets whether or not this user has operator permissions, will be true for console and players that are
     * operators.
     *
     * @return If this user is an operator or the console
     */
    boolean isOperator();
    
    /**
     * [[HAZARD]]
     * 
     * Sets whether or not this user is an operator.
     * @param op Whether or not they should be.
     */
    void setOperator(boolean op);

}
