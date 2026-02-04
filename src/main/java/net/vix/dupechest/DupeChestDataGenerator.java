package net.vix.dupechest;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.vix.dupechest.datagen.ModAdvancementProvider; // Import your new provider

public class DupeChestDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		// Add the Advancement Provider here
		pack.addProvider(ModAdvancementProvider::new);
	}
}