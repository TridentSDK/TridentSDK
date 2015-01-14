package com.gmail.woodyc40.battledome;

import com.google.common.collect.Maps;
import net.tridentsdk.Coordinates;
import net.tridentsdk.concurrent.TridentRunnable;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.factory.Factories;
import net.tridentsdk.meta.ChatColor;
import net.tridentsdk.plugin.TridentPlugin;

import java.util.Map;

/**
 * An arena in BattleDome
 *
 * @author Pierre C
 */
public class Game {
    private final int id;
    private final Map<Player, Team> players;

    private GameState state;

    private Coordinates spawn;
    private Coordinates purpleTeam;
    private Coordinates greenTeam;

    private Coordinates purpleObby;
    private Coordinates greenObby;

    public final BattleTimer runnable = new BattleTimer(this);


    private Game(int id, Map<Player, Team> players) {
        this.id = id;
        this.players = players;

        Factories.tasks().syncRepeat(TridentPlugin.instance(), runnable, 0L, 20L);
    }

    /////////////////////////////////////////////// GENERAL METHODS //////////////////////////////////////////////

    public static Game newGame(int id) {
        return new Game(id, Maps.<Player, Team>newHashMap());
    }

    public static Game newGame(int id, Game deserial) {
        return new Game(id, deserial.players());
    }

    public int id() {
        return id;
    }

    public Map<Player, Team> players() {
        return players;
    }

    public GameState state() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void sendAll(String message) {
        for (Player player : players.keySet()) {
            player.sendMessage(CommandHandler.PLAYER_PREFIX + message);
        }
    }

    /////////////////////////////////////// JOIN/LEAVE /////////////////////////////////////////

    public Team teamOf(Player player) {
        return players.get(player);
    }

    public void join(Player player) {
        this.players.put(player, nextTeam());
    }

    public void remove(Player player) {
        this.players.remove(player);
    }

    /////////////////////////////////////////// TEAMS //////////////////////////////////////////////

    private Team alternator = Team.PURPLE;
    private Team nextTeam() {
        Team team = alternator;
        alternator = team == Team.GREEN ? Team.PURPLE : Team.GREEN;
        return team;
    }

    public void teleport(Player player, Team team) {
        if (team == Game.Team.PURPLE)
            player.teleport(purpleTeam);
        else player.teleport(greenTeam);
    }

    public void handleDeath(Player player) {
        Team team = this.teamOf(player);
        int green = 0;
        int purple = 0;

        for (Team teams : players.values()) {
            if (teams == Team.GREEN) green++;
            else purple++;
        }

        if (team == Team.GREEN) green--;
        else purple--;

        if (green == 0)
            win(Team.PURPLE);
        else if (purple == 0)
            win(Team.GREEN);
    }

    public void win(Team team) {
        for (Player player : players.keySet())
            player.sendMessage(CommandHandler.PLAYER_PREFIX + "Team " + team.toString() + " has won the game");

        Factories.tasks().syncLater(TridentPlugin.instance(), new TridentRunnable() {
            @Override
            public void run() {
                for (Player player : players.keySet()) {
                    GameManager.newHandler().removePlayer(player);
                }
            }
        }, 200L);
    }

    ///////////////////////////////////////// SETUP //////////////////////////////////////////////////

    public void setup(Coordinates spawn, Coordinates purpleTeam, Coordinates greenTeam) {
        this.spawn = spawn;
        this.purpleTeam = purpleTeam;
        this.greenTeam = greenTeam;
    }

    public boolean setPurpleObby(Coordinates coordinates) {
        if (purpleObby != null)
            return false;
        this.purpleObby = coordinates;
        return true;
    }

    public boolean setGreenObby(Coordinates coordinates) {
        if (greenObby != null)
            return false;
        this.greenObby = coordinates;
        return true;
    }

    public Coordinates purpleObby() {
        return this.purpleObby;
    }

    public Coordinates greenObby() {
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
