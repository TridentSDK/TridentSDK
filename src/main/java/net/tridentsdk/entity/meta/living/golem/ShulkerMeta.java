package net.tridentsdk.entity.meta.living.golem;

import net.tridentsdk.base.BlockDirection;
import net.tridentsdk.base.Vector;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
// TODO - documentation
public interface ShulkerMeta extends GolemMeta {

    BlockDirection getFacingDirection();

    void setFacingDirection(BlockDirection direction);

    Vector getAttachmentPosition();

    void setAttachmentPosition(Vector position);

    byte getShieldHeight();

    void setShieldHeight(byte height);

}
