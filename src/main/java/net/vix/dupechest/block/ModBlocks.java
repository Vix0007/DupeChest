package net.vix.dupechest.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.vix.dupechest.DupeChest;

import java.util.function.Function;

public class ModBlocks {

    public static final Block DUPE_CHEST_BLOCK = registerBlock("dupe_chest", DupeChestBlock::new);

    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> factory) {
        Identifier id = Identifier.of(DupeChest.MOD_ID, name);
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, id);

        AbstractBlock.Settings settings = AbstractBlock.Settings.create()
                .registryKey(key)
                .strength(2.0f);

        // 1. Create the Block
        Block block = factory.apply(settings);

        // 2. THE FIX: Register the BLOCK FIRST!
        // The game needs the block to exist in the registry before we make an Item for it.
        Block registeredBlock = Registry.register(Registries.BLOCK, id, block);

        // 3. NOW Register the Item
        registerBlockItem(name, registeredBlock);

        return registeredBlock;
    }

    private static Item registerBlockItem(String name, Block block) {
        Identifier id = Identifier.of(DupeChest.MOD_ID, name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);

        Item.Settings itemSettings = new Item.Settings()
                .registryKey(key);

        return Registry.register(Registries.ITEM, id,
                new BlockItem(block, itemSettings));
    }

    public static void registerModBlocks() {
        DupeChest.LOGGER.info("Registering Mod Blocks for " + DupeChest.MOD_ID);
    }
}