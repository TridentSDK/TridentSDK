package com.gmail.woodyc40.battledome;

import net.tridentsdk.concurrent.ScheduledRunnable;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.plugin.Plugin;
import net.tridentsdk.registry.Registered;
import net.tridentsdk.util.WeakEntity;

import java.util.Map;

/**
 * Scheduled task and time tracking for Battle Dome
 *
 * @author Pierre C
 */
public class BattleTimer extends ScheduledRunnable {
    private final Game game;

    public int limit = 5 * 60;
    public int period = 0;
    public int countdown = 10;
    public boolean warned = false;

    public BattleTimer(Game game) {
        this.game = game;
    }

    public void run() {
        switch (game.state()) {
            case LOBBY:
                period++;
                if (game.players().size() > 5) {
                    if (!warned) {
                        warned = true;
                        game.sendAll("5 players have joined, timer reduced to 1 minute.");
                        limit = 60;
                    }
                }

                if (period % 30 == 0) {
                    double minutes = (limit - period) / 60;
                    int floor = (int) Math.floor(minutes);
                    if (minutes - floor != 0)
                        minutes = floor + 0.5;
                    game.sendAll(minutes + " minutes left in lobby");
                }

                // Looks like we've ended the lobby
                if (period >= limit) {
                    if (game.players().size() < 2) {
                        limit = 5 * 60;
                        return;
                    }

                    // There are players, reset the period and move on
                    game.setState(Game.GameState.STARTING);
                    period = 0;
                    limit = 0;
                    warned = false;

                    for (Map.Entry<WeakEntity<Player>, Game.Team> entry : game.players().entrySet()) {
                        game.teleport(entry.getKey().obtain(), entry.getValue());
                    }
                }
                break;
            case STARTING:
                if (!warned) {
                    warned = true;
                    game.sendAll("Game starting in 10 seconds");
                }

                game.sendAll("Game starts in " + countdown + " seconds");
                countdown--;
                if (countdown < 0) {
                    game.setState(Game.GameState.IN_GAME);
                    game.sendAll("Game has started");
                    countdown = 60 * 60;
                }

                break;
            case IN_GAME:
                period++;
                if (period % (5 * 60) == 0) {
                    game.sendAll(15 - (period / 60) + " more minutes left to build");
                }

                if (period > 15 * 60) {
                    game.setState(Game.GameState.FIGHT);
                    period = 0;
                    game.sendAll("Build phase has ended. Fight!");
                }
                break;
            case FIGHT:
                period++;
                if (period % (5 * 60) == 0) {
                    game.sendAll(45 - (period / 60) + " more minutes left to fight");
                }

                if (period > 45 * 60) {
                    game.setState(Game.GameState.DEATH_MATCH);
                    period = 0;
                    game.sendAll("Fight phase has ended. Entering DM.");
                }
                break;
            case DEATH_MATCH:
                period++;
                if (period % 60 == 0) {
                    game.sendAll(5 - (period / 60) + " more minutes left to fight");
                }

                if (period > 5 * 60) {
                    game.setState(Game.GameState.END);
                    period = 0;
                    game.sendAll("DM has ended");

                    int green = 0;
                    int purple = 0;

                    for (Game.Team teams : game.players().values()) {
                        if (teams == Game.Team.GREEN)
                            green++;
                        else
                            purple++;
                    }

                    if (green < purple)
                        game.win(Game.Team.PURPLE);
                    else if (purple < green)
                        game.win(Game.Team.GREEN);
                    else
                        game.sendAll("Match tied");
                }
                break;
            case END:
                Registered.tasks().syncLater(Plugin.instance(), new ScheduledRunnable() {
                    public void run() {
                        for (WeakEntity<Player> player : WeakEntity.iterate(game.players().keySet())) {
                            GameManager.instance().removePlayer(player.entity());
                        }
                    }
                }, 200L);
                this.cancel();
        }
    }
}