package org.jephacake.msml.api.utils.registers;

import org.bukkit.NamespacedKey;
import org.jephacake.msml.api.blocks.Block;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class GlobalRegistry {

    protected static final HashMap<NamespacedKey, Block> BlockRegistry = new HashMap<>();

    public static Block getBlock(NamespacedKey id) {
        return BlockRegistry.get(id);
    }

    public static void registerBlock(Block block) {
        BlockRegistry.put(block.id, block);
    }

    public static void unregisterBlock(@NotNull Block block) {
        BlockRegistry.remove(block.id);
    }
}
