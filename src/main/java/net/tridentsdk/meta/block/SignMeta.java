package net.tridentsdk.meta.block;

import net.tridentsdk.base.Block;

/**
 * Represents metadata held by a sign
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface SignMeta extends BlockMeta<Block> {
    /**
     * Obtains the text at the given index [0-3]
     *
     * @param index the index
     * @return the text
     */
    String textAt(int index);

    /**
     * Sets the text at the index [0-3] to the provided text
     *
     * @param index the index
     * @param text  the text
     */
    void setTextAt(int index, String text);
}
