package org.jephacake.msml.api.registers;

import org.bukkit.NamespacedKey;
import org.jephacake.msml.api.blocks.Block;
import org.jephacake.msml.api.items.Item;
import org.jephacake.msml.core.utils.Logger;
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
        Logger.info("Registered block " + block.id);
    }

    public static void unregisterBlock(@NotNull Block block) {
        BlockRegistry.remove(block.id);
        Logger.info("Unregistered block " + block.id);
    }

    public static Item getItem(NamespacedKey id) {
        return ItemRegistry.get(id);
    }

    public static void registerItem(Item item) {
        ItemRegistry.put(item.id, item);
        Logger.info("Registered item " + item.id);
    }

    public static void unregisterItem(@NotNull Item item) {
        ItemRegistry.remove(item.id);
        Logger.info("Unregistered item " + item.id);
    }
}
