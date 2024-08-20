package org.jephacake.exampleMod;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.jephacake.exampleMod.Blocks.TitaniumOreBlock;
import org.jephacake.msml.api.Mod;

public class Main extends Mod {

    public static final String nameSpace = "exampleMod";

    public Main(JavaPlugin plugin) {
        super(plugin, nameSpace);
    }

    @Override
    public void onEnable() {
        this.plugin.getLogger().info("ExampleMod has been enabled!");
        registerBlocks();
    }

    @Override
    public void onDisable() {
        this.plugin.getLogger().info("ExampleMod has been disabled!");
    }

    public void registerBlocks() {
        new TitaniumOreBlock(new NamespacedKey(nameSpace, "titanium_ore"));
    }
}