package net.tridentsdk.entity.meta.minecart;

import net.tridentsdk.entity.meta.EntityMeta;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
// TODO - documentation
public interface MinecartMeta extends EntityMeta {

    int getShakingPower();

    void setShakingPower(int shakingPower);

    int getShakingDirection();

    void setShakingDirection(int shakingDirection);

    float getShakingMultiplier();

    void setShakingMultiplier(boolean shakingMultiplier);

    int getMinecartBlockID();

    void setMinecartBlockID(int blockID);

    int getMinecartBlockData();

    void setMinecartBlockData(int blockData);

    int getMinecartBlockY();

    void setMinecartBlockY(int blockY);

    boolean isShowBlock();

    void setShowBlock(boolean showBlock);

}
