package org.jephacake.msml.api;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class Mod {

    public JavaPlugin plugin;
    public String nameSpace;

    public Mod(JavaPlugin plugin, String nameSpace) {
        this.plugin = plugin;
        this.nameSpace = nameSpace;
    }

    public abstract void onEnable();
    public abstract void onDisable();

}
