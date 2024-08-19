package org.jephacake.msml.api.items;

import org.bukkit.NamespacedKey;
import org.jephacake.msml.api.utils.Logger;
import org.jephacake.msml.api.utils.registers.GlobalRegistry;

public class Item {

    public NamespacedKey id;
    public String name;
    public String pluginName;

    public Item(NamespacedKey id) {
        this.id = id;
        this.name = id.getKey();
        this.pluginName = id.getNamespace();
        if ("minecraft".equals(this.pluginName)) {
            Logger.severe("pluginName cannot be 'minecraft' since it is a reserved namespace");
            throw new IllegalArgumentException("pluginName cannot be 'minecraft' since it is a reserved namespace");
        }
        GlobalRegistry.registerItem(this);
    }

}
