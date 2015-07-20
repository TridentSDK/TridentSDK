package net.tridentsdk.world.settings;

import java.util.Set;

/**
 * Represents the settings of the world, such as the level type, gamemode, etc...
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface WorldSettings {
    /**
     * Gets the dimension of a world
     *
     * @return The dimension of a world
     */
    Dimension dimension();

    /**
     * Gets the difficulty set in a world
     *
     * @return The difficulty set in a world
     */
    Difficulty difficulty();

    /**
     * Sets the world difficulty
     *
     * @param difficulty the difficulty
     */
    void setDifficulty(Difficulty difficulty);

    /**
     * Gets the default gamemode in a given chunk
     *
     * @return The default gamemode in a given chunk
     */
    GameMode defaultGameMode();

    /**
     * Sets the world game mode
     *
     * @param gameMode the world gamemode
     */
    void setGameMode(GameMode gameMode);

    /**
     * Gets the type of a world
     *
     * @return The type of a world
     */
    LevelType levelType();

    /**
     * Gets the set boolean for the given gamerule
     *
     * @return The set boolean for the given gamerule
     */
    boolean isRule(String rule);

    /**
     * Obtains a list of the game rules applied to this world
     *
     * @return the world's game rules
     */
    Set<String> gameRules();

    /**
     * Checks if structures are generated in a world (Stronghold, villages, dungeons)
     *
     * @return True if structures are generated in a world (Stronghold, villages, dungeons)
     */
    boolean generateStructures();
}
