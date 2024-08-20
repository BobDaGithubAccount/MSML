package org.jephacake.msml;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jephacake.msml.core.utils.Logger;
import org.jephacake.msml.core.commands.CommandManager;
import org.jephacake.msml.core.loader.ModLoader;

import java.io.File;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Logger.logger = getLogger();
        Logger.info("MSML has been enabled!");
        CommandManager commandManager = new CommandManager();
        commandManager.initCommandRegistry(this);
        registerEvents();
        initModLoadingLifecycle();
    }
    @Override
    public void onDisable() {
        getLogger().info("MSML has been disabled!");
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    public void initModLoadingLifecycle() {
        File pluginsFolder = getDataFolder().getParentFile();
        Logger.info("Loading mods from " + pluginsFolder.getAbsolutePath());
        ModLoader.locateMods(pluginsFolder);
    }
}