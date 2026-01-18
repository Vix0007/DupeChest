package net.vix.dupechest.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.vix.dupechest.DupeChest;
import net.vix.dupechest.block.ModBlocks;

public class ModBlockEntities {
    // Register the "Brain" and say which block it belongs to
    public static final BlockEntityType<DupeChestBlockEntity> DUPE_CHEST_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(DupeChest.MOD_ID, "dupe_chest_be"),
                    FabricBlockEntityTypeBuilder.create(DupeChestBlockEntity::new, ModBlocks.DUPE_CHEST_BLOCK).build());

    public static void registerBlockEntities() {
        DupeChest.LOGGER.info("Registering Block Entities for " + DupeChest.MOD_ID);
    }
}