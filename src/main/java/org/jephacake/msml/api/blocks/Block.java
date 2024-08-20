package org.jephacake.msml.api.blocks;

import org.bukkit.NamespacedKey;
import org.jephacake.msml.api.registers.GlobalRegistry;

public abstract class Block {

    public NamespacedKey id;

    public Block(NamespacedKey id) {
        this.id = id;
        GlobalRegistry.registerBlock(this);
    }

}
