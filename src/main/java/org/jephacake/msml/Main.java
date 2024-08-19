package org.jephacake.msml;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jephacake.msml.api.utils.Logger;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Logger.logger = getLogger();
        Logger.info("MSML has been enabled!");
        registerEvents();
    }
    @Override
    public void onDisable() {
        getLogger().info("MSML has been disabled!");
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(this, this);
    }
}