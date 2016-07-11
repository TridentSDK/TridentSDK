package com.gmail.woodyc40.battledome;

import com.google.common.collect.Maps;
import net.tridentsdk.base.Position;
import net.tridentsdk.base.Substance;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.event.Listener;
import net.tridentsdk.event.block.BlockBreakEvent;
import net.tridentsdk.event.block.BlockPlaceEvent;
import net.tridentsdk.event.player.*;

import java.util.Map;

/**
 * Listens for events for BattleDome
 *
 * @author Pierre C
 */
public class BattleListener implements Listener {
    // TODO damagee for EntityDamageEvent

    private final Map<Player, SetupSession> sessions = Maps.newHashMap();

    // Listeners loaded after the plugin has
    // therefore, this is safe no matter what
    private final GameManager manager = GameManager.instance();

    public void putSession(Player player, Game game) {
        sessions.put(player, new SetupSession(player, game));
    }

    public void join(PlayerJoinEvent event) {
        event.player().grantPermission("bd.all");
    }

    public void breakBlock(BlockBreakEvent event) {
        Substance substance = event.block().substance();
        Player player = event.player();

        if (substance == Substance.GLASS) {
            player.sendMessage(CommandHandler.ERROR + "You cannot break glass");
            event.cancel(true);
        } else if (substance == Substance.OBSIDIAN) {
            Game game = this.manager.findGame(player);

            // The player is not playing BattleDome
            if (game == null)
                return;

            if (game.state() == Game.GameState.FIGHT) {
                Game.Team team = game.teamOf(player);
                Position position = event.block().position();

                if (team == Game.Team.PURPLE) {
                    if (position.equals(game.purpleObby())) {
                        player.sendMessage(CommandHandler.ERROR + "You cannot break your own obsidian");
                        event.cancel(true);
                    } else {
                        game.win(Game.Team.PURPLE);
                    }
                } else {
                    if (position.equals(game.greenObby())) {
                        player.sendMessage(CommandHandler.ERROR + "You cannot break your own obsidian");
                        event.cancel(true);
                    } else {
                        game.win(Game.Team.GREEN);
                    }
                }
            }
        }
    }

    public void putBlock(BlockPlaceEvent event) {
        Player player = event.player();
        Substance sub = event.block().substance();

        if (sub == Substance.GLASS) {
            player.sendMessage(CommandHandler.ERROR + "You cannot place glass");
            event.cancel(true);
        } else if (sub == Substance.OBSIDIAN) {
            Game game = this.manager.findGame(player);

            // The player is not playing BattleDome
            if (game == null)
                return;

            if (game.state() == Game.GameState.IN_GAME) {
                Game.Team team = game.teamOf(player);
                Position position = event.block().position();

                boolean done;
                if (team == Game.Team.PURPLE)
                    done = game.setGreenObby(position);
                else
                    done = game.setPurpleObby(position);

                if (done)
                    player.sendMessage(CommandHandler.PREFIX + "Set the obsidian at the placed position.");
                else {
                    player.sendMessage(
                            CommandHandler.ERROR + "Obsidian already set, removing the one you placed.");
                    event.cancel(true);
                }
            }
        }
    }

    public void interactBlock(PlayerInteractEvent event) {
        Player player = event.player();
        if (player.heldItem().type() != Substance.BLAZE_ROD)
            return;

        SetupSession session = sessions.get(player);
        if (session != null) {
            Position position = event.block().position();
            Game game = session.game();
            switch (session.stage()) {
                case SPAWN:
                    session.setSpawn(position);
                    session.setStage(SetupStage.PURPLE_SPAWN);
                    player.sendMessage(CommandHandler.PREFIX + "Click the purple spawn now");
                    break;
                case PURPLE_SPAWN:
                    session.setPurpleTeam(position);
                    session.setStage(SetupStage.GREEN_SPAWN);
                    player.sendMessage(CommandHandler.PREFIX + "Click the green spawn now");
                    break;
                case GREEN_SPAWN:
                    session.setGreenTeam(position);
                    game.setup(session.spawn(), session.purpleTeam(), session.greenTeam());
                    sessions.remove(player);
                    player.sendMessage(CommandHandler.PREFIX + "Completed setup for " + game.id());
            }
        }
    }

    public void die(PlayerDeathEvent event) {
        Player player = event.player();
        Game game = manager.findGame(player);
        if (game != null) {
            game.handleDeath(player);
            manager.removePlayer(player);
        }
    }

    public void damage(PlayerDamageEvent event) {
        Game game = manager.findGame(event.player());
        if (game != null) {
            if (game.state() == Game.GameState.STARTING)
                event.cancel(true);
            if (game.state() == Game.GameState.LOBBY)
                event.cancel(true);
            if (game.state() == Game.GameState.IN_GAME)
                event.cancel(true);
        }
    }

    public void move(PlayerMoveEvent event) {
        Game game = manager.findGame(event.player());
        if (game != null)
            if (game.state() == Game.GameState.STARTING)
                event.cancel(true);
    }

    private enum SetupStage {
        SPAWN, PURPLE_SPAWN, GREEN_SPAWN
    }

    private class SetupSession {
        private final Player player;
        private final Game game;
        private SetupStage stage;

        private Position spawn;
        private Position purpleTeam;
        private Position greenTeam;

        public SetupSession(Player player, Game game) {
            this.player = player;
            this.game = game;
            this.stage = SetupStage.SPAWN;
        }

        public Player player() {
            return this.player;
        }

        public Game game() {
            return this.game;
        }

        public SetupStage stage() {
            return stage;
        }

        public Position spawn() {
            return spawn;
        }

        public Position purpleTeam() {
            return purpleTeam;
        }

        public Position greenTeam() {
            return greenTeam;
        }

        public void setSpawn(Position spawn) {
            this.spawn = spawn;
        }

        public void setPurpleTeam(Position purpleTeam) {
            this.purpleTeam = purpleTeam;
        }

        public void setGreenTeam(Position greenTeam) {
            this.greenTeam = greenTeam;
        }

        public void setStage(SetupStage stage) {
            this.stage = stage;
        }
    }
}
