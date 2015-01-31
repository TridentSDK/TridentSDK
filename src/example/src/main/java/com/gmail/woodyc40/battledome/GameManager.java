package com.gmail.woodyc40.battledome;

import com.google.common.collect.Maps;
import net.tridentsdk.config.ConfigSection;
import net.tridentsdk.config.JsonConfig;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.factory.Factories;
import net.tridentsdk.plugin.TridentPlugin;
import net.tridentsdk.util.WeakEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * Manages arenas in BattleDone
 *
 * @author Pierre C
 */
public class GameManager {
    // TODO plugin storage config way to verbose
    // TODO find values in a config/section

    public static final String SNAP_SHOTS = "snapshots";
    private static final String STORED_COUNT = "counter";
    private static final Map<Integer, Game> GAMES = Maps.newHashMap();
    private static final Map<WeakEntity<Player>, PlayerSnapshot> SNAPSHOTS = Maps.newHashMap();
    private static final JsonConfig storage = Factories.configs().
            createConfig(TridentPlugin.instance(BattleDome.class).configDirectory() + "games.json");
    private static int counter = 0;

    private GameManager() {
    }

    public static GameManager newHandler() {
        return new GameManager();
    }

    ////////////////////////////////////////////////// GAME MANAGEMENT /////////////////////////////////////////////////

    public Game createGame() {
        int gameId = findNextId();

        Game game = Game.newGame(gameId);
        GAMES.put(gameId, game);
        return game;
    }

    public void removeGame(int gameId) {
        GAMES.remove(gameId);
        storage.remove(String.valueOf(gameId));
    }

    @Nullable
    public Game findGame(int gameId) {
        return GAMES.get(gameId);
    }

    public boolean containsGame(int gameId) {
        return GAMES.containsKey(gameId);
    }

    private int findNextId() {
        int gameId = -1;
        // Try to find any existing arenas that were removed
        for (int i = 0; i <= counter; i++) {
            if (!containsGame(i))
                gameId = i;
        }

        // If none were found, expand the counter
        if (gameId == -1) {
            gameId = counter++;
        }

        return gameId;
    }

    /////////////////////////////////// SERIALIZATION/DESERIALIZATION ////////////////////////////////////////////////

    public void loadGames() {
        // Restore the counter
        counter = storage.getInt(STORED_COUNT);

        // Load each arena ID
        for (int i = 0; i <= counter; i++) {
            // Once we reach the tail, it becomes null
            String tag = String.valueOf(i);
            if (!storage.contains(tag))
                break;

            GAMES.put(i, Game.newGame(i, storage.getObject(tag, Game.class)));
        }
    }

    public void save() {
        // Write the counter
        storage.setInt(STORED_COUNT, counter);

        // Write each game
        for (Game game : GAMES.values()) {
            storage.setObject(String.valueOf(game.id()), game);
        }

        ConfigSection section = storage.getConfigSection(SNAP_SHOTS);
        for (PlayerSnapshot snapshot : SNAPSHOTS.values()) {
            section.setObject(snapshot.uuid().toString(), snapshot);
        }

        storage.save();
    }

    ////////////////////////////////////////////////// JOIN/LEAVE MECH /////////////////////////////////////////////////

    public void sendPlayer(Player player, Game game) {
        SNAPSHOTS.put(WeakEntity.orEmpty(player), PlayerSnapshot.take(player, game.id()));
        game.join(player);
    }

    public Game removePlayer(Player player) {
        PlayerSnapshot snapshot = SNAPSHOTS.remove(WeakEntity.finderOf(player));
        if (snapshot == null)
            return null;

        Game game = findGame(snapshot.gameId());

        if (game == null)
            return null;

        // Clear inventory
        snapshot.restore(player);
        game.remove(player);
        return game;
    }

    @Nullable
    public Game findGame(Player player) {
        PlayerSnapshot snapshot = SNAPSHOTS.get(WeakEntity.finderOf(player));
        if (snapshot == null)
            return null;
        return GAMES.get(snapshot.gameId());
    }

    public boolean isPlaying(Player player) {
        return SNAPSHOTS.containsKey(WeakEntity.finderOf(player));
    }
}
