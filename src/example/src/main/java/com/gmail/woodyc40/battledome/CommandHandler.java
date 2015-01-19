package com.gmail.woodyc40.battledome;

import net.tridentsdk.base.Substance;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.meta.ChatColor;
import net.tridentsdk.plugin.TridentPlugin;
import net.tridentsdk.plugin.annotation.CommandDescription;
import net.tridentsdk.plugin.cmd.Command;
import net.tridentsdk.plugin.cmd.ServerConsole;
import net.tridentsdk.window.inventory.Item;

/**
 * Handles commands for BattleDome
 *
 * @author Pierre C
 */
@CommandDescription(name = "bd", permission = "bd.all", aliases = "battledome")
public class CommandHandler extends Command {
    public static final String PREFIX = ChatColor.GREEN + "[Battle" + ChatColor.DARK_PURPLE + "Dome]" + ChatColor.AQUA;
    public static final String ERROR = PREFIX + ChatColor.RED;
    private static final String WARN = PREFIX + ChatColor.GOLD;

    // Initialized after the plugin object is created
    // therefore, this is completely safe
    private final GameManager manager = GameManager.newHandler();

    @Override
    public void handlePlayer(Player player, String arguments, String alias) {
        String[] args = arguments.split(" ");
        if (args.length == 0) {
            player.sendMessage(WARN + "That is not enough arguments");
            this.sendHelp(player);
            return;
        }

        switch (args[0]) {
            case "create":
                this.handleCreate(player);
                break;
            case "join":
                if (args.length < 2) {
                    player.sendMessage(ERROR + "Insufficient arguments");
                    sendHelp(player);
                    return;
                }
                int gameId = this.parseId(player, args[1]);
                if (gameId == -1)
                    return;
                this.handleJoin(player, gameId);
                break;
            case "leave":
                this.handleLeave(player);
                break;
            case "remove":
                if (args.length < 2) {
                    player.sendMessage(ERROR + "Insufficient arguments");
                    sendHelp(player);
                    return;
                }
                int id = this.parseId(player, args[1]);
                if (id == -1)
                    return;
                this.handleRemove(player, id);
                break;
            case "start":
                if (args.length < 2) {
                    player.sendMessage(ERROR + "Insufficient arguments");
                    sendHelp(player);
                    return;
                }
                int id0 = this.parseId(player, args[1]);
                if (id0 == -1)
                    return;
                this.handleStart(player, id0);
                break;
            default:
                player.sendMessage(ERROR + "That command doesn't exist");
                this.sendHelp(player);
        }
    }

    @Override
    public void handleConsole(ServerConsole sender, String arguments, String alias) {
        sender.sendRaw(ERROR + "You cannot execute BattleDome commands via the console");
    }

    private void sendHelp(Player player) {
        player.sendMessage(ChatColor.GREEN + "===== BattleDome " + ChatColor.DARK_PURPLE + "Help =====");
        player.sendMessage(ChatColor.GREEN + "/bd create - " + ChatColor.DARK_PURPLE + "Creates an arena");
        player.sendMessage(ChatColor.GREEN + "/bd remove [id] - " + ChatColor.DARK_PURPLE + "Removes an arena");
        player.sendMessage(ChatColor.GREEN + "/bd join [id] - " + ChatColor.DARK_PURPLE + "Joins a game");
        player.sendMessage(ChatColor.GREEN + "/bd leave - " + ChatColor.DARK_PURPLE + "Leaves the arena");
        player.sendMessage(ChatColor.GREEN + "/bd start [id] - " + ChatColor.DARK_PURPLE + "Start an arena");
        player.sendMessage(ChatColor.GREEN + "===== End BattleDome " + ChatColor.DARK_PURPLE + "Help =====");
    }

    private int parseId(Player player, String string) {
        int gameId;
        try {
            gameId = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            player.sendMessage(ERROR + "That's not a valid game ID");
            return -1;
        }

        // Seriously, don't even waste our time on this
        if (gameId == -1 || gameId < 0) {
            player.sendMessage(ERROR + "That's not a valid game ID");
            return -1;
        }

        if (!this.manager.containsGame(gameId)) {
            player.sendMessage(ERROR + "That game does not exist");
            return -1;
        }

        return gameId;
    }

    private void handleCreate(Player player) {
        BattleListener listener = TridentPlugin.instance().listenerBy(BattleListener.class);
        Game game = this.manager.createGame();

        listener.putSession(player, game);
        player.getInventory().putItem(new Item(Substance.BLAZE_ROD));
        player.sendMessage(PREFIX + "Beginning setup of game " + game.id());
        player.sendMessage(CommandHandler.PREFIX + "Click the lobby spawn with the blaze rod");
    }

    private void handleRemove(Player player, int gameId) {
        this.manager.removeGame(gameId);
        player.sendMessage(PREFIX + "Removed the game successfully");
    }

    private void handleJoin(Player player, int gameId) {
        if (this.manager.isPlaying(player)) {
            player.sendMessage(ERROR + "You cannot join two games");
            return;
        }

        // The ID was checked before getting, so it is guaranteed not to be null
        Game game = this.manager.findGame(gameId);
        this.manager.sendPlayer(player, game);
    }

    private void handleLeave(Player player) {
        Game removedFrom = this.manager.removePlayer(player);
        if (removedFrom == null) {
            player.sendMessage(ERROR + "You can't be leave if you aren't playing");
        } else
            player.sendMessage(PREFIX + "Left game " + removedFrom.id());
    }

    private void handleStart(Player player, int gameId) {
        Game game = this.manager.findGame(gameId);
        BattleTimer r = game.runnable;

        game.setState(Game.GameState.STARTING);
        r.period = 0;
        r.limit = 0;
        r.warned = false;
    }
}
