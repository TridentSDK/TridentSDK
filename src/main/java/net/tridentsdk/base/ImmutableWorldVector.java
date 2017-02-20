package net.tridentsdk.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.tridentsdk.world.World;

import javax.annotation.concurrent.Immutable;

/**
 * This class represents an immutable copy of a Vector
 * object which is used to contain a constant set of values
 * that shouldn't change (i.e. the location of a block).
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Getter
@Immutable
@AllArgsConstructor
public class ImmutableWorldVector {
    private final World world;
    private final int x;
    private final int y;
    private final int z;
}