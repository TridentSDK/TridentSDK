package net.tridentsdk.entity.meta.living;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
// TODO - documentation
public interface PlayerMeta extends LivingEntityMeta {

    float getAdditionalHearts();

    void setAdditionalHearts(float hearts);

    int getScore();

    void setScore(int score);

    byte getSkinFlags();

    void setSkinFlags(byte skinFlags);

    boolean isCapeEnabled();

    void setCapeEnabled(boolean enabled);

    boolean isJacketEnabled();

    void setJacketEnabled(boolean enabled);

    boolean isLeftSleeveEnabled();

    void setLeftSleeveEnabled(boolean enabled);

    boolean isRightSleeveEnabled();

    void setRightSleeveEnabled(boolean enabled);

    boolean isLeftLegPantsEnabled();

    void setLeftLegPantsEnabled(boolean enabled);

    boolean isRightLegPantsEnabled();

    void setRightLegPantsEnabled(boolean enabled);

    boolean isHatEnabled();

    void setHatEnabled(boolean enabled);

    boolean isLeftHandMain();

    void setLeftHandMain(boolean main);

}
