package com.gmail.woodyc40.battledome;

import com.google.common.collect.Maps;
import net.tridentsdk.Position;
import net.tridentsdk.concurrent.TridentRunnable;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.factory.Factories;
import net.tridentsdk.meta.ChatColor;
import net.tridentsdk.plugin.TridentPlugin;
import net.tridentsdk.util.WeakEntity;

import java.util.Map;

/**
 * An arena in BattleDome
 *
 * @author Pierre C
 */
public class Game {
    public final BattleTimer runnable = new BattleTimer(this);
    private final int id;
    private final Map<WeakEntity<Player>, Team> players;
    private GameState state;
    private Position spawn;
    private Position purpleTeam;
    private Position greenTeam;
    private Position purpleObby;
    private Position greenObby;
    private Team alternator = Team.PURPLE;

    /////////////////////////////////////////////// GENERAL METHODS //////////////////////////////////////////////

    private Game(int id, Map<WeakEntity<Player>, Team> players) {
        this.id = id;
        this.players = players;

        Factories.tasks().syncRepeat(TridentPlugin.instance(), runnable, 0L, 20L);
    }

    public static Game newGame(int id) {
        return new Game(id, Maps.<WeakEntity<Player>, Team>newHashMap());
    }

    public static Game newGame(int id, Game deserial) {
        return new Game(id, deserial.players());
    }

    public int id() {
        return id;
    }

    public Map<WeakEntity<Player>, Team> players() {
        return players;
    }

    public GameState state() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    /////////////////////////////////////// JOIN/LEAVE /////////////////////////////////////////

    public void sendAll(String message) {
        for (WeakEntity<Player> player : players.keySet()) {
            if (player.isNull())
                continue;
            player.obtain().sendMessage(CommandHandler.PREFIX + message);
        }
    }

    public Team teamOf(Player player) {
        return players.get(WeakEntity.finderOf(player));
    }

    public void join(Player player) {
        this.players.put(WeakEntity.of(player), nextTeam());
    }

    /////////////////////////////////////////// TEAMS //////////////////////////////////////////////

    public void remove(Player player) {
        this.players.remove(WeakEntity.finderOf(player));
    }

    private Team nextTeam() {
        Team team = alternator;
        alternator = team == Team.GREEN ? Team.PURPLE : Team.GREEN;
        return team;
    }

    public void teleport(Player player, Team team) {
        if (team == Game.Team.PURPLE)
            player.teleport(purpleTeam);
        else
            player.teleport(greenTeam);
    }

    public void handleDeath(Player player) {
        Team team = this.teamOf(player);
        int green = 0;
        int purple = 0;

        for (Team teams : players.values()) {
            if (teams == Team.GREEN)
                green++;
            else
                purple++;
        }

        if (team == Team.GREEN)
            green--;
        else
            purple--;

        if (green == 0)
            win(Team.PURPLE);
        else if (purple == 0)
            win(Team.GREEN);
    }

    public void win(Team team) {
        for (WeakEntity<Player> player : players.keySet()) {
            if (player.isNull()) {
                WeakEntity.runMarkSweep();
                continue;
            }
            player.obtain().sendMessage(CommandHandler.PREFIX + "Team " + team.toString() + " has won the game");
        }

        Factories.tasks().syncLater(TridentPlugin.instance(), new TridentRunnable() {
            @Override
            public void run() {
                for (WeakEntity<Player> player : players.keySet()) {
                    GameManager.newHandler().removePlayer(player.entity());
                }
            }
        }, 200L);
    }

    ///////////////////////////////////////// SETUP //////////////////////////////////////////////////

    public void setup(Position spawn, Position purpleTeam, Position greenTeam) {
        this.spawn = spawn;
        this.purpleTeam = purpleTeam;
        this.greenTeam = greenTeam;
    }

    public boolean setPurpleObby(Position position) {
        if (purpleObby != null)
            return false;
        this.purpleObby = position;
        return true;
    }

    public boolean setGreenObby(Position position) {
        if (greenObby != null)
            return false;
        this.greenObby = position;
        return true;
    }

    public Position purpleObby() {
        return this.purpleObby;
    }

    public Position greenObby() {
        return this.greenObby;
    }

    ///////////////////////////////////////// MECHANICS ////////////////////////////////////////////

    public enum GameState {
        LOBBY, STARTING, IN_GAME, FIGHT, DEATH_MATCH, GameState, END
    }

    public enum Team {
        GREEN {
            @Override
            public String toString() {
                return ChatColor.GREEN + "green" + ChatColor.AQUA;
            }
        }, PURPLE {
            @Override
            public String toString() {
                return ChatColor.DARK_PURPLE + "purple" + ChatColor.AQUA;
            }
        }
    }
}
