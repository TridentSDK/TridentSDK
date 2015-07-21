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
package net.tridentsdk.service;

/**
 * Represents an object which holds tags called permissions
 *
 * <p>The granting of permissions does not necessarily reflect an immediate action, rather it is a condition variable
 * which is invoked by the handler for that particular permission.</p>
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public interface PermissionHolder {
    /**
     * Offers the permission holder a permission which they can hold
     *
     * <p>This has no effect if the holder already has the given permission</p>
     *
     * @param perm the new permission the holder should hold
     */
    void grantPermission(String perm);

    /**
     * Removes the permission tag from the holder such that the next invocation of {@link #holdsPermission(String)}
     * returns {@code false}
     *
     * @param perm the permission to remove
     */
    void revokePermission(String perm);

    /**
     * Observes the holder to see if it holds the specified permission
     *
     * @param perm the permission to check if held
     * @return {@code true} if the permission is held, {@code false} if it is not
     */
    boolean holdsPermission(String perm);

    /**
     * Checks if the permission holder has operator status
     *
     * @return {@code true} to indicate that the holder is an operator
     */
    boolean opped();
}