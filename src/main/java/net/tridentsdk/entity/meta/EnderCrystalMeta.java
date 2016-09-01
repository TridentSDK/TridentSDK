package net.tridentsdk.entity.meta;

import net.tridentsdk.base.Vector;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface EnderCrystalMeta extends EntityMeta {

    Vector getBeamTarget();

    void setBeamTarget(Vector beamTarget);

    boolean isShowBottom();

    void setShowBottom(boolean showBottom);

}
