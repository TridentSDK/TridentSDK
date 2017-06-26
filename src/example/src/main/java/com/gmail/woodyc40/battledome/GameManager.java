package com.gmail.woodyc40.battledome;

import com.google.common.collect.Maps;
import net.tridentsdk.config.Config;
import net.tridentsdk.config.ConfigSection;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.plugin.Plugin;
import net.tridentsdk.util.WeakEntity;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Manages arenas in BattleDone
 *
 * @author Pierre C
 */
public class GameManager {
    public final String SNAP_SHOTS = "snapshots";
    private final String STORED_COUNT = "counter";
    private final Map<Integer, Game> GAMES = Maps.newHashMap();
    private final Map<WeakEntity<Player>, PlayerSnapshot> SNAPSHOTS = Maps.newHashMap();
    private final Config storage = new Config(Plugin.instance(BattleDome.class), "games.json");
    private int counter = 0;

    private static GameManager instance;

    private GameManager() {
    }

    public static GameManager instance() {
        GameManager manager = instance;
        if (manager == null) {
            manager = instance = new GameManager();
        }

        return manager;
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
        for (String key : storage.keys()) {
            int i = Integer.parseInt(key);

            GAMES.put(i, Game.newGame(i, storage.getObject(key, Game.class)));
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
        PlayerSnapshot snapshot = SNAPSHOTS.remove(WeakEntity.searchFor(player));
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
        PlayerSnapshot snapshot = SNAPSHOTS.get(WeakEntity.searchFor(player));
        if (snapshot == null)
            return null;
        return GAMES.get(snapshot.gameId());
    }

    public boolean isPlaying(Player player) {
        return SNAPSHOTS.containsKey(WeakEntity.searchFor(player));
    }
}
