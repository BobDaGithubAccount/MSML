package org.jephacake.msml.api.utils.registers;

import org.bukkit.NamespacedKey;
import org.jephacake.msml.api.blocks.Block;
import org.jephacake.msml.api.items.Item;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class GlobalRegistry {

    protected static final HashMap<NamespacedKey, Block> BlockRegistry = new HashMap<>();
    protected static final HashMap<NamespacedKey, Item> ItemRegistry = new HashMap<>();

    public static Block getBlock(NamespacedKey id) {
        return BlockRegistry.get(id);
    }

    public static void registerBlock(Block block) {
        BlockRegistry.put(block.id, block);
    }

    public static void unregisterBlock(@NotNull Block block) {
        BlockRegistry.remove(block.id);
    }

    public static Item getItem(NamespacedKey id) {
        return ItemRegistry.get(id);
    }

    public static void registerItem(Item item) {
        ItemRegistry.put(item.id, item);
    }

    public static void unregisterItem(@NotNull Item item) {
        ItemRegistry.remove(item.id);
    }
}
