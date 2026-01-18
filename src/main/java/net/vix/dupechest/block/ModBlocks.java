package net.vix.dupechest.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.vix.dupechest.DupeChest; // Uses your Main Class

public class ModBlocks {
    // 1. Create the Block (Strength 2.0 is like wood)
    public static final Block DUPE_CHEST_BLOCK = registerBlock("dupe_chest",
            new DupeChestBlock(AbstractBlock.Settings.create().strength(2.0f))
    );

    // Helper method to register Block + Item at the same time
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(DupeChest.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(DupeChest.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        DupeChest.LOGGER.info("Registering Blocks for " + DupeChest.MOD_ID);
    }
}