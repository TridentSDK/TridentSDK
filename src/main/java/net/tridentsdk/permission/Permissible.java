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
package net.tridentsdk.permission;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Represents an object that is capable of holding
 * permission strings, which allow certain actions that
 * are "permissible" (to be checked by the appropriate
 * listener).
 *
 * @author TridentSDK
 * @since 0.4-alpha
 */
@ThreadSafe
public interface Permissible {
    /**
     * Whether or not the permissible object holds the given
     * permission string.
     *
     * @param perm the permission to check
     * @return {@code true} if the holder does have the
     * given permission
     */
    boolean hasPermission(String perm);

    /**
     * Adds the given permission to this holder's collection
     * of permissions.
     *
     * @param perm the permission to add
     */
    void addPermission(String perm);

    /**
     * Removes the permission from this holder's collection
     * of permissions.
     *
     * @param perm the permission to remove
     * @return {@code true} if the removal had the effect of
     * removing the permission, otherwise {@code false} if
     * it failed or it was not contained in the holder's
     * collection
     */
    boolean removePermission(String perm);

    /**
     * Sets whether or not the permissible is an operator,
     * or otherwise holds all permissions.
     *
     * @param op {@code true} to set to an operator,
     * {@code false} to set to a non-operator.
     */
    void setOp(boolean op);

    /**
     * Checks to see whether or not this permissible object
     * is set to an operator of the server.
     *
     * @return {@code true} if this permissible object is an
     * operator, {@code false} if it is not
     */
    boolean isOp();
}
