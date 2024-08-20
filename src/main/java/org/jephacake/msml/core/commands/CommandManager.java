package org.jephacake.msml.core.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jephacake.msml.core.loader.Mod;
import org.jephacake.msml.core.loader.ModLoader;

import java.util.HashMap;
import java.util.Map.Entry;

@SuppressWarnings("deprecation")
public class CommandManager implements CommandExecutor {

    HashMap<String, CommandHandler> commands = new HashMap<>();

    interface CommandHandler {
        boolean onCommand(Player player, Command command, String label, String[] args);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for (Entry<String, CommandHandler> e : commands.entrySet()) {
            if (!command.getName().equalsIgnoreCase(e.getKey())) {
                continue;
            }
            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
                break;
            }
            Player player = (Player) sender;
            if(!player.isOp()) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command - you must be OP!!");
                break;
            }
            return e.getValue().onCommand(player, command, label, args);
        }
        return false;
    }

    public void registerCommand(JavaPlugin plugin, String commandName, CommandHandler handler) {
        commands.put(commandName, handler);
        plugin.getCommand(commandName).setExecutor(this);
    }

    public void initCommandRegistry(JavaPlugin plugin) {
        registerCommand(plugin, "listmods", (player, command, label, args) -> {
            player.sendMessage(ChatColor.GOLD + "Loaded Mods:");
            for(Entry<String, Mod> mod : ModLoader.mods.entrySet()) {
                player.sendMessage(ChatColor.GOLD + "----------------");
                player.sendMessage(ChatColor.GREEN + "Name: " + mod.getValue().config.name);
                player.sendMessage(ChatColor.GREEN + "Version: " + mod.getValue().config.version);
                player.sendMessage(ChatColor.GREEN + "Authors: " + mod.getValue().config.authors);
                for(Entry<String, String> entry : mod.getValue().config.additionalData.entrySet()) {
                    player.sendMessage(ChatColor.GREEN + entry.getKey() + ": " + entry.getValue());
                }
                player.sendMessage(ChatColor.GOLD + "----------------");
            }
            return true;
        });
    }

}
