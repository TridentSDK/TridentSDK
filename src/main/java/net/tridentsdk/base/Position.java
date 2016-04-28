package net.tridentsdk.base;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Position is a container class that represents a three-
 * dimensional coordinate within a volume grid.
 *
 * <p>This container also may hold a yaw and pitch values
 * for use with rotatable objects such as entities.</p>
 *
 * <p>Consideration for this class to be immutable was
 * dismissed as being significantly too costly. However,
 * methods that perform atomic operations will
 * <strong>ALWAYS</strong> return a new instance in order
 * to preserve thread safety. These methods are documented
 * below.</p>
 */
@ThreadSafe
public final class Position {
    // Unpadded 3 coordinate values
    private volatile double x;
    private volatile double y;
    private volatile double z;

    // private volatile long

    private volatile double pitch;
    private volatile double yaw;

    // TODO
}
