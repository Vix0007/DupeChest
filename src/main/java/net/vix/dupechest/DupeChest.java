package net.vix.dupechest;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents; // Import this
import net.minecraft.item.ItemGroups; // Import this
import net.vix.dupechest.block.ModBlocks;
import net.vix.dupechest.block.entity.ModBlockEntities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DupeChest implements ModInitializer {
	public static final String MOD_ID = "dupechest";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// 1. Register Blocks
		ModBlocks.registerModBlocks();

		// 2. Register Block Entities
		ModBlockEntities.registerBlockEntities();

		// 3. Add to Creative Tab (Functional Blocks)
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> {
			content.add(ModBlocks.DUPE_CHEST_BLOCK);
		});
	}
}