package net.tridentsdk.world.change;

import net.tridentsdk.Coordinates;
import net.tridentsdk.base.Substance;

/**
 * Change large amounts of blocks at one time
 * <p/>
 * <p>Normally, block changes cause changes to immediately be sent to the client
 * which can be expensive with large amounts of changes being sent at once near a lot of players</p>
 */
public interface MassChange {


    /**
     * Add a change to make at when this change is committed
     *
     * @param x  the x of the block to set
     * @param y  the y of the block to set
     * @param z  the z of the block to set
     * @param id the block id to change the block to
     * @throws java.lang.IllegalStateException if this change has already been committed
     */
    public void setBlock(int x, int y, int z, int id) throws IllegalStateException;

    /**
     * Add a change to make at when this change is committed
     *
     * @param x         the x of the block to set
     * @param y         the y of the block to set
     * @param z         the z of the block to set
     * @param substance the substance to change the block to
     * @throws java.lang.IllegalStateException if this change has already been committed
     */
    public void setBlock(int x, int y, int z, Substance substance) throws IllegalStateException;

    /**
     * Add a change to make at when this change is committed
     *
     * @param x    the x of the block to set
     * @param y    the y of the block to set
     * @param z    the z of the block to set
     * @param id   the block id to change the block to
     * @param data the data value to give the new block
     * @throws java.lang.IllegalStateException if this change has already been committed
     */
    public void setBlock(int x, int y, int z, int id, byte data) throws IllegalStateException;

    /**
     * Add a change to make at when this change is committed
     *
     * @param x         the x of the block to set
     * @param y         the y of the block to set
     * @param z         the z of the block to set
     * @param substance the substance to change the block to
     * @param data      the data value to give the new block
     * @throws java.lang.IllegalStateException
     */
    public void setBlock(int x, int y, int z, Substance substance, byte data)
            throws IllegalStateException;

    /**
     * Add a change to make at when this change is committed
     *
     * @param coords the location to set, values will be rounded
     * @param id     the id to change the block to
     * @throws IllegalArgumentException if coords are not from the same world as this change
     * @throws IllegalStateException    if this change has already been committed
     */
    public void setBlock(Coordinates coords, int id)
            throws IllegalArgumentException, IllegalStateException;

    /**
     * Add a change to make at when this change is committed
     *
     * @param coords the location to set, values will be rounded
     * @param id     the id to change the block to
     * @param data   the data value to change the block to
     * @throws IllegalArgumentException if coords are not from the same world as this change
     * @throws IllegalStateException    if this change has already been committed
     */
    public void setBlock(Coordinates coords, int id, byte data)
            throws IllegalArgumentException, IllegalStateException;

    /**
     * Add a change to make at when this change is committed
     *
     * @param coords    the location to set, values will be rounded
     * @param substance the substance to change the block to
     * @throws IllegalArgumentException if coords are not from the same world as this change
     * @throws IllegalStateException    if this change has already been committed
     */
    public void setBlock(Coordinates coords, Substance substance)
            throws IllegalArgumentException, IllegalStateException;

    /**
     * Add a change to make at when this change is committed
     *
     * @param coords    the location to set, values will be rounded
     * @param substance the id to change the block to
     * @param data      the data value to change the block to
     * @throws IllegalArgumentException if coords are not from the same world as this change
     * @throws IllegalStateException    if this change has already been committed
     */
    public void setBlock(Coordinates coords, Substance substance, byte data)
            throws IllegalArgumentException, IllegalStateException;

    /**
     * Sends the block changes to the clients and does lighting recalculations
     * <p/>
     * <p>This change will be rendered useless after this method is called, and should be discarded</p>
     *
     * @return Whether or not the changes were successful.
     * @throws java.lang.IllegalStateException if this change has already been committed
     */
    public boolean commitChanges() throws IllegalStateException;
}
