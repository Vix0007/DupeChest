package net.vix.dupechest;

import net.fabricmc.api.ModInitializer;

import net.vix.dupechest.block.ModBlocks;
import net.vix.dupechest.block.entity.ModBlockEntities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DupeChest implements ModInitializer {
	public static final String MOD_ID = "dupechest";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Initialize the Mod!
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
	}
}