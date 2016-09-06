package net.tridentsdk.ui.bossbar;

import net.tridentsdk.Impl;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface BossBarManager {

    /**
     * Get the singleton instance of the Boss Bar manager
     *
     * @return The singleton Boss Bar manager
     */
    static BossBarManager getInstance(){
        return Impl.get().getBossBarManager();
    }

    /**
     * Gets whether or not a given boss bar is the default one.
     *
     * @param bar The boss bar.
     *
     * @return True iff it is a default boss bar.
     */
    boolean isDefault(BossBar bar);

    /**
     * Get a new boss bar.
     *
     * @return A new boss bar.
     */
    BossBar newBossBar();

}
