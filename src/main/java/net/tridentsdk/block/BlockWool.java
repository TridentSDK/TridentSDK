package net.tridentsdk.block;

import net.tridentsdk.meta.block.BlockMeta;
import net.tridentsdk.meta.block.BlockMetaOwner;
import net.tridentsdk.meta.block.WoolMeta;
import net.tridentsdk.meta.component.MetaCollection;
import net.tridentsdk.meta.component.MetaFactory;

/**
 * Represents a block of wool which has meta access
 *
 * @author The TridentSDK Team
 */
public class BlockWool implements BlockMetaOwner<BlockWool> {
    private final MetaCollection<BlockWool> collection = MetaFactory.newCollection();

    @Override
    public <T extends BlockMeta<BlockWool>> T valueOf(Class<T> cls) {
        return null;
    }

    @Override
    public <M extends BlockMeta<BlockWool>> M getOrMake(Class<M> cls) {
        return null;
    }

    @Override
    public <M extends BlockMeta<BlockWool>> void commit(M meta) {

    }

    public static void main(String[] args) {
        BlockWool wool = new BlockWool();
        WoolMeta meta = wool.valueOf(WoolMeta.class);
    }
}
