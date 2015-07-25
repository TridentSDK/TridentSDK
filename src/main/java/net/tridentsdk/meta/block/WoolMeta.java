package net.tridentsdk.meta.block;

import net.tridentsdk.base.SubstanceColor;
import net.tridentsdk.block.BlockWool;

/**
 * Represents metadata belonging to blocks of wool
 *
 * @author The TridentSDK Team
 */
public interface WoolMeta extends BlockMeta<BlockWool> {
    /**
     * Sets the wool colour
     *
     * @param color the color to set the wool
     */
    void setColor(SubstanceColor color);

    /**
     * Obtains the current colouring of the wool
     *
     * @return the wool colour
     */
    SubstanceColor color();
}
