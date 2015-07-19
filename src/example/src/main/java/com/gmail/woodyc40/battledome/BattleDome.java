package com.gmail.woodyc40.battledome;

import net.tridentsdk.plugin.Plugin;
import net.tridentsdk.plugin.annotation.Desc;

/**
 * Battledome main class
 *
 * @author Pierre C
 */
@Desc(name = "BattleDome", author = "Pierre C")
public class BattleDome extends Plugin {
    @Override
    public void enable() {
        GameManager manager = GameManager.instance();
        manager.loadGames();
    }

    @Override
    public void disable() {
        GameManager manager = GameManager.instance();
        manager.save();
    }
}
