package net.tridentsdk.effect.sound;

import net.tridentsdk.effect.RemoteEffect;

/**
 * Represents a sound effect
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface SoundEffect extends RemoteEffect<SoundEffectType> {

    /**
     * Set the volume of the sound
     *
     * @param volume The volume of the sound (1 is 100%, can be higher)
     */
    void setVolume(float volume);

    /**
     * Set the pitch of the sound
     *
     * @param pitch The pitch of the sound (63 is 100%, can be higher)
     */
    void setPitch(byte pitch);

    /**
     * Returns the volume of the sound
     *
     * @return The volume of the sound
     */
    float volume();

    /**
     * Returns the pitch of the sound
     *
     * @return The pitch of the sound
     */
    byte pitch();

}
