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
    boolean hasPerm(String perm);

    /**
     * Adds the given permission to this holder's collection
     * of permissions.
     *
     * @param perm the permission to add
     */
    void addPerm(String perm);

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
    boolean removePerm(String perm);

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