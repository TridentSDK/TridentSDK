package net.tridentsdk.entity;

/**
 * Represents various settings of pace of which the player can move at
 *
 * @author The TridentSDK Team
 */
public interface PlayerSpeed {
    /**
     * Returns the flying speed of the Player
     *
     * @return float flying speed of the Player
     */
    float flyingSpeed();

    /**
     * Set the flying speed of the Player
     *
     * @param flyingSpeed float flying speed of the Player
     */
    void setFlyingSpeed(float flyingSpeed);

    /**
     * Returns the sneak speed of the player
     *
     * @return float the Player's current sneak speed
     */
    float sneakSpeed();

    /**
     * Sets the Player's sneak speed
     *
     * @param speed float Player's sneak speed
     */
    void setSneakSpeed(float speed);

    /**
     * Returns the walk speed of the player
     *
     * @return float the Player's current walk speed
     */
    float walkSpeed();

    /**
     * Sets the Player's walk speed
     *
     * @param speed float Player's walk speed
     */
    void setWalkSpeed(float speed);
}
