package net.tridentsdk.entity.meta;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface BoatMeta extends EntityMeta {

    int getTimeSinceLastHit();

    void setTimeSinceLastHit(int timeSinceLastHit);

    int getForwardDirection();

    void setForwardDirection(int forwardDirection);

    float getDamageTaken();

    void setDamageTaken(float damageTaken);

    int getBoatType();

    void setBoatType(int boatType);

    boolean isLeftPaddleTurning();

    void setLeftPaddleTurning(boolean leftPaddleTurning);

    boolean isRightPaddleTurning();

    void setRightPaddleTurning(boolean rightPaddleTurning);

}
