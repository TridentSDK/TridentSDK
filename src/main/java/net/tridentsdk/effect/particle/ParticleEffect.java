package net.tridentsdk.effect.particle;

import net.tridentsdk.effect.RemoteEffect;
import net.tridentsdk.util.Vector;

/**
 * Represents a particle effect
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface ParticleEffect extends RemoteEffect<ParticleEffectType> {

    /**
     * Set the count how many of the effects should be spawned
     *
     * @param count The cound how many should be spawned
     */
    void setCount(int count);

    /**
     * Set the particle to be long distance
     * Increases distance from 256 to 65536
     *
     * @param longDistance Whether the particle should be long distance
     */
    void setLongDistance(boolean longDistance);

    /**
     * Set the data of the particle
     * Only used for ICON_CRACK, BLOCK_CRACK and BLOCK_DUST
     * ICON_CRACK requires array of two integers
     * BLOCK_CRACK and BLOCK_DUST requires an array of a single integer
     *
     * @param data Data of the particle
     */
    void setData(int[] data);

    /**
     * The randomized offset for each particle
     * If multiple particles are spawned, this will be re-run for each
     *
     * @param offset The offset for each particle
     */
    void setOffset(Vector offset);

    /**
     * Returns the count of how many particles will be spawned
     *
     * @return The count of how many particles will be spawned
     */
    int count();

    /**
     * Returns whether the particle should be long distance
     *
     * @return Whether the particle should be long distance
     */
    boolean longDistance();

    /**
     * Returns the data of the particle
     *
     * @return The data of the particle
     */
    int[] data();

    /**
     * Returns the offset for each particle
     *
     * @return The offset for each particle
     */
    Vector offset();

}
