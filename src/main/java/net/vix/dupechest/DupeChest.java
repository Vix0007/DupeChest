package net.vix.dupechest;

import net.fabricmc.api.ModInitializer;
import net.vix.dupechest.block.ModBlocks;
import net.vix.dupechest.block.entity.ModBlockEntities; // <--- CHECK THIS IMPORT
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DupeChest implements ModInitializer {
	public static final String MOD_ID = "dupechest";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// 1. Register Blocks
		ModBlocks.registerModBlocks();

		// 2. Register Block Entities (THIS IS THE FIX)
		// If this line is missing or commented out, the game crashes on placement!
		ModBlockEntities.registerBlockEntities();
	}
}